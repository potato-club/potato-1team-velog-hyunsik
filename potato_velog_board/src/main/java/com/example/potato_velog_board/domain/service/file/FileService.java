package com.example.potato_velog_board.domain.service.file;

import com.example.potato_velog_board.web.dto.response.board.BoardImageResponse;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    BoardImageResponse uploadBoardImage(MultipartFile uploadFile, String uuid);

    BoardImageResponse uploadThumbnail(MultipartFile file, String uuid);
}
