package com.velog.veloguser.service.user;

import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.velogcommon.exception.AlreadyExistException;
import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.user.dto.request.UserRequest;
import com.velog.velogcommon.user.entity.User;
import org.springframework.validation.BindException;

import java.util.List;


public interface UserService {


    User createUser(UserRequest.Create request) throws NotFoundException, AlreadyExistException, BindException;

    List<BoardResponse> retrieveBoardList(String token);

//    User updateNameAndIntroduce(UserRequest.Create request, String token) throws NotFoundException;
}
