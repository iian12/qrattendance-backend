package com.dju.qrattendence.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequest {

    private String username;
    private String password;

    LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
