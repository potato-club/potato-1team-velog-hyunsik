package com.example.potato_velog_board.web.controller;

import com.example.potato_velog_board.domain.service.file.FileService;
import com.example.potato_velog_board.web.client.UserServiceClient;
import com.example.potato_velog_board.web.dto.response.BoardImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Slf4j
@RestController
@RequestMapping("upload")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final UserServiceClient userServiceClient;

    @PostMapping("/boardImage")
    public ResponseEntity<BoardImageResponse> uploadBoardImage(@RequestParam("file") MultipartFile file,
                                                         @RequestHeader(name = "Authorization") String token) {
        final String uuid = userServiceClient.validateToken(token);
        return ResponseEntity.ok(fileService.uploadBoardImage(file, uuid));
    }

    @PostMapping("/thumbnail")
    public ResponseEntity<BoardImageResponse> uploadThumbnail(@RequestParam("file") MultipartFile file,
                                                              @RequestHeader(name = "Authorization") String token) {
        final String uuid = userServiceClient.validateToken(token);
        return ResponseEntity.ok(fileService.uploadThumbnail(file, uuid));
    }


}
