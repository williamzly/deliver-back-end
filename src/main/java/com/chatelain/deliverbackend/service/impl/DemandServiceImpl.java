package com.chatelain.deliverbackend.service.impl;

import com.chatelain.deliverbackend.entity.Account;
import com.chatelain.deliverbackend.entity.Demand;
import com.chatelain.deliverbackend.entity.Record;
import com.chatelain.deliverbackend.enums.DemandStatus;
import com.chatelain.deliverbackend.exception.BusinessException;
import com.chatelain.deliverbackend.repository.DemandRepository;
import com.chatelain.deliverbackend.repository.RecordRepository;
import com.chatelain.deliverbackend.service.DemandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DemandServiceImpl implements DemandService {

    @Autowired
    private DemandRepository demandRepository;

    @Autowired
    private RecordRepository recordRepository;

    @Override
    public List<Demand> getDemandsByStatus(DemandStatus demandStatus) {
        return demandRepository.findAllByDemandStatus(demandStatus);
    }

    @Override
    public Demand createDemand(Demand toCreate, Account creator) {
        Date now = new Date();

        toCreate.setDemandStatus(DemandStatus.PENDING);
        toCreate.setCreateTime(now);
        toCreate.setCreator(creator);
        Demand saved = demandRepository.save(toCreate);

        Record record = generateRecord(DemandStatus.PENDING, creator, null, saved, now);
        recordRepository.save(record);
        return saved;
    }

    @Override
    public Demand updateDemandStatus(Integer demandId, DemandStatus forUpdate, Account worker) {
        Optional<Demand> optional = demandRepository.findById(demandId);
        if(optional.isPresent()) {
            Demand toUpdate = optional.get();
            if(!forUpdate.isBehind(toUpdate.getDemandStatus())) {
                throw new BusinessException("invalid status");
            }
            toUpdate.setDemandStatus(forUpdate);

            Record record = generateRecord(forUpdate, toUpdate.getCreator(), worker, toUpdate, new Date());
            recordRepository.save(record);

            return toUpdate;
        }
        throw new BusinessException("there is no such demand");
    }


    private Record generateRecord(DemandStatus changedStatus, Account user, Account worker, Demand demand, Date now) {
        Record record = new Record();
        record.setDemand(demand);
        record.setChangedStatus(changedStatus);
        record.setUser(user);
        record.setWorker(worker);
        record.setTime(now);
        return record;
    }

}
