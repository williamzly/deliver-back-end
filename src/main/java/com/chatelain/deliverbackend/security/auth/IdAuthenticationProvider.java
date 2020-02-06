package com.chatelain.deliverbackend.security.auth;

import com.chatelain.deliverbackend.entity.Account;
import com.chatelain.deliverbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class IdAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        IdToken idToken = (IdToken) authentication;
        Integer id = (Integer) idToken.getPrincipal();

        Account account = userService.getAccountById(id);
        if(Objects.isNull(account)) {
            throw new UsernameNotFoundException("wrong account id");
        }
        idToken.setAuthenticated(true);
        idToken.setDetails(account);
        return idToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == IdToken.class;
    }
}
