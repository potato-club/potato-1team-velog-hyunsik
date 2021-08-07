package com.velog.veloguser.service.user;

import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.veloguser.security.jwt.TokenProvider;
import com.velog.veloguser.web.client.BoardServiceClient;
import com.velog.velogcommon.user.dto.request.UserCreateRequest;
import com.velog.velogcommon.user.dto.response.UserResponse;
import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.exception.AlreadyExistException;
import com.velog.velogcommon.user.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final BoardServiceClient apiClient;


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

    @Override
    public List<BoardResponse> retrieveBoardList(String token) {
       return apiClient.myBoardList(token);
    }
}
