package com.velog.velogboard.service;

import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.utils.error.ErrorCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardServiceUtils {

    public static void validateUserId(String userId, String findUserId) throws NotFoundException {
        if (userId != findUserId) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER);
        }
    }
}
