package com.dev.portfolio.exception;


/*
    예외처리를 해주기 위한 클래스
    @RestControllerAdvice : 공통의 Exception 처리 전용 객체를 사용하는 방식
    @ExceptionHandler : 예외를 처리할 클래스를 정의

 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private UrlPathHelper urlPathHelper;

    @ExceptionHandler(UserDefineException.class) // UserDefineException이 발생하면 실행될 메소드
    public ResponseEntity handlerUserDefineException(HttpServletRequest request, UserDefineException e){
        String requestUrl = urlPathHelper.getOriginatingRequestUri(request); // 호출한 Url을 그대로 가져옴
        return null;
    }

}
