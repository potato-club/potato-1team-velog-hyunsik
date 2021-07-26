package com.velog.velogeureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class VelogEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(VelogEurekaApplication.class, args);
    }

}
