package com.example.potato_velog_board.web.dto.response.board;

import com.example.potato_velog_board.domain.entity.board.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponse {

    private String uuid;
    private String nickName;
    private String thumbnail;
    private LocalDateTime createdDate;
    private String content;

    public CommentResponse(String uuid, String nickName, String thumbnail, LocalDateTime createdDate, String content) {
        this.uuid = uuid;
        this.nickName = nickName;
        this.thumbnail = thumbnail;
        this.createdDate = createdDate;
        this.content = content;
    }

    public static CommentResponse of(Comment comment) {
        return new CommentResponse(comment.getUuid(), comment.getNickName(), comment.getThumbnail(),
                comment.getCreatedDate(), comment.getContent());
    }
}
