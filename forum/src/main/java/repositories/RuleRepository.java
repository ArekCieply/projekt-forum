/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package repositories;

import entities.Rule;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Arek
 */
@Transactional
public interface RuleRepository extends CrudRepository<Rule, Integer> {

    public void deleteById(Integer id);

    @Modifying
    @Query("update Rule r set r.rule = ?1 where r.id = ?2")
    void editById(String newRule, Integer id);
}
