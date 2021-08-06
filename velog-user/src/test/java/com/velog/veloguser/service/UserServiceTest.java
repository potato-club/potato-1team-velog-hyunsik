package com.velog.veloguser.service;

import com.velog.veloguser.service.user.UserService;
import com.velog.veloguser.web.dto.request.UserCreateRequest;
import com.velog.veloguser.web.dto.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

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