package com.example.potato_velog_user.web.controller;

import com.example.potato_velog_user.config.SpringCloudConfig;
import com.example.potato_velog_user.domain.service.user.UserService;
import com.example.potato_velog_user.utils.validation.FieldErrorDetail;
import com.example.potato_velog_user.utils.validation.ValidationCode;
import com.example.potato_velog_user.utils.validation.ValidationResult;
import com.example.potato_velog_user.web.controller.user.UserController;
import com.example.potato_velog_user.web.dto.user.request.UserInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@ContextConfiguration(classes = { SpringCloudConfig.class, ValidationCode.class })
//@TestPropertySource(locations = "classpath:/validation.properties")
@WebFluxTest(controllers = UserController.class)
class UserControllerTest {


    @MockBean
    UserService userService;

    @Autowired
    private WebTestClient webClient;

    @BeforeEach
    public void init() {
        String baseUri = "http://localhost:8070/potato-velog-user";
        webClient = WebTestClient.bindToServer().responseTimeout(Duration.ofSeconds(100)).baseUrl(baseUri).build();

    }

    @Test
    @DisplayName("회원 가입 시 @Valid 모든 예외 처리가 들어간 에러 발생!!")
    public void 회원_가입_에러() throws Exception {

        //given
        UserRequest.Create request = UserRequest.Create.of
                ("", "", "", "", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        //then
        webClient.post()
                .uri("/createUser")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectBody(ValidationResult.class)
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().size()).isEqualTo(9))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getMessage).collect(Collectors.toList()))
                        .contains("이메일은 최소 2글자 이상이어야 합니다.", "이메일을 입력해주세요.",
                                "비밀번호를 입력해주세요.", "비밀번호는 최소 8글자 최대 20글자 이하여야 합니다.",
                                "이름을 입력해주세요.", "이름은 최소 2글자 최대 20글자 이하여야 합니다.",
                                "별명을 입력해주세요.", "별명은 최소 6글자 최대 16글자 이하여야 합니다.",
                                "한줄소개는 최대 30글자까지 입니다."
                        ))
//                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getMessage).collect(Collectors.toList()))
//                         .contains(validationCode.getNotBlankEmail(),validationCode.getSizeEmail(),
//                                 validationCode.getNotBlankPassword(), validationCode.getSizePassword(),
//                                 validationCode.getNotBlankName(), validationCode.getSizeName(),
//                                 validationCode.getNotBlankNickName(), validationCode.getSizeNickName(),
//                                 validationCode.getSizeIntroduce()))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getCode).collect(Collectors.toList()))
                        .contains("Size", "NotBlank"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getObjectName).collect(Collectors.toList()))
                        .containsOnly("create"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getField).collect(Collectors.toList()))
                        .contains("email", "password", "name", "nickName", "introduce"));
    }

    @Test
    @DisplayName("이름이랑 한줄소개 변경 시 @Valid 모든 예외 처리가 들어간 에러 발생!!!")
    public void 이름_한줄소개_변경_에러() throws Exception {
        //given
        UserRequest.UpdateNameAndIntroduce request = new UserRequest.UpdateNameAndIntroduce("", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        String token = "tokentokentokentokentokentokentoken";
        //then
        webClient.post().uri("/setting/updateNameAndIntroduce")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectBody(ValidationResult.class)
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().size()).isEqualTo(3))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getMessage).collect(Collectors.toList()))
                        .contains("이름을 입력해주세요.", "이름은 최소 2글자 최대 20글자 이하여야 합니다.",
                                "한줄소개는 최대 30글자까지 입니다."))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getCode).collect(Collectors.toList()))
                        .contains("NotBlank", "Size"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getObjectName).collect(Collectors.toList()))
                        .containsOnly("updateNameAndIntroduce"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getField).collect(Collectors.toList()))
                        .contains("name", "introduce"));

    }

    @Test
    @DisplayName("유저 상세정보 변경 시 블로그 제목을 입력하지 않았을 때 에러 발생")
    public void 유저_상세정보_변경_에러() throws Exception {
        //given
        UserInfoRequest request = new UserInfoRequest("", true, true);
        String token = "tokentokentokentokentokentokentoken";
        //then
        webClient.post().uri("/setting/updateUserInfo")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectBody(ValidationResult.class)
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().size()).isEqualTo(1))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getMessage).collect(Collectors.toList()))
                        .contains("블로그 제목을 입력해주세요."))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getCode).collect(Collectors.toList()))
                        .contains("NotBlank"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getObjectName).collect(Collectors.toList()))
                        .containsOnly("userInfoRequest"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getField).collect(Collectors.toList()))
                        .containsOnly("velogName"));

    }


    @Test
    @DisplayName("유저 상세정보 변경 시 블로그 제목이 30글자 초과했을 시 에러 발생")
    public void 유저_상세정보_변경_에러2() throws Exception {
        //given
        UserInfoRequest request = new UserInfoRequest("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", true, true);
        String token = "tokentokentokentokentokentokentoken";
        //then
        webClient.post().uri("/setting/updateUserInfo")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectBody(ValidationResult.class)
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().size()).isEqualTo(1))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getMessage).collect(Collectors.toList()))
                        .contains("블로그 제목는 최대 30글자까지 입니다."))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getCode).collect(Collectors.toList()))
                        .contains("Size"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getObjectName).collect(Collectors.toList()))
                        .containsOnly("userInfoRequest"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getField).collect(Collectors.toList()))
                        .containsOnly("velogName"));

    }




}