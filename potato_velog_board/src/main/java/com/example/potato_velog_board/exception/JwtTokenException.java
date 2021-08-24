package com.example.potato_velog_board.exception;


import com.example.potato_velog_board.utils.error.ErrorCode;

public class JwtTokenException extends BusinessException{

    public JwtTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
