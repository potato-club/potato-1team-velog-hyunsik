package com.example.potato_velog_board.domain.entity;

import com.example.potato_velog_board.web.dto.request.BoardRequest;
import com.example.potato_velog_board.web.dto.request.BoardSeriesRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardSeries {

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
        return new BoardSeries(request.getBoardSeriesRequest().getName());
    }


    public void update(BoardSeriesRequest boardSeriesRequest) {
        if (boardSeriesRequest == null) {
           this.name = "";
           return;
        }
        this.name = boardSeriesRequest.getName();
    }
}
