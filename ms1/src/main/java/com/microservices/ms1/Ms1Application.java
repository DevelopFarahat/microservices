package com.microservices.ms1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Ms1Application {

    public static void main(String[] args) {
        SpringApplication.run(Ms1Application.class, args);
    }

/*
    @RestController
    class ms1Controller{
        @GetMapping(value = "/api/sayHello")
        public String sayHelloFromMs1(){
            return "hello from ms1";
        }
    }

 */


}
