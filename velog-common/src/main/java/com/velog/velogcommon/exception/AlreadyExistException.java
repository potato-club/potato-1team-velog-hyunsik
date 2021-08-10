package com.velog.velogcommon.exception;

import com.velog.velogcommon.utils.error.ErrorCode;

public class AlreadyExistException extends BusinessException {


    public AlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
