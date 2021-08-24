package com.example.potato_velog_board.config;

import com.example.potato_velog_board.web.error.FeignErrorDecoder;
import feign.Logger;
import org.springframework.context.annotation.Bean;

public class SpringCloudConfig {

    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public FeignErrorDecoder getFeignErrorDecoder() {
        return new FeignErrorDecoder();
    }
}
