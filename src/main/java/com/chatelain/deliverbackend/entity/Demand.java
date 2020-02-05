package com.chatelain.deliverbackend.entity;

import com.chatelain.deliverbackend.enums.DemandStatus;
import com.chatelain.deliverbackend.enums.DemandType;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
public class Demand {

    @Id
    @GeneratedValue
    private Integer id;

    private DemandType demandType;

    private String username;

    private String address;

    private String contact;

    private String detail;

    private DemandStatus demandStatus;

    private Date createTime;

    @OneToOne
    private Account creator;

}
