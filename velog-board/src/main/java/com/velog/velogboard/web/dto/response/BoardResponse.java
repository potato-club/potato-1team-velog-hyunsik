package com.velog.velogboard.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.velog.velogboard.domain.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponse {

    @JsonIgnore
    private Long id;

    private String title;

    private String content;

    private String userId;

    @Builder
    public BoardResponse(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.userId = board.getUserId();
    }

    public static BoardResponse of(Board board) {
        return new BoardResponse(board);
    }
}
