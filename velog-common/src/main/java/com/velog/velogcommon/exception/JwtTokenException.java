package com.velog.velogcommon.exception;

import com.velog.velogcommon.utils.error.ErrorCode;

public class JwtTokenException extends BusinessException{

    public JwtTokenException(ErrorCode errorCode) {
        super(errorCode);
    }
}
