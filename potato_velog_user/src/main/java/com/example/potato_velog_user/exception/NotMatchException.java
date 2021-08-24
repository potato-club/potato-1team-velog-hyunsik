package com.example.potato_velog_user.exception;


import com.example.potato_velog_user.utils.error.ErrorCode;

public class NotMatchException extends BusinessException{


    public NotMatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}
