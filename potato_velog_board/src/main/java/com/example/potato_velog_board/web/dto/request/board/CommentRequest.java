package com.example.potato_velog_board.web.dto.request.board;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor
public class CommentRequest {

    @Size(min = 2, max = 200)
    private String content;

    public CommentRequest(@Size(min = 2, max = 200) String content) {
        this.content = content;
    }
}
