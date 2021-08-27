package com.example.potato_velog_user.web.dto.user.response;

import com.example.potato_velog_user.domain.entity.UserImage;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserImageResponse {

    private String originalImageName;

    private String uploadImageUrl;

    private String uploadImageName;

    @Builder
    public UserImageResponse(String originalImageName, String uploadImageUrl, String uploadImageName) {
        this.originalImageName = originalImageName;
        this.uploadImageUrl = uploadImageUrl;
        this.uploadImageName = uploadImageName;
    }

    public static UserImageResponse of(UserImage userImage) {
        return new UserImageResponse().builder()
                .originalImageName(userImage.getOriginalImageName())
                .uploadImageUrl(userImage.getUploadImageUrl())
                .uploadImageName(userImage.getUploadImageName())
                .build();
    }
}
