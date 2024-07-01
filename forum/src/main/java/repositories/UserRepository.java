package repositories;

import entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByNameEquals(String name);

    User findFirstByNameEquals(String name);
    
    List<User> findByNameContainingIgnoreCase(String name);

    User findFirstByIdEquals(int id);

    User findFirstByProviderId(String providerId);

    @Modifying
    @Query("update User u set u.role = 'moderator' where u.id = ?1")
    void promoteToModById(Integer userId);
    
    @Modifying
    @Query("update User u set u.role = 'user' where u.id = ?1")
    void demoteToUserById(Integer userId);

    @Modifying
    @Query("update User u set u.pass = ?1 where u.id = ?2")
    void changePassById(String newPass, Integer userId);
    
    @Modifying
    @Query("update User u set u.isBanned = 1 where u.id = ?1")
    void banById(Integer userId);
    
    @Modifying
    @Query("update User u set u.isBanned = 0 where u.id = ?1")
    void unbanById(Integer userId);
    
    @Modifying
    @Query("update User u set u.score = u.score+?2 where u.id = ?1")
    void updateScoreById(Integer userId, Integer score);
}
