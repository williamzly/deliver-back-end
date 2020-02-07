package com.chatelain.deliverbackend.service.impl;

import com.chatelain.deliverbackend.dto.request.AuthorizationForm;
import com.chatelain.deliverbackend.entity.Account;
import com.chatelain.deliverbackend.enums.RoleType;
import com.chatelain.deliverbackend.exception.ExternalHttpException;
import com.chatelain.deliverbackend.repository.UserRepository;
import com.chatelain.deliverbackend.service.UserService;
import com.chatelain.deliverbackend.utils.wx.AESDecoder;
import com.chatelain.deliverbackend.utils.wx.DecodedMobileJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import javax.crypto.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Objects;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AESDecoder decoder;

    @Override
    public Account updateOrCreateAccountByOpenidAndSessionKey(String openid, String sessionKey) {

        Account found = userRepository.findAccountByOpenid(openid);
        Date now = new Date();
        if(Objects.nonNull(found)) {
            found.setLastLogin(now);
            found.setLastSessionKey(sessionKey);
            return found;
        }else {
            Account toCreate = new Account();
            toCreate.setOpenid(openid);
            toCreate.setRoleType(RoleType.VISITOR);
            toCreate.setFirstLogin(now);
            toCreate.setLastSessionKey(sessionKey);
            return userRepository.save(toCreate);
        }
    }

    @Override
    public Account authorize(Account account, AuthorizationForm authorizationForm) {
        DecodedMobileJson mobileJson;
        try {
            mobileJson = decoder.decode(account.getLastSessionKey(), authorizationForm.getIv(), authorizationForm.getEncryptedData());
        } catch (Exception e) {
            throw new ExternalHttpException("wrong params for decoding");
        }
        String phoneNumber = mobileJson.getPhoneNumber();
        account.setContact(phoneNumber);
        // TODO call remote method to set account new role
        userRepository.findById(account.getId()).ifPresent(found -> {
            found.setContact(phoneNumber);
        });
        return account;
    }

    @Override
    public Account getAccountById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
