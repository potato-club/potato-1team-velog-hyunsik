package com.example.potato_velog_user.domain.service.file;

import com.example.potato_velog_user.web.dto.user.response.UserImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {

    UserImageResponse upload(MultipartFile uploadFile, String uuId) throws IOException;

}
