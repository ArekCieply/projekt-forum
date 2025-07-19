/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package dto;

import enums.RoleEnum;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author Arek
 */
public record UserDto(String name,
        Integer id,
        Boolean isBanned,
        Integer score,
        @Enumerated(EnumType.STRING) RoleEnum role,
        String token) {

}
