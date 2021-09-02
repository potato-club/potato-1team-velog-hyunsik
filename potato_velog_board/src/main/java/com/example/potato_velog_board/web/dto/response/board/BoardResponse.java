package com.example.potato_velog_board.web.dto.response.board;

import com.example.potato_velog_board.domain.entity.board.Board;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponse {

    private String title;

    private String content;

    private BoardSeriesResponse boardSeriesResponse;

    private BoardInfoResponse boardInfoResponse;

    private List<BoardImageResponse> boardImageResponseList = new ArrayList<>();

    private List<HashTagResponse> hashTagResponseList = new ArrayList<>();

    @Builder
    public BoardResponse(String title, String content, BoardSeriesResponse boardSeriesResponse, BoardInfoResponse boardInfoResponse, List<BoardImageResponse> boardImageResponseList, List<HashTagResponse> hashTagResponseList) {
        this.title = title;
        this.content = content;
        this.boardSeriesResponse = boardSeriesResponse;
        this.boardInfoResponse = boardInfoResponse;
        this.boardImageResponseList = boardImageResponseList;
        this.hashTagResponseList = hashTagResponseList;
    }

    

    public static BoardResponse of(Board board){
        if (board.getBoardSeries() == null) {
            return new BoardResponse().builder()
                    .title(board.getTitle())
                    .content(board.getContent())
                    .boardInfoResponse(BoardInfoResponse.of(board))
                    .boardImageResponseList(BoardImageResponse.of(board))
                    .hashTagResponseList(HashTagResponse.of(board))
                    .build();
        }
        return new BoardResponse().builder()
                .title(board.getTitle())
                .content(board.getContent())
                .boardSeriesResponse(BoardSeriesResponse.of(board))
                .boardInfoResponse(BoardInfoResponse.of(board))
                .boardImageResponseList(BoardImageResponse.of(board))
                .hashTagResponseList(HashTagResponse.of(board))
                .build();
    }
}
