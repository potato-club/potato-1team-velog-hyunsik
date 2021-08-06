package com.velog.veloguser.web.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardResponse {

    @JsonIgnore
    private Long id;

    private String title;

    private String content;

    private String userId;
}
