package com.example.potato_velog_board.web.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardRequest {

    @NotBlank
    @Size(min = 2, max = 50)
    private String title;

    @NotBlank
    @Size(min = 10, max = 1000)
    private String content;

    private BoardSeriesRequest boardSeriesRequest;

    private BoardInfoRequest boardInfoRequest;

    private List<BoardImageRequest> boardImageRequestList = new ArrayList<>();

    private List<HashTagRequest> hashTagRequestList = new ArrayList<>();

}
