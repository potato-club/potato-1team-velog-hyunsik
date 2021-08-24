package com.example.potato_velog_user.web.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient("potato-velog-board")
public interface BoardServiceClient {


}
