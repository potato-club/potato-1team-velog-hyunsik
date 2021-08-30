package com.example.potato_velog_board.domain.service.board;

import com.example.potato_velog_board.domain.entity.*;
import com.example.potato_velog_board.domain.repository.BoardRepository;
import com.example.potato_velog_board.exception.NotFoundException;
import com.example.potato_velog_board.utils.error.ErrorCode;
import com.example.potato_velog_board.web.dto.request.BoardImageRequest;
import com.example.potato_velog_board.web.dto.request.BoardRequest;
import com.example.potato_velog_board.web.dto.request.HashTagRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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

    public static Board updateBoard(BoardRequest request, Board board) {
        board.update(request.getTitle(), request.getContent());
        board.getBoardInfo().update(request.getBoardInfoRequest());
        board.getBoardSeries().update(request.getBoardSeriesRequest());
        updateBoardImage(request, board);
        updateHashTag(request,board);
        return board;
    }

    public static Board validateExistBoard(BoardRepository boardRepository, Long id, String uuid) {
       return boardRepository.findByIdAndUuid(id, uuid).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_BOARD));
    }

    private static void updateHashTag(BoardRequest boardRequest, Board board) {
        board.getHashTagList().clear();
        final List<HashTag> hashTagList = HashTag.of(boardRequest);
        for (HashTag hashTag : hashTagList) {
            board.addHashTag(hashTag);
        }
    }

    private static void updateBoardImage(BoardRequest boardRequest, Board board) {
        board.getBoardImageList().clear();
        final List<BoardImage> boardImageList = BoardImage.of(boardRequest);
        for (BoardImage boardImage : boardImageList) {
            board.addBoardImage(boardImage);
        }
    }

}
