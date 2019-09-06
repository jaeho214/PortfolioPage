package com.dev.portfolio.security;

/*
    로그인 되어있지 않은 사용자가 접근했을 때 login.jsp를 자동적으로 띄우게 되어있다
    하지만 Restful 방식에서는 view를 제공해주지 않기 때문에 login화면을 띄울 수 있도록 http통신으로 에러 코드를 보내게 커스텀
 */



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HttpAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedHandlerCustom.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
        logger.error("AuthenticationException ===> HttpAuthenticationEntryPoint");
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @Override
    public void afterPropertiesSet() throws Exception { // 빈 생성할 때 실행 됨, 이름부터 지정하고 시작하는 듯;;;;
        setRealmName("realMNave"); //realm은 Basic Authentication을 사용할 때 구분하기 위해 이름을 정해주는 듯 하다.
        super.afterPropertiesSet();
    }
}
