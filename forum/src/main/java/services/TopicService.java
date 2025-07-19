/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package services;

import dto.TopicDto;
import entities.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.TopicRepository;
import mappers.TopicMapper;
import dto.VoteDto;
import entities.Post;
import entities.PostVote;
import entities.TopicVote;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import repositories.PostRepository;
import repositories.PostVoteRepository;
import repositories.SectionRepository;
import repositories.SubSectionRepository;
import repositories.TopicVoteRepository;
import repositories.UserRepository;

/**
 *
 * @author Arek
 */
@Service
@RequiredArgsConstructor
public class TopicService {

    final private TopicRepository topicRepository;
    final CheckUserId check;
    final TopicMapper topicMapper;
    final private UserRepository userRepository;
    final TopicVoteRepository topicVoteRepository;
    final PostVoteRepository postVoteRepository;
    final private SubSectionRepository sub_sectionRepository;
   // private SectionRepository sectionRepository;
    final private PostRepository postRepository;

    public void addTopic(TopicDto topicDto, String auth) {// działa  
        if (check.checkId(auth, topicDto.userId())) {
            Topic topic = topicMapper.dtoToEntity(topicDto);
            topic.setScore(0);
            topic.setSubSection(sub_sectionRepository.findFirstById(topicDto.subSectionId()));
            topic.setUser(userRepository.findFirstByIdEquals(topicDto.userId()));
            topicRepository.save(topic);
        }
    }

    public Iterable<Topic> getTopics(Integer subSectionId) {
        return topicRepository.findBySubSectionIdEquals(subSectionId);
    }
    
    public Topic getTopic(Integer topicId) {
        return topicRepository.findFirstByIdEquals(topicId);
    }
    
    public Iterable<Topic> getTopicsByUser(Integer userId) {
        return topicRepository.findByUserIdEquals(userId);
    }
    
    public Iterable<Topic> findTopics(String name) {
        return topicRepository.findByNameContainingIgnoreCase(name);
    }

    public void vote(VoteDto dto, int score, String auth) {
        if (check.checkId(auth, dto.userId())) {
            if (topicVoteRepository.findByTopicIdEqualsAndUserIdEquals(dto.targetId(), dto.userId()).isEmpty()) {
                Topic topic = topicRepository.findFirstById(dto.targetId());
                userRepository.updateScoreById(topic.getUser().getId(), score);
                topicRepository.updateScoreById(topic.getId(), score);
                TopicVote topicVote = new TopicVote();
                topicVote.setTopic(topic);
                topicVote.setUser(userRepository.findFirstByIdEquals(dto.userId()));
                topicVoteRepository.save(topicVote);
            }
        }
    }

    public void editTopic(int topicId, TopicDto topicDto, String auth) {
            topicRepository.editById(topicDto.content(), topicDto.name(), topicId);
        
    }

    public void deleteTopic(int topicId, String auth) {
            topicVoteRepository.deleteByTopicIdEquals(topicId);
            List<Post> posts = postRepository.findByTopicIdEquals(topicId);
            List<Integer> postIds = posts.stream().map(post -> post.getId()).collect(Collectors.toList());
            postVoteRepository.deleteByPostIdIn(postIds);
            postRepository.deleteByTopicId(topicId);
            topicRepository.deleteById(topicId);
    }
}
