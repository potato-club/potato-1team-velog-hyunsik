package com.velog.veloguser.web.controller;

import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.user.dto.request.SocialInfoRequest;
import com.velog.velogcommon.user.dto.request.UserInfoRequest;
import com.velog.velogcommon.user.dto.response.UserInfoResponse;
import com.velog.velogcommon.user.dto.response.UserSocialInfoResponse;
import com.velog.velogcommon.user.entity.UserInfo;
import com.velog.velogcommon.utils.validation.ValidationUtils;
import com.velog.velogcommon.user.dto.request.UserRequest;
import com.velog.velogcommon.user.dto.response.UserResponse;
import com.velog.veloguser.service.user.UserService;
import com.velog.veloguser.web.client.AuthServiceClient;
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
    private final AuthServiceClient authServiceClient;

    @GetMapping("wasCheck")
    public String status() {
        return "port(local.server.port) = " + env.getProperty("local.server.port")
                + ", port(server.port) = " + env.getProperty("server.port")
                + ", with token secret = " + env.getProperty("token.secret")
                + ", with token time = " + env.getProperty("token.expiration_time");
    }

    /** 회원 가입 **/
    @PostMapping("createUser")
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest.Create request, BindingResult bindingResult) throws BindException, NotFoundException {
        ValidationUtils.validateBindingResult(bindingResult);
        return ResponseEntity.ok(UserResponse.of(userService.createUser(request)));
    }

    /**
     * 회원 상세 정보 수정하기 (벨로그 제목, 이메일 수신설정)
     */
    @PostMapping("setting/updateUserInfo")
    public ResponseEntity<UserInfoResponse> updateUserInfo(@RequestBody @Valid UserInfoRequest request, BindingResult bindingResult,
                                                           @RequestHeader(name = "Authorization") String token) throws BindException {
        ValidationUtils.validateBindingResult(bindingResult);
        Long userId = authServiceClient.validateToken(token);
        return ResponseEntity.ok(UserInfoResponse.of(userService.updateUserInfo(request, userId)));
    }

    /**
     * 회원 이름, 한줄 소개 수정하기
     **/
    @PostMapping("setting/updateNameAndIntroduce")
    public ResponseEntity<UserResponse> updateNameAndIntroduce(@RequestBody @Valid UserRequest.UpdateNameAndIntroduce request, BindingResult bindingResult,
                                                       @RequestHeader(name = "Authorization") String token) throws BindException, NotFoundException {
        ValidationUtils.validateBindingResult(bindingResult);
        Long userId = authServiceClient.validateToken(token);
        return ResponseEntity.ok(UserResponse.of(userService.updateNameAndIntroduce(request, userId)));
    }


    /**
     * 소셜 정보 수정하기
     */
    @PutMapping("setting/updateSocialInfo")
    public ResponseEntity<UserSocialInfoResponse> updateSocialInfo(@RequestBody SocialInfoRequest request,
                                                                   @RequestHeader(name = "Authorization") String token) {
        Long userId = authServiceClient.validateToken(token);
        return ResponseEntity.ok(UserSocialInfoResponse.of(userService.updateSocialInfo(request, userId)));
    }


    //Board 서버에서 해도 되지만 FeignClient 시험용으로 User 서버에서 시도해봄
    @GetMapping("myBoardList")
    public ResponseEntity<List<BoardResponse>> myBoardList(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(userService.retrieveBoardList(token));
    }



}
