package com.velog.velogcommon.board.dto.response;

import com.velog.velogcommon.board.entity.Board;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSeriesResponse {

    private String name;

    public BoardSeriesResponse(String name) {
        this.name = name;
    }

    public static BoardSeriesResponse of(Board board) {
        return new BoardSeriesResponse(board.getBoardSeries().getName());
    }
}
