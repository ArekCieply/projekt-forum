/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package controllers;

import dto.PrivateMessageDto;
import entities.PrivateMessage;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import mappers.PrivateMessageMapper;
/**
 *
 * @author Arek
 */
@RestController
@RequestMapping("/private_message")
@RequiredArgsConstructor
public class PrivateMessageController {
    
    final PrivateMessageService privateMessageService;
    final PrivateMessageMapper privateMessageMapper;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewMessage(@RequestBody PrivateMessageDto privateMessageDto, @RequestHeader("Authorization") String auth) {
        privateMessageService.addMessage(privateMessageDto, auth);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<PrivateMessageDto>> getMessages(@RequestParam Integer conversationId) {
        return ResponseEntity.ok().body(privateMessageMapper.entityToDTO(privateMessageService.getMessages(conversationId))); 
    }

}
