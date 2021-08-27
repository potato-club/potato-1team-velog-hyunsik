package com.example.potato_velog_board.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("potato-velog-user")
public interface UserServiceClient {
    @PostMapping("validateToken")
    String validateToken(@RequestBody String token);

}
