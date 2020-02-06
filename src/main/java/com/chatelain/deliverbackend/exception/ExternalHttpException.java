package com.chatelain.deliverbackend.exception;

public class ExternalHttpException extends RuntimeException {

    public ExternalHttpException(String message) {
        super(message);
    }
}
