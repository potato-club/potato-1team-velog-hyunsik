package com.velog.velogcommon.board.entity;

import com.velog.velogcommon.board.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.*;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public BoardImage(String originalImageName, String uploadImageUrl, String uploadImageName, ImageType imageType) {
        this.originalImageName = originalImageName;
        this.uploadImageUrl = uploadImageUrl;
        this.uploadImageName = uploadImageName;
        this.imageType = imageType;
    }

    public void addBoard(Board board) {
        this.board = board;
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
