/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package services;

import dto.SubSectionDto;
import entities.Post;
import mappers.SubSectionMapper;
import entities.SubSection;
import entities.Topic;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.PostRepository;
import repositories.SectionRepository;
import repositories.SubSectionRepository;
import repositories.TopicRepository;
import repositories.UserRepository;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author Arek
 */
@Service
@RequiredArgsConstructor
public class SubSectionService {

    final private SubSectionRepository sub_sectionRepository;
    final private SectionRepository sectionRepository;
    final private UserRepository userRepository;
    final CheckUserId check;
    final SubSectionMapper subSectionMapper;
    final private PostRepository postRepository;
    final private TopicRepository topicRepository;

    public void addSubSection(SubSectionDto subSectionDto, String auth) {
        if (check.checkId(auth, subSectionDto.userId())) {
            SubSection subSection = subSectionMapper.dtoToEntity(subSectionDto);
            subSection.setSection(sectionRepository.findFirstById(subSectionDto.sectionId()));
            subSection.setUser(userRepository.findFirstByIdEquals(subSectionDto.userId()));
            sub_sectionRepository.save(subSection);
        }
    }

    public Iterable<SubSection> getSubSections(Integer sectionId) {
        return sub_sectionRepository.findBySectionIdEquals(sectionId);
    }

    public void editSubSection(int subSectionId, SubSectionDto subSectionDto, String auth) {
            sub_sectionRepository.editById(subSectionDto.name(), subSectionId);
        
    }

    public void deleteSubSection(int subSectionId, String auth) {
        List<Topic> topics = topicRepository.findBySubSectionIdEquals(subSectionId);
        List<Integer> topicIds = topics.stream().map(topic -> topic.getId()).collect(Collectors.toList());
        postRepository.deleteByTopicIdIn(topicIds);
        topicRepository.deleteBySubSectionId(subSectionId);
        sub_sectionRepository.deleteById(subSectionId);
    }
}
