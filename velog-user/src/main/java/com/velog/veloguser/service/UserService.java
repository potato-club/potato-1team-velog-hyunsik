package com.velog.veloguser.service;

import com.velog.veloguser.domain.dto.request.LoginRequest;
import com.velog.veloguser.domain.dto.request.UserCreateRequest;
import com.velog.veloguser.domain.dto.response.UserResponse;
import com.velog.veloguser.exception.AlreadyExistException;
import javassist.NotFoundException;
import org.springframework.validation.BindException;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    UserResponse createUser(UserCreateRequest request) throws NotFoundException, AlreadyExistException, BindException;

}
