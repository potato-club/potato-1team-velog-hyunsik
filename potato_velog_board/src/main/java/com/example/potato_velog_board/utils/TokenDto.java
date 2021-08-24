package com.example.potato_velog_board.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {

    private String token;

    public TokenDto(String token) {
        this.token = token;
    }

    public static TokenDto of(String token) {
        return new TokenDto(token);
    }
}
