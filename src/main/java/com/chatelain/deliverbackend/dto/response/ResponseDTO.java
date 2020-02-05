package com.chatelain.deliverbackend.dto.response;

import lombok.Data;

@Data
public abstract class ResponseDTO {

    private Integer code;

    private String message;

    public static final Integer SUCCESS_CODE = 200;


    protected static final Integer DEFAULT_CODE = SUCCESS_CODE;
    protected static final String DEFAULT_MESSAGE = "";


    ResponseDTO() {
        this.code = DEFAULT_CODE;
        this.message = DEFAULT_MESSAGE;
    }

    ResponseDTO(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
