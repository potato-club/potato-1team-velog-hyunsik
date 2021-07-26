package com.velog.veloguser.service;

import com.velog.veloguser.domain.dto.request.UserCreateRequest;
import com.velog.veloguser.domain.dto.response.UserResponse;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;


    @Test
    public void 회원_가입_성공() throws Exception {
        //given
        UserCreateRequest request = UserCreateRequest.of("aaaa@naver.com", "12345678", "hyunsik");
        //when
        UserResponse savedUser = userService.createUser(request);
        //then
        assertThat(savedUser.getEmail()).isEqualTo("aaaa@naver.com");
        assertThat(savedUser.getName()).isEqualTo("hyunsik");
    }



}