package com.example.potato_velog_user.domain.service.user;

import com.example.potato_velog_user.domain.entity.User;
import com.example.potato_velog_user.domain.entity.UserImage;
import com.example.potato_velog_user.domain.entity.UserInfo;
import com.example.potato_velog_user.domain.entity.UserSocialInfo;
import com.example.potato_velog_user.domain.repository.user.UserRepository;
import com.example.potato_velog_user.exception.AlreadyExistException;
import com.example.potato_velog_user.utils.error.ErrorCode;
import com.example.potato_velog_user.web.dto.user.request.SocialInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserRequest;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceUtils {

    public static User createUser(UserRequest.Create request, String encodedPassword) {
        final User user = User.createUser(request.getEmail(), request.getName(), encodedPassword, request.getNickName(), request.getIntroduce());
        final UserInfo userInfo = UserInfo.createUserInfo(user.getNickName() + ".log", false, false);
        final UserSocialInfo userSocialInfo = UserSocialInfo.of(null, null, null, null, null);
        final UserImage userImage = UserImage.of(null, null, null);
        userInfo.addUserSocialInfo(userSocialInfo);
        user.addUserInfo(userInfo);
        user.addUserImage(userImage);
        return user;
    }

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

    public static UserInfo updateUserInfo(UserRepository userRepository, User user, UserInfoRequest request) {
        user.getUserInfo().update(request);
        return userRepository.save(user).getUserInfo();
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
