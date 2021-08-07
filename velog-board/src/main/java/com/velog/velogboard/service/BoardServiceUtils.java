package com.velog.velogboard.service;

import com.velog.velogcommon.exception.JwtTokenException;
import javassist.NotFoundException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardServiceUtils {

    public static void validateUserId(String userId, String findUserId) throws NotFoundException {
        if (userId != findUserId) {
            throw new NotFoundException("유저의 고유 아이디값이 잘못되었습니다.");
        }
    }
}
