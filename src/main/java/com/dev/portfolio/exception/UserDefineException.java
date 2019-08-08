package com.dev.portfolio.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDefineException extends RuntimeException{
    private String originalErrorMessage;
    private String errorMethod;

    public UserDefineException(String message){
        super(message);
    }

    public UserDefineException(String message, String originalErrorMessage){
        super(message);
        this.originalErrorMessage = originalErrorMessage;
    }

    public UserDefineException(String message, String originalErrorMessage, String errorMethod){
        super(message);
        this.originalErrorMessage = originalErrorMessage;
        this.errorMethod = errorMethod;
    }

}
