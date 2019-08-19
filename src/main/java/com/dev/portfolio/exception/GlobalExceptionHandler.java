package com.dev.portfolio.exception;


/*
    예외처리를 해주기 위한 클래스
    @RestControllerAdvice : 공통의 Exception 처리 전용 객체를 사용하는 방식
    @ExceptionHandler : 예외를 처리할 클래스를 정의

 */

import com.dev.portfolio.model.dto.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private UrlPathHelper urlPathHelper;

    @ExceptionHandler(UserDefineException.class) // UserDefineException이 발생하면 실행될 메소드
    public ResponseEntity handlerUserDefineException(HttpServletRequest request, UserDefineException e){
        String requestURL = urlPathHelper.getOriginatingRequestUri(request); // 호출한 Url을 그대로 가져옴

        logger.info("======================================");
        logger.info("예외 발생 시간 : " + LocalDateTime.now());
        logger.info("요청 HTTP 메소드 : " + request.getMethod());
        logger.info("요청 URL : " + requestURL);
        logger.info("클라이언트 : " + request.getRemoteHost());
        logger.info("사용자 정의 에러 메세지 : " + e.getMessage());
        logger.info("원본 에러 메세지 : " + e.getOriginalErrorMessage());
        logger.info("예외발생 메소드 : " + e.getErrorMethod());
        logger.info("Cause : " + e.getCause());
        logger.info("======================================");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Explanation", "Portfolio Management Service");

        return new ResponseEntity<>(ErrorDto.builder()
                .userDefineErrorMessage(e.getMessage())
                .originalErrorMessage(e.getOriginalErrorMessage())
                .requestURL(requestURL)
                .build(), httpHeaders, e.getErrorCode());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handlerException(HttpServletRequest request, Exception e){
        String requestURL = urlPathHelper.getOriginatingRequestUri(request);

        logger.info("======================================");
        logger.info("예외 발생 시간 : " + LocalDateTime.now());
        logger.info("요청 HTTP 메소드 : " + request.getMethod());
        logger.info("요청 URL : " + requestURL);
        logger.info("클라이언트 : " + request.getRemoteHost());
        logger.info("원본 에러 메세지 : " + e.toString());
        logger.info("Cause : " + e.getCause());
        logger.info("======================================");

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Explanation", "Portfolio Management Service");

        return new ResponseEntity<>(ErrorDto.builder()
                .userDefineErrorMessage("예상치 못한 예외 발생")
                .originalErrorMessage(e.toString())
                .requestURL(requestURL)
                .build(), httpHeaders, HttpStatus.BAD_REQUEST);
    }

}
