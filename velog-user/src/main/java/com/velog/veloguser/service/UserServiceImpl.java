package com.velog.veloguser.service;

import com.velog.veloguser.domain.dto.request.UserCreateRequest;
import com.velog.veloguser.domain.dto.response.UserResponse;
import com.velog.veloguser.domain.entity.User;
import com.velog.veloguser.exception.AlreadyExistException;
import com.velog.veloguser.repository.UserRepository;
import com.velog.veloguser.validator.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserResponse createUser(UserCreateRequest request, BindingResult bindingResult) throws AlreadyExistException, BindException {
        ValidationUtils.validateBindingResult(bindingResult);

        String encodedPassword = UserServiceUtils.encodePassword(passwordEncoder, request.getPassword());
        UserServiceUtils.validateEmail(userRepository, request.getEmail());

        User user = User.of(request.getEmail(), request.getName(), UUID.randomUUID().toString(), encodedPassword);

        User savedUser = userRepository.save(user);
        return UserResponse.of(savedUser.getEmail(), savedUser.getName(), savedUser.getUserId());
    }


}
