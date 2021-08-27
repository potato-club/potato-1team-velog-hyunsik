package com.example.potato_velog_board.domain.service.file;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.potato_velog_board.domain.entity.ImageType;
import com.example.potato_velog_board.domain.repository.BoardRepository;
import com.example.potato_velog_board.web.dto.response.BoardImageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class FileServiceUtils {


    public static BoardImageResponse createBoardImage(String originalName , String fileName, String uploadImageUrl) {
        String markDown = "![" + originalName + "]("+ uploadImageUrl +")";
        return BoardImageResponse.createBoardImage(originalName, uploadImageUrl, fileName, ImageType.CONTENT, markDown);
    }

    public static BoardImageResponse createBoardThumbnail(String originalName , String fileName, String uploadImageUrl) {
        return BoardImageResponse.createBoardImage(originalName, uploadImageUrl, fileName, ImageType.THUMBNAIL,null);
    }

    public static Optional<File> convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            log.error("파일 컨버팅 중 에러 발생", e);
            e.printStackTrace();
        }
        return Optional.of(convertedFile);
    }

    public static String getFileName(File uploadFile, String uuid, String mainImage) {
        return uuid + "/" + mainImage + "/" + UUID.randomUUID() + "_" + uploadFile.getName();
    }

    // S3로 업로드
    public static String uploadToS3(AmazonS3 s3Client, File uploadFile, String fileName, String bucketName) {
        try {
            s3Client.putObject(new PutObjectRequest(bucketName, fileName, uploadFile).withCannedAcl(CannedAccessControlList.PublicRead));
            // 로컬 파일 지우기
            uploadFile.delete();
        } catch (Exception e) {
            log.info("S3 업로드 중 오류 발생 = {}", e.getMessage());
            e.printStackTrace();
        }
        return s3Client.getUrl(bucketName, fileName).toString();
    }


}
