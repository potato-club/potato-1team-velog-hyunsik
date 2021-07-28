package com.velog.veloguser.controller;

import com.velog.veloguser.domain.dto.request.LoginRequest;
import com.velog.veloguser.domain.dto.response.UserResponse;
import com.velog.veloguser.domain.utils.Result;
import com.velog.veloguser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("login")
    public Result<UserResponse> login(HttpServletRequest request, @RequestBody @Valid LoginRequest loginRequest, BindingResult bindingResult) throws BindException {
        // 토큰 인가처리는 Gateway에서 해주기 떄문에 검증만 하면 될듯?


        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return Result.success(userService.login(request, loginRequest));

    }
}
