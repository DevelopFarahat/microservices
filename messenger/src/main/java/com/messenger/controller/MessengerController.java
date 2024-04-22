package com.messenger.controller;


import com.messenger.Dto.MessageDTO;
import com.messenger.entity.Message;
import com.messenger.feign.User;
import com.messenger.projection.MessageProjection;
import com.messenger.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messenger/v1")
public class MessengerController {
    @Autowired
    private User user;
    @Autowired
    private MessageRepository messageRepository;

    @GetMapping(value = "/messages/user/{userId}")
        public ResponseEntity<List<MessageDTO>> getAllMessagesOfTheUser(@PathVariable long userId) {
        List<MessageProjection> userReceivedMessages =  messageRepository.getAllMessages(userId);
       List<MessageDTO> messages =  userReceivedMessages.stream()
                .map(messageProjection -> new MessageDTO(messageProjection.getId(), messageProjection.getContent()))
                .collect(Collectors.toList());
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping(value = "/sendMessage/sender/{userSenderId}/receiver/{userReceiverId}")
    public ResponseEntity<?> sendMessage(@PathVariable long userSenderId, @PathVariable long userReceiverId, @RequestBody String messageContent) {
        //checking for the presence of the sender
        if ( user.getUserDetails(userSenderId).getBody() instanceof LinkedHashMap) {
            //checking for the presence of the receiver
            if (user.getUserDetails(userReceiverId).getBody() instanceof LinkedHashMap) {
                Message message = Message.builder()
                        .content(messageContent)
                        .user_sender_id(userSenderId)
                        .user_receiver_id(userReceiverId)
                        .build();
                messageRepository.save(message);
                return new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);



    }
}
