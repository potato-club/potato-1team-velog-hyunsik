package com.example.potato_velog_board.web.controller;

import com.example.potato_velog_board.domain.service.board.BoardService;
import com.example.potato_velog_board.utils.Result;
import com.example.potato_velog_board.utils.validation.ValidationUtils;
import com.example.potato_velog_board.web.client.UserServiceClient;
import com.example.potato_velog_board.web.dto.request.BoardRequest;
import com.example.potato_velog_board.web.dto.response.BoardResponse;
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
    private final UserServiceClient userServiceClient;

    @PostMapping("createBoard")
    public Result<BoardResponse> createBoard(@Valid @RequestBody BoardRequest request, BindingResult bindingResult, @RequestHeader(name = "Authorization") String token) throws BindException {
        ValidationUtils.validateBindingResult(bindingResult);
        String uuid = userServiceClient.validateToken(token);
        return Result.success(boardService.createBoard(request, uuid));
    }

    @GetMapping("myBoardList")
    public ResponseEntity<List<BoardResponse>> myBoardList(@RequestHeader(name = "Authorization") String token) {
        return ResponseEntity.ok(boardService.retrieveBoardList(token));
    }
}
