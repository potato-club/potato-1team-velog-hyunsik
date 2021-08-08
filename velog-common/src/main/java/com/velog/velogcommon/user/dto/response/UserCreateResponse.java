package com.velog.velogcommon.user.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserCreateResponse {

    private String email;
    private String name;
    private String nickName;
    private String introduce;

    @Builder
    public UserCreateResponse(String email, String name, String nickName, String introduce) {
        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.introduce = introduce;
    }

    public static UserCreateResponse of(String email, String name, String nickName, String introduce) {
        return new UserCreateResponse().builder()
                .email(email)
                .name(name)
                .nickName(nickName)
                .introduce(introduce)
                .build();
    }
}
