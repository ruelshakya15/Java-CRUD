package org.blogplatform.exception;

public class EntityAlreadyExistsException extends RuntimeException {
    private String message;
    public EntityAlreadyExistsException(String message) {
        super(message);
    }

}
