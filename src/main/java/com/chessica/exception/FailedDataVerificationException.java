package com.chessica.exception;

public class FailedDataVerificationException extends RuntimeException {
    public FailedDataVerificationException(String message) {
        super(message);
    }
}
