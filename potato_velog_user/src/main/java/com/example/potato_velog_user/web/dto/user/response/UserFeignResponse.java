package com.example.potato_velog_user.web.dto.user.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserFeignResponse {

    private String uuid;
    private String nickName;
    private String thumbnail;

    public UserFeignResponse(String uuid, String nickName, String thumbnail) {
        this.uuid = uuid;
        this.nickName = nickName;
        this.thumbnail = thumbnail;
    }

    public static UserFeignResponse of(String uuid, String nickName, String thumbnail) {
        return new UserFeignResponse(uuid, nickName, thumbnail);
    }
}
