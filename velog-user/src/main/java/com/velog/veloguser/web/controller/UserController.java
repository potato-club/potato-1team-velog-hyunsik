package com.velog.veloguser.web.controller;

import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.utils.Result;
import com.velog.velogcommon.utils.validation.ValidationUtils;
import com.velog.velogcommon.user.dto.request.UserRequest;
import com.velog.velogcommon.user.dto.response.UserResponse;
import com.velog.veloguser.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final Environment env;


    @GetMapping("wasCheck")
    public String status() {
        return "port(local.server.port) = " + env.getProperty("local.server.port")
                + ", port(server.port) = " + env.getProperty("server.port")
                + ", with token secret = " + env.getProperty("token.secret")
                + ", with token time = " + env.getProperty("token.expiration_time");
    }

    /** 회원 가입 **/
    @PostMapping("createUser")
    public ResponseEntity<UserResponse.Create> createUser(@RequestBody @Valid UserRequest.Create request, BindingResult bindingResult) throws BindException, NotFoundException {
        ValidationUtils.validateBindingResult(bindingResult);
        return ResponseEntity.ok(UserResponse.Create.of(userService.createUser(request)));
    }

    /**
     * 회원 이름, 한줄 소개 수정하기
     **/
    @PostMapping("setting/updateNameAndIntroduce")
    public ResponseEntity<UserResponse.Create> updateNameAndIntroduce(@RequestBody @Valid UserRequest.UpdateNameAndIntroduce request, BindingResult bindingResult,
                                                       @RequestHeader(name = "Authorization") String token) throws BindException, NotFoundException {
        ValidationUtils.validateBindingResult(bindingResult);
        return ResponseEntity.ok(UserResponse.Create.of(userService.updateNameAndIntroduce(request, token)));
    }

    //Board 서버에서 해도 되지만 FeignClient 시험용으로 User 서버에서 시도해봄
    @GetMapping("myBoardList")
    public ResponseEntity<List<BoardResponse>> myBoardList(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(userService.retrieveBoardList(token));
    }
}
