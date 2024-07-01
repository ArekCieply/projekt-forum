/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package controllers;

import dto.RuleDto;
import entities.Rule;
import java.util.List;
import lombok.RequiredArgsConstructor;
import mappers.RuleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import services.RuleService;

/**
 *
 * @author Arek
 */
@RestController
@RequestMapping("/rule")
@RequiredArgsConstructor
public class RuleController {
    
    @Autowired
    RuleService ruleService;
    @Autowired
    RuleMapper ruleMapper;
    
    @PostMapping
    public @ResponseBody
    ResponseEntity addNewRule(@RequestBody RuleDto ruleDto, @RequestHeader("Authorization") String auth) {
        ruleService.addRule(ruleDto, auth);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<RuleDto>> getAllRules() {
        return ResponseEntity.ok().body(ruleMapper.entityToDTO(ruleService.getRules()));
    }

    @PatchMapping
    public @ResponseBody
    ResponseEntity editRule(@RequestParam Integer ruleId, @RequestBody RuleDto ruleDto, @RequestHeader("Authorization") String auth) {
        ruleService.editRule(ruleId, ruleDto, auth);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public @ResponseBody
    ResponseEntity deleteRule(@RequestParam Integer ruleId, @RequestHeader("Authorization") String auth) {
        ruleService.deleteRule(ruleId, auth);
        return ResponseEntity.ok().build();
    }
}
