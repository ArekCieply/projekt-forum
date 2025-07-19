/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package mappers;

import dto.UserDto;
import entities.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author Arek
 */

@Mapper(componentModel = "spring")
public interface UserMapper {
    //UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    UserDto entityToDTO(User user);

    List<UserDto> entityToDTO(Iterable<User> user);

    User dtoToEntity(UserDto user);

    List<User> dtoToEntity(Iterable<UserDto> user);
}
