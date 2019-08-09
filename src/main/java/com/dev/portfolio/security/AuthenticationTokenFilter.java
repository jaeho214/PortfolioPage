package com.dev.portfolio.security;

/*
    시큐리티가 시작 전에 실행이 되도록 만들어놓은 Filter
    토큰을 확인하여 유효함을 확인하고 시큐리티에 권한 세팅
 */


import io.jsonwebtoken.JwtException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationTokenFilter extends GenericFilterBean {
    private final JwtProvider jwtProvider;

    public AuthenticationTokenFilter(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        try{
            String token = jwtProvider.resolveToken(getAsHttpRequest(request)); // request를 token으로 풀어낸다.
            if(token != null && jwtProvider.validateToken(token)){ // 토큰이 있고 그 토큰이 유효하다면
                SecurityContextHolder.getContext().setAuthentication(jwtProvider.getAuthenticationByToken(token)); // Security Context 안에 Authentication(권한)을 세팅
            }
        }catch (JwtException | IllegalArgumentException e){ // 토큰의 유효함에 대해 예외가 발생하면
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("Expired or invalid JWT token"); // 토큰이 유효하지 않다는 에러 메세지를
        }catch (UsernameNotFoundException e){ //사용자에 대한 예외가 발생하면
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write("User not found"); // 회원을 찾을 수 없다는 에러 메세지를 띄운다.
        }
    }

    private HttpServletRequest getAsHttpRequest(ServletRequest request){
        if(!(request instanceof HttpServletRequest)){ // HTTP 프로토콜로 요청이 들어오지 않은 경우
            throw new RuntimeException("Expecting an HTTP request"); // 예외를 발생시키고
        }
        return (HttpServletRequest) request; // HTTP 프로토콜로 요청이 들어온 경우에는 그냥 리턴
    }
}
