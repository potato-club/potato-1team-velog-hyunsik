package com.velog.velogboard.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("potato-velog-auth")
public interface AuthServiceClient {

    @PostMapping("validateToken")
    String validateToken(@RequestBody String token);
}
