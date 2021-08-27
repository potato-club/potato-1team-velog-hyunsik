package com.example.potato_velog_user.domain.service.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.potato_velog_user.domain.repository.user.UserRepository;
import com.example.potato_velog_user.exception.S3FileException;
import com.example.potato_velog_user.utils.error.ErrorCode;
import com.example.potato_velog_user.web.dto.user.response.UserImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${application.bucket.name}")
    private String bucketName;
    private static final String mainImage = "mainImage";

    private final AmazonS3 s3Client;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserImageResponse upload(MultipartFile uploadFile, String uuid) {
//        FileServiceUtils.validateExistFile(s3Client, userRepository, bucketName, uuid, mainImage);
        // 파일 변환할 수 없으면 에러
        final File convertedFile = FileServiceUtils.convertMultiPartFileToFile(uploadFile).orElseThrow(() -> new S3FileException(ErrorCode.FILE_CONVERT_EXCEPTION));
        // 파일 이름 만들기 : uuid + originalFileName;
        final String fileName = FileServiceUtils.getFileName(convertedFile, uuid, mainImage);
        // S3에 업로드하기
        final String uploadImageUrl = FileServiceUtils.uploadToS3(s3Client, convertedFile, fileName, bucketName);
        // 업로드된 이미지 객체 리턴
        return FileServiceUtils.createUserImage(userRepository, convertedFile.getName(), fileName, uploadImageUrl, uuid);
    }


}
