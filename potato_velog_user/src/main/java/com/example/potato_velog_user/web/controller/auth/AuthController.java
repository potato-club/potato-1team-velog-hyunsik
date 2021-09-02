package com.example.potato_velog_user.web.controller.auth;

import com.example.potato_velog_user.domain.service.auth.AuthService;
import com.example.potato_velog_user.exception.NotFoundException;
import com.example.potato_velog_user.utils.TokenDto;
import com.example.potato_velog_user.utils.validation.ValidationUtils;
import com.example.potato_velog_user.web.dto.auth.request.LoginRequest;
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
    public ResponseEntity<TokenDto> login(@RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult) throws BindException, NotFoundException, IOException {
        ValidationUtils.validateBindingResult(bindingResult);
        TokenDto tokenDto = authService.login(loginRequest);
        HttpHeaders httpHeaders = authService.addHeaders(tokenDto);
        return new ResponseEntity<>(tokenDto, httpHeaders, HttpStatus.OK);
    }

    @PostMapping("validateToken")
    public ResponseEntity<String> validateToken(@RequestBody String token) {
        return ResponseEntity.ok(authService.validateToken(token));
    }





}
