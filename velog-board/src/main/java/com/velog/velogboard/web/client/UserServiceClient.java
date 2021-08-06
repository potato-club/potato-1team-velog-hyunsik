package com.velog.velogboard.web.client;

import com.velog.velogboard.web.dto.response.UserIdResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "localhost:8070")
public interface UserServiceClient {

    @PostMapping("/potato-velog-user/getUserId")
    String getUserId(String token);

    @GetMapping("/potato-velog-user/validateUserId")
    void validateUserId(String userId);
}
