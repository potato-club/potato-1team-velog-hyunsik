package com.velog.velogcommon.board.dto.response;

import com.velog.velogcommon.board.entity.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BoardInfoResponse {

    private String introduce;

    private boolean isPublic;

    private String boardUrl;

    @Builder
    public BoardInfoResponse(String introduce, boolean isPublic, String boardUrl) {
        this.introduce = introduce;
        this.isPublic = isPublic;
        this.boardUrl = boardUrl;
    }

    public static BoardInfoResponse of(Board board) {
        return new BoardInfoResponse().builder()
                .introduce(board.getBoardInfo().getIntroduce())
                .isPublic(board.getBoardInfo().isPublic())
                .boardUrl(board.getBoardInfo().getBoardUrl())
                .build();
    }
}
