/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package dto;

/**
 *
 * @author Arek
 */
public record PrivateConversationDto(
        Integer user1Id,
        Integer user2Id,
        UserDto user1,
        UserDto user2) {

}
