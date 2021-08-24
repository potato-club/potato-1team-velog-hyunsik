package com.example.potato_velog_board.exception;


import com.example.potato_velog_board.utils.error.ErrorCode;

public class AlreadyExistException extends BusinessException {


    public AlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
