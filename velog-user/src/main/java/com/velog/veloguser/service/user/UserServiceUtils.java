package com.velog.veloguser.service.user;

import com.velog.velogcommon.exception.AlreadyExistException;
import com.velog.velogcommon.user.repository.UserRepository;
import com.velog.velogcommon.utils.error.ErrorCode;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceUtils {

    public static String encodePassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.encode(password);
    }

    public static void validateExistEmail(UserRepository userRepository, String email) throws AlreadyExistException {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new AlreadyExistException(ErrorCode.ALREADY_EXIST_EXCEPTION_USER);
        }
    }


    public static void validateExistNickName(UserRepository userRepository, String nickName) {
        if (userRepository.findByNickName(nickName).isPresent()) {
            throw new AlreadyExistException(ErrorCode.ALREADY_EXIST_EXCEPTION_NICKNAME);
        }
    }
}
