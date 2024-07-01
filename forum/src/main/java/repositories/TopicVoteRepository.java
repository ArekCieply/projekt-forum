/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package repositories;

import entities.TopicVote;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Arek
 */
@Transactional
public interface TopicVoteRepository extends CrudRepository<TopicVote, Integer> {

    List<TopicVote> findByTopicIdEqualsAndUserIdEquals(Integer topicId, Integer userId);
    
    public void deleteByTopicIdEquals(Integer topicId);
}
