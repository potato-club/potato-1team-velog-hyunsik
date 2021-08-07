package com.velog.velogcommon.user.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserIdResponse {

    private String userId;

    public UserIdResponse(String userId) {
        this.userId = userId;
    }

    public static UserIdResponse of(String userId) {
       return new UserIdResponse(userId);
    }
}
