package com.nickwellman.bff.login;

import com.nickwellman.bff.login.model.LoginInfo;
import com.nickwellman.bff.login.model.LoginRequest;
import com.nickwellman.bff.login.model.LoginResponse;
import com.nickwellman.bff.session.SessionManager;
import com.nickwellman.bff.session.model.SessionDetails;
import com.nickwellman.bff.session.model.SessionDetailsMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginClient client;
    private final SessionManager sessionManager;

    public LoginService(final LoginClient client, final SessionManager sessionManager) {
        this.client = client;
        this.sessionManager = sessionManager;
    }

    public LoginInfo login(final LoginRequest request) {
        final LoginResponse response = client.login(request);

        if (response.isCredentialsValid() && response.isEnabled()) {
            final SessionDetails sessionDetails = SessionDetailsMapper.fromLoginResponse(response);
            String token = sessionManager.handleLogin(sessionDetails);
            return new LoginInfo(sessionDetails.getUsername(), sessionDetails.getRoles(), token);
        }

        return null;
    }

    public void logout(String token) {
        sessionManager.handleLogout(token);
    }

    public Object getUsersByRole(final int role) {
        return client.getUsersByRole(role);
    }
}
