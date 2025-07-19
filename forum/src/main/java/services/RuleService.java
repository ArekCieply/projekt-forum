/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package services;

import dto.RuleDto;
import entities.Rule;
import lombok.RequiredArgsConstructor;
import mappers.RuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.RuleRepository;

/**
 *
 * @author Arek
 */
@Service
@RequiredArgsConstructor
public class RuleService {

    final private RuleRepository ruleRepository;
    final CheckUserId check;
    final RuleMapper ruleMapper;

    public Iterable<Rule> getRules() {
        return ruleRepository.findAll();
    }

    public void addRule(RuleDto ruleDto, String auth) {
        if (check.checkId(auth, ruleDto.userId())) {
            Rule rule = ruleMapper.dtoToEntity(ruleDto);
            ruleRepository.save(rule);
        }
    }

    public void editRule(int ruleId, RuleDto ruleDto, String auth) {
            ruleRepository.editById(ruleDto.rule(), ruleId);
    }

    public void deleteRule(Integer ruleId, String auth) {
            ruleRepository.deleteById(ruleId);
    }

}
