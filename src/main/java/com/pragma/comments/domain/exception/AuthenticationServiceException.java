package com.pragma.comments.domain.exception;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Excepción personalizada para manejar errores del servicio de autenticación.
 * Se lanza cuando el servicio de autenticación no puede validar las credenciales del usuario,
 * cuando hay problemas de conexión, timeouts, o cuando el usuario no existe o no está activo.
 * Esta excepción ayuda a distinguir entre errores de autenticación y otros tipos de errores
 * en el sistema de comentarios.
 */
public class AuthenticationServiceException extends RuntimeException {

    private final UUID userId;
    private final String operation;
    private final ErrorType errorType;
    private final LocalDateTime timestamp;

    /**
     * Tipos de errores posibles en el servicio de autenticación.
     */
    public enum ErrorType {
        CONNECTION_ERROR,
        TIMEOUT,
        USER_NOT_FOUND,
        USER_INACTIVE,
        INVALID_TOKEN,
        SERVICE_UNAVAILABLE,
        UNKNOWN_ERROR
    }

    /**
     * Constructor básico con mensaje de error.
     * @param message Descripción del error de autenticación
     */
    public AuthenticationServiceException(String message) {
        super(message);
        this.userId = null;
        this.operation = null;
        this.errorType = ErrorType.UNKNOWN_ERROR;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor con causa raíz.
     * @param message Descripción del error
     * @param cause Excepción original que causó el error
     */
    public AuthenticationServiceException(String message, Throwable cause) {
        super(message, cause);
        this.userId = null;
        this.operation = null;
        this.errorType = ErrorType.UNKNOWN_ERROR;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor con información del usuario y tipo de error.
     * @param userId ID del usuario que no pudo ser validado
     * @param errorType Tipo específico de error de autenticación
     * @param message Mensaje descriptivo
     */
    public AuthenticationServiceException(UUID userId, ErrorType errorType, String message) {
        super(message);
        this.userId = userId;
        this.operation = null;
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor completo con toda la información del error.
     * @param userId ID del usuario
     * @param operation Operación que se intentaba realizar
     * @param errorType Tipo de error
     * @param message Mensaje descriptivo
     */
    public AuthenticationServiceException(UUID userId, String operation, 
                                          ErrorType errorType, String message) {
        super(message);
        this.userId = userId;
        this.operation = operation;
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Constructor con causa raíz y tipo de error.
     * @param message Mensaje de error
     * @param cause Excepción original
     * @param errorType Tipo de error
     */
    public AuthenticationServiceException(String message, Throwable cause, ErrorType errorType) {
        super(message, cause);
        this.userId = null;
        this.operation = null;
        this.errorType = errorType;
        this.timestamp = LocalDateTime.now();
    }

    public UUID getUserId() {
        return userId;
    }

    public String getOperation() {
        return operation;
    }

    public ErrorType getErrorType() {
        return errorType;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Verifica si el error es de tipo conexión.
     * @return true si es error de conexión
     */
    public boolean isConnectionError() {
        return errorType == ErrorType.CONNECTION_ERROR;
    }

    /**
     * Verifica si el error es de timeout.
     * @return true si es error de timeout
     */
    public boolean isTimeout() {
        return errorType == ErrorType.TIMEOUT;
    }

    /**
     * Verifica si el servicio está temporalmente no disponible.
     * @return true si el servicio no está disponible
     */
    public boolean isServiceUnavailable() {
        return errorType == ErrorType.SERVICE_UNAVAILABLE;
    }

    @Override
    public String toString() {
        return "AuthenticationServiceException{" +
                "userId=" + userId +
                ", operation='" + operation + '\'' +
                ", errorType=" + errorType +
                ", timestamp=" + timestamp +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}