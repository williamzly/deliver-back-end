package com.chatelain.deliverbackend.security.auth;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
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
        String header = request.getHeader(JWT_HEAD);
 
        if (header == null || !header.startsWith(JWT_HEAD_START)) {
            chain.doFilter(request, response);
            return;
        }
        IdToken token = parse(request);
        SecurityContextHolder.getContext().setAuthentication(token);
        chain.doFilter(request, response);
    }
 
    private IdToken parse(HttpServletRequest request) {
        String token = request.getHeader(JWT_HEAD);
        if (token != null) {
            String idStr = null;
            try {
                idStr = Jwts.parser()
                        .setSigningKey(JWT_SECRET)
                        .parseClaimsJws(token.replace(JWT_HEAD_START, ""))
                        .getBody()
                        .getSubject();
            } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
                throw new AuthenticationServiceException("wrong token" ,e);
            }

            if (idStr != null) {
                return new IdToken(Integer.parseInt(idStr));
            }
            return null;
        }
        return null;
    }
 
}