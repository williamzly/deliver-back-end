package com.chatelain.deliverbackend.entity;

import com.chatelain.deliverbackend.enums.DemandStatus;
import com.chatelain.deliverbackend.enums.DemandType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Demand {

    @Id
    private Integer id;

    private DemandType demandType;

    private String username;

    private String address;

    private String contact;

    private String detail;

    private DemandStatus demandStatus;

}
