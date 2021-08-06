package com.velog.veloguser.web.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    @NotBlank
    @Size(min = 2)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

}
