package com.messenger.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user")
public interface User {

    @GetMapping(value = "api/user/v1/userDetails/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable long userId);


}
