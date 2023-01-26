package fr.sorbonne.paris.nord.university.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message) {
        super(message);
    }
    public String getMessage(){
        return super.getMessage();
    }
}
