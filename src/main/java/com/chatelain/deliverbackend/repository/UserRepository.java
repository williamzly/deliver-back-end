package com.chatelain.deliverbackend.repository;

import com.chatelain.deliverbackend.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Account, Integer> {

    Account findAccountByOpenid(String openid);

}
