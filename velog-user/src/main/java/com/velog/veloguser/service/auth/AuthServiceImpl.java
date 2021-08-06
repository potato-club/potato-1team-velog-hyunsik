package com.velog.veloguser.service.auth;

import com.velog.veloguser.domain.entity.User;
import com.velog.veloguser.repository.UserRepository;
import com.velog.veloguser.security.PrincipalDetails;
import com.velog.veloguser.security.jwt.TokenProvider;
import com.velog.veloguser.web.dto.request.LoginRequest;
import com.velog.veloguser.web.dto.response.TokenResponse;
import com.velog.veloguser.web.dto.response.UserIdResponse;
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
    public TokenResponse login(LoginRequest loginRequest) throws NotFoundException {

        User findUser = userRepository.findUserByEmail(loginRequest.getEmail()).orElseThrow(() -> new NotFoundException("해당하는 계정을 찾을 수 없습니다."));
        String jwt = tokenProvider.createToken(findUser.getUserId());

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token", "Bearer " + jwt);

        return TokenResponse.of(jwt);
    }

    @Override
    public UserIdResponse getUserId(PrincipalDetails principalDetails) {
        return UserIdResponse.of(principalDetails.getUserId());
    }
}
