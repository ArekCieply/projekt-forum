/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;

import dto.RuleDto;
import entities.Rule;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Arek
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RuleMapper {

    RuleDto entityToDTO(Rule rule);

    List<RuleDto> entityToDTO(Iterable<Rule> rule);

    Rule dtoToEntity(RuleDto rule);

    List<Rule> dtoToEntity(Iterable<RuleDto> rule);
}
