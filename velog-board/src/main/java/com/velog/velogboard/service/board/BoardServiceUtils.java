package com.velog.velogboard.service.board;

import com.velog.velogcommon.board.dto.request.BoardRequest;
import com.velog.velogcommon.board.entity.*;
import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.user.entity.User;
import com.velog.velogcommon.utils.error.ErrorCode;
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


    public static Board createBoard(BoardRequest request, User user) {
        Board board = Board.createBoard(request);
        BoardInfo boardInfo = BoardInfo.createBoardInfo(request);
        List<BoardImage> boardImageList = BoardImage.createBoardImageList(request);
        BoardSeries boardSeries = BoardSeries.createBoardSeries(request);
        List<HashTag> hashTagList = HashTag.createHashTagList(request);
        user.addBoard(Board.of(board, boardSeries, boardInfo, null, hashTagList, boardImageList));
        return Board.of(board, boardSeries, boardInfo, null, hashTagList, boardImageList);
    }
}
