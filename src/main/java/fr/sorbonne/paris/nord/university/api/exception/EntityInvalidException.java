package fr.sorbonne.paris.nord.university.api.exception;

public class EntityInvalidException extends RuntimeException{

    public EntityInvalidException(String message) {
        super(message);
    }
    public String getMessage(){
        return super.getMessage();
    }

}
