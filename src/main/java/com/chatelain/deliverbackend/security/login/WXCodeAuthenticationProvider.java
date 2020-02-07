package com.chatelain.deliverbackend.security.login;

import com.chatelain.deliverbackend.entity.Account;
import com.chatelain.deliverbackend.exception.ExternalHttpException;
import com.chatelain.deliverbackend.service.UserService;
import com.chatelain.deliverbackend.utils.wx.Code2SessionResponse;
import com.chatelain.deliverbackend.utils.wx.WXRequestTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class WXCodeAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private WXRequestTool wxRequestTool;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        WXCodeToken wxCodeToken = (WXCodeToken)authentication;

        Code2SessionResponse code2SessionResponse;
        try {
            code2SessionResponse = wxRequestTool.codeToSession(wxCodeToken.getWxCode());
        } catch (ExternalHttpException e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
        String openid = code2SessionResponse.getOpenid();
        if(StringUtils.isEmpty(openid)){
            throw new AuthenticationServiceException("openid is empty");
        }
        Account account = userService.updateOrCreateAccountByOpenidAndSessionKey(openid, code2SessionResponse.getSession_key());
        wxCodeToken.setAuthenticated(true);
        wxCodeToken.setDetails(account.getId());
        return wxCodeToken;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass == WXCodeToken.class;
    }
}
