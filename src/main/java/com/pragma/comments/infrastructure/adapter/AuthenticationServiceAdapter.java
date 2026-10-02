package com.pragma.comments.infrastructure.adapter;

import com.pragma.comments.domain.exception.AuthenticationServiceException;
import com.pragma.comments.domain.port.AuthenticationServicePort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.UUID;

@Component
public class AuthenticationServiceAdapter implements AuthenticationServicePort {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceAdapter.class);
    private static final String SERVICE_UNAVAILABLE_MSG = "Authentication service is unavailable";
    private static final String VALIDATION_FAILED_MSG = "User validation failed";

    private final RestTemplate restTemplate;
    private final String authServiceBaseUrl;
    private final Duration connectionTimeout;
    private final Duration readTimeout;

    public AuthenticationServiceAdapter(
            RestTemplateBuilder restTemplateBuilder,
            @Value("${app.authentication-service.base-url:http://localhost:8081}") String authServiceBaseUrl,
            @Value("${app.authentication-service.connection-timeout:2000}") int connectionTimeoutMs,
            @Value("${app.authentication-service.read-timeout:5000}") int readTimeoutMs) {
        this.restTemplate = restTemplateBuilder
                .connectTimeout(Duration.ofMillis(connectionTimeoutMs))
                .readTimeout(Duration.ofMillis(readTimeoutMs))
                .build();
        this.authServiceBaseUrl = authServiceBaseUrl;
        this.connectionTimeout = Duration.ofMillis(connectionTimeoutMs);
        this.readTimeout = Duration.ofMillis(readTimeoutMs);
    }

    @Override
    public boolean validateUser(UUID userId) {
        if (userId == null) {
            log.warn("Attempted to validate null user ID");
            return false;
        }

        try {
            String url = authServiceBaseUrl + "/api/v1/users/" + userId + "/validate";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class);
            log.debug("User {} validation successful", userId);
            return true;
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("User {} not found in authentication service", userId);
            return false;
        } catch (Exception e) {
            log.error("Error validating user {}: {}", userId, e.getMessage());
            throw new AuthenticationServiceException(VALIDATION_FAILED_MSG, e);
        }
    }

    @Override
    public String getUserRole(UUID userId) {
        if (userId == null) {
            throw new AuthenticationServiceException("Cannot get role for null user ID");
        }

        try {
            String url = authServiceBaseUrl + "/api/v1/users/" + userId + "/role";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            String role = restTemplate.exchange(url, HttpMethod.GET, entity, String.class).getBody();
            log.debug("Retrieved role {} for user {}", role, userId);
            return role;
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("User {} not found when retrieving role", userId);
            return "UNKNOWN";
        } catch (Exception e) {
            log.error("Error retrieving role for user {}: {}", userId, e.getMessage());
            throw new AuthenticationServiceException(SERVICE_UNAVAILABLE_MSG, e);
        }
    }

    @Override
    public boolean isUserActive(UUID userId) {
        if (userId == null) {
            log.warn("Attempted to check active status for null user ID");
            return false;
        }

        try {
            String url = authServiceBaseUrl + "/api/v1/users/" + userId + "/status";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            Boolean active = restTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class).getBody();
            log.debug("User {} active status: {}", userId, active);
            return Boolean.TRUE.equals(active);
        } catch (HttpClientErrorException.NotFound e) {
            log.warn("User {} not found when checking active status", userId);
            return false;
        } catch (Exception e) {
            log.error("Error checking active status for user {}: {}", userId, e.getMessage());
            throw new AuthenticationServiceException(SERVICE_UNAVAILABLE_MSG, e);
        }
    }
}