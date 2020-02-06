package com.chatelain.deliverbackend.security.login;

import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;

public class WXCodeToken extends AbstractAuthenticationToken {

    @Getter
    private String wxCode;

    public WXCodeToken(String wxCode) {
        super(null);
        this.wxCode = wxCode;
    }

    @Override
    public Object getCredentials() {
        return this.wxCode;
    }

    @Override
    public Object getPrincipal() {
        return this.wxCode;
    }
}
