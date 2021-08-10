package com.velog.velogauth.web.controller;

import com.velog.velogauth.service.AuthService;
import com.velog.velogcommon.board.repository.BoardRepository;
import com.velog.velogcommon.user.dto.request.LoginRequest;
import com.velog.velogcommon.user.repository.UserRepository;
import com.velog.velogcommon.utils.validation.FieldErrorDetail;
import com.velog.velogcommon.utils.validation.ValidationResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import java.time.Duration;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AuthController.class)
class AuthControllerTest {

    @MockBean
    AuthService authService;
    @MockBean
    BoardRepository boardRepository;

    @MockBean
    UserRepository userRepository;

    @Autowired
    private WebTestClient webClient;

    @BeforeEach
    public void init() {
        String baserUri = "http://localhost:8070/potato-velog-auth";
        webClient = WebTestClient.bindToServer().responseTimeout(Duration.ofSeconds(10)).baseUrl(baserUri).build();
    }

    @Test
    @DisplayName("로그인 시 @Valid 모든 예외 처리가 들어간 에러 발생!!")
    public void 로그인_에러() throws Exception {
        //given
        LoginRequest request = new LoginRequest("", "");
        //when
        webClient.post().uri("/authenticate")
                .bodyValue(request)
                .exchange()
                .expectBody(ValidationResult.class)
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getCode).collect(Collectors.toList()))
                        .containsOnly("Size", "NotBlank"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().size()).isEqualTo(4))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getMessage).collect(Collectors.toList()))
                        .contains("이메일을 입력해주세요.", "이메일은 최소 5글자 이상이어야 합니다.",
                                "비밀번호를 입력해주세요.", "비밀번호는 최소 8글자 최대 20글자 이하여야 합니다."))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getField).collect(Collectors.toList()))
                        .contains("email", "password"))
                .consumeWith(result -> assertThat(result.getResponseBody().getErrors().stream().map(FieldErrorDetail::getObjectName).collect(Collectors.toList()))
                        .containsOnly("loginRequest"));


    }
}