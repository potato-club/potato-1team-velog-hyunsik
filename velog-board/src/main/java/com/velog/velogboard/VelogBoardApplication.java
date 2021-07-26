package com.velog.velogboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class VelogBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(VelogBoardApplication.class, args);
    }

}
