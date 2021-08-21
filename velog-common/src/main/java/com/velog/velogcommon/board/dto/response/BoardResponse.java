package com.velog.velogcommon.board.dto.response;

import com.velog.velogcommon.board.dto.request.BoardImageRequest;
import com.velog.velogcommon.board.dto.request.BoardInfoRequest;
import com.velog.velogcommon.board.dto.request.BoardSeriesRequest;
import com.velog.velogcommon.board.dto.request.HashTagRequest;
import com.velog.velogcommon.board.entity.Board;
import com.velog.velogcommon.board.entity.HashTag;
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
