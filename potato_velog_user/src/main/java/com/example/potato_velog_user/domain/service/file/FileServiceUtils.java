package com.example.potato_velog_user.domain.service.file;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.potato_velog_user.domain.entity.User;
import com.example.potato_velog_user.domain.entity.UserImage;
import com.example.potato_velog_user.domain.repository.user.UserRepository;
import com.example.potato_velog_user.exception.NotFoundException;
import com.example.potato_velog_user.utils.error.ErrorCode;
import com.example.potato_velog_user.web.dto.user.response.UserImageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;


@Slf4j
public class FileServiceUtils {


    public static UserImageResponse createUserImage(UserRepository userRepository, String originalName,
                                                    String fileName, String uploadImageUrl, String uuid) {
        User findUser = userRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
        findUser.addUserImage(UserImage.of(originalName, uploadImageUrl, fileName));
        userRepository.save(findUser);
        return UserImageResponse.of(UserImage.of(originalName, uploadImageUrl, fileName));
    }

//    public static void validateExistFile(AmazonS3 s3Client, UserRepository userRepository, String bucketName,
//                                         String uuid, String mainImage) {
//        User findUser = userRepository.findByUuid(uuid).orElseThrow(() -> new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION_USER));
//        final String checkFolder = uuid + "/" + mainImage;
//        final ObjectListing list = s3Client.listObjects(bucketName, checkFolder);
//        if (!list.getCommonPrefixes().isEmpty()) {
//            list.getObjectSummaries().clear();
//        }
//        userRepository.deleteUserImageById(findUser.getId());
//    }

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
       return uuid + "/" + mainImage + UUID.randomUUID() + "_" + uploadFile.getName();
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
