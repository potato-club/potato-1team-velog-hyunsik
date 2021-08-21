package com.velog.velogcommon.board.dto.response;

import com.velog.velogcommon.board.entity.Board;
import com.velog.velogcommon.board.entity.ImageType;
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

    @Builder
    public BoardImageResponse(String originalImageName, String uploadImageUrl, String uploadImageName, ImageType imageType) {
        this.originalImageName = originalImageName;
        this.uploadImageUrl = uploadImageUrl;
        this.uploadImageName = uploadImageName;
        this.imageType = imageType;
    }

    public static List<BoardImageResponse> of(Board board) {
        return board.getBoardImageList().stream().map(i -> new BoardImageResponse().builder()
                .originalImageName(i.getOriginalImageName())
                .uploadImageUrl(i.getUploadImageUrl())
                .uploadImageName(i.getUploadImageName())
                .imageType(i.getImageType()).build())
                .collect(Collectors.toList());
    }
}
