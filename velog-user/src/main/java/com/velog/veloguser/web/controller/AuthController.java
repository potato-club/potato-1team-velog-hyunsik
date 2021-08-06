package com.velog.veloguser.web.controller;

import com.velog.veloguser.security.PrincipalDetails;
import com.velog.veloguser.service.auth.AuthService;
import com.velog.veloguser.web.dto.request.LoginRequest;
import com.velog.veloguser.web.dto.response.TokenResponse;
import com.velog.veloguser.web.dto.response.UserIdResponse;
import com.velog.veloguser.web.validation.ValidationUtils;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("authenticate")
    public TokenResponse login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) throws BindException, NotFoundException, IOException {
        ValidationUtils.validateBindingResult(bindingResult);
        return authService.login(loginRequest);
    }

    @GetMapping("getUserId")
    public UserIdResponse getUserId(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        return authService.getUserId(principalDetails);
    }
}
