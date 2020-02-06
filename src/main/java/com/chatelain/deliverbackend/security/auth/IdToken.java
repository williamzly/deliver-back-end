package com.chatelain.deliverbackend.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class IdToken extends AbstractAuthenticationToken {

    private Integer id;

    public IdToken(Integer id) {
        super(null);
        this.id = id;
    }

    @Override
    public Object getCredentials() {
        return this.id;
    }

    @Override
    public Object getPrincipal() {
        return this.id;
    }
}
