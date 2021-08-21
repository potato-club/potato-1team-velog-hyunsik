package com.velog.velogboard.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadFile(final MultipartFile file);

    byte[] downloadFile(final String fileName);

    String deleteFile(String fileName);
}
