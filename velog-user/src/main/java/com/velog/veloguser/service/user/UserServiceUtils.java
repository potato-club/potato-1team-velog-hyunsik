package com.velog.veloguser.service.user;

import com.velog.velogcommon.exception.AlreadyExistException;
import com.velog.velogcommon.user.dto.request.SocialInfoRequest;
import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.user.entity.UserInfo;
import com.velog.velogcommon.user.entity.UserSocialInfo;
import com.velog.velogcommon.user.repository.UserRepository;
import com.velog.velogcommon.utils.error.ErrorCode;
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
