package com.user.feign;


import com.user.Dto.MessageDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("messenger")
public interface Messages {
    @GetMapping(value = "api/messenger/v1/messages/user/{userId}")
    public ResponseEntity<List<MessageDTO>> getAllMessagesOfTheUser(@PathVariable long userId);
}
