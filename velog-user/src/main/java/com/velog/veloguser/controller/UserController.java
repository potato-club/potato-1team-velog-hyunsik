package com.velog.veloguser.controller;

import com.velog.veloguser.domain.utils.Result;
import com.velog.veloguser.domain.dto.request.UserCreateRequest;
import com.velog.veloguser.domain.dto.response.UserResponse;
import com.velog.veloguser.service.UserService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("createUser")
    public Result<UserResponse> createUser(@RequestBody @Valid UserCreateRequest request, BindingResult bindingResult) throws BindException, NotFoundException {
        return Result.success(userService.createUser(request, bindingResult));
    }
}
