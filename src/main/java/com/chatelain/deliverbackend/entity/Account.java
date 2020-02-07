package com.chatelain.deliverbackend.entity;

import com.chatelain.deliverbackend.enums.RoleType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private Integer id;

    private RoleType roleType;

    private String openid;

    private String name;

    private String room;

    private String village;

    private String community;

    private String contact;

    private String lastSessionKey;

    private Date firstLogin;

    private Date lastLogin;
}
