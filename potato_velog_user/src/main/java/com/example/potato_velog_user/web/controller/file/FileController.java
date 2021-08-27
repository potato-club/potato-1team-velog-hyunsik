package com.example.potato_velog_user.web.controller.file;

import com.example.potato_velog_user.domain.service.auth.AuthService;
import com.example.potato_velog_user.domain.service.file.FileService;
import com.example.potato_velog_user.web.dto.user.response.UserImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final AuthService authService;

    @PostMapping("/uploadUserImage")
    public ResponseEntity<UserImageResponse> uploadFile(@RequestParam("file") MultipartFile file,
                                                        @RequestHeader(name = "Authorization") String token) throws IOException {
        final String uuId = authService.validateToken(token);
        return ResponseEntity.ok(fileService.upload(file, uuId));
    }
}
