package com.messageGateway.controller;

import com.messageGateway.feign.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class GatewayMessagesController {
    @Autowired
    private User user;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping(value = "/api/messages/user/{userId}")
    public ResponseEntity<?> getAllMessagesOfTheUser(@PathVariable long userId) {
        if (user.getUserDetails(userId).getBody() instanceof String) {
            return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
        }
        String sql = "SELECT content FROM messages WHERE user_receiver_id = ?";
        List<String> contents = jdbcTemplate.queryForList(sql, new Object[]{userId}, String.class);
        return new ResponseEntity<>(contents,HttpStatus.OK);
    }
}
