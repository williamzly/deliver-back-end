package com.chatelain.deliverbackend.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Account {

    @Id
    private Integer id;

    private Integer roleType;

    private String openid;

    private String mobile;
}
