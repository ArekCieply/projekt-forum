/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package controllers;

import dto.TopicDto;
import dto.VoteDto;
import entities.Topic;
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
import services.TopicService;
import mappers.TopicMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
/**
 *
 * @author Arek
 */
@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController {

    final TopicService topicService;
    final TopicMapper TopicMapper;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewTopic(@RequestBody TopicDto topicDto, @RequestHeader("Authorization") String auth) {
        topicService.addTopic(topicDto, auth);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<TopicDto>> getTopics(@RequestParam Integer sub_section_id) {
        return ResponseEntity.ok().body(TopicMapper.entityToDTO(topicService.getTopics(sub_section_id)));
    }
    @GetMapping(path = "/one")
    public @ResponseBody
    ResponseEntity<TopicDto> getTopic(@RequestParam Integer topicId) {
        return ResponseEntity.ok().body(TopicMapper.entityToDTO(topicService.getTopic(topicId)));
    }
    
    @GetMapping(path = "/user_activity")
    public @ResponseBody
    ResponseEntity<List<TopicDto>> getTopicsCreatedByUser(@RequestParam Integer userId) {
        return ResponseEntity.ok().body(TopicMapper.entityToDTO(topicService.getTopicsByUser(userId)));
    }
    
    @GetMapping(path = "/find")
    public @ResponseBody
    ResponseEntity<List<TopicDto>> findTopics(@RequestParam String name) {
        return ResponseEntity.ok().body(TopicMapper.entityToDTO(topicService.findTopics(name)));
    }

    @PostMapping(path = "/vote/plus")
    public @ResponseBody
    ResponseEntity votePlus(@RequestBody VoteDto dto, @RequestHeader("Authorization") String auth) {
        topicService.vote(dto, 1, auth);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/vote/minus")
    public @ResponseBody
    ResponseEntity<Object> voteMinus(@RequestBody VoteDto dto, @RequestHeader("Authorization") String auth) {
        topicService.vote(dto, -1, auth);
        return ResponseEntity.ok().build();

    }
    
    @PatchMapping
    public @ResponseBody
    ResponseEntity editTopic(@RequestParam Integer topicId, @RequestBody TopicDto topicDto, @RequestHeader("Authorization") String auth) {
        topicService.editTopic(topicId, topicDto, auth);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public @ResponseBody
    ResponseEntity deleteTopic(@RequestParam Integer topicId, @RequestHeader("Authorization") String auth) {
        topicService.deleteTopic(topicId, auth);
        return ResponseEntity.ok().build();
    }

}
