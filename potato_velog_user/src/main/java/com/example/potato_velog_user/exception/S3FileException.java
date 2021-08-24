package com.example.potato_velog_user.exception;


import com.example.potato_velog_user.utils.error.ErrorCode;

public class S3FileException extends BusinessException{
    public S3FileException(ErrorCode errorCode) {
        super(errorCode);
    }
}
