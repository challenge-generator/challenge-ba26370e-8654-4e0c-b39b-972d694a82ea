package com.pragma.comments.domain.port;

import java.util.UUID;

public interface AuthenticationServicePort {
    boolean validateUser(UUID userId);
    String getUserRole(UUID userId);
    boolean isUserActive(UUID userId);
}