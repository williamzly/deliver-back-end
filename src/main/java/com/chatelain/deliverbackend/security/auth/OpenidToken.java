package com.chatelain.deliverbackend.security.auth;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class OpenidToken extends AbstractAuthenticationToken {

    private String openid;

    public OpenidToken(String openid) {
        super(null);
        this.openid = openid;
    }

    @Override
    public Object getCredentials() {
        return this.openid;
    }

    @Override
    public Object getPrincipal() {
        return this.openid;
    }
}
