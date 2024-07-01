/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package controllers;

import dto.PostDto;
import dto.VoteDto;
import entities.Post;
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
import services.PostService;
import mappers.PostMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author Arek
 */
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    //@Autowired
    final PostService postService;
    // @Autowired
    final PostMapper postMapper;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewPost(@RequestBody PostDto postDto, @RequestHeader("Authorization") String auth) {
        postService.addPost(postDto, auth);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<PostDto>> getPosts(@RequestParam Integer topic_id) {
        return ResponseEntity.ok().body(postMapper.entityToDTO(postService.getPosts(topic_id)));
    }

    @PostMapping(path = "/vote/plus")
    public @ResponseBody
    ResponseEntity votePlus(@RequestBody VoteDto dto, @RequestHeader("Authorization") String auth) {
        postService.vote(dto, 1, auth);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/vote/minus")
    public @ResponseBody
    ResponseEntity voteMinus(@RequestBody VoteDto dto, @RequestHeader("Authorization") String auth) {
        postService.vote(dto, -1, auth);
        return ResponseEntity.ok().build();
    }

    @PatchMapping
    public @ResponseBody
    ResponseEntity editPost(@RequestParam Integer postId, @RequestBody PostDto postDto, @RequestHeader("Authorization") String auth) {
        postService.editPost(postId, postDto, auth);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public @ResponseBody
    ResponseEntity deletePost(@RequestParam Integer postId, @RequestHeader("Authorization") String auth) {
        postService.deletePost(postId, auth);
        return ResponseEntity.ok().build();
    }
}
