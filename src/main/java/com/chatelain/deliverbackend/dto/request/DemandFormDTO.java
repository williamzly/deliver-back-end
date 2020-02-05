package com.chatelain.deliverbackend.dto.request;

import com.chatelain.deliverbackend.enums.DemandType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
public class DemandFormDTO {

    @NotNull
    private DemandType demandType;

    @NotBlank
    private String username;

    @NotBlank
    private String address;

    @NotBlank
    private String contact;

    @NotBlank
    private String detail;

}
