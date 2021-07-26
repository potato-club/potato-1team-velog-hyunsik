package com.velog.veloguser.service;

import com.velog.veloguser.domain.dto.request.UserCreateRequest;
import com.velog.veloguser.domain.dto.response.UserResponse;
import com.velog.veloguser.exception.AlreadyExistException;
import javassist.NotFoundException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public interface UserService {

    UserResponse createUser(UserCreateRequest request, BindingResult bindingResult) throws NotFoundException, AlreadyExistException, BindException;
}
