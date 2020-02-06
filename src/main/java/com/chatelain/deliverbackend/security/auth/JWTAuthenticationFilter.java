package com.chatelain.deliverbackend.security.auth;

import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.chatelain.deliverbackend.security.Constant.*;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
 
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
 
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("JWTAuthenticationFilter");
        String header = request.getHeader(JWT_HEAD);
 
        if (header == null || !header.startsWith(JWT_HEAD_START)) {
            chain.doFilter(request, response);
            return;
        }
        OpenidToken token = parse(request);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }
 
    private OpenidToken parse(HttpServletRequest request) {
        String token = request.getHeader(JWT_HEAD);
        if (token != null) {
            String openid = Jwts.parser()
                    .setSigningKey(JWT_SECRET)
                    .parseClaimsJws(token.replace(JWT_HEAD_START, ""))
                    .getBody()
                    .getSubject();
 
            if (openid != null) {
                return new OpenidToken(openid);
            }
            return null;
        }
        return null;
    }
 
}