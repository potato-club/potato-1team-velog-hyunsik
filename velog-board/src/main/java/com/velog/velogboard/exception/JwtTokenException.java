package com.velog.velogboard.exception;

public class JwtTokenException extends BusinessException{
    public JwtTokenException(String message) {
        super(message);
    }
}