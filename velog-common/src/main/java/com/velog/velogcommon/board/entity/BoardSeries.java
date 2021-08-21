package com.velog.velogcommon.board.entity;

import com.velog.velogcommon.board.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSeries {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "boardSeries")
    private final List<Board> boardList = new ArrayList<>();


    public BoardSeries(String name) {
        this.name = name;
    }

    public static BoardSeries createBoardSeries(BoardRequest request) {
        return new BoardSeries(request.getBoardSeriesRequest().getName());
    }
}
