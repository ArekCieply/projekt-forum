package repositories;

import java.util.List;
import entities.Post;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface PostRepository extends CrudRepository<Post, Integer> {

    List<Post> findByTopicIdEquals(Integer topicId);

    @Modifying
    @Query("update Post p set p.score = p.score+?2 where p.id = ?1")
    public void updateScoreById(Integer id, int score);

    public Post findFirstById(Integer id);

    @Modifying
    @Query("update Post p set p.content = ?1 where p.id = ?2")
    public void editById(String content, Integer id);

    public void deleteById(Integer id);
    
    public void deleteByTopicId(Integer id);

    public List<Post> findByTopicIdIn(List<Integer> topicIds);

    public void deleteByTopicIdIn(List<Integer> topicIds);


}
