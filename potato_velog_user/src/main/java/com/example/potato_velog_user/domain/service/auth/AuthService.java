package com.example.potato_velog_user.domain.service.auth;

import com.example.potato_velog_user.exception.NotFoundException;
import com.example.potato_velog_user.utils.TokenDto;
import com.example.potato_velog_user.web.dto.auth.request.LoginRequest;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

public interface AuthService {

    TokenDto login(LoginRequest loginRequest) throws IOException, NotFoundException;

    HttpHeaders addHeaders(TokenDto tokenDto);

    String validateToken(String token);
}
