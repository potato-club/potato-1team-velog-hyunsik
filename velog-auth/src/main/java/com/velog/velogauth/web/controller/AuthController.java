package com.velog.velogauth.web.controller;

import com.velog.velogauth.service.AuthService;
import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.user.dto.request.LoginRequest;
import com.velog.velogcommon.utils.TokenDto;
import com.velog.velogcommon.utils.validation.ValidationUtils;
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
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) throws BindException, NotFoundException, IOException {
        ValidationUtils.validateBindingResult(bindingResult);
        TokenDto tokenDto = authService.login(loginRequest);
        HttpHeaders httpHeaders = authService.addHeaders(tokenDto);
        return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("validateToken")
    public ResponseEntity<Long> validateToken(@RequestBody String token) {
        return ResponseEntity.ok(authService.validateToken(token));
    }

}
