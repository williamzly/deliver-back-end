package com.chatelain.deliverbackend.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AuthorizationForm {

    @NotBlank
    private String iv;

    @NotBlank
    private String encryptedData;

}
