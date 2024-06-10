package com.nickwellman.bff.session.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash(value = "SessionDetails", timeToLive = 7200)
public class SessionDetails implements Serializable {
    @Id
    private String authToken;
    private String username;
    private int roles;
}
