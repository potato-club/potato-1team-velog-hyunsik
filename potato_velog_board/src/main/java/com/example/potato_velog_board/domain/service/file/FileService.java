package com.example.potato_velog_board.domain.service.file;

import com.example.potato_velog_board.web.dto.response.BoardImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public interface FileService {

    BoardImageResponse upload(MultipartFile uploadFile) throws IOException;

    void removeNewFile(File targetFile);

    Optional<File> convertMultiPartFileToFile(MultipartFile file);


}
