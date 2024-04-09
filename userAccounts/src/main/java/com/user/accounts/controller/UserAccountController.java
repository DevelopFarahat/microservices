package com.user.accounts.controller;

import com.user.accounts.entity.Message;
import com.user.accounts.entity.UserAccount;
import com.user.accounts.repository.MessageRepository;
import com.user.accounts.repository.UserAccountRepository;
import com.user.accounts.request.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserAccountController {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private MessageRepository messageRepository;

    @PostMapping(value = "/api/user/accounts/create")
    public ResponseEntity<String> createUserAccount(@RequestBody UserAccount UserAccount) {
        userAccountRepository.save(UserAccount);
        return new ResponseEntity<>("Account Created Successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/api/user/accounts/login")
    public ResponseEntity<?> loginToTheUserAccount(@RequestBody LoginData loginData) {
        Optional<UserAccount> userAccount = userAccountRepository.findByUsername(loginData.getUsername());
        if (userAccount.isPresent())
            return new ResponseEntity<>(userAccount.get(), HttpStatus.OK);
        else return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/user/accounts/getUserDetails/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable long userId) {
        Optional<UserAccount> userAccount = userAccountRepository.findById(userId);
        if (userAccount.isPresent())
            return new ResponseEntity<>(userAccount.get(), HttpStatus.OK);
        else return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/api/messageGateway/sendMessage/sender/{userSenderId}/receiver/{userReceiverId}")
    public ResponseEntity<String> sendMessage(@PathVariable long userSenderId, @PathVariable long userReceiverId, @RequestBody String messageContent) {
        Optional<UserAccount> sender = userAccountRepository.findById(userSenderId);
        if (sender.isPresent()) {
            Optional<UserAccount> receiver = userAccountRepository.findById(userReceiverId);
            if (receiver.isPresent()) {
                Message message = Message.builder()
                        .content(messageContent)
                        .user(sender.get())
                        .user_receiver_id(userReceiverId)
                        .build();
                messageRepository.save(message);
                return  new ResponseEntity<>("Message sent successfully", HttpStatus.OK);
            }
          return   new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);

    }


}
