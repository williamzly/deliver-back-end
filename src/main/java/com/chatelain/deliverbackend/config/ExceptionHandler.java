package com.chatelain.deliverbackend.config;

import com.chatelain.deliverbackend.dto.response.ResponseDTO;
import com.chatelain.deliverbackend.dto.response.SingleResponseDTO;
import com.chatelain.deliverbackend.exception.BusinessException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ResponseDTO businessExceptionHandler(Exception e) {
        return new SingleResponseDTO(400, e.getMessage());
    }
}
