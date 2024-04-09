package com.messageGateway.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("useraccounts")
public interface User {

    @GetMapping(value = "/api/user/accounts/getUserDetails/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable long userId);
}
