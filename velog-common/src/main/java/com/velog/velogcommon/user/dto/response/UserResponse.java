package com.velog.velogcommon.user.dto.response;

import com.velog.velogcommon.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserResponse {

        private String email;
        private String name;
        private String nickName;
        private String introduce;

        @Builder
        public UserResponse(String email, String name, String nickName, String introduce) {
            this.email = email;
            this.name = name;
            this.nickName = nickName;
            this.introduce = introduce;
        }

        public static UserResponse of(User user) {
            return new UserResponse().builder()
                    .email(user.getEmail())
                    .name(user.getName())
                    .nickName(user.getNickName())
                    .introduce(user.getIntroduce())
                    .build();
        }

}
