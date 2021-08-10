package com.velog.velogcommon.exception;

import com.velog.velogcommon.utils.error.ErrorCode;

public class NotMatchException extends BusinessException{


    public NotMatchException(ErrorCode errorCode) {
        super(errorCode);
    }
}
