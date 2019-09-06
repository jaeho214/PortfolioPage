package com.dev.portfolio.security;

import com.dev.portfolio.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final HttpAuthenticationEntryPoint httpAuthenticationEntryPoint;
    private final AccessDeniedHandlerCustom accessDeniedHandlerCustom;
    private final LogoutSuccessHandlerCustom logoutSuccessHandlerCustom;
    private final AuthenticationTokenFilter authenticationTokenFilter;

    private static final String[] AUTH_ARR = {
            "/v2/api-docs",
            "/configuration/ui",
            "/swagger-resources/**",
            "/configuration/security",
            "/swagger-ui.html/**",
            "/webjars/**",
            "favicon.ico"
    };

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, PasswordEncoder passwordEncoder, HttpAuthenticationEntryPoint httpAuthenticationEntryPoint,
                          AccessDeniedHandlerCustom accessDeniedHandlerCustom, LogoutSuccessHandlerCustom logoutSuccessHandlerCustom, AuthenticationTokenFilter authenticationTokenFilter){
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.httpAuthenticationEntryPoint = httpAuthenticationEntryPoint;
        this.accessDeniedHandlerCustom = accessDeniedHandlerCustom;
        this.logoutSuccessHandlerCustom = logoutSuccessHandlerCustom;
        this.authenticationTokenFilter = authenticationTokenFilter;
    }


    @Override
    //인터셉터로 요청을 안전하게 보호하는 방법을 설정
    //다른 URL 패스들에 대해 선택적으로 보안을 적용
    //리소스외에 페이지의 인증/비인증/인증권한등을 설정하는게 좋은것 같습니다.
    //스프링 시큐리티 룰
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //REST API와 같이 세션을 사용하지 않을 때는 세션 생성 규칙을 'stateless'로 변경해야 한다.
                .and()
                    .authorizeRequests()
                        //.antMatchers(HttpMethod.OPTIONS,"/oauth/token").permitAll()
                        .antMatchers("/portfolio/sign/signup").permitAll()
                        .antMatchers(HttpMethod.PUT, "/portfolio/sign").hasRole("USER")
                        .antMatchers(HttpMethod.DELETE, "/portfolio/sign").hasRole("USER")
                        .antMatchers("/manageMember").hasRole("ADMIN")
                        .anyRequest().anonymous()
                    .and()
                        .exceptionHandling().authenticationEntryPoint(httpAuthenticationEntryPoint).accessDeniedHandler(accessDeniedHandlerCustom)
                    .and()
                        .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class) // UsernamePasswordAuthenticationFilter(인증 요청 관리) 관련 필터를 만들어서 추가하겠다~~ 이거야
                        .logout().logoutUrl("/portfolio/logout").logoutSuccessHandler(logoutSuccessHandlerCustom);
    }

    @Override
    //시큐리티의 필터 연결을 설정 /무조건 항상 열려있어야 하는 것들
    //이런식으로 인증할것들을 풀어주는겁니다.
    //스프링 시큐리티 룰을 무시하게 하는 Url 규칙
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(AUTH_ARR)
                .antMatchers(HttpMethod.POST, "/portfolio/sign")
                .antMatchers(HttpMethod.POST, "/portfolio/sign/user")
                .antMatchers(HttpMethod.POST, "/portfolio/sign/admin");

    }

    @Override
    //사용자 세부 서비스를 설정
    //AuthenticationProvider 구현체
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }



}
