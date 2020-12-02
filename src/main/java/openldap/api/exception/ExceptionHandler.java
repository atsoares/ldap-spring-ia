package openldap.api.exception;

import openldap.api.tos.response.ErrorResponseTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class ExceptionHandler {


    @org.springframework.web.bind.annotation.ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<ErrorResponseTO> handleUserDoesNotExistException(UserDoesNotExistException ex) {
        return new ResponseEntity<>(new ErrorResponseTO("User does not exist"), HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseTO> handleUserAlreadyExistsException(UserAlreadyExistsException ex) {
        return new ResponseEntity<>(new ErrorResponseTO("User already exists"), HttpStatus.CONFLICT);
    }

}
