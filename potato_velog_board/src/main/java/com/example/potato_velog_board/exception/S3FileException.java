package com.example.potato_velog_board.exception;


import com.example.potato_velog_board.utils.error.ErrorCode;

public class S3FileException extends BusinessException{
    public S3FileException(ErrorCode errorCode) {
        super(errorCode);
    }
}
