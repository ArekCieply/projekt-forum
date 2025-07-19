/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package controllers;

import dto.SubSectionDto;
import entities.SubSection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import services.SubSectionService;
import mappers.SubSectionMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
/**
 *
 * @author Arek
 */
@RestController
@RequestMapping("/sub_section")
@RequiredArgsConstructor
public class SubSectionController {

    final SubSectionService subSectionService;
    final SubSectionMapper subSectionMapper;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewSub_Section(@RequestBody SubSectionDto subSectionDto, @RequestHeader("Authorization") String auth) {
        subSectionService.addSubSection(subSectionDto, auth);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<List<SubSectionDto>> getSub_Sections(@RequestParam Integer section_id) {
        return ResponseEntity.ok().body(subSectionMapper.entityToDTO(subSectionService.getSubSections(section_id)));
    }
    
    @PatchMapping
    public @ResponseBody
    ResponseEntity editSub_Section(@RequestParam Integer subSectionId, @RequestBody SubSectionDto subSectionDto, @RequestHeader("Authorization") String auth) {
        subSectionService.editSubSection(subSectionId, subSectionDto, auth);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public @ResponseBody
    ResponseEntity deleteSub_Section(@RequestParam Integer subSectionId, @RequestHeader("Authorization") String auth) {
        subSectionService.deleteSubSection(subSectionId, auth);
        return ResponseEntity.ok().build();
    }

}
