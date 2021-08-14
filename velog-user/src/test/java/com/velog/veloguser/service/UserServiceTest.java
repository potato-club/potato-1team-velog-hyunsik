package com.velog.veloguser.service;

import com.velog.velogcommon.exception.AlreadyExistException;
import com.velog.velogcommon.user.dto.request.LoginRequest;
import com.velog.velogcommon.user.dto.request.SocialInfoRequest;
import com.velog.velogcommon.user.dto.request.UserInfoRequest;
import com.velog.velogcommon.user.dto.request.UserRequest;
import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.user.entity.UserInfo;
import com.velog.velogcommon.user.entity.UserSocialInfo;
import com.velog.velogcommon.user.repository.UserRepository;
import com.velog.velogcommon.utils.TokenDto;
import com.velog.veloguser.service.user.UserService;
import com.velog.veloguser.web.client.AuthServiceClient;
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

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    AuthServiceClient authServiceClient;
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
        Long userId = 1L;
        //when
        UserInfo userInfo = userService.updateUserInfo(request, userId);
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
        Long userId = 1L;
        //when
        User changedUser = userService.updateNameAndIntroduce(request, userId);
        //then
        assertThat(changedUser.getName()).isEqualTo("홍길동2");
        assertThat(changedUser.getIntroduce()).isEqualTo("안반가워요");

    }


    @Test
    @DisplayName("소셜 정보 수정 시 성공")
    public void 소셜_정보_등록_수정() throws Exception {
        //given
        SocialInfoRequest request = new SocialInfoRequest("bbb@gmail.com", "hyun6ik2", "hyun6ikTwitter", "", "https://github.com/hyun6ik");
        Long userId = 1L;
        //when
        UserSocialInfo socialInfo = userService.updateSocialInfo(request, userId);
        //then
        assertThat(socialInfo.getEmail()).isEqualTo("bbb@gmail.com");
        assertThat(socialInfo.getGithub()).isEqualTo("hyun6ik2");
        assertThat(socialInfo.getTwitter()).isEqualTo("hyun6ikTwitter");
        assertThat(socialInfo.getFacebook()).isBlank();
        assertThat(socialInfo.getHomePage()).isEqualTo("https://github.com/hyun6ik");
        assertThat(socialInfo.getUserInfo().getUser().getId()).isEqualTo(1L);
        assertThat(socialInfo.getUserInfo().getId()).isEqualTo(1L);
    }


}