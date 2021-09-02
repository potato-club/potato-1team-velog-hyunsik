package com.example.potato_velog_board.web.dto.request.board;

import com.example.potato_velog_board.domain.entity.board.ImageType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor
public class BoardImageRequest {

    private String originalImageName;

    private String uploadImageUrl;

    private String uploadImageName;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    private String markDown;

    public BoardImageRequest(String originalImageName, String uploadImageUrl, String uploadImageName, ImageType imageType, String markDown) {
        this.originalImageName = originalImageName;
        this.uploadImageUrl = uploadImageUrl;
        this.uploadImageName = uploadImageName;
        this.imageType = imageType;
        this.markDown = markDown;
    }
}
