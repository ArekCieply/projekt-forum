/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package controllers;

import dto.PrivateConversationDto;
import entities.PrivateConversation;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
import services.PrivateConversationService;
import services.PrivateMessageService;

/**
 *
 * @author Arek
 */
@RestController
@RequestMapping("/private_conversation")
@RequiredArgsConstructor
@Controller
public class PrivateConversationController {

    //@Autowired
    final PrivateConversationService privateConversationService;
    // @Autowired
    final PrivateMessageService privateMessageService;

    @PostMapping
    public @ResponseBody
    PrivateConversation addNewConversation(@RequestBody PrivateConversationDto privateConversationDto, @RequestHeader("Authorization") String auth) {
        return privateConversationService.addConversation(privateConversationDto, auth);
    }

    @GetMapping
    public @ResponseBody
    Iterable<PrivateConversation> getConversations(@RequestParam Integer userId, @RequestHeader("Authorization") String auth) {
        return privateConversationService.getConversations(userId, auth);
    }

}
