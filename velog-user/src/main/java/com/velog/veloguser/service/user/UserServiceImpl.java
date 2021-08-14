package com.velog.veloguser.service.user;

import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.user.dto.request.SocialInfoRequest;
import com.velog.velogcommon.user.entity.UserImage;
import com.velog.velogcommon.user.entity.UserInfo;
import com.velog.velogcommon.user.entity.UserSocialInfo;
import com.velog.velogcommon.utils.error.ErrorCode;
import com.velog.veloguser.web.client.AuthServiceClient;
import com.velog.veloguser.web.client.BoardServiceClient;
import com.velog.velogcommon.user.dto.request.UserRequest;
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
    public User createUser(UserRequest.Create request) throws AlreadyExistException {
        UserServiceUtils.validateExistEmail(userRepository, request.getEmail());
        UserServiceUtils.validateExistNickName(userRepository, request.getNickName());

        String encodedPassword = UserServiceUtils.encodePassword(passwordEncoder, request.getPassword());
        User user = User.createUser(request.getEmail(), request.getName(), encodedPassword, request.getNickName(), request.getIntroduce());
        UserInfo userInfo = UserInfo.createUserInfo(user.getNickName() + ".log", false, false);
        UserSocialInfo userSocialInfo = UserSocialInfo.of("null", "null", "null", "null", "null");
        userInfo.addUserSocialInfo(userSocialInfo);
        user.addUserInfo(userInfo);

        return userRepository.save(user);

    }


    @Override
    public List<BoardResponse> retrieveBoardList(String token) {
        authServiceClient.validateToken(token);
        return boardServiceClient.myBoardList(token);
    }

    @Transactional
    @Override
    public User updateNameAndIntroduce(UserRequest.UpdateNameAndIntroduce request, Long userId) throws NotFoundException {
        User findUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
        return UserServiceUtils.updateNameAndIntroduce(userRepository, findUser, request.getName(), request.getIntroduce());
    }


    @Transactional
    @Override
    public UserSocialInfo updateSocialInfo(SocialInfoRequest request, Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
        return UserServiceUtils.updateSocialInfo(userRepository, findUser.getUserInfo().getUserSocialInfo(), request);
    }
}
