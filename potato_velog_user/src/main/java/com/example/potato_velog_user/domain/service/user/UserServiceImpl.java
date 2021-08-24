package com.example.potato_velog_user.domain.service.user;

import com.example.potato_velog_user.domain.entity.User;
import com.example.potato_velog_user.domain.entity.UserInfo;
import com.example.potato_velog_user.domain.entity.UserSocialInfo;
import com.example.potato_velog_user.domain.repository.UserRepository;
import com.example.potato_velog_user.exception.AlreadyExistException;
import com.example.potato_velog_user.exception.NotFoundException;
import com.example.potato_velog_user.utils.error.ErrorCode;
import com.example.potato_velog_user.web.client.BoardServiceClient;
import com.example.potato_velog_user.web.dto.user.request.SocialInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final BoardServiceClient boardServiceClient;


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


    @Transactional
    @Override
    public UserInfo updateUserInfo(UserInfoRequest request, String uuId) {
        User findUser = userRepository.findByUserUUId(uuId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
        return UserServiceUtils.updateUserInfo(userRepository, findUser, request);
    }

    @Transactional
    @Override
    public User updateNameAndIntroduce(UserRequest.UpdateNameAndIntroduce request, String uuId) throws NotFoundException {
        User findUser = userRepository.findByUserUUId(uuId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
        return UserServiceUtils.updateNameAndIntroduce(userRepository, findUser, request.getName(), request.getIntroduce());
    }


    @Transactional
    @Override
    public UserSocialInfo updateSocialInfo(SocialInfoRequest request, String uuId) {
        User findUser = userRepository.findByUserUUId(uuId).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
        return UserServiceUtils.updateSocialInfo(userRepository, findUser.getUserInfo().getUserSocialInfo(), request);
    }


}
