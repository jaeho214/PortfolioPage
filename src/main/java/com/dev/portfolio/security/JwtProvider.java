package com.dev.portfolio.security;

import com.dev.portfolio.model.entity.MemberRole;
import com.dev.portfolio.service.UserDetailsServiceImpl;
import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtProvider {
    private final UserDetailsServiceImpl userDetailsService;
    private final long validityInMilliseconds = 36000000;
    private String secretKey = "THISISNEVERTHAT";

    public JwtProvider(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String username, List<MemberRole> roles){
        Claims claims = Jwts.claims().setSubject(username); // 클레임의 주제
        claims.put("roles", roles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims) // 클레임 세팅
                .setIssuedAt(now) // 토큰이 발급된 시간 세팅
                .setExpiration(validity) // 토큰의 만료기간 세팅
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public boolean validateToken(String token) throws JwtException, IllegalArgumentException {
        Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return claims.getBody().getExpiration().after(new Date());
    }

    public String resolveToken(HttpServletRequest req){
        ServletRequest request;
        String bearerToken = req.getHeader("Authorization");
        if(bearerToken != null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public String resolveToken(String bearerToken){
        if(bearerToken !=null && bearerToken.startsWith("Bearer ")){
            return bearerToken.substring(7);
        }
        return null;
    }

    public Authentication getAuthenticationByToken(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getUserIdByToken(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUserIdByToken(String bearerToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(resolveToken(bearerToken)).getBody().getSubject();
    }

    public List<MemberRole> getUserRolesByToken(String bearerToken) {
        //List<MemberRole> roles = new ArrayList<>();
        List list = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(resolveToken(bearerToken)).getBody().get("roles", List.class);
        return list;
    }



}
