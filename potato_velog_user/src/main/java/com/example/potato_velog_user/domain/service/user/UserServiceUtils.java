package com.example.potato_velog_user.domain.service.user;

import com.example.potato_velog_user.domain.entity.User;
import com.example.potato_velog_user.domain.entity.UserInfo;
import com.example.potato_velog_user.domain.entity.UserSocialInfo;
import com.example.potato_velog_user.domain.repository.UserRepository;
import com.example.potato_velog_user.exception.AlreadyExistException;
import com.example.potato_velog_user.utils.error.ErrorCode;
import com.example.potato_velog_user.web.dto.user.request.SocialInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserInfoRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceUtils {

    public static String encodePassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.encode(password);
    }

    public static void validateExistEmail(UserRepository userRepository, String email) throws AlreadyExistException {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new AlreadyExistException(ErrorCode.ALREADY_EXIST_EXCEPTION_USER);
        }
    }


    public static void validateExistNickName(UserRepository userRepository, String nickName) {
        if (userRepository.findByNickName(nickName).isPresent()) {
            throw new AlreadyExistException(ErrorCode.ALREADY_EXIST_EXCEPTION_NICKNAME);
        }
    }

    public static UserInfo updateUserInfo(UserRepository userRepository, UserInfo userInfo, UserInfoRequest request) {
        userInfo.update(request);
        return userRepository.save(userInfo.getUser()).getUserInfo();
    }

    public static User updateNameAndIntroduce(UserRepository userRepository, User findUser, String name, String introduce) {
        User changedUser = User.updateNameAndIntroduce(findUser, name, introduce);
        User savedUser = userRepository.save(changedUser);
        return savedUser;
    }


    public static UserSocialInfo updateSocialInfo(UserRepository userRepository, UserSocialInfo userSocialInfo, SocialInfoRequest request) {
        userSocialInfo.update(request);
        return userRepository.save(userSocialInfo.getUserInfo().getUser()).getUserInfo().getUserSocialInfo();

    }


}
