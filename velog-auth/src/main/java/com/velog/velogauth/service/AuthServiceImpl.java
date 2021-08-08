package com.velog.velogauth.service;

import com.velog.velogauth.security.jwt.JwtFilter;
import com.velog.velogauth.security.jwt.TokenProvider;
import com.velog.velogcommon.user.dto.request.LoginRequest;
import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.user.repository.UserRepository;
import com.velog.velogcommon.utils.TokenDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    public TokenDto login(LoginRequest loginRequest) throws NotFoundException {

        User findUser = userRepository.findUserByEmail(loginRequest.getEmail()).orElseThrow(() -> new NotFoundException("해당하는 계정을 찾을 수 없습니다."));
        String jwt = tokenProvider.createToken(findUser.getId());

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
