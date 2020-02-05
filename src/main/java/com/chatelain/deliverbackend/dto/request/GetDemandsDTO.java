package com.chatelain.deliverbackend.dto.request;

import com.chatelain.deliverbackend.enums.DemandStatus;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class GetDemandsDTO {

    @NotNull
    private DemandStatus demandStatus;

    private String location;
}
