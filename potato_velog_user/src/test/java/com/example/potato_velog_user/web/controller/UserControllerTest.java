package com.example.potato_velog_user.web.controller;

import com.example.potato_velog_user.config.SpringCloudConfig;
import com.example.potato_velog_user.domain.service.user.UserService;
import com.example.potato_velog_user.utils.validation.FieldErrorDetail;
import com.example.potato_velog_user.utils.validation.ValidationCode;
import com.example.potato_velog_user.utils.validation.ValidationResult;
import com.example.potato_velog_user.web.controller.user.UserController;
import com.example.potato_velog_user.web.dto.user.request.UserInfoRequest;
import com.example.potato_velog_user.web.dto.user.request.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentationConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.stream.Collectors;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;


@ContextConfiguration(classes = { SpringCloudConfig.class, ValidationCode.class })
//@TestPropertySource(locations = "classpath:/validation.properties")
@WebFluxTest(controllers = UserController.class)
@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
class UserControllerTest {

    @MockBean
    UserService userService;

    @Autowired
    private WebTestClient webClient;


    @BeforeEach
    public void setUp(ApplicationContext applicationContext,
                      RestDocumentationContextProvider restDocumentation) {
        webClient = WebTestClient.bindToApplicationContext(applicationContext)
                .configureClient()
                .baseUrl("http://localhost:8070/potato-velog-user")
                .filter(documentationConfiguration(restDocumentation))
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }


    @Test
    @DisplayName("?????? ?????? ??? @Valid ?????? ?????? ????????? ????????? ?????? ??????!!")
    public void ??????_??????_??????() throws Exception {

        //given
        UserRequest.Create request = UserRequest.Create.of
                ("", "", "", "", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        //then
        webClient.post()
                .uri("/createUser")
                .bodyValue(request)
                .exchange()
                .expectBody(ValidationResult.class)
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().size()).isEqualTo(9))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getMessage).collect(Collectors.toList()))
                        .contains("???????????? ?????? 2?????? ??????????????? ?????????.", "???????????? ??????????????????.",
                                "??????????????? ??????????????????.", "??????????????? ?????? 8?????? ?????? 20?????? ???????????? ?????????.",
                                "????????? ??????????????????.", "????????? ?????? 2?????? ?????? 20?????? ???????????? ?????????.",
                                "????????? ??????????????????.", "????????? ?????? 6?????? ?????? 16?????? ???????????? ?????????.",
                                "??????????????? ?????? 30???????????? ?????????."
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
                        .contains("email", "password", "name", "nickName", "introduce"))
                .consumeWith(document("index"));
    }

    @Test
    @DisplayName("???????????? ???????????? ?????? ??? @Valid ?????? ?????? ????????? ????????? ?????? ??????!!!")
    public void ??????_????????????_??????_??????() throws Exception {
        //given
        UserRequest.UpdateNameAndIntroduce request = new UserRequest.UpdateNameAndIntroduce("", "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        String token = "tokentokentokentokentokentokentoken";
        //then
        webClient.post().uri("/setting/updateNameAndIntroduce")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectBody(ValidationResult.class)
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().size()).isEqualTo(3))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getMessage).collect(Collectors.toList()))
                        .contains("????????? ??????????????????.", "????????? ?????? 2?????? ?????? 20?????? ???????????? ?????????.",
                                "??????????????? ?????? 30???????????? ?????????."))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getCode).collect(Collectors.toList()))
                        .contains("NotBlank", "Size"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getObjectName).collect(Collectors.toList()))
                        .containsOnly("updateNameAndIntroduce"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getField).collect(Collectors.toList()))
                        .contains("name", "introduce"))
                .consumeWith(document("index"));


    }

    @Test
    @DisplayName("?????? ???????????? ?????? ??? ????????? ????????? ???????????? ????????? ??? ?????? ??????")
    public void ??????_????????????_??????_??????() throws Exception {
        //given
        UserInfoRequest request = new UserInfoRequest("", true, true);
        String token = "tokentokentokentokentokentokentoken";
        //then
        webClient.post().uri("/setting/updateUserInfo")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectBody(ValidationResult.class)
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().size()).isEqualTo(1))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getMessage).collect(Collectors.toList()))
                        .contains("????????? ????????? ??????????????????."))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getCode).collect(Collectors.toList()))
                        .contains("NotBlank"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getObjectName).collect(Collectors.toList()))
                        .containsOnly("userInfoRequest"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getField).collect(Collectors.toList()))
                        .containsOnly("velogName"))
                .consumeWith(document("index"));


    }


    @Test
    @DisplayName("?????? ???????????? ?????? ??? ????????? ????????? 30?????? ???????????? ??? ?????? ??????")
    public void ??????_????????????_??????_??????2() throws Exception {
        //given
        UserInfoRequest request = new UserInfoRequest("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", true, true);
        String token = "tokentokentokentokentokentokentoken";
        //then
        webClient.post().uri("/setting/updateUserInfo")
                .header("Authorization", token)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectBody(ValidationResult.class)
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().size()).isEqualTo(1))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getMessage).collect(Collectors.toList()))
                        .contains("????????? ????????? ?????? 30???????????? ?????????."))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getCode).collect(Collectors.toList()))
                        .contains("Size"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getObjectName).collect(Collectors.toList()))
                        .containsOnly("userInfoRequest"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getField).collect(Collectors.toList()))
                        .containsOnly("velogName"))
                .consumeWith(document("index"));


    }




}