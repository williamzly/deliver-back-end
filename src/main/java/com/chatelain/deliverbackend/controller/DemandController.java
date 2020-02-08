package com.chatelain.deliverbackend.controller;

import com.chatelain.deliverbackend.dto.request.DemandFormDTO;
import com.chatelain.deliverbackend.dto.request.GetDemandsDTO;
import com.chatelain.deliverbackend.dto.response.ArrayResponseDTO;
import com.chatelain.deliverbackend.dto.response.ResponseDTO;
import com.chatelain.deliverbackend.dto.response.SingleResponseDTO;
import com.chatelain.deliverbackend.entity.Account;
import com.chatelain.deliverbackend.entity.Demand;
import com.chatelain.deliverbackend.enums.DemandStatus;
import com.chatelain.deliverbackend.security.auth.IdToken;
import com.chatelain.deliverbackend.service.DemandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/demands")
public class DemandController {

    @Autowired
    private DemandService demandService;

    @GetMapping
    public ResponseDTO getDemands(@Validated GetDemandsDTO getDemandsDTO, @ApiIgnore IdToken principal) {
        Account account = (Account) principal.getDetails();
        List<Demand> demands = demandService.getDemandsByStatus(getDemandsDTO.getDemandStatus());
        return new ArrayResponseDTO(demands);
    }

    @PostMapping
    public ResponseDTO createDemand(@Validated @RequestBody DemandFormDTO form, @ApiIgnore IdToken principal) {
        Account account = (Account) principal.getDetails();
        Demand toCreate = new Demand();
        BeanUtils.copyProperties(form, toCreate);
        Demand saved = demandService.createDemand(toCreate, account);
        return new SingleResponseDTO(saved);
    }

    @PatchMapping("/{id}")
    public ResponseDTO updateDemandStatus(@PathVariable Integer id, @RequestBody DemandStatus forUpdate, @ApiIgnore IdToken principal) {
        Account account = (Account) principal.getDetails();
        Demand saved = demandService.updateDemandStatus(id, forUpdate, account);
        return new SingleResponseDTO(saved);
    }

}
