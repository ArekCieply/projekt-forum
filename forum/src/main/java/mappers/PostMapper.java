/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;

import dto.PostDto;
import entities.Post;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 *
 * @author Arek
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {

    PostDto entityToDTO(Post post);

    List<PostDto> entityToDTO(Iterable<Post> post);

    Post dtoToEntity(PostDto Post);

    List<Post> dtoToEntity(Iterable<PostDto> post);
}
