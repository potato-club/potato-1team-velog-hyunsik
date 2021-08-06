package com.velog.veloguser.service.auth;

import com.velog.veloguser.security.PrincipalDetails;
import com.velog.veloguser.web.dto.request.LoginRequest;
import com.velog.veloguser.web.dto.response.TokenResponse;
import com.velog.veloguser.web.dto.response.UserIdResponse;
import javassist.NotFoundException;

import java.io.IOException;

public interface AuthService {

    TokenResponse login(LoginRequest loginRequest) throws IOException, NotFoundException;

}
