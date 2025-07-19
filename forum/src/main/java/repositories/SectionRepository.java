package repositories;

import entities.Section;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SectionRepository extends CrudRepository<Section, Integer> {

    Section findFirstById(Integer id);
    
    public void deleteById(Integer id);
    
    @Modifying
    @Query("update Section s set s.name = ?1 where s.id = ?2")
    void editById(String newName, Integer id);
}
