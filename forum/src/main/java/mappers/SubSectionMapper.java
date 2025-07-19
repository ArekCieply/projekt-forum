/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;

import dto.SubSectionDto;
import dto.SubSectionDto;
import entities.SubSection;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Arek
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubSectionMapper {
    SubSectionDto entityToDTO(SubSection subSection);

    List<SubSectionDto> entityToDTO(Iterable<SubSection> subSection);

    SubSection dtoToEntity(SubSectionDto subSection);

    List<SubSection> dtoToEntity(Iterable<SubSectionDto> subSection);
    
}
