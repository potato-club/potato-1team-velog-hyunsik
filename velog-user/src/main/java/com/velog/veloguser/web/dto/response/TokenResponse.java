package com.velog.veloguser.web.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenResponse {

    private String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public static TokenResponse of(String token) {
        return new TokenResponse(token);
    }
}
