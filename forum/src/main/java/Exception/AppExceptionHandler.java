/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author Arek
 */
@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(NameTakenException.class)
    public ResponseEntity handleException(NameTakenException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body("User name taken");
    }

}
