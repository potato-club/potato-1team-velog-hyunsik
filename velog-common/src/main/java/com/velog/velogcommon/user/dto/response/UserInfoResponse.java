package com.velog.velogcommon.user.dto.response;

import com.velog.velogcommon.user.dto.request.UserInfoRequest;
import com.velog.velogcommon.user.entity.UserInfo;
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
        return new UserInfoResponse().builder()
                .velogName(userInfo.getVelogName())
                .isCommentAlert(userInfo.isCommentAlert())
                .isUpdateAlert(userInfo.isUpdateAlert())
                .build();
    }
}
