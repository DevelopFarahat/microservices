package com.user.accounts.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("ms2")
public interface Ms1 {
    @GetMapping(value = "/api/ms2/sayHello")
    public ResponseEntity<String> sayHelloFromMs2();
}
