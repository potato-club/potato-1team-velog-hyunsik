package com.example.potato_velog_board.domain.service.file;


import com.example.potato_velog_board.domain.entity.ImageType;
import com.example.potato_velog_board.domain.repository.BoardRepository;
import com.example.potato_velog_board.web.dto.response.BoardImageResponse;

public class FileServiceUtils {


    public static BoardImageResponse createBoardImage(BoardRepository boardRepository, String originalName , String fileName, String uploadImageUrl) {
        String markDown = "![" + originalName + "]("+ uploadImageUrl +")";
        return BoardImageResponse.createBoardImage(originalName, uploadImageUrl, fileName, ImageType.CONTENT, false, markDown);
    }
}
