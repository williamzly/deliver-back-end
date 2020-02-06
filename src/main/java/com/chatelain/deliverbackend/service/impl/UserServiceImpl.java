package com.chatelain.deliverbackend.service.impl;

import com.chatelain.deliverbackend.entity.Account;
import com.chatelain.deliverbackend.enums.RoleType;
import com.chatelain.deliverbackend.repository.UserRepository;
import com.chatelain.deliverbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Account getOrCreateAccountByOpenid(String openid) {

        Account found = userRepository.findAccountByOpenid(openid);
        if(Objects.nonNull(found)) {
            return found;
        }else {
            Account toCreate = new Account();
            toCreate.setOpenid(openid);
            toCreate.setRoleType(RoleType.VISITOR);
            toCreate.setFirstLogin(new Date());
            return userRepository.save(toCreate);
        }
    }

    @Override
    public Account getAccountById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
