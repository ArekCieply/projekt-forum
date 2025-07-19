/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package dto;

/**
 *
 * @author Arek
 */
public record PostDto(Integer id,
        String content,
        Integer userId,
        Integer topicId,
        UserDto user,
        Integer score) {

}
