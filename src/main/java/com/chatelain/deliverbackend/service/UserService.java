package com.chatelain.deliverbackend.service;

import com.chatelain.deliverbackend.dto.request.AuthorizationForm;
import com.chatelain.deliverbackend.entity.Account;

public interface UserService {

    Account getAccountById(Integer id);

    Account updateOrCreateAccountByOpenidAndSessionKey(String openid, String sessionKey);

    Account authorize(Account account, AuthorizationForm authorizationForm);
}
