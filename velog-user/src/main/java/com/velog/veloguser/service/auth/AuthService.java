package com.velog.veloguser.service.auth;

import com.velog.velogcommon.user.dto.request.LoginRequest;
import com.velog.velogcommon.utils.TokenDto;
import javassist.NotFoundException;
import org.springframework.http.HttpHeaders;

import java.io.IOException;

public interface AuthService {

    TokenDto login(LoginRequest loginRequest) throws IOException, NotFoundException;

    HttpHeaders addHeaders(TokenDto tokenDto);

    String validateToken(String token);
}
