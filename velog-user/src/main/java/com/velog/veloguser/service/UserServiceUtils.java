package com.velog.veloguser.service;

import com.velog.veloguser.domain.dto.request.LoginRequest;
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

    public static void validateExistEmail(UserRepository userRepository, String email) throws AlreadyExistException {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new AlreadyExistException("이미 존재하는 계정입니다");
        }
    }

    public static void validateEmail(UserRepository userRepository, String email) throws NotFoundException {
        if (userRepository.findUserByEmail(email).isEmpty()) {
            throw new NotFoundException("해당 계정이 존재하지 않습니다.");
        }
    }

    public static void validatePassword(UserRepository userRepository, LoginRequest loginRequest) {

    }
}
