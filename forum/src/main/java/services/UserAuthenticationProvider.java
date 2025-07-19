/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
//import entities.Auth;
import entities.User;
import enums.RoleEnum;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
//import repositories.AuthRepository;
import repositories.UserRepository;

/**
 *
 * @author Arek
 */
@Component
@RequiredArgsConstructor
public class UserAuthenticationProvider implements AuthenticationProvider {

    final UserRepository userRepository;
    final JwtService jwt;
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    @Value("${google.api}")
    String CLIENT_ID;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        System.out.println(authentication.getName());
        System.out.println(authentication.getCredentials());

        String token = authentication.getCredentials().toString();
        if(!name.equals(jwt.getName(token))){
            throw new BadCredentialsException("names don't match");
        }
        User user = userRepository.findFirstByNameEquals(name);
        if (user == null) {
            throw new BadCredentialsException("name not found");
        }

        if (jwt.isValid(token, name)) {
            return new UsernamePasswordAuthenticationToken(name,token, getUserRoles(user));
        } else {
            throw new BadCredentialsException("token invalid");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private List<GrantedAuthority> getUserRoles(User user) {
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (user.getRole().equals(RoleEnum.user)) {
            authorityList.add(new SimpleGrantedAuthority("user"));
        }
        if (user.getRole().equals(RoleEnum.moderator)) {
            authorityList.add(new SimpleGrantedAuthority("user"));
            authorityList.add(new SimpleGrantedAuthority("moderator"));
        }
        if (user.getRole().equals(RoleEnum.administrator)) {
            authorityList.add(new SimpleGrantedAuthority("user"));
            authorityList.add(new SimpleGrantedAuthority("moderator"));
            authorityList.add(new SimpleGrantedAuthority("administrator"));
        }
        if (user.getIsBanned()) {
            authorityList.clear();
            authorityList.add(new SimpleGrantedAuthority("banned"));
        }

        return authorityList;
    }  

}
