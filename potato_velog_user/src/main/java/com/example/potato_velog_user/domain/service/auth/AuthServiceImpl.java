package com.example.potato_velog_user.domain.service.auth;

import com.example.potato_velog_user.domain.entity.User;
import com.example.potato_velog_user.domain.repository.AuthRepository;
import com.example.potato_velog_user.exception.NotFoundException;
import com.example.potato_velog_user.security.jwt.JwtFilter;
import com.example.potato_velog_user.security.jwt.TokenProvider;
import com.example.potato_velog_user.utils.TokenDto;
import com.example.potato_velog_user.utils.error.ErrorCode;
import com.example.potato_velog_user.web.dto.auth.request.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenProvider tokenProvider;
    private final AuthRepository authRepository;

    @Override
    public TokenDto login(LoginRequest loginRequest) throws NotFoundException {

        User findUser = authRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
        String jwt = tokenProvider.createToken(findUser.getUserUUId());

        return TokenDto.of(jwt);
    }

    @Override
    public HttpHeaders addHeaders(TokenDto tokenDto) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, tokenDto.getToken());
        return httpHeaders;
    }

    @Override
    public String validateToken(String token) {
        return tokenProvider.validateTokenAndGetUserId(token);
    }


}
