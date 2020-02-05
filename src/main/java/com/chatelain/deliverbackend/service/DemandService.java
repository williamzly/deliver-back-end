package com.chatelain.deliverbackend.service;

import com.chatelain.deliverbackend.entity.Account;
import com.chatelain.deliverbackend.entity.Demand;
import com.chatelain.deliverbackend.enums.DemandStatus;

import java.util.List;

public interface DemandService {
    List<Demand> getDemandsByStatus(DemandStatus demandStatus);

    Demand createDemand(Demand toCreate, Account creator);

    Demand updateDemandStatus(Integer demandId, DemandStatus forUpdate, Account worker);

}
