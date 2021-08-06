package com.velog.veloguser.web.controller;

import com.velog.veloguser.domain.utils.Result;
import com.velog.veloguser.web.dto.request.UserCreateRequest;
import com.velog.veloguser.web.dto.response.UserResponse;
import com.velog.veloguser.service.user.UserService;
import com.velog.veloguser.web.validation.ValidationUtils;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

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

    @PostMapping("createUser")
    public Result<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request, BindingResult bindingResult) throws BindException, NotFoundException {
        ValidationUtils.validateBindingResult(bindingResult);
        return Result.success(userService.createUser(request));
    }
}
