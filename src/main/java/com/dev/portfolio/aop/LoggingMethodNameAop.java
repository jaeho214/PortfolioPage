package com.dev.portfolio.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingMethodNameAop {
    private static final Logger logger = LoggerFactory.getLogger(LoggingMethodNameAop.class);

    @Before("execution(* com.dev.portfolio.service..*.*(..))")
    public void loggingMethod(JoinPoint jp){
        logger.info("=====================================");
        logger.info("실행될 메소드 이름: " + jp.getSignature().getName());
        logger.info("=====================================");
    }
}
