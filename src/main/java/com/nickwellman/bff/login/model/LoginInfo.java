package com.nickwellman.bff.login.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginInfo {
    private String username;
    private int roles;
    private String authToken;
}
