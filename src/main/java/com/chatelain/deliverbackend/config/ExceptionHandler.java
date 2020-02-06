package com.chatelain.deliverbackend.config;

import com.chatelain.deliverbackend.dto.response.ResponseDTO;
import com.chatelain.deliverbackend.dto.response.SingleResponseDTO;
import com.chatelain.deliverbackend.exception.BusinessException;
import com.chatelain.deliverbackend.exception.ExternalHttpException;
import com.chatelain.deliverbackend.exception.InternalHttpException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(BusinessException.class)
    public ResponseDTO businessExceptionHandler(Exception e) {
        return new SingleResponseDTO(400, e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler({InternalHttpException.class, ExternalHttpException.class})
    public ResponseDTO httpExceptionHandler(Exception e) {
        return new SingleResponseDTO(500, e.getMessage());
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AuthenticationException.class)
    public ResponseDTO authExceptionHandler(Exception e){
        return new SingleResponseDTO(403, e.getMessage());
    }

}
