package com.nickwellman.bff.login;

import com.nickwellman.bff.configuration.ProjectConstants;
import com.nickwellman.bff.login.model.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Validated
public class LoginController {

    private final LoginService service;

    public LoginController(final LoginService service) {
        this.service = service;
    }

    @PostMapping("/api/v1/login")
    public Object login(@RequestBody final LoginRequest request) {
        log.info("Login request for: " + request);
        return service.login(request);
    }

    @GetMapping("/api/v1/logout")
    public void logout(@RequestHeader(ProjectConstants.AUTH_TOKEN) String token) {
        service.logout(token);
    }

    @GetMapping("/api/v1/users")
    public Object getUsersById(@RequestParam("role") final int role) {
        return service.getUsersByRole(role);
    }
}
