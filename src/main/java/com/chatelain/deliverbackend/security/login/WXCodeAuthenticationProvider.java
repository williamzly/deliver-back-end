package com.chatelain.deliverbackend.security.login;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class WXCodeAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WXCodeToken wxCodeToken = (WXCodeToken)authentication;
        System.out.println("WXCodeAuthenticationProvider::wxCode:: "+ wxCodeToken.getWxCode());
        // TODO 补写验证微信小程序code -> openid等数据 如果是不存在的，这创建Account
        wxCodeToken.setAuthenticated(true);
        wxCodeToken.setDetails("zxcvbnm");
        return wxCodeToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == WXCodeToken.class;
    }
}
