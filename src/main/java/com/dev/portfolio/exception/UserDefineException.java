package com.dev.portfolio.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UserDefineException extends RuntimeException{
    private String originalErrorMessage;
    private String errorMethod;
    private HttpStatus errorCode = HttpStatus.BAD_REQUEST;

    public UserDefineException(String message){
        super(message);
    }

    public UserDefineException(String message, HttpStatus errorCode){
        super(message);
        this.errorCode = errorCode;
    }

    public UserDefineException(String message, String originalErrorMessage, String errorMethod){
        super(message);
        this.originalErrorMessage = originalErrorMessage;
        this.errorMethod = errorMethod;
    }

}
