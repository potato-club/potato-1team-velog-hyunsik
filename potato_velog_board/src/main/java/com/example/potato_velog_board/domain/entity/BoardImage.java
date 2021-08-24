package com.example.potato_velog_board.domain.entity;

import com.example.potato_velog_board.web.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalImageName;

    private String uploadImageUrl;

    private String uploadImageName;

    @Enumerated(EnumType.STRING)
    private ImageType imageType;

    private boolean isSuccessUpload;

    private String markDown;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public BoardImage(String originalImageName, String uploadImageUrl, String uploadImageName, ImageType imageType,
                      boolean isSuccessUpload, String markDown) {
        this.originalImageName = originalImageName;
        this.uploadImageUrl = uploadImageUrl;
        this.uploadImageName = uploadImageName;
        this.imageType = imageType;
        this.isSuccessUpload = isSuccessUpload;
        this.markDown = markDown;

    }

    public void addBoard(Board board) {
        this.board = board;
    }

    public static BoardImage createBoardImage(String originalImageName, String uploadImageUrl, String uploadImageName,
                                              ImageType imageType, boolean isSuccessUpload, String markDown) {
        return new BoardImage().builder().originalImageName(originalImageName).uploadImageUrl(uploadImageUrl)
                .imageType(imageType).isSuccessUpload(isSuccessUpload).markDown(markDown).build();
    }

    public static List<BoardImage> createBoardImageList(BoardRequest request) {
        return request.getBoardImageRequestList().stream().map(i -> new BoardImage().builder()
                .originalImageName(i.getOriginalImageName())
                .uploadImageUrl(i.getUploadImageUrl())
                .uploadImageName(i.getUploadImageName())
                .imageType(i.getImageType())
                .build()).collect(Collectors.toList());

    }


}