package com.velog.veloguser.service;

import com.velog.velogcommon.exception.AlreadyExistException;
import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.user.repository.UserRepository;
import com.velog.veloguser.service.user.UserService;
import com.velog.velogcommon.user.dto.request.UserCreateRequest;
import com.velog.velogcommon.user.dto.response.UserCreateResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @BeforeEach
    public void init() {
        User user = User.createUser("bbb@naver.com", "12345678", "윤현식", "nickName1", "안녕하세요");
        userRepository.save(user);
    }

    @AfterEach
    public void clear() {
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("회원 가입 성공 코드")
    public void 회원가입_성공() throws Exception {
        //given
        UserCreateRequest request = UserCreateRequest.of
                ("aaaa@naver.com", "12345678", "윤현식", "nickName2", "안녕하세요");
        //when
        UserCreateResponse savedUser = userService.createUser(request);
        //then
        assertThat(savedUser.getEmail()).isEqualTo("aaaa@naver.com");
        assertThat(savedUser.getName()).isEqualTo("윤현식");
        assertThat(savedUser.getNickName()).isEqualTo("nickName2");
        assertThat(savedUser.getIntroduce()).isEqualTo("안녕하세요");

    }
    @Test
    @DisplayName("회원 가입 시 이메일이 중복되는 경우에는 에러 처리")
    public void 회원가입_이메일중복() throws Exception {
        //given
        UserCreateRequest request = UserCreateRequest.of
                ("bbb@naver.com", "12345678", "윤현식", "nickName2", "안녕하세요");
        //then
        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(AlreadyExistException.class)
                .hasMessageContaining("이미 존재하는 계정입니다.");
    }

    @Test
    @DisplayName("회원 가입 시 별명이 중복되는 경우에는 에러 처리")
    public void 회원가입_별명중복() throws Exception {
        //given
        UserCreateRequest request = UserCreateRequest.of
                ("aaaa@naver.com", "12345678", "윤현식", "nickName1", "안녕하세요");
        //then
        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(AlreadyExistException.class)
                .hasMessageContaining("이미 존재하는 별명입니다.");
    }




}