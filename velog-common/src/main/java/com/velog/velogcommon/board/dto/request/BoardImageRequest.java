package com.velog.velogcommon.board.dto.request;

import com.velog.velogcommon.board.entity.ImageType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardImageRequest {

    private String originalImageName;

    private String uploadImageUrl;

    private String uploadImageName;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;
}
