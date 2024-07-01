/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package controllers;

import dto.SectionDto;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import services.SectionService;
import mappers.SectionMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Arek
 */
@RestController
@RequestMapping("/section")
@RequiredArgsConstructor
public class SectionController {

    final SectionService sectionService;
    final SectionMapper sectionMapper;

    @PostMapping
    public @ResponseBody
    ResponseEntity addNewSection(@RequestBody SectionDto sectionDto, @RequestHeader("Authorization") String auth) {
        sectionService.addSection(sectionDto, auth);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public @ResponseBody
    ResponseEntity<Iterable<SectionDto>> getAllSections() {
        return ResponseEntity.ok().body(sectionMapper.entityToDTO(sectionService.getSections()));
    }

    @PatchMapping
    public @ResponseBody
    ResponseEntity editSection(@RequestParam Integer sectionId, @RequestBody SectionDto sectionDto, @RequestHeader("Authorization") String auth) {
        sectionService.editSection(sectionId, sectionDto, auth);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public @ResponseBody
    ResponseEntity deleteSection(@RequestParam Integer sectionId, @RequestHeader("Authorization") String auth) {
        sectionService.deleteSection(sectionId, auth);
        return ResponseEntity.ok().build();
    }
}
