package com.velog.velogboard.web.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("potato-velog-user")
public interface UserServiceClient {

}
