/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package repositories;

import entities.PrivateConversation;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Arek
 */
public interface PrivateConversationRepository extends CrudRepository<PrivateConversation, Integer> {

    PrivateConversation findFirstByUser1IdEquals(Integer user1Id);

    PrivateConversation findFirstByUser2IdEquals(Integer user2Id);

    List<PrivateConversation> findByIdEquals(Integer Id);

    //@Query(value = "SELECT * FROM private_conversations WHERE (user1Id = ?1 AND user2Id = ?2) OR (user1Id = ?2 AND user2Id = ?1)")
    //PrivateConversation findByUsersId(Integer user1Id, Integer user2Id);
    
    PrivateConversation findByUser1IdEqualsAndUser2IdEqualsOrUser1IdEqualsAndUser2IdEquals(Integer user1Id, Integer user2Id, Integer user1Id2, Integer user2Id2);
    //@Query(value = "SELECT * FROM private_conversations WHERE user1Id = ?1 OR user2Id = ?1")
    //List<PrivateConversation> findByUserId(Integer userId);
    List<PrivateConversation> findByUser1IdEqualsOrUser2IdEquals(Integer user1Id, Integer user2Id);
    
    PrivateConversation findFirstByIdEquals(Integer Id);
}
