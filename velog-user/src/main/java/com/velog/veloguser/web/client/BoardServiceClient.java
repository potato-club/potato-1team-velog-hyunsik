package com.velog.veloguser.web.client;

import com.velog.veloguser.domain.utils.Result;
import com.velog.veloguser.web.dto.response.BoardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient("potato-velog-board")
public interface BoardServiceClient {

    @GetMapping("myBoardList")
    List<BoardResponse> myBoardList(@RequestHeader(name = "Authorization") String token);

}
