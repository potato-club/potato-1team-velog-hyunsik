package com.example.potato_velog_board.exception;


import com.example.potato_velog_board.utils.error.ErrorCode;

public class NotFoundException extends BusinessException{
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
