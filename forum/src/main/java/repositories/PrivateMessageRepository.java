/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package repositories;

import entities.PrivateMessage;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Arek
 */
public interface PrivateMessageRepository extends CrudRepository<PrivateMessage, Integer> {
        List<PrivateMessage> findByPrivateConversationIdEquals(Integer Id);

}
