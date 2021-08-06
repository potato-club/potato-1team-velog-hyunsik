package com.velog.velogboard.web.controller;

import com.velog.velogboard.domain.utils.Result;
import com.velog.velogboard.service.BoardService;
import com.velog.velogboard.validation.ValidationUtils;
import com.velog.velogboard.web.dto.request.BoardRequest;
import com.velog.velogboard.web.dto.response.BoardResponse;
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

    @PostMapping("createBoard")
    public Result<BoardResponse> createBoard(@Valid @RequestBody BoardRequest request, BindingResult bindingResult, @RequestHeader(name = "Authorization") String token) throws BindException {
        ValidationUtils.validateBindingResult(bindingResult);
        return Result.success(boardService.createBoard(request, token));
    }

    @GetMapping("myBoardList")
    public ResponseEntity<List<BoardResponse>> myBoardList(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(boardService.retrieveBoardList(token));
    }
}
