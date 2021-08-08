package com.velog.veloguser.service.user;

import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.velogcommon.user.dto.request.UserCreateRequest;
import com.velog.velogcommon.user.dto.response.UserCreateResponse;
import com.velog.velogcommon.exception.AlreadyExistException;
import javassist.NotFoundException;
import org.springframework.validation.BindException;

import java.util.List;


public interface UserService {


    UserCreateResponse createUser(UserCreateRequest request) throws NotFoundException, AlreadyExistException, BindException;

    List<BoardResponse> retrieveBoardList(String token);
}
