package repositories;

import java.util.List;
import entities.Topic;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface TopicRepository extends CrudRepository<Topic, Integer> {

    List<Topic> findBySubSectionIdEquals(Integer subSectionId);

    Topic findFirstById(Integer id);

    @Modifying
    @Query("update Topic t set t.score = t.score+?2 where t.id = ?1")
    void updateScoreById(Integer id, Integer score);

    @Modifying
    @Query("update Topic t set t.content = ?1, t.name = ?2 where t.id = ?3")
    public void editById(String content, String name, Integer id);

    public void deleteBySubSectionId(Integer id);

    public List<Topic> findBySubSectionIdIn(List<Integer> subSectionIds);

    public void deleteBySubSectionIdIn(List<Integer> subSectionIds);

    List<Topic> findByNameEquals(String name);
    
    List<Topic> findByNameContainingIgnoreCase(String name);

    public Iterable<Topic> findByUserIdEquals(Integer userId);

    public Topic findFirstByIdEquals(Integer topicId);
}
