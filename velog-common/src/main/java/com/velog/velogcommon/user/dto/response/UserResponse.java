package com.velog.velogcommon.user.dto.response;

import com.velog.velogcommon.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponse {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Create {
        private String email;
        private String name;
        private String nickName;
        private String introduce;

        @Builder
        public Create(String email, String name, String nickName, String introduce) {
            this.email = email;
            this.name = name;
            this.nickName = nickName;
            this.introduce = introduce;
        }

        public static Create of(User user) {
            return new Create().builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickName(user.getNickName())
                    .introduce(user.getIntroduce())
                    .build();
        }
    }


}
