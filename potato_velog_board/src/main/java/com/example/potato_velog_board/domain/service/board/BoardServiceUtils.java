package com.example.potato_velog_board.domain.service.board;

import com.example.potato_velog_board.domain.entity.*;
import com.example.potato_velog_board.exception.NotFoundException;
import com.example.potato_velog_board.utils.error.ErrorCode;
import com.example.potato_velog_board.web.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardServiceUtils {

    public static void validateUserId(String userId, String findUserId) throws NotFoundException {
        if (userId != findUserId) {
            throw new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER);
        }
    }


    public static Board createBoard(BoardRequest request, Long userId) {
        Board board = Board.createBoard(request, userId);
        BoardInfo boardInfo = BoardInfo.createBoardInfo(request);
        List<BoardImage> boardImageList = BoardImage.createBoardImageList(request);
        BoardSeries boardSeries = BoardSeries.createBoardSeries(request);
        List<HashTag> hashTagList = HashTag.createHashTagList(request);
        return Board.of(board, boardSeries, boardInfo, null, hashTagList, boardImageList);
    }


}
