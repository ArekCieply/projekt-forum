/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package dto;

/**
 *
 * @author Arek
 */
public record PrivateMessageDto(
        Integer userId,
        String content,
        Integer privateConversationId,
        UserDto user) {

}
