package com.user.controller;


import com.user.Dto.MessageDTO;
import com.user.entity.User;
import com.user.feign.Messages;
import com.user.projection.UserDetailsProjection;
import com.user.repository.UserRepository;
import com.user.request.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/user/v1")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private Messages messages;

    @PostMapping(value = "/account/create")
    public ResponseEntity<String> createUserAccount(@RequestBody User user) {
        userRepository.save(user);
        return new ResponseEntity<>("Account Created Successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/login")
    public ResponseEntity<?> loginToTheUserAccount(@RequestBody LoginData loginData) {
        Optional<User> userAccount = userRepository.findByUsername(loginData.getUsername());
        if (userAccount.isPresent())
            return new ResponseEntity<>(userAccount.get(), HttpStatus.OK);
        else return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/userDetails/{userId}")
    public ResponseEntity<?> getUserDetails(@PathVariable long userId) {

        Optional<UserDetailsProjection> userDetailsOpt = userRepository.findUserDetailsById(userId);
        if (userDetailsOpt.isPresent()) {
            return new ResponseEntity<>(userDetailsOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/userMessages/userIdentity/{userId}")
    public ResponseEntity<?> getUserMessages(@PathVariable Long userId) {
        Optional<UserDetailsProjection> userDetailsProjectionOpt = userRepository.findUserDetailsById(userId);
        if (userDetailsProjectionOpt.isPresent()) {
            List<MessageDTO> userMessages = messages.getAllMessagesOfTheUser(userId).getBody();
            return new ResponseEntity<>(userMessages, HttpStatus.OK);
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }
}
