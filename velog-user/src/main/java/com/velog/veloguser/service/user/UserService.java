package com.velog.veloguser.service.user;

import com.velog.veloguser.web.dto.request.UserCreateRequest;
import com.velog.veloguser.web.dto.response.BoardResponse;
import com.velog.veloguser.web.dto.response.UserResponse;
import com.velog.veloguser.exception.AlreadyExistException;
import javassist.NotFoundException;
import org.springframework.validation.BindException;

import java.util.List;


public interface UserService {


    UserResponse createUser(UserCreateRequest request) throws NotFoundException, AlreadyExistException, BindException;

    String getUserId(String token);

    void validateUserId(String userId) throws NotFoundException;

    List<BoardResponse> retrieveBoardList(String token);
}
