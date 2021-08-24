package com.example.potato_velog_user.exception;


import com.example.potato_velog_user.utils.error.ErrorCode;

public class NotFoundException extends BusinessException{
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
