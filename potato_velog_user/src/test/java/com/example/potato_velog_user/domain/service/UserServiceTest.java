package com.example.potato_velog_user.domain.service;

import com.example.potato_velog_user.config.StorageConfig;
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
import com.netflix.discovery.converters.Auto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@TestPropertySource(locations="classpath:application.yml")
@ExtendWith(RestDocumentationExtension.class)
@Transactional
class UserServiceTest {

    @Autowired
    UserService userService;
    @Autowired
    AuthService authService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    StorageConfig storageConfig;
    @Autowired
    EntityManager em;

    @BeforeEach
    public void init() throws BindException {
        UserRequest.Create request = UserRequest.Create.of
                ("bbb@naver.com", "12345678", "?????????", "nickName", "???????????????");
        userService.createUser(request);

    }

    @AfterEach
    public void clear() {
        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("?????? ?????? ?????? ??????")
    public void ????????????_??????() throws Exception {
        //given
        UserRequest.Create request = UserRequest.Create.of
                ("aaaa@naver.com", "12345678", "?????????", "nickName2", "???????????????");
        //when
        User savedUser = userService.createUser(request);
        //then
        assertThat(savedUser.getEmail()).isEqualTo("aaaa@naver.com");
        assertThat(savedUser.getName()).isEqualTo("?????????");
        assertThat(savedUser.getNickName()).isEqualTo("nickName2");
        assertThat(savedUser.getIntroduce()).isEqualTo("???????????????");
        assertThat(savedUser.getUserInfo().getVelogName()).isEqualTo(savedUser.getNickName() + ".log");
        assertThat(savedUser.getUserInfo().isCommentAlert()).isFalse();
        assertThat(savedUser.getUserInfo().isUpdateAlert()).isFalse();

    }
    @Test
    @DisplayName("?????? ?????? ??? ???????????? ???????????? ???????????? ?????? ??????")
    public void ????????????_???????????????() throws Exception {
        //given
        UserRequest.Create request = UserRequest.Create.of
                ("bbb@naver.com", "12345678", "?????????", "nickName2", "???????????????");
        //then
        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(AlreadyExistException.class)
                .hasMessageContaining("?????? ???????????? ???????????????.");
    }

    @Test
    @DisplayName("?????? ?????? ??? ????????? ???????????? ???????????? ?????? ??????")
    public void ????????????_????????????() throws Exception {
        //given
        UserRequest.Create request = UserRequest.Create.of
                ("aaaa@naver.com", "12345678", "?????????", "nickName", "???????????????");
        //then
        assertThatThrownBy(() -> userService.createUser(request))
                .isInstanceOf(AlreadyExistException.class)
                .hasMessageContaining("?????? ???????????? ???????????????.");
    }

    @Test
    @DisplayName("?????? ???????????? ?????? ??? ??????(????????? ??????, ?????? ??????)")
    public void ??????_????????????_??????_??????() throws Exception {
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
    @DisplayName("??????, ???????????? ?????? ??? ??????")
    public void ??????_????????????_??????_??????() throws Exception {
        //given
        UserRequest.UpdateNameAndIntroduce request = UserRequest.UpdateNameAndIntroduce.of("?????????2", "???????????????");
        final TokenDto tokenDto = authService.login(new LoginRequest("bbb@naver.com", "12345678"));
        final String uuId = authService.validateToken(tokenDto.getToken());
        //when
        User changedUser = userService.updateNameAndIntroduce(request, uuId);
        //then
        assertThat(changedUser.getName()).isEqualTo("?????????2");
        assertThat(changedUser.getIntroduce()).isEqualTo("???????????????");

    }


    @Test
    @DisplayName("?????? ?????? ?????? ??? ??????")
    public void ??????_??????_??????_??????() throws Exception {
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