package com.chatelain.deliverbackend.repository;

import com.chatelain.deliverbackend.entity.Demand;
import com.chatelain.deliverbackend.enums.DemandStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandRepository extends JpaRepository<Demand, Integer> {

    List<Demand> findAllByDemandStatus(DemandStatus demandStatus);

}
