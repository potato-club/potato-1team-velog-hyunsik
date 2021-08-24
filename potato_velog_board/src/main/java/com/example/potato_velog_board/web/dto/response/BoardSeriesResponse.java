package com.example.potato_velog_board.web.dto.response;

import com.example.potato_velog_board.domain.entity.Board;
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
