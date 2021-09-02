package com.example.potato_velog_board.domain.entity.board;

import com.example.potato_velog_board.utils.BaseTimeEntity;
import com.example.potato_velog_board.web.dto.request.board.BoardRequest;
import com.example.potato_velog_board.web.dto.request.board.BoardSeriesRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSeries extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "boardSeries", cascade = ALL)
    private final List<Board> boardList = new ArrayList<>();


    public BoardSeries(String name) {
        this.name = name;
    }

    public static BoardSeries of(BoardRequest request) {
        if (request.getBoardSeriesRequest() == null) {
            return new BoardSeries(null);
        }
        return new BoardSeries(request.getBoardSeriesRequest().getName());
    }


    public void update(BoardSeriesRequest boardSeriesRequest) {
        if (boardSeriesRequest == null) {
           this.name = null;
           return;
        }
        this.name = boardSeriesRequest.getName();
    }
}
