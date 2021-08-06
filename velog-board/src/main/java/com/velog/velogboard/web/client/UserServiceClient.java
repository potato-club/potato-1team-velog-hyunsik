package com.velog.velogboard.web.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "localhost:8070")
public interface UserServiceClient {

}
