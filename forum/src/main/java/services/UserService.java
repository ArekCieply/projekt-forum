/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package services;

import Exception.NameTakenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import dto.UserDto;
import mappers.UserMapper;
import entities.User;
import enums.RoleEnum;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.stream.IntStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import repositories.UserRepository;
import services.FbResponse;
import java.util.Date;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.Jwt.Builder;
import java.time.Duration;

/**
 *
 * @author Arek
 */
@Service
@RequiredArgsConstructor
public class UserService {

    final private UserRepository userRepository;
    final UserMapper userMapper;
    final CheckUserId check;
    final private JwtService jwt;
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    private final RedisTemplate<String, String> redisTemplate;

    public void addUser(User user) {
        userRepository.save(user);
    }

    public User getUser(Integer id) {
        return userRepository.findFirstByIdEquals(id);
    }

    public Iterable<User> findUsers(String name) {
        return userRepository.findByNameContainingIgnoreCase(name);
    }

    public UserDto registerUser(String name, String pass) {
        if (userRepository.findByNameEquals(name).isEmpty()) {
            User user = new User(name);
            user.setPass(passwordEncoder.encode(pass));
            user.setRole(RoleEnum.user);
            user.setProvider("db");
            user.setIsBanned(false);
            user.setScore(0);
            userRepository.save(user);
            user.setToken(jwt.generateToken(name));
            return userMapper.entityToDTO(user);
        } else {
            throw new NameTakenException();
        }

    }

    public User loginUser(String name, String inputPass) {
        User user = userRepository.findFirstByNameEquals(name);
        System.out.println(name);
        if (passwordEncoder.matches(inputPass, user.getPass())) {
            user.setToken(jwt.generateToken(name));
            return user;
        }
        throw new BadCredentialsException("Wrong login data");

    }

    public String randomPass(int length) {
        SecureRandom rand = new SecureRandom();
        String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder pass = new StringBuilder();
        IntStream stream = rand.ints(length, 0, chars.length() + 1);
        stream.forEach(i -> {
            pass.append(chars.charAt(i));
        });
        System.out.println(pass.length());
        System.out.println(pass);

        return pass.toString();
    }

    public User facebookLogin(String token) {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FbResponse> response = restTemplate
                .getForEntity("https://graph.facebook.com/me?access_token=" + token, FbResponse.class);
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        FbResponse fb = response.getBody();

        // if (response.getStatusCode().equals(HttpStatus.OK)) {
        if (userRepository.findFirstByProviderId(fb.id) == null) {// create new acc
            if (!userRepository.findByNameEquals(fb.name).isEmpty()) {
                throw new NameTakenException();
            }
            System.out.println(fb.name);
            System.out.println(fb.id);

            User user = new User(fb.name);
            user.setProviderId(fb.id);
            user.setProvider("facebook");
            user.setRole(RoleEnum.user);
            user.setPass(passwordEncoder.encode(randomPass(12)));
            user.setIsBanned(false);
            user.setScore(0);
            user.setToken(jwt.generateToken(fb.name));
            userRepository.save(user);

            return user;

        } else if (userRepository.findFirstByProviderId(fb.id) != null) {// acc exist
            User user = userRepository.findFirstByProviderId(fb.id);
            user.setToken(jwt.generateToken(user.getName()));
            return user;
        }
        // }
        return null;
    }

    public User googleLogin(String token) throws GeneralSecurityException, IOException {
        String CLIENT_ID = "1075732285027-flj3tbhgdtsfp5ujcg3dumq0p94rj89k.apps.googleusercontent.com";
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();

        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null) {
            Payload payload = idToken.getPayload();
            String userId = payload.getSubject();
            String name = (String) payload.get("name");

            if (userRepository.findFirstByProviderId(userId) == null) {
                if (!userRepository.findByNameEquals(name).isEmpty()) {
                    throw new NameTakenException();
                }
                System.out.println(name);
                System.out.println(userId);

                User user = new User(name);
                user.setProviderId(userId);
                user.setProvider("google");
                user.setRole(RoleEnum.user);
                user.setPass(passwordEncoder.encode(randomPass(12)));
                user.setIsBanned(false);
                user.setScore(0);
                user.setToken(jwt.generateToken(name));
                userRepository.save(user);

                return user;
            } else if (userRepository.findFirstByProviderId(userId) != null) {// acc exist
                User user = userRepository.findFirstByProviderId(userId);
                user.setToken(jwt.generateToken(name));
                return user;
            }

        }
        return null;
    }

    public void promoteMod(int userId, int targetId, String auth) {
        if (check.checkId(auth, userId)) {
            userRepository.promoteToModById(targetId);
        }
    }

    public void demoteMod(int userId, int targetId, String auth) {
        if (check.checkId(auth, userId)) {
            userRepository.demoteToUserById(targetId);
        }
    }

    public void changePass(Integer userId, String newPass, String oldPass, String auth) {
        if (check.checkId(auth, userId)) {
            User user = userRepository.findFirstByIdEquals(userId);
            if (passwordEncoder.matches(oldPass, user.getPass())) {
                newPass = passwordEncoder.encode(newPass);
                userRepository.changePassById(newPass, userId);
            }
        }
    }

    public void banUser(int userId, int targetId, String auth) {
        if (check.checkId(auth, userId)) {
            userRepository.banById(targetId);
        }
    }

    public void unbanUser(int userId, int targetId, String auth) {
        if (check.checkId(auth, userId)) {
            userRepository.unbanById(targetId);
        }
    }

    public void qrStart(String token) {
        redisTemplate.opsForValue().set(token, " ", Duration.ofSeconds(100));
        System.out.println("start " + token);
    }

    public void qrTie(String token, int id, String auth) {
        if (check.checkId(auth, id)) {
            System.out.println("tie attempt " + token + " " + id);
            if (redisTemplate.hasKey(token)) {
                System.out.println("tied " + token + " id " + id);
                redisTemplate.opsForValue().set(token, Integer.toString(id), Duration.ofSeconds(100));
            }
        }
    }

    public User qrCheck(String token) {
        String id = redisTemplate.opsForValue().get(token);
        System.out.println("check for " + token);
        if (id == null) {
            return null;
        }
        if (!id.equals(" ")) {
            System.out.println("chk ok id " + id + "!" + Integer.parseInt(id.trim()) + "!");
            int userId = Integer.parseInt(id.trim());
            User user = userRepository.findFirstByIdEquals(userId);
            user.setToken(jwt.generateToken(user.getName()));
            redisTemplate.delete(token);
            return user;
        } else {
            return null;
        }

    }
}
