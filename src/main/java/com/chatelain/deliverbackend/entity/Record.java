package com.chatelain.deliverbackend.entity;

import com.chatelain.deliverbackend.enums.DemandStatus;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
public class Record {

    @Id
    private Integer id;

    private DemandStatus changedStatus;

    private Date time;

    @OneToOne
    private Account user;

    @OneToOne
    private Account worker;

    @OneToOne
    private Demand demand;
}
