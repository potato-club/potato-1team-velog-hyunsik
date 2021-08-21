package com.velog.velogboard.web.controller;

import com.velog.velogboard.service.board.BoardService;
import com.velog.velogboard.web.client.AuthServiceClient;
import com.velog.velogcommon.utils.validation.ValidationUtils;
import com.velog.velogcommon.board.dto.request.BoardRequest;
import com.velog.velogcommon.board.dto.response.BoardResponse;
import com.velog.velogcommon.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final AuthServiceClient authServiceClient;

    @PostMapping("createBoard")
    public Result<BoardResponse> createBoard(@Valid @RequestBody BoardRequest request, BindingResult bindingResult, @RequestHeader(name = "Authorization") String token) throws BindException {
        ValidationUtils.validateBindingResult(bindingResult);
        Long userId = authServiceClient.validateToken(token);
        return Result.success(boardService.createBoard(request, userId));
    }

    @GetMapping("myBoardList")
    public ResponseEntity<List<BoardResponse>> myBoardList(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(boardService.retrieveBoardList(token));
    }
}
