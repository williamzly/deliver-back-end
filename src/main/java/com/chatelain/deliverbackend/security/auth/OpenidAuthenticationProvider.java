package com.chatelain.deliverbackend.security.auth;

import com.chatelain.deliverbackend.entity.Account;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class OpenidAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        OpenidToken openidToken = (OpenidToken) authentication;
        String openid = (String) openidToken.getPrincipal();
        System.out.println(openid);
        // TODO need to find User
        openidToken.setAuthenticated(true);
        Account account = new Account();
        account.setOpenid(openid);
        openidToken.setDetails(account);
        return openidToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == OpenidToken.class;
    }
}
