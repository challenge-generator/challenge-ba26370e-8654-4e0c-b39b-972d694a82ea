package com.pragma.comments.application.service;

import com.pragma.comments.application.validator.CommentValidator;
import com.pragma.comments.domain.exception.AuthenticationServiceException;
import com.pragma.comments.domain.exception.DuplicateCommentException;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.AuthenticationServicePort;
import com.pragma.comments.domain.port.CommentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CommentService {

    private static final Logger log = LoggerFactory.getLogger(CommentService.class);

    private final CommentRepository commentRepository;
    private final AuthenticationServicePort authenticationService;
    private final CommentValidator commentValidator;

    public CommentService(
            CommentRepository commentRepository,
            AuthenticationServicePort authenticationService,
            CommentValidator commentValidator) {
        this.commentRepository = commentRepository;
        this.authenticationService = authenticationService;
        this.commentValidator = commentValidator;
    }

    public Comment createComment(String content, Integer rating, UUID productId, UUID userId) {
        log.info("Creating comment for product {} by user {}", productId, userId);

        validateUserAuthentication(userId);

        commentValidator.validateForCreation(content, rating, productId, userId);

        checkIdempotency(userId);

        Comment comment = Comment.createNew(content, rating, productId, userId);
        Comment savedComment = commentRepository.save(comment);

        log.info("Comment created successfully with id {}", savedComment.id());
        return savedComment;
    }

    public Comment updateComment(UUID commentId, String newContent, Integer newRating, UUID userId) {
        log.info("Updating comment {} by user {}", commentId, userId);

        validateUserAuthentication(userId);

        Comment existingComment = commentRepository.findById(commentId)
            .orElseThrow(() -> new InvalidCommentException(
                "Comentario no encontrado con id: " + commentId));

        if (!existingComment.userId().equals(userId)) {
            throw new InvalidCommentException("No tienes permiso para modificar este comentario");
        }

        commentValidator.validateForUpdate(newContent, newRating);

        LocalDateTime updatedAt = LocalDateTime.now();
        Comment updatedComment = existingComment.updateContent(newContent, newRating, updatedAt);

        return commentRepository.save(updatedComment);
    }

    public void deleteComment(UUID commentId, UUID userId) {
        log.info("Deleting comment {} by user {}", commentId, userId);

        validateUserAuthentication(userId);

        Comment comment = commentRepository.findById(commentId)
            .orElseThrow(() -> new InvalidCommentException(
                "Comentario no encontrado con id: " + commentId));

        String userRole = authenticationService.getUserRole(userId);
        boolean isAdmin = "ADMIN".equalsIgnoreCase(userRole);

        if (!comment.userId().equals(userId) && !isAdmin) {
            throw new InvalidCommentException("No tienes permiso para eliminar este comentario");
        }

        commentRepository.delete(commentId);
        log.info("Comment {} deleted successfully", commentId);
    }

    public Optional<Comment> getCommentById(UUID commentId) {
        return commentRepository.findById(commentId);
    }

    public List<Comment> getCommentsByProduct(UUID productId) {
        log.debug("Fetching comments for product {}", productId);
        return commentRepository.findByProductId(productId);
    }

    public List<Comment> getCommentsByUser(UUID userId) {
        log.debug("Fetching comments for user {}", userId);
        return commentRepository.findByUserId(userId);
    }

    private void validateUserAuthentication(UUID userId) {
        try {
            if (!authenticationService.validateUser(userId)) {
                throw new AuthenticationServiceException(
                    "El usuario no es válido o no existe: " + userId);
            }

            if (!authenticationService.isUserActive(userId)) {
                throw new AuthenticationServiceException(
                    "El usuario no está activo: " + userId);
            }
        } catch (Exception e) {
            if (e instanceof AuthenticationServiceException) {
                throw (AuthenticationServiceException) e;
            }
            log.error("Error al validar el usuario {}: {}", userId, e.getMessage());
            throw new AuthenticationServiceException(
                "Error al validar el usuario: " + e.getMessage());
        }
    }

    private void checkIdempotency(UUID userId) {
        LocalDateTime now = LocalDateTime.now();
        boolean exists = commentRepository.existsByUserIdAndCreatedAt(userId, now);

        if (exists) {
            log.warn("Duplicate comment detected for user {} at {}", userId, now);
            throw new DuplicateCommentException(
                "Ya existe un comentario reciente del usuario. Intenta de nuevo en unos segundos.");
        }
    }
}