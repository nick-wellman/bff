package com.nickwellman.bff.session;

import com.nickwellman.bff.session.model.SessionDetails;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends CrudRepository<SessionDetails, String> {
}
