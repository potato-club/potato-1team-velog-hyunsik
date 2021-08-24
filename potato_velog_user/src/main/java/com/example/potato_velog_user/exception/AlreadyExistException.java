package com.example.potato_velog_user.exception;


import com.example.potato_velog_user.utils.error.ErrorCode;

public class AlreadyExistException extends BusinessException {


    public AlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
