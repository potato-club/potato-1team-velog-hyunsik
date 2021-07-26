package com.velog.veloguser.service;

import com.velog.veloguser.domain.entity.User;
import com.velog.veloguser.exception.AlreadyExistException;
import com.velog.veloguser.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserServiceUtils {

    public static String encodePassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.encode(password);
    }

    public static void validateEmail(UserRepository userRepository, String email) throws AlreadyExistException {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new AlreadyExistException("이미 존재하는 계정입니다");
        }
    }
}
