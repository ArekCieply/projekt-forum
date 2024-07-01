/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;

import dto.PrivateMessageDto;
import entities.PrivateMessage;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Arek
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PrivateMessageMapper {
    PrivateMessageDto entityToDTO(PrivateMessage privateMessageDto);

    List<PrivateMessageDto> entityToDTO(Iterable<PrivateMessage> privateMessageDto);

    PrivateMessage dtoToEntity(PrivateMessageDto privateMessageDto);

    List<PrivateMessage> dtoToEntity(Iterable<PrivateMessageDto> privateMessageDto);
}
