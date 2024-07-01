/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package services;

import dto.PostDto;
import mappers.PostMapper;
import dto.VoteDto;
import entities.Post;
import entities.PostVote;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PostRepository;
import repositories.PostVoteRepository;
import repositories.TopicRepository;
import repositories.UserRepository;

/**
 *
 * @author Arek
 */
@Service
@RequiredArgsConstructor
public class PostService {

    //@Autowired
    final PostRepository postRepository;
    //@Autowired
    final CheckUserId check;
    //@Autowired
    final PostMapper postMapper;
    //@Autowired
    final PostVoteRepository postVoteRepository;
    //@Autowired
    final private TopicRepository topicRepository;
    //@Autowired
    final private UserRepository userRepository;

    public void addPost(PostDto postDto, String auth) {
        if (check.checkId(auth, postDto.userId())) {
            Post post = postMapper.dtoToEntity(postDto);
            post.setScore(0);
            post.setTopic(topicRepository.findFirstById(postDto.topicId()));
            post.setUser(userRepository.findFirstByIdEquals(postDto.userId()));
            postRepository.save(post);
        }
    }

    public Iterable<Post> getPosts(Integer topicId) {
        return postRepository.findByTopicIdEquals(topicId);
    }

    public void vote(VoteDto dto, int score, String auth) {
        if (check.checkId(auth, dto.userId())) {
            if (postVoteRepository.findByPostIdEqualsAndUserIdEquals(dto.targetId(), dto.userId()).isEmpty()) {
                Post post = postRepository.findFirstById(dto.targetId());
                userRepository.updateScoreById(post.getUser().getId(), score);
                postRepository.updateScoreById(post.getId(), score);
                PostVote postVote = new PostVote();
                postVote.setPost(post);
                postVote.setUser(userRepository.findFirstByIdEquals(dto.userId()));
                postVoteRepository.save(postVote);
            }
        }
    }

    public void editPost(int postId, PostDto postDto, String auth) {
            postRepository.editById(postDto.content(), postId);
    }

    public void deletePost(int postId, String auth) {
            postVoteRepository.deleteByPostIdEquals(postId);
            postRepository.deleteById(postId);
    }

}
