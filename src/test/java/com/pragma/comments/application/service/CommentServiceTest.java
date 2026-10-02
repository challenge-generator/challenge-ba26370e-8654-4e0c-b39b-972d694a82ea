package com.pragma.comments.application.service;

import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.AuthenticationServicePort;
import com.pragma.comments.domain.port.CommentRepository;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.exception.AuthenticationServiceException;
import com.pragma.comments.domain.exception.DuplicateCommentException;
import com.pragma.comments.application.validator.CommentValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CommentService - Pruebas Unitarias")
class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private AuthenticationServicePort authenticationService;

    @Mock
    private CommentValidator commentValidator;

    @InjectMocks
    private CommentService commentService;

    private UUID productId;
    private UUID userId;
    private Comment sampleComment;
    private LocalDateTime fixedTimestamp;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        userId = UUID.randomUUID();
        fixedTimestamp = LocalDateTime.now();
        sampleComment = Comment.createNew(
            productId,
            userId,
            "Excelente producto, muy recomendado",
            5
        );
    }

    @Nested
    @DisplayName("Crear comentario")
    class CreateComment {

        @Test
        @DisplayName("Debe crear comentario exitosamente cuando el usuario es válido")
        void shouldCreateCommentSuccessfully_WhenUserIsValid() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(true);
            when(commentRepository.existsByUserIdAndCreatedAt(any(), any())).thenReturn(false);
            when(commentRepository.save(any(Comment.class))).thenReturn(sampleComment);

            Comment result = commentService.createComment(productId, userId, "Excelente producto", 5);

            assertNotNull(result);
            assertEquals(productId, result.productId());
            assertEquals(userId, result.userId());
            verify(commentRepository).save(any(Comment.class));
            verify(authenticationService).validateUser(userId);
            verify(authenticationService).isUserActive(userId);
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el usuario no existe")
        void shouldThrowException_WhenUserDoesNotExist() {
            when(authenticationService.validateUser(userId)).thenReturn(false);

            assertThrows(AuthenticationServiceException.class, () ->
                commentService.createComment(productId, userId, "Contenido", 4)
            );

            verify(commentRepository, never()).save(any());
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el usuario está inactivo")
        void shouldThrowException_WhenUserIsInactive() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(false);

            assertThrows(AuthenticationServiceException.class, () ->
                commentService.createComment(productId, userId, "Contenido", 3)
            );

            verify(commentRepository, never()).save(any());
        }

        @Test
        @DisplayName("Debe lanzar excepción para contenido inválido")
        void shouldThrowException_WhenContentIsInvalid() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(true);
            doThrow(new InvalidCommentException("Contenido vacío"))
                .when(commentValidator).validateContent("");

            assertThrows(InvalidCommentException.class, () ->
                commentService.createComment(productId, userId, "", 4)
            );
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el rating está fuera de rango")
        void shouldThrowException_WhenRatingOutOfRange() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(true);
            doThrow(new InvalidCommentException("Rating debe estar entre 1 y 5"))
                .when(commentValidator).validateRating(0);

            assertThrows(InvalidCommentException.class, () ->
                commentService.createComment(productId, userId, "Contenido", 0)
            );
        }

        @Test
        @DisplayName("Debe lanzar excepción por comentario duplicado (idempotencia)")
        void shouldThrowException_WhenCommentIsDuplicate() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(true);
            when(commentRepository.existsByUserIdAndCreatedAt(any(), any())).thenReturn(true);

            assertThrows(DuplicateCommentException.class, () ->
                commentService.createComment(productId, userId, "Nuevo contenido", 5)
            );

            verify(commentRepository, never()).save(any());
        }
    }

    @Nested
    @DisplayName("Obtener comentarios")
    class GetComments {

        @Test
        @DisplayName("Debe obtener comentarios por ID de producto")
        void shouldGetCommentsByProductId() {
            List<Comment> comments = List.of(sampleComment);
            when(commentRepository.findByProductId(productId)).thenReturn(comments);

            List<Comment> result = commentService.getCommentsByProductId(productId);

            assertEquals(1, result.size());
            assertEquals(productId, result.get(0).productId());
            verify(commentRepository).findByProductId(productId);
        }

        @Test
        @DisplayName("Debe obtener comentarios por ID de usuario")
        void shouldGetCommentsByUserId() {
            List<Comment> comments = List.of(sampleComment);
            when(commentRepository.findByUserId(userId)).thenReturn(comments);

            List<Comment> result = commentService.getCommentsByUserId(userId);

            assertEquals(1, result.size());
            assertEquals(userId, result.get(0).userId());
            verify(commentRepository).findByUserId(userId);
        }

        @Test
        @DisplayName("Debe retornar lista vacía cuando no hay comentarios")
        void shouldReturnEmptyList_WhenNoCommentsExist() {
            when(commentRepository.findByProductId(productId)).thenReturn(List.of());

            List<Comment> result = commentService.getCommentsByProductId(productId);

            assertTrue(result.isEmpty());
            verify(commentRepository).findByProductId(productId);
        }
    }

    @Nested
    @DisplayName("Actualizar comentario")
    class UpdateComment {

        @Test
        @DisplayName("Debe actualizar comentario exitosamente")
        void shouldUpdateCommentSuccessfully() {
            UUID commentId = UUID.randomUUID();
            Comment updatedComment = sampleComment.updateContent("Contenido actualizado", 4, fixedTimestamp);

            when(commentRepository.findById(commentId)).thenReturn(Optional.of(sampleComment));
            when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);

            Comment result = commentService.updateComment(commentId, "Contenido actualizado", 4);

            assertNotNull(result);
            verify(commentRepository).findById(commentId);
            verify(commentRepository).save(any(Comment.class));
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el comentario no existe")
        void shouldThrowException_WhenCommentNotFound() {
            UUID commentId = UUID.randomUUID();
            when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () ->
                commentService.updateComment(commentId, "Nuevo contenido", 5)
            );
        }
    }

    @Nested
    @DisplayName("Eliminar comentario")
    class DeleteComment {

        @Test
        @DisplayName("Debe eliminar comentario exitosamente")
        void shouldDeleteCommentSuccessfully() {
            UUID commentId = UUID.randomUUID();
            when(commentRepository.findById(commentId)).thenReturn(Optional.of(sampleComment));
            doNothing().when(commentRepository).delete(commentId);

            assertDoesNotThrow(() -> commentService.deleteComment(commentId));

            verify(commentRepository).findById(commentId);
            verify(commentRepository).delete(commentId);
        }

        @Test
        @DisplayName("Debe lanzar excepción cuando el comentario no existe para eliminar")
        void shouldThrowException_WhenCommentNotFoundToDelete() {
            UUID commentId = UUID.randomUUID();
            when(commentRepository.findById(commentId)).thenReturn(Optional.empty());

            assertThrows(RuntimeException.class, () ->
                commentService.deleteComment(commentId)
            );
        }
    }

    @Nested
    @DisplayName("Validación de contenido inapropiado")
    class ContentValidation {

        @Test
        @DisplayName("Debe rechazar contenido con palabras prohibidas")
        void shouldRejectContentWithForbiddenWords() {
            when(authenticationService.validateUser(userId)).thenReturn(true);
            when(authenticationService.isUserActive(userId)).thenReturn(true);
            doThrow(new InvalidCommentException("Contenido inapropiado detectado"))
                .when(commentValidator).validateContent(anyString());

            assertThrows(InvalidCommentException.class, () ->
                commentService.createComment(productId, userId, "contenido inapropiado", 3)
            );
        }
    }
}