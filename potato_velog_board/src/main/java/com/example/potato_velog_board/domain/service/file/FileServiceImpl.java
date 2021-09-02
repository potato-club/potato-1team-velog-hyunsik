package com.example.potato_velog_board.domain.service.file;

import com.amazonaws.services.s3.AmazonS3;
import com.example.potato_velog_board.exception.S3FileException;
import com.example.potato_velog_board.utils.error.ErrorCode;
import com.example.potato_velog_board.web.dto.response.board.BoardImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${application.bucket.name}")
    private String bucketName;
    private static final String boardImage = "boardImage";
    private static final String boardThumbnail = "boardThumbnail";

    private final AmazonS3 s3Client;

    @Override
    public BoardImageResponse uploadBoardImage(MultipartFile uploadFile, String uuid) {
        // 파일 변환할 수 없으면 에러
        final File convertedFile = FileServiceUtils.convertMultiPartFileToFile(uploadFile).orElseThrow(() -> new S3FileException(ErrorCode.FILE_CONVERT_EXCEPTION));
        final String fileName = FileServiceUtils.getFileName(convertedFile, uuid, boardImage);
        // S3에 업로드하기
        final String uploadImageUrl = FileServiceUtils.uploadToS3(s3Client, convertedFile, fileName, bucketName);
        // 업로드된 이미지 객체 리턴
        return FileServiceUtils.createBoardImage(convertedFile.getName(), fileName, uploadImageUrl);
    }

    @Override
    public BoardImageResponse uploadThumbnail(MultipartFile uploadFile, String uuid) {
        final File convertedFile = FileServiceUtils.convertMultiPartFileToFile(uploadFile).orElseThrow(() -> new S3FileException(ErrorCode.FILE_CONVERT_EXCEPTION));
        final String fileName = FileServiceUtils.getFileName(convertedFile, uuid, boardThumbnail);
        final String uploadImageUrl = FileServiceUtils.uploadToS3(s3Client, convertedFile, fileName, bucketName);
        return FileServiceUtils.createBoardThumbnail(convertedFile.getName(), fileName, uploadImageUrl);
    }



}
