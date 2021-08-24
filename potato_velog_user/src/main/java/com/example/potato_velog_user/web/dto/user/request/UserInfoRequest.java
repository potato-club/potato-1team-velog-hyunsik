package com.example.potato_velog_user.web.dto.user.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoRequest {

    @NotBlank
    @Size(max = 30)
    private String velogName;

    private boolean isCommentAlert;

    private boolean isUpdateAlert;

    public UserInfoRequest(String velogName, boolean isCommentAlert, boolean isUpdateAlert) {
        this.velogName = velogName;
        this.isCommentAlert = isCommentAlert;
        this.isUpdateAlert = isUpdateAlert;
    }
}
