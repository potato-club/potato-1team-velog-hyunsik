package com.example.potato_velog_board.web.dto.response.board;

import com.example.potato_velog_board.domain.entity.board.Board;
import com.example.potato_velog_board.domain.entity.board.BoardImage;
import com.example.potato_velog_board.domain.entity.board.ImageType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyBoardResponse {

    private String title;
    private String content;
    private String uploadImageUrl;
    private boolean isPublic;
    private int commentCount;
    private LocalDateTime createdDate;


    public MyBoardResponse(String title, String content, String uploadImageUrl, boolean isPublic, int commentCount, LocalDateTime createdDate) {
        this.title = title;
        this.content = content;
        this.uploadImageUrl = uploadImageUrl;
        this.isPublic = isPublic;
        this.commentCount = commentCount;
        this.createdDate = createdDate;
    }

    public static MyBoardResponse of(Board board) {

        if (board.getBoardImageList().size() == 0) {
            return new MyBoardResponse(board.getTitle(), board.getContent(),
                    null,
                    board.getBoardInfo().isPublic(), board.getCommentList().size(), board.getCreatedDate());
        }

        if(!board.getBoardImageList().stream().anyMatch(b -> b.getImageType().equals(ImageType.THUMBNAIL))){
            return new MyBoardResponse(board.getTitle(), board.getContent(),
                    board.getBoardImageList().get(0).getUploadImageUrl(),
                    board.getBoardInfo().isPublic(), board.getCommentList().size(), board.getCreatedDate());
        }
        return new MyBoardResponse(board.getTitle(), board.getContent(),
                board.getBoardImageList().stream().filter(b -> b.getImageType().equals(ImageType.THUMBNAIL)).map(BoardImage::getUploadImageUrl).toString(),
                board.getBoardInfo().isPublic(), board.getCommentList().size(), board.getCreatedDate());
    }
}
