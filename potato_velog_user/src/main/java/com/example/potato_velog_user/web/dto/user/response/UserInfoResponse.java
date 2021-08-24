package com.example.potato_velog_user.web.dto.user.response;

import com.example.potato_velog_user.domain.entity.UserInfo;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoResponse {


    private String velogName;

    private boolean isCommentAlert;

    private boolean isUpdateAlert;

    @Builder
    public UserInfoResponse(String velogName, boolean isCommentAlert, boolean isUpdateAlert) {
        this.velogName = velogName;
        this.isCommentAlert = isCommentAlert;
        this.isUpdateAlert = isUpdateAlert;
    }

    public static UserInfoResponse of(UserInfo userInfo) {
        return new UserInfoResponse(userInfo.getVelogName(), userInfo.isCommentAlert(), userInfo.isUpdateAlert());
    }
}
