package com.dev.portfolio.security;


/*
    로그아웃에 성공했을 때 API 통신으로 200 을 보내줌
 */


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LogoutSuccessHandlerCustom implements LogoutSuccessHandler {

    private static final Logger logger = LoggerFactory.getLogger(AccessDeniedHandlerCustom.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        logger.info("Logout Successfully!!");
        response.sendError(HttpServletResponse.SC_OK);
    }
}
