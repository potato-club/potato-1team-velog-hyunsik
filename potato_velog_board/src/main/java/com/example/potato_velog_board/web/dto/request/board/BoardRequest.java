package com.example.potato_velog_board.web.dto.request.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class BoardRequest {

    @NotBlank
    @Size(min = 2, max = 50)
    private String title;

    @NotBlank
    @Size(min = 10, max = 1000)
    private String content;

    private BoardSeriesRequest boardSeriesRequest;

    private BoardInfoRequest boardInfoRequest;

    private List<BoardImageRequest> boardImageRequestList = new ArrayList<>();

    private List<HashTagRequest> hashTagRequestList = new ArrayList<>();


    public BoardRequest(String title, String content, BoardSeriesRequest boardSeriesRequest, BoardInfoRequest boardInfoRequest,
                        List<BoardImageRequest> boardImageRequestList, List<HashTagRequest> hashTagRequestList) {
        this.title = title;
        this.content = content;
        this.boardSeriesRequest = boardSeriesRequest;
        this.boardInfoRequest = boardInfoRequest;
        this.boardImageRequestList = boardImageRequestList;
        this.hashTagRequestList = hashTagRequestList;
    }
}
