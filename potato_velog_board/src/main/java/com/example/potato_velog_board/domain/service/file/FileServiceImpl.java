package com.example.potato_velog_board.domain.service.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.potato_velog_board.domain.repository.BoardRepository;
import com.example.potato_velog_board.exception.S3FileException;
import com.example.potato_velog_board.utils.error.ErrorCode;
import com.example.potato_velog_board.web.dto.response.BoardImageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Value("${application.bucket.name}")
    private String bucketName;

    private final AmazonS3 s3Client;
    private final BoardRepository boardRepository;



    public BoardImageResponse upload(MultipartFile uploadFile)  {
        File convertedFile = convertMultiPartFileToFile(uploadFile)  // 파일 변환할 수 없으면 에러
                .orElseThrow(() -> new S3FileException(ErrorCode.FILE_CONVERT_EXCEPTION));
        return uploadToS3(convertedFile);
    }

    // S3로 파일 업로드하기
    private BoardImageResponse uploadToS3(File uploadFile) {
        String fileName = UUID.randomUUID() + "_" + uploadFile.getName();   // S3에 저장된 파일 이름
        String uploadImageUrl = putS3(uploadFile, fileName); // s3로 업로드
        removeNewFile(uploadFile);
        return FileServiceUtils.createBoardImage(boardRepository, uploadFile.getName(), fileName, uploadImageUrl);
    }

    // S3로 업로드
    private String putS3(File uploadFile, String fileName) {
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
        return s3Client.getUrl(bucketName, fileName).toString();
    }

    // 로컬에 저장된 이미지 지우기
    public void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("File delete success");
            return;
        }
        log.info("File delete fail");
    }

    public Optional<File> convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("파일 컨버팅 중 에러 발생", e);
            e.printStackTrace();
        }
         return Optional.of(convertedFile);
    }
}
