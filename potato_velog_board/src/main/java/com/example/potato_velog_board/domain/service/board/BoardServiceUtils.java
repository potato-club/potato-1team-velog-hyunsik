package com.example.potato_velog_board.domain.service.board;

import com.example.potato_velog_board.domain.entity.*;
import com.example.potato_velog_board.web.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardServiceUtils {


    public static Board createBoard(BoardRequest request, String uuid) {
        Board board = Board.createBoard(request, uuid);
        BoardInfo boardInfo = BoardInfo.createBoardInfo(request);
        List<BoardImage> boardImageList = BoardImage.createBoardImageList(request);
        BoardSeries boardSeries = BoardSeries.createBoardSeries(request);
        List<HashTag> hashTagList = HashTag.createHashTagList(request);
        return Board.of(board, boardSeries, boardInfo, hashTagList, boardImageList);
    }


}
