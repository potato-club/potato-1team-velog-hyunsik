package com.example.potato_velog_user.exception;


import com.example.potato_velog_user.utils.error.ErrorCode;

public class JwtTokenException extends BusinessException{

    public JwtTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
