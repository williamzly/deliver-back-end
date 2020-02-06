package com.chatelain.deliverbackend.exception;

public class InternalHttpException extends RuntimeException {

    public InternalHttpException(String message) {
        super(message);
    }
}
