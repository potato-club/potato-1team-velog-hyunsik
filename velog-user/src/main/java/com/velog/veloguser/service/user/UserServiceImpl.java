package com.velog.veloguser.service.user;

import com.velog.veloguser.security.PrincipalDetails;
import com.velog.veloguser.security.jwt.TokenProvider;
import com.velog.veloguser.web.dto.request.UserCreateRequest;
import com.velog.veloguser.web.dto.response.UserIdResponse;
import com.velog.veloguser.web.dto.response.UserResponse;
import com.velog.veloguser.domain.entity.User;
import com.velog.veloguser.exception.AlreadyExistException;
import com.velog.veloguser.repository.UserRepository;
import io.jsonwebtoken.Claims;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;


    @Transactional
    @Override
    public UserResponse createUser(UserCreateRequest request) throws AlreadyExistException {
        UserServiceUtils.validateExistEmail(userRepository, request.getEmail());

        String encodedPassword = UserServiceUtils.encodePassword(passwordEncoder, request.getPassword());
        User user = User.of(request.getEmail(), request.getName(), UUID.randomUUID().toString(), encodedPassword);

        User savedUser = userRepository.save(user);
        return UserResponse.of(savedUser.getEmail(), savedUser.getName(), savedUser.getUserId());
    }

    @Override
    public String getUserId(String token) {
        return tokenProvider.getToken(token).getSubject();
    }

    @Override
    public void validateUserId(String userId) throws NotFoundException {
        if (userRepository.findUserByUserId(userId).isEmpty()) {
            throw new NotFoundException("존재 하지 않는 계정입니다.");
        }
    }
}
