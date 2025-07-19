/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package repositories;

import entities.PostVote;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Arek
 */
@Transactional
public interface PostVoteRepository extends CrudRepository<PostVote, Integer> {

    PostVote findFirstByUserIdAndPostId(Integer userId, Integer postId);

    List<PostVote> findByPostIdEqualsAndUserIdEquals(Integer postId, Integer userId);
    
    public void deleteByPostIdEquals(Integer postId);
    
    public void deleteByIdIn(List<Integer> id);
    
    public void deleteByPostIdIn(List<Integer> postId);
}
