/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;

import dto.PrivateConversationDto;
import entities.PrivateConversation;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Arek
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrivateConversationMapper {

    PrivateConversationDto entityToDTO(PrivateConversation privateConversationDto);

    List<PrivateConversationDto> entityToDTO(Iterable<PrivateConversation> privateConversationDto);

    PrivateConversation dtoToEntity(PrivateConversationDto privateConversationDto);

    List<PrivateConversation> dtoToEntity(Iterable<PrivateConversationDto> privateConversationDto);
}
