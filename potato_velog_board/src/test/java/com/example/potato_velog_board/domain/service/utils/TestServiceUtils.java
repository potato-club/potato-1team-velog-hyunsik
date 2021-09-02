package com.example.potato_velog_board.domain.service.utils;

import com.example.potato_velog_board.domain.entity.board.ImageType;
import com.example.potato_velog_board.web.dto.request.board.*;

import java.util.ArrayList;
import java.util.List;

public class TestServiceUtils {

    public static BoardRequest createBoardRequest() {
        final BoardSeriesRequest boardSeriesRequest = new BoardSeriesRequest("JAVA");
        final BoardInfoRequest boardInfoRequest = new BoardInfoRequest("자기소개", true, "/@hyun6ik/test");

        List<BoardImageRequest> boardImageRequestList = new ArrayList<>();
        final BoardImageRequest boardImageRequest1 = new BoardImageRequest("test1.png", "amazons3/.....",
                "amazonserver/userUuid/uuid_test1.png", ImageType.CONTENT, "![]{}");
        final BoardImageRequest boardImageRequest2 = new BoardImageRequest("test2.png", "amazons3/.....",
                "amazonserver/userUuid/uuid_test2.png", ImageType.CONTENT, "![]{}");
        boardImageRequestList.add(boardImageRequest1);
        boardImageRequestList.add(boardImageRequest2);

        List<HashTagRequest> hashTagRequestList = new ArrayList<>();
        final HashTagRequest hashTagRequest1 = new HashTagRequest("JAVA");
        final HashTagRequest hashTagRequest2 = new HashTagRequest("Spring");
        hashTagRequestList.add(hashTagRequest1);
        hashTagRequestList.add(hashTagRequest2);

        return new BoardRequest("게시글1", "내용", boardSeriesRequest, boardInfoRequest, boardImageRequestList, hashTagRequestList);
    }

    public static BoardRequest updateBoardRequest(){
        final BoardSeriesRequest boardSeriesRequest = new BoardSeriesRequest("UPDATE");
        final BoardInfoRequest boardInfoRequest = new BoardInfoRequest("자기소개 수정", false, "/@hyun6ik/update");

        List<BoardImageRequest> boardImageRequestList = new ArrayList<>();
        final BoardImageRequest boardImageRequest = new BoardImageRequest("updatetest1.png", "amazons3/.....",
                "amazonserver/userUuid/uuid_updatetest1.png", ImageType.CONTENT, "![]{}");
        boardImageRequestList.add(boardImageRequest);

        List<HashTagRequest> hashTagRequestList = new ArrayList<>();
        final HashTagRequest hashTagRequest1 = new HashTagRequest("UPDATE_JAVA");
        final HashTagRequest hashTagRequest2 = new HashTagRequest("UPDATE_Spring");
        final HashTagRequest hashTagRequest3 = new HashTagRequest("UPDATE_JPA");
        hashTagRequestList.add(hashTagRequest1);
        hashTagRequestList.add(hashTagRequest2);
        hashTagRequestList.add(hashTagRequest3);

        return new BoardRequest("1번 게시글 수정", "수정된 내용", boardSeriesRequest, boardInfoRequest, boardImageRequestList, hashTagRequestList);
    }
}
