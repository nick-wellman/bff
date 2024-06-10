package com.nickwellman.bff.session;

import com.nickwellman.bff.session.model.SessionDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class SessionManager {

    private final SessionRepository sessionRepository;

    public SessionManager(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    public SessionDetails getSessionDetails(String token) {
        return sessionRepository.findById(token).orElse(null);
    }

    public String handleLogin(final SessionDetails sessionDetails) {
        String token = UUID.randomUUID().toString();

        sessionDetails.setAuthToken(token);
        sessionRepository.save(sessionDetails);

        return token;
    }

    public void handleLogout(String token) {
        Optional<SessionDetails> deets = sessionRepository.findById(token);
        log.info(deets.toString());
        deets.ifPresent(sessionRepository::delete);
    }
}
