package com.example.potato_velog_board.domain.service.board;

import com.example.potato_velog_board.domain.entity.*;
import com.example.potato_velog_board.domain.repository.BoardRepository;
import com.example.potato_velog_board.exception.NotFoundException;
import com.example.potato_velog_board.utils.error.ErrorCode;
import com.example.potato_velog_board.web.dto.request.BoardRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardServiceUtils {


    public static Board createBoard(BoardRequest request, String uuid) {
        Board board = Board.createBoard(request, uuid);
        BoardInfo boardInfo = BoardInfo.of(request);
        List<BoardImage> boardImageList = BoardImage.of(request);
        BoardSeries boardSeries = BoardSeries.of(request);
        List<HashTag> hashTagList = HashTag.of(request);
        return Board.of(board, boardSeries, boardInfo, hashTagList, boardImageList);
    }

    public static Board updateBoard(BoardRepository boardRepository, BoardRequest request, Board board, String uuid) {
        boardRepository.delete(board);
        return createBoard(request, uuid);
    }

    public static Board validateExistBoard(BoardRepository boardRepository, Long id, String uuid) {
       return boardRepository.findByIdAndUuid(id, uuid).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_BOARD));
    }

}
