/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;

import dto.TopicDto;
import entities.Topic;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Arek
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TopicMapper {
    TopicDto entityToDTO(Topic topic);

    List<TopicDto> entityToDTO(Iterable<Topic> topic);

    Topic dtoToEntity(TopicDto Topic);

    List<Topic> dtoToEntity(Iterable<TopicDto> tpoic);
}
