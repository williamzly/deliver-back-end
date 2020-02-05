package com.chatelain.deliverbackend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Account {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer roleType;

    private String openid;

    private String mobile;
}
