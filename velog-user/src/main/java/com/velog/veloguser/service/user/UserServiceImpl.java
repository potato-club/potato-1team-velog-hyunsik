package com.velog.veloguser.service.user;

import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.veloguser.web.client.AuthServiceClient;
import com.velog.veloguser.web.client.BoardServiceClient;
import com.velog.velogcommon.user.dto.request.UserCreateRequest;
import com.velog.velogcommon.user.dto.response.UserResponse;
import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.exception.AlreadyExistException;
import com.velog.velogcommon.user.repository.UserRepository;
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
    private final BoardServiceClient boardServiceClient;
    private final AuthServiceClient authServiceClient;


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
    public List<BoardResponse> retrieveBoardList(String token) {
        authServiceClient.validateToken(token);
        return boardServiceClient.myBoardList(token);
    }
}
