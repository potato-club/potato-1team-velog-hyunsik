package com.example.potato_velog_board.web.dto.response;

import com.example.potato_velog_board.domain.entity.Board;
import com.example.potato_velog_board.domain.entity.ImageType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardImageResponse {

    private String originalImageName;

    private String uploadImageUrl;

    private String uploadImageName;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    private String markDown;

    @Builder
    public BoardImageResponse(String originalImageName, String uploadImageUrl, String uploadImageName, ImageType imageType, String markDown) {
        this.originalImageName = originalImageName;
        this.uploadImageUrl = uploadImageUrl;
        this.uploadImageName = uploadImageName;
        this.imageType = imageType;
        this.markDown = markDown;
    }

    public static List<BoardImageResponse> of(Board board) {
        return board.getBoardImageList().stream().map(i -> new BoardImageResponse().builder()
                .originalImageName(i.getOriginalImageName())
                .uploadImageUrl(i.getUploadImageUrl())
                .uploadImageName(i.getUploadImageName())
                .imageType(i.getImageType())
                .markDown(i.getMarkDown()).build())
                .collect(Collectors.toList());
    }

    public static BoardImageResponse createBoardImage(String originalName, String uploadImageUrl, String uploadImageName,
                                                      ImageType imageType, String markDown) {
        return new BoardImageResponse().builder()
                .originalImageName(originalName)
                .uploadImageUrl(uploadImageUrl)
                .uploadImageName(uploadImageName)
                .imageType(imageType)
                .markDown(markDown)
                .build();
    }
}
