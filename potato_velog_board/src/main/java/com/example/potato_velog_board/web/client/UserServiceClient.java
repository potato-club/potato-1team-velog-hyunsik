package com.example.potato_velog_board.web.client;

import com.example.potato_velog_board.web.dto.response.user.UserFeignResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("potato-velog-user")
public interface UserServiceClient {

    @PostMapping("validateToken")
    String validateToken(@RequestBody String token);

    @GetMapping("getUser")
    UserFeignResponse getUser(@RequestParam String token);

}
