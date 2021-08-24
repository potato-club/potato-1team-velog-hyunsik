package com.example.potato_velog_board.web.dto.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardInfoRequest {

    @Size(max = 30)
    private String introduce;

    private boolean isPublic;

    private String boardUrl;
}
