package com.pragma.comments.domain.model;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.UUID;

public record Comment(
    UUID id,
    UUID productId,
    UUID userId,
    @NotBlank(message = "El contenido del comentario no puede estar vacío")
    @Size(max = 1000, message = "El contenido del comentario no puede exceder los 1000 caracteres")
    String content,
    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    Integer rating,
    @NotNull(message = "La fecha de creación es obligatoria")
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
    public Comment {
        if (content != null && content.length() > 1000) {
            throw new IllegalArgumentException("El contenido del comentario no puede exceder los 1000 caracteres");
        }
        if (rating != null && (rating < 1 || rating > 5)) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5");
        }
    }

    public static Comment createNew(
            UUID productId,
            UUID userId,
            String content,
            Integer rating,
            LocalDateTime createdAt) {
        return new Comment(
                UUID.randomUUID(),
                productId,
                userId,
                content,
                rating,
                createdAt,
                null
        );
    }

    public Comment updateContent(String newContent, Integer newRating, LocalDateTime updatedAt) {
        if (newContent == null || newContent.trim().isEmpty()) {
            throw new IllegalArgumentException("El contenido no puede estar vacío");
        }
        if (newRating == null || newRating < 1 || newRating > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5");
        }
        return new Comment(
                this.id,
                this.productId,
                this.userId,
                newContent,
                newRating,
                this.createdAt,
                updatedAt
        );
    }

    public boolean isSameAs(UUID userId, LocalDateTime createdAt) {
        return this.userId.equals(userId) && this.createdAt.equals(createdAt);
    }
}
"