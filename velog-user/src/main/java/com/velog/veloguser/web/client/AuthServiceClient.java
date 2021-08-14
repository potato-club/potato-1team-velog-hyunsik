package com.velog.veloguser.web.client;

import com.velog.velogcommon.exception.NotFoundException;
import com.velog.velogcommon.user.dto.request.LoginRequest;
import com.velog.velogcommon.utils.TokenDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.io.IOException;

@FeignClient("potato-velog-auth")
public interface AuthServiceClient {

    @PostMapping("validateToken")
    Long validateToken(@RequestBody String token);

    @PostMapping("authenticate")
    TokenDto login(@RequestBody @Valid LoginRequest loginRequest);


    //테스트 코드용
    @PostMapping("getToken")
    TokenDto getToken(@RequestBody LoginRequest loginRequest);

}
