package com.velog.veloguser.domain.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserResponse {

    private String email;
    private String name;
    private String userId;

    @Builder
    public UserResponse(String email, String name, String userId) {
        this.email = email;
        this.name = name;
        this.userId = userId;
    }

    public static UserResponse of(String email, String name, String userId) {
        return new UserResponse().builder()
                .email(email)
                .name(name)
                .userId(userId)
                .build();
    }
}
