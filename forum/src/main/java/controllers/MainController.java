package controllers;

import repositories.UserRepository;
import entities.Post;
import repositories.PostRepository;
import entities.Section;
import repositories.SectionRepository;
import entities.SubSection;
import repositories.SubSectionRepository;
import entities.Topic;
import repositories.TopicRepository;
import entities.User;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Map;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import services.PostService;
import services.SectionService;
import services.SubSectionService;
import services.TopicService;
import services.UserService;
//import config.WebSecurityConfig;

@Controller
public class MainController {

    
   // @Autowired
    //PostService postService = new PostService();
/*
    //user
    @PostMapping(path = "/users")
    public @ResponseBody
    String addNewUser(@RequestBody User user) {
        userRepository.save(user);
        return "Saved";
    }

    @GetMapping(path = "/users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userService.getUsers();
    }

    @PostMapping(path = "/users/register")
    public @ResponseBody
    User registerUser(@RequestBody Map<String, String> json) {
        return userService.registerUser(json.get("name"), json.get("pass"));
    }

    @PostMapping(path = "/users/login")
    public @ResponseBody
    User loginUser(@RequestBody Map<String, String> json) {
        User user = userService.loginUser(json.get("name"), json.get("pass"));
        return user;
    }
    
    @PostMapping(path = "/users/login/facebook")
    public @ResponseBody
    User loginUserFacebook(@RequestBody Map<String, String> json) {
        //https://graph.facebook.com/me?access_token=
        User user = userService.facebookLogin(json.get("token"));
        return user;
    }
    
    @PostMapping(path = "/users/login/google")
    public @ResponseBody
    User loginUserGoogle(@RequestBody Map<String, String> json) throws GeneralSecurityException, IOException {
        //https://graph.facebook.com/me?access_token=
        User user = userService.googleLogin(json.get("token"));
        return user;
    }
    
    

    //section
    @PostMapping(path = "/section")
    public @ResponseBody
    String addNewSection(@RequestBody Section section, @RequestHeader("Authorization") String auth) {

        sectionService.addSection(section, auth);
        return "Saved";
    }

    @GetMapping(path = "/section")
    public @ResponseBody
    Iterable<Section> getAllSections() {
        return sectionService.getSections();
    }

    //sub_section
    @PostMapping(path = "/sub_section")
    public @ResponseBody
    String addNewSub_Section(@RequestBody SubSection sub_section, @RequestHeader("Authorization") String auth) {
        //sub_sectionRepository.save(sub_section);
        subSectionService.addSubSection(sub_section, auth);
        return "Saved";
    }

    @GetMapping(path = "/sub_section")
    public @ResponseBody
    Iterable<SubSection> getSub_Sections(@RequestParam Integer section_id) {
        return subSectionService.getSubSections(section_id);//sub_sectionRepository.findBySectionIdEquals(section_id);
    }

    //post
    @PostMapping(path = "/post")
    public @ResponseBody
    String addNewPost(@RequestBody Post post, @RequestHeader("Authorization") String auth) {
        postService.addPost(post, auth);
        return "Saved";
    }

    @GetMapping(path = "/post")
    public @ResponseBody
    Iterable<Post> getPosts(@RequestParam Integer topic_id) {
        return postService.getPosts(topic_id);
    }

    //topic
    @PostMapping(path = "/topic")
    public @ResponseBody
    String addNewTopic(@RequestBody Topic topic, @RequestHeader("Authorization") String auth) {
        topicService.addTopic(topic, auth);
        return "Saved";
    }

    @GetMapping(path = "/topic")
    public @ResponseBody
    Iterable<Topic> getTopics(@RequestParam Integer sub_section_id) {
        return topicService.getTopics(sub_section_id);
    }
*/
}
