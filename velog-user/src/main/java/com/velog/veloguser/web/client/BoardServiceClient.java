package com.velog.veloguser.web.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="potato-velog-board")
public interface BoardServiceClient {


}
