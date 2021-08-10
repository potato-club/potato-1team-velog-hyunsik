package com.velog.velogauth.service;

import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.user.dto.request.LoginRequest;
import com.velog.velogcommon.utils.TokenDto;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

public interface AuthService {

    TokenDto login(LoginRequest loginRequest) throws IOException, NotFoundException;

    HttpHeaders addHeaders(TokenDto tokenDto);

    Long validateToken(String token);
}
