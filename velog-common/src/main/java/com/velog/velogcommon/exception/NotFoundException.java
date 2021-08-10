package com.velog.velogcommon.exception;

import com.velog.velogcommon.utils.error.ErrorCode;

public class NotFoundException extends BusinessException{
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
