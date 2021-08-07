package com.velog.veloguser.web.controller;

import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.velogcommon.utils.Result;
import com.velog.velogcommon.utils.validation.ValidationUtils;
import com.velog.veloguser.web.client.BoardServiceClient;
import com.velog.velogcommon.user.dto.request.UserCreateRequest;
import com.velog.velogcommon.user.dto.response.UserResponse;
import com.velog.veloguser.service.user.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final BoardServiceClient boardServiceClient;
    private final UserService userService;
    private final Environment env;


    @GetMapping("wasCheck")
    public String status() {
        return "port(local.server.port) = " + env.getProperty("local.server.port")
                + ", port(server.port) = " + env.getProperty("server.port")
                + ", with token secret = " + env.getProperty("token.secret")
                + ", with token time = " + env.getProperty("token.expiration_time");
    }

    @PostMapping("createUser")
    public Result<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request, BindingResult bindingResult) throws BindException, NotFoundException {
        ValidationUtils.validateBindingResult(bindingResult);
        return Result.success(userService.createUser(request));
    }

    //Board 서버에서 해도 되지만 FeignClient 시험용으로 User 서버에서 시도해봄
    @GetMapping("myBoardList")
    public Result<List<BoardResponse>> myBoardList(@RequestHeader(name = "Authorization") String token) {
        return Result.success(userService.retrieveBoardList(token));
    }
}
