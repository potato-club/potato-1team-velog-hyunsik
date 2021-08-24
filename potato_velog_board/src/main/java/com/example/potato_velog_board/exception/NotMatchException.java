package com.example.potato_velog_board.exception;

import com.example.potato_velog_board.utils.error.ErrorCode;

public class NotMatchException extends BusinessException{


    public NotMatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}
