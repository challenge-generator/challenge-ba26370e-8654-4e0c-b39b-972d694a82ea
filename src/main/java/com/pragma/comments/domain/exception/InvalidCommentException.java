package com.pragma.comments.domain.exception;

import java.util.List;

/**
 * Excepción personalizada para manejar errores de validación de comentarios.
 * Se lanza cuando el contenido del comentario no cumple con las reglas de validación
 * establecidas en el sistema, como longitud mínima/máxima, contenido inapropiado,
 * o rating fuera de los límites permitidos.
 */
public class InvalidCommentException extends RuntimeException {

    private final String fieldName;
    private final Object rejectedValue;
    private final List<String> validationErrors;

    /**
     * Constructor con mensaje simple.
     * @param message Descripción del error de validación
     */
    public InvalidCommentException(String message) {
        super(message);
        this.fieldName = null;
        this.rejectedValue = null;
        this.validationErrors = List.of(message);
    }

    /**
     * Constructor con mensaje y causa raíz.
     * @param message Descripción del error
     * @param cause Excepción original que causó el error
     */
    public InvalidCommentException(String message, Throwable cause) {
        super(message, cause);
        this.fieldName = null;
        this.rejectedValue = null;
        this.validationErrors = List.of(message);
    }

    /**
     * Constructor con información detallada del campo que falló.
     * @param fieldName Nombre del campo que no pasó la validación
     * @param rejectedValue Valor que fue rechazado
     * @param message Mensaje descriptivo del error
     */
    public InvalidCommentException(String fieldName, Object rejectedValue, String message) {
        super(message);
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
        this.validationErrors = List.of(message);
    }

    /**
     * Constructor con múltiples errores de validación.
     * Útil cuando se validan varios campos simultáneamente.
     * @param validationErrors Lista de mensajes de error
     */
    public InvalidCommentException(List<String> validationErrors) {
        super("Validación de comentario fallida: " + String.join("; ", validationErrors));
        this.fieldName = null;
        this.rejectedValue = null;
        this.validationErrors = validationErrors;
    }

    /**
     * Constructor completo con toda la información de validación.
     * @param fieldName Nombre del campo que falló
     * @param rejectedValue Valor rechazado
     * @param message Mensaje de error
     * @param validationErrors Lista de errores
     */
    public InvalidCommentException(String fieldName, Object rejectedValue, 
                                    String message, List<String> validationErrors) {
        super(message);
        this.fieldName = fieldName;
        this.rejectedValue = rejectedValue;
        this.validationErrors = validationErrors;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getRejectedValue() {
        return rejectedValue;
    }

    public List<String> getValidationErrors() {
        return validationErrors;
    }

    @Override
    public String toString() {
        return "InvalidCommentException{" +
                "fieldName='" + fieldName + '\'' +
                ", rejectedValue=" + rejectedValue +
                ", validationErrors=" + validationErrors +
                ", message='" + getMessage() + '\'' +
                '}';
    }
}