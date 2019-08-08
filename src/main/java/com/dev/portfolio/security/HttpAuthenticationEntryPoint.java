package com.dev.portfolio.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class HttpAuthenticationEntryPoint extends BasicAuthenticationEntryPoint { //로그인 되어있지 않은 사용자가 접근했을 때 login 창을 바로 띄우지 않고 API 통신
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException{
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    @Override
    public void afterPropertiesSet() throws Exception { // 빈 생성할 때 실행 됨, 이름부터 지정하고 시작하는 듯;;;;
        setRealmName("realMNave"); //realm은 Basic Authentication을 사용할 때 구분하기 위해 이름을 정해주는 듯 하다.
        super.afterPropertiesSet();
    }
}
