package com.pragma.comments.application.validator;

import com.pragma.comments.domain.exception.InvalidCommentException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class CommentValidator {

    private static final int MIN_CONTENT_LENGTH = 10;
    private static final int MAX_CONTENT_LENGTH = 1000;
    private static final int MIN_RATING = 1;
    private static final int MAX_RATING = 5;
    private static final String INAPPROPRIATE_CONTENT_PATTERN = "(?i)(spam|scam|phishing|malware|offensive)";

    private final Validator validator;

    public CommentValidator(Validator validator) {
        this.validator = validator;
    }

    public void validateForCreation(String content, Integer rating, UUID productId, UUID userId) {
        if (content == null || content.isBlank()) {
            throw new InvalidCommentException("El contenido del comentario no puede estar vacío");
        }

        if (content.length() < MIN_CONTENT_LENGTH) {
            throw new InvalidCommentException(
                "El contenido debe tener al menos " + MIN_CONTENT_LENGTH + " caracteres");
        }

        if (content.length() > MAX_CONTENT_LENGTH) {
            throw new InvalidCommentException(
                "El contenido no puede exceder " + MAX_CONTENT_LENGTH + " caracteres");
        }

        if (containsInappropriateContent(content)) {
            throw new InvalidCommentException(
                "El contenido contiene palabras o frases no permitidas");
        }

        if (rating == null) {
            throw new InvalidCommentException("La calificación es obligatoria");
        }

        if (rating < MIN_RATING || rating > MAX_RATING) {
            throw new InvalidCommentException(
                "La calificación debe estar entre " + MIN_RATING + " y " + MAX_RATING);
        }

        if (productId == null) {
            throw new InvalidCommentException("El producto es obligatorio");
        }

        if (userId == null) {
            throw new InvalidCommentException("El usuario es obligatorio");
        }
    }

    public void validateForUpdate(String content, Integer rating) {
        if (content != null) {
            if (content.isBlank()) {
                throw new InvalidCommentException("El contenido no puede estar vacío");
            }

            if (content.length() < MIN_CONTENT_LENGTH) {
                throw new InvalidCommentException(
                    "El contenido debe tener al menos " + MIN_CONTENT_LENGTH + " caracteres");
            }

            if (content.length() > MAX_CONTENT_LENGTH) {
                throw new InvalidCommentException(
                    "El contenido no puede exceder " + MAX_CONTENT_LENGTH + " caracteres");
            }

            if (containsInappropriateContent(content)) {
                throw new InvalidCommentException(
                    "El contenido contiene palabras o frases no permitidas");
            }
        }

        if (rating != null && (rating < MIN_RATING || rating > MAX_RATING)) {
            throw new InvalidCommentException(
                "La calificación debe estar entre " + MIN_RATING + " y " + MAX_RATING);
        }
    }

    private boolean containsInappropriateContent(String content) {
        return content.matches(".*" + INAPPROPRIATE_CONTENT_PATTERN + ".*");
    }
}