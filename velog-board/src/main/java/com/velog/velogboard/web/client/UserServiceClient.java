package com.velog.velogboard.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "potato-velog-user")
public interface UserServiceClient {

//    @GetMapping("/potato-velog-user/login")
}
