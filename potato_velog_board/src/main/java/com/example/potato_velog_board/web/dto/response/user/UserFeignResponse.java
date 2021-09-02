package com.example.potato_velog_board.web.dto.response.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UserFeignResponse {

    private String uuid;
    private String nickName;
    private String thumbnail;
    private LocalDateTime createdDate;

    public UserFeignResponse(String uuid, String nickName, String thumbnail, LocalDateTime createdDate) {
        this.uuid = uuid;
        this.nickName = nickName;
        this.thumbnail = thumbnail;
        this.createdDate = createdDate;
    }
}
