package com.velog.velogboard.web.controller;

import com.velog.velogboard.domain.utils.Result;
import com.velog.velogboard.service.BoardService;
import com.velog.velogboard.validation.ValidationUtils;
import com.velog.velogboard.web.dto.request.BoardRequest;
import com.velog.velogboard.web.dto.response.BoardResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("createBoard")
    public Result<BoardResponse> createBoard(@Valid @RequestBody BoardRequest request, BindingResult bindingResult, @RequestHeader(name = "Authorization") String token) throws BindException {

        System.out.println("token = " + token);
        ValidationUtils.validateBindingResult(bindingResult);
        return Result.success(boardService.createBoard(request, token));
    }
}
