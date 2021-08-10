package com.velog.velogcommon.user.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Create {
        @NotBlank
        @Size(min = 2)
        private String email;

        @NotBlank
        @Size(min = 8, max = 20)
        private String password;

        @NotBlank
        @Size(min = 2, max = 20)
        private String name;

        @NotBlank
        @Size(min = 6, max = 16)
        private String nickName;

        @Size(max = 30)
        private String introduce;

        @Builder
        public Create(String email, String password, String name, String nickName, String introduce) {
            this.email = email;
            this.password = password;
            this.name = name;
            this.nickName = nickName;
            this.introduce = introduce;
        }

        public static Create of(String email, String password, String name, String nickName, String introduce) {
            return new Create().builder().email(email).password(password).name(name).nickName(nickName).introduce(introduce).build();
        }
    }


    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateNameAndIntroduce{
        @NotBlank
        @Size(min = 2, max = 20)
        private String name;

        @Size(max = 30)
        private String introduce;

        @Builder
        public UpdateNameAndIntroduce(String name, String introduce) {
            this.name = name;
            this.introduce = introduce;
        }

        public static UpdateNameAndIntroduce of(String name, String introduce) {
            return new UpdateNameAndIntroduce().builder().name(name).introduce(introduce).build();
        }

    }

}
