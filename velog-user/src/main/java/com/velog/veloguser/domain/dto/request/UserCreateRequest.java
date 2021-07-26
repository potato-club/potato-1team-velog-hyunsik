package com.velog.veloguser.domain.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserCreateRequest {

    @NotBlank
    @Size(min = 2)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

    @NotBlank
    @Size(min = 2)
    private String name;


    //테스트 코드 작성용
    @Builder
    public UserCreateRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    //테스트 코드 작성용
    public static UserCreateRequest of(String email, String password, String name) {
        return new UserCreateRequest().builder()
                .email(email)
                .password(password)
                .name(name)
                .build();
    }
}
