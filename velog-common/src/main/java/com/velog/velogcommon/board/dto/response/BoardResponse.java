package com.velog.velogcommon.board.dto.response;

import com.velog.velogcommon.board.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponse {

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
