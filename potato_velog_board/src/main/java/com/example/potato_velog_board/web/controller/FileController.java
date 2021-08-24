package com.example.potato_velog_board.web.controller;

import com.example.potato_velog_board.domain.service.file.FileService;
import com.example.potato_velog_board.web.dto.response.BoardImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @PostMapping("/uploadBoard")
    public ResponseEntity<BoardImageResponse> uploadFile(@RequestParam("file")MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileService.upload(file));
    }


}
