package com.velog.veloguser.web.controller;

import com.velog.veloguser.service.auth.AuthService;
import com.velog.veloguser.web.dto.request.LoginRequest;
import com.velog.veloguser.web.dto.response.TokenResponse;
import com.velog.veloguser.web.validation.ValidationUtils;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) throws BindException, NotFoundException, IOException {
        ValidationUtils.validateBindingResult(bindingResult);
        TokenResponse tokenResponse = authService.login(loginRequest);
        HttpHeaders httpHeaders = authService.addHeaders(tokenResponse);
        return new ResponseEntity<>(tokenResponse, httpHeaders, HttpStatus.OK);
    }

}
