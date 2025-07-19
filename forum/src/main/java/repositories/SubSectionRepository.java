package repositories;

import java.util.List;
import entities.SubSection;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface SubSectionRepository extends CrudRepository<SubSection, Integer> {

    List<SubSection> findBySectionIdEquals(Integer sectionId);

    SubSection findFirstById(Integer id);

    @Modifying
    @Query("update SubSection s set s.name = ?1 where s.id = ?2")
    public void editById(String name, Integer id);

    public void deleteBySectionId(Integer id);

    public void deleteBySectionIdIn(List<Integer> id);

}
