package com.velog.veloguser.service.user;

import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.veloguser.web.client.AuthServiceClient;
import com.velog.veloguser.web.client.BoardServiceClient;
import com.velog.velogcommon.user.dto.request.UserCreateRequest;
import com.velog.velogcommon.user.dto.response.UserCreateResponse;
import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.exception.AlreadyExistException;
import com.velog.velogcommon.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public UserCreateResponse createUser(UserCreateRequest request) throws AlreadyExistException {
        UserServiceUtils.validateExistEmail(userRepository, request.getEmail());
        UserServiceUtils.validateExistNickName(userRepository, request.getNickName());

        String encodedPassword = UserServiceUtils.encodePassword(passwordEncoder, request.getPassword());
        User user = User.createUser(request.getEmail(), request.getName(), encodedPassword, request.getNickName(), request.getIntroduce());

        User savedUser = userRepository.save(user);
        return UserCreateResponse.of(savedUser.getEmail(), savedUser.getName(), savedUser.getNickName(), savedUser.getIntroduce());
    }


    @Override
    public List<BoardResponse> retrieveBoardList(String token) {
        authServiceClient.validateToken(token);
        return boardServiceClient.myBoardList(token);
    }
}
