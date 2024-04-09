package com.user.accounts.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_ACCOUNTS")
public class UserAccount {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private Long id;
        @Column(name = "USERNAME")
        private String username;
        @Column(name = "FIRST_NAME")
        private String firstName;
        @Column(name = "LAST_NAME")
        private String lastName;
        @Column(name = "AGE")
        private int age;
        @Column(name = "PASSWORD")
        private String password;
        @Column(name = "EMAIL")
        private String email;
}
