package com.example.potato_velog_user.domain.service;

import com.example.potato_velog_user.domain.entity.User;
import com.example.potato_velog_user.domain.entity.UserInfo;
import com.example.potato_velog_user.domain.entity.UserSocialInfo;
import com.example.potato_velog_user.domain.repository.user.UserRepository;
import com.example.potato_velog_user.domain.service.auth.AuthService;
import com.example.potato_velog_user.exception.AlreadyExistException;
import com.example.potato_velog_user.domain.service.user.UserService;
import com.example.potato_velog_user.utils.TokenDto;
import com.example.potato_velog_user.web.dto.auth.request.LoginRequest;
import com.example.potato_velog_user.web.dto.user.request.SocialInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager em;

    @BeforeEach
    public void init() throws BindException {
        UserRequest.Create request = UserRequest.Create.of
                ("bbb@naver.com", "12345678", "홍길동", "nickName", "안녕하세요");
        userService.createUser(request);

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
        UserRequest.Create request = UserRequest.Create.of
                ("aaaa@naver.com", "12345678", "윤현식", "nickName2", "안녕하세요");
        //when
        User savedUser = userService.createUser(request);
        //then
        assertThat(savedUser.getEmail()).isEqualTo("aaaa@naver.com");
        assertThat(savedUser.getName()).isEqualTo("윤현식");
        assertThat(savedUser.getNickName()).isEqualTo("nickName2");
        assertThat(savedUser.getIntroduce()).isEqualTo("안녕하세요");
        assertThat(savedUser.getUserInfo().getVelogName()).isEqualTo(savedUser.getNickName() + ".log");
        assertThat(savedUser.getUserInfo().isCommentAlert()).isFalse();
        assertThat(savedUser.getUserInfo().isUpdateAlert()).isFalse();

    }
    @Test
    @DisplayName("회원 가입 시 이메일이 중복되는 경우에는 에러 처리")
    public void 회원가입_이메일중복() throws Exception {
        //given
        UserRequest.Create request = UserRequest.Create.of
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
        UserRequest.Create request = UserRequest.Create.of
                ("aaaa@naver.com", "12345678", "윤현식", "nickName", "안녕하세요");
        //then
        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(AlreadyExistException.class)
                .hasMessageContaining("이미 존재하는 별명입니다.");
    }

    @Test
    @DisplayName("유저 상세정보 수정 시 성공(블로그 이름, 알람 설정)")
    public void 유저_상세정보_수정_성공() throws Exception {
        //given
        UserInfoRequest request = new UserInfoRequest("hyun6ikVelog.log", false, true);

        //when
        final TokenDto tokenDto = authService.login(new LoginRequest("bbb@naver.com", "12345678"));
        final String uuId = authService.validateToken(tokenDto.getToken());
        UserInfo userInfo = userService.updateUserInfo(request, uuId);
        //then
        assertThat(userInfo.getVelogName()).isEqualTo("hyun6ikVelog.log");
        assertThat(userInfo.isCommentAlert()).isFalse();
        assertThat(userInfo.isUpdateAlert()).isTrue();


    }

    @Test
    @DisplayName("이름, 한줄소개 수정 시 성공")
    public void 이름_한줄소개_수정_성공() throws Exception {
        //given
        UserRequest.UpdateNameAndIntroduce request = UserRequest.UpdateNameAndIntroduce.of("홍길동2", "안반가워요");
        final TokenDto tokenDto = authService.login(new LoginRequest("bbb@naver.com", "12345678"));
        final String uuId = authService.validateToken(tokenDto.getToken());
        //when
        User changedUser = userService.updateNameAndIntroduce(request, uuId);
        //then
        assertThat(changedUser.getName()).isEqualTo("홍길동2");
        assertThat(changedUser.getIntroduce()).isEqualTo("안반가워요");

    }


    @Test
    @DisplayName("소셜 정보 수정 시 성공")
    public void 소셜_정보_등록_수정() throws Exception {
        //given
        SocialInfoRequest request = new SocialInfoRequest("bbb@gmail.com", "hyun6ik2", "hyun6ikTwitter", "", "https://github.com/hyun6ik");
        final TokenDto tokenDto = authService.login(new LoginRequest("bbb@naver.com", "12345678"));
        final String uuId = authService.validateToken(tokenDto.getToken());
        //when
        UserSocialInfo socialInfo = userService.updateSocialInfo(request, uuId);
        //then
        assertThat(socialInfo.getEmail()).isEqualTo("bbb@gmail.com");
        assertThat(socialInfo.getGithub()).isEqualTo("hyun6ik2");
        assertThat(socialInfo.getTwitter()).isEqualTo("hyun6ikTwitter");
        assertThat(socialInfo.getFacebook()).isBlank();
        assertThat(socialInfo.getHomePage()).isEqualTo("https://github.com/hyun6ik");
        assertThat(socialInfo.getUserInfo().getUser().getId()).isEqualTo(2L);
        assertThat(socialInfo.getUserInfo().getId()).isEqualTo(2L);
    }


}