package com.example.potato_velog_eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PotatoVelogEurekaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PotatoVelogEurekaApplication.class, args);
    }

}
