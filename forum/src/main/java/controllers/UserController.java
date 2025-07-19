/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package controllers;

import Exception.NameTakenException;
import dto.ChangePassDto;
import dto.PromoteModDto;
import dto.QRDto;
import dto.UserDto;
import mappers.UserMapper;
import entities.User;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import services.UserService;

/**
 *
 * @author Arek
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    final UserService userService;
    final UserMapper userMapper;

    @GetMapping
    public @ResponseBody
    ResponseEntity<UserDto> getOneUser(@RequestParam Integer id) {
        return ResponseEntity.ok().body(userMapper.entityToDTO(userService.getUser(id)));
    }

    @GetMapping(path = "/find")
    public @ResponseBody
    ResponseEntity<Iterable<UserDto>> findTopics(@RequestParam String name) {
        return ResponseEntity.ok().body(userMapper.entityToDTO(userService.findUsers(name)));//todo like
    }

    @PostMapping(path = "/register")
    public @ResponseBody
    ResponseEntity registerUser(@RequestBody Map<String, String> json) {
        try {
            return ResponseEntity.ok().body(userService.registerUser(json.get("name"), json.get("pass")));
        } catch (NameTakenException ex) {
            return ResponseEntity.status(409).body("Name taken");
        }
    }

    @PostMapping(path = "/login")
    public @ResponseBody
    ResponseEntity loginUser(@RequestBody Map<String, String> json) {
        try{
        User user = userService.loginUser(json.get("name"), json.get("pass"));
        return ResponseEntity.ok().body(userMapper.entityToDTO(user));
        } catch (BadCredentialsException ex){
            return ResponseEntity.status(401).body("Wrong login data");
        }
    }

    @PostMapping(path = "/login/facebook")
    public @ResponseBody
    ResponseEntity<UserDto> loginUserFacebook(@RequestBody Map<String, String> json) {
        User user = userService.facebookLogin(json.get("token"));
        return ResponseEntity.ok().body(userMapper.entityToDTO(user));
    }

    @PostMapping(path = "/login/google")
    public @ResponseBody
    ResponseEntity<UserDto> loginUserGoogle(@RequestBody Map<String, String> json) throws GeneralSecurityException, IOException {
        User user = userService.googleLogin(json.get("token"));
        return ResponseEntity.ok().body(userMapper.entityToDTO(user));
    }

    @PostMapping(path = "/promote")
    public @ResponseBody
    ResponseEntity promoteMod(@RequestBody PromoteModDto dto, @RequestHeader("Authorization") String auth) {
        userService.promoteMod(dto.userId(), dto.targetId(), auth);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/demote")
    public @ResponseBody
    ResponseEntity demoteMod(@RequestBody PromoteModDto dto, @RequestHeader("Authorization") String auth) {
        userService.demoteMod(dto.userId(), dto.targetId(), auth);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/change_pass")
    public @ResponseBody
    ResponseEntity changePass(@RequestBody ChangePassDto dto, @RequestHeader("Authorization") String auth) {
        userService.changePass(dto.userId(), dto.newPass(), dto.oldPass(), auth);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/ban")
    public @ResponseBody
    ResponseEntity banUser(@RequestBody PromoteModDto dto, @RequestHeader("Authorization") String auth) {
        userService.banUser(dto.userId(), dto.targetId(), auth);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/unban")
    public @ResponseBody
    ResponseEntity unBanUser(@RequestBody PromoteModDto dto, @RequestHeader("Authorization") String auth) {
        userService.unbanUser(dto.userId(), dto.targetId(), auth);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/qr/start")
    public @ResponseBody
    ResponseEntity QRStart(@RequestBody Map<String, String> json ) {
        userService.qrStart(json.get("token"));
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/qr/tie")
    public @ResponseBody
    ResponseEntity QRTie(@RequestBody QRDto dto, @RequestHeader("Authorization") String auth ) {
        userService.qrTie(dto.token(), dto.id(), auth);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/qr/check")
    public @ResponseBody
    ResponseEntity QRCheck(@RequestBody Map<String, String> json ) {
        User user = userService.qrCheck(json.get("token"));
        if(user!=null){
        return ResponseEntity.ok().body(userMapper.entityToDTO(user));
        }else{
            return ResponseEntity.status(202).body("pending");
        }
    }
}
