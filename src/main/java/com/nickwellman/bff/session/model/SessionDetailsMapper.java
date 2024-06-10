package com.nickwellman.bff.session.model;

import com.nickwellman.bff.login.model.LoginResponse;

public class SessionDetailsMapper {
    public static SessionDetails fromLoginResponse(final LoginResponse response) {
        return SessionDetails.builder().roles(response.getRoles()).username(response.getUsername()).build();
    }
}
