package com.example.potato_velog_user.web.controller.user;

import com.example.potato_velog_user.domain.service.auth.AuthService;
import com.example.potato_velog_user.exception.NotFoundException;
import com.example.potato_velog_user.domain.service.user.UserService;
import com.example.potato_velog_user.utils.validation.ValidationUtils;
import com.example.potato_velog_user.web.dto.user.request.SocialInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserRequest;
import com.example.potato_velog_user.web.dto.user.response.UserInfoResponse;
import com.example.potato_velog_user.web.dto.user.response.UserResponse;
import com.example.potato_velog_user.web.dto.user.response.UserSocialInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;
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
        final String uuId = authService.validateToken(token);
        return ResponseEntity.ok(UserInfoResponse.of(userService.updateUserInfo(request, uuId)));
    }

    /**
     * 회원 이름, 한줄 소개 수정하기
     **/
    @PostMapping("setting/updateNameAndIntroduce")
    public ResponseEntity<UserResponse> updateNameAndIntroduce(@RequestBody @Valid UserRequest.UpdateNameAndIntroduce request, BindingResult bindingResult,
                                                       @RequestHeader(name = "Authorization") String token) throws BindException, NotFoundException {
        ValidationUtils.validateBindingResult(bindingResult);
        final String uuId = authService.validateToken(token);
        return ResponseEntity.ok(UserResponse.of(userService.updateNameAndIntroduce(request, uuId)));
    }


    /**
     * 소셜 정보 수정하기
     */
    @PostMapping("setting/updateSocialInfo")
    public ResponseEntity<UserSocialInfoResponse> updateSocialInfo(@RequestBody SocialInfoRequest request,
                                                                   @RequestHeader(name = "Authorization") String token) {
        final String uuId = authService.validateToken(token);
        return ResponseEntity.ok(UserSocialInfoResponse.of(userService.updateSocialInfo(request, uuId)));
    }



}
