package com.pragma.comments.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Excepción personalizada para manejar duplicados de comentarios.
 * Se lanza cuando se intenta crear un comentario que ya existe para el mismo usuario
 * en el mismo momento (timestamp), garantizando la idempotencia del sistema.
 * El sistema usa una clave única compuesta por el ID del usuario y el timestamp de creación
 * para detectar y prevenir duplicados.
 */
public class DuplicateCommentException extends RuntimeException {

    private final UUID userId;
    private final UUID productId;
    private final LocalDateTime createdAt;
    private final String existingCommentId;

    /**
     * Constructor básico con mensaje de error.
     * @param message Descripción del error de duplicado
     */
    public DuplicateCommentException(String message) {
        super(message);
        this.userId = null;
        this.productId = null;
        this.createdAt = null;
        this.existingCommentId = null;
    }

    /**
     * Constructor con causa raíz.
     * @param message Mensaje descriptivo
     * @param cause Excepción original
     */
    public DuplicateCommentException(String message, Throwable cause) {
        super(message, cause);
        this.userId = null;
        this.productId = null;
        this.createdAt = null;
        this.existingCommentId = null;
    }

    /**
     * Constructor con información del comentario duplicado.
     * @param userId ID del usuario que intentó crear el comentario
     * @param productId ID del producto
     * @param createdAt Timestamp del comentario
     */
    public DuplicateCommentException(UUID userId, UUID productId, LocalDateTime createdAt) {
        super("El comentario ya existe para el usuario " + userId + 
              " en el producto " + productId + " con fecha " + createdAt);
        this.userId = userId;
        this.productId = productId;
        this.createdAt = createdAt;
        this.existingCommentId = null;
    }

    /**
     * Constructor completo con ID del comentario existente.
     * @param userId ID del usuario
     * @param productId ID del producto
     * @param createdAt Timestamp del comentario
     * @param existingCommentId ID del comentario existente
     */
    public DuplicateCommentException(UUID userId, UUID productId, 
                                      LocalDateTime createdAt, String existingCommentId) {
        super("El comentario ya existe para el usuario " + userId + 
              " en el producto " + productId + ". Comentario existente: " + existingCommentId);
        this.userId = userId;
        this.productId = productId;
        this.createdAt = createdAt;
        this.existingCommentId = existingCommentId;
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getProductId() {
        return productId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getExistingCommentId() {
        return existingCommentId;
    }

    /**
     * Verifica si ya existe un comentario identificable.
     * @return true si se tiene el ID del comentario existente
     */
    public boolean hasExistingCommentId() {
        return existingCommentId != null && !existingCommentId.isBlank();
    }

    @Override
    public String toString() {
        return "DuplicateCommentException{" +
                "userId=" + userId +
                ", productId=" + productId +
                ", createdAt=" + createdAt +
                ", existingCommentId='" + existingCommentId + '\'' +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}