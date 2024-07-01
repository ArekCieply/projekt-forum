/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package services;

import dto.SectionDto;
import mappers.SectionMapper;
import entities.Section;
import entities.SubSection;
import entities.Topic;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PostRepository;
import repositories.SectionRepository;
import repositories.SubSectionRepository;
import repositories.TopicRepository;
import repositories.UserRepository;

/**
 *
 * @author Arek
 */
@Service
@RequiredArgsConstructor
public class SectionService {

    final private SectionRepository sectionRepository;
    final private UserRepository userRepository;
    final CheckUserId check;
    final SectionMapper sectionMapper;
    final private SubSectionRepository sub_sectionRepository;
    final private PostRepository postRepository;
    final private TopicRepository topicRepository;

    public void addSection(SectionDto sectionDto, String auth) {
        if (check.checkId(auth, sectionDto.userId())) {
            Section section = sectionMapper.dtoToEntity(sectionDto);
            section.setUser(userRepository.findFirstByIdEquals(sectionDto.userId()));
            sectionRepository.save(section);
        }
    }

    public Iterable<Section> getSections() {
        return sectionRepository.findAll();
    }

    public void deleteSection(int sectionId, String auth) {
            List<SubSection> subSections = sub_sectionRepository.findBySectionIdEquals(sectionId);
            List<Integer> subSectionIds = subSections.stream().map(subSection -> subSection.getId()).collect(Collectors.toList());
            List<Topic> topics = topicRepository.findBySubSectionIdIn(subSectionIds);
            List<Integer> topicIds = topics.stream().map(topic -> topic.getId()).collect(Collectors.toList());
            postRepository.deleteByTopicIdIn(topicIds);
            topicRepository.deleteBySubSectionIdIn(subSectionIds);
            sub_sectionRepository.deleteBySectionId(sectionId);
            sectionRepository.deleteById(sectionId);
    }

    public void editSection(int sectionId, SectionDto sectionDto, String auth) {
            sectionRepository.editById(sectionDto.name(), sectionId);
    }
}
