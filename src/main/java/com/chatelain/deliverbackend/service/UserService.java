package com.chatelain.deliverbackend.service;

import com.chatelain.deliverbackend.entity.Account;

public interface UserService {

    Account getOrCreateAccountByOpenid(String openid);

    Account getAccountById(Integer id);
}
