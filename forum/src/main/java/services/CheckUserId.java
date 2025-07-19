/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repositories.UserRepository;

/**
 *
 * @author Arek
 */
@Component
@RequiredArgsConstructor
public class CheckUserId {

    //@Autowired
    final private UserRepository userRepository;
    static final int Basic = 6;

    public boolean checkId(String auth, int id) {//sprawdzenie czy użytkownik z nagłówka zgadza się z id z ciała żądania  
        String decodedHeader = new String(Base64.decodeBase64(auth.substring(Basic)));
        String name = decodedHeader.split(":")[0];
        System.out.println(name);
        return id == userRepository.findFirstByNameEquals(name).getId();

    }
}
