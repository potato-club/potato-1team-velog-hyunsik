package com.velog.veloguser.domain.dto.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest{

    @NotBlank
    @Size(min = 2)
    @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 20)
    private String password;

}
