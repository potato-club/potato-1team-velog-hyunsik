package com.example.potato_velog_board.web.dto.request.board;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class BoardInfoRequest {

    @Size(max = 30)
    private String introduce;

    @JsonProperty
    private boolean isPublic;

    private String boardUrl;

    public BoardInfoRequest(@Size(max = 30) String introduce, boolean isPublic, String boardUrl) {
        this.introduce = introduce;
        this.isPublic = isPublic;
        this.boardUrl = boardUrl;
    }
}
