/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dto.PostDto;
import dto.VoteDto;
import javax.transaction.Transactional;
import mainPack.ForumApplication;
import mappers.PostMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import repositories.PostRepository;
import repositories.PostVoteRepository;
import repositories.TopicRepository;
import repositories.UserRepository;
import services.CheckUserId;
import services.PostService;

/**
 *
 * @author Arek
 */
@SpringBootTest(classes = ForumApplication.class)
@Transactional
public class PostControllerTestIT {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    String auth = "Basic dGVzdDE6dGVzdA==";

    Integer topic_id = 3;

    Integer postId = 42;
    Integer userId = 10;
    PostController instance;
    @Autowired
    PostRepository postRepository;
    @Autowired
    PostMapper postMapper;
    @Autowired
    TopicRepository topicRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PostVoteRepository postVoteRepository;
    @Autowired
    CheckUserId check;
    @Autowired
    PostService postService;

    public PostControllerTestIT() {
    }

    @BeforeAll
    public static void setUpClass() {

    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        instance = new PostController(postService, postMapper);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addNewPost method, of class PostController.
     */
    @Test
    public void testAddNewPost() throws Exception {
        System.out.println("addNewPost");
        PostDto postDto = new PostDto(null, "test", 10, 3, null, null);

        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(postDto);
        this.mockMvc.perform(post("/post").header("Authorization", auth).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test of getPosts method, of class PostController.
     */
    @Test
    public void testGetPosts() throws JsonProcessingException, Exception {
        System.out.println("getPosts");
        Integer topic_id = 3;

        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(topic_id);
        this.mockMvc.perform(get("/post").header("Authorization", auth).param("topic_id", topic_id.toString()).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value("11"));
    }

    /**
     * Test of votePlus method, of class PostController.
     */
    @Test
    public void testVotePlus() throws Exception {
        System.out.println("votePlus");
        VoteDto dto = new VoteDto(42, 10);

        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dto);
        this.mockMvc.perform(post("/post/vote/plus").header("Authorization", auth).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test of voteMinus method, of class PostController.
     */
    @Test
    public void testVoteMinus() throws Exception {
        System.out.println("voteMinus");
        VoteDto dto = new VoteDto(59, 10);

        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(dto);
        this.mockMvc.perform(post("/post/vote/minus").header("Authorization", auth).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test of editSection method, of class PostController.
     */
    @Test
    public void testEditPost() throws Exception {
        System.out.println("editPost");
        PostDto postDto = new PostDto(59, "test ed", 10, 3, null, null);

        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(postDto);
        this.mockMvc.perform(patch("/post").header("Authorization", auth).param("postId", postId.toString()).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * Test of deleteSection method, of class PostController.
     */
    @Test
    public void testDeletePost() throws Exception {
        System.out.println("deletePost");
        PostDto postDto = new PostDto(42, null, 10, null, null, null);

        var mapper = new ObjectMapper();
        var json = mapper.writeValueAsString(postDto);
        this.mockMvc.perform(delete("/post").header("Authorization", auth).param("postId", postId.toString())
                .param("userId", userId.toString()).contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
