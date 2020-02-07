package com.chatelain.deliverbackend.security.login;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.chatelain.deliverbackend.security.Constant.*;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        super(new AntPathRequestMatcher(LOGIN_PATH, HttpMethod.POST.name()));
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            MiniProgramUser user = new ObjectMapper().readValue(request.getInputStream(), MiniProgramUser.class);
            return authenticationManager.authenticate(new WXCodeToken(user.getCode()));
        } catch (IOException e) {
            throw new InternalAuthenticationServiceException("field: code is missing");
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getDetails() + "")
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
        response.addHeader(JWT_HEAD, JWT_HEAD_START + token);
    }
}
