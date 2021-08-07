package com.velog.veloguser.service.user;

import com.velog.velogcommon.exception.AlreadyExistException;
import com.velog.velogcommon.user.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceUtils {

    public static String encodePassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.encode(password);
    }

    public static void validateExistEmail(UserRepository userRepository, String email) throws AlreadyExistException {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new AlreadyExistException("이미 존재하는 계정입니다");
        }
    }


}
