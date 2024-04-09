package com.user.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class UserAccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAccountsApplication.class, args);
    }



}
