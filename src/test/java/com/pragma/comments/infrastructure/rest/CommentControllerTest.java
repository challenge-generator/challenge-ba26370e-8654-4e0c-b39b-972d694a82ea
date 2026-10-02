package com.pragma.comments.infrastructure.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pragma.comments.application.service.CommentService;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.exception.InvalidCommentException;
import com.pragma.comments.domain.exception.AuthenticationServiceException;
import com.pragma.comments.domain.exception.DuplicateCommentException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@DisplayName("CommentController - Pruebas de Integración")
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CommentService commentService;

    private UUID productId;
    private UUID userId;
    private UUID commentId;
    private Comment sampleComment;
    private String sampleContent;
    private Integer sampleRating;

    @BeforeEach
    void setUp() {
        productId = UUID.randomUUID();
        userId = UUID.randomUUID();
        commentId = UUID.randomUUID();
        sampleContent = "Excelente producto, muy recomendado";
        sampleRating = 5;
        sampleComment = Comment.createNew(productId, userId, sampleContent, sampleRating);
    }

    @Nested
    @DisplayName("POST /api/comments - Crear comentario")
    class CreateComment {

        @Test
        @DisplayName("Debe retornar 201 Created cuando el comentario se crea exitosamente")
        void shouldReturn201_WhenCommentCreatedSuccessfully() throws Exception {
            when(commentService.createComment(any(), any(), anyString(), anyInt()))
                .thenReturn(sampleComment);

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "productId": "%s",
                            "userId": "%s",
                            "content": "%s",
                            "rating": %d
                        }
                        """.formatted(productId, userId, sampleContent, sampleRating)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").value(productId.toString()))
                .andExpect(jsonPath("$.userId").value(userId.toString()))
                .andExpect(jsonPath("$.content").value(sampleContent))
                .andExpect(jsonPath("$.rating").value(sampleRating));

            verify(commentService).createComment(eq(productId), eq(userId), eq(sampleContent), eq(sampleRating));
        }

        @Test
        @DisplayName("Debe retornar 400 Bad Request cuando el contenido está vacío")
        void shouldReturn400_WhenContentIsEmpty() throws Exception {
            when(commentService.createComment(any(), any(), anyString(), anyInt()))
                .thenThrow(new InvalidCommentException("El contenido no puede estar vacío"));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "productId": "%s",
                            "userId": "%s",
                            "content": "",
                            "rating": %d
                        }
                        """.formatted(productId, userId, sampleRating)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe retornar 401 Unauthorized cuando el usuario no existe")
        void shouldReturn401_WhenUserDoesNotExist() throws Exception {
            when(commentService.createComment(any(), any(), anyString(), anyInt()))
                .thenThrow(new AuthenticationServiceException("Usuario no encontrado"));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "productId": "%s",
                            "userId": "%s",
                            "content": "%s",
                            "rating": %d
                        }
                        """.formatted(productId, userId, sampleContent, sampleRating)))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe retornar 409 Conflict cuando el comentario es duplicado")
        void shouldReturn409_WhenCommentIsDuplicate() throws Exception {
            when(commentService.createComment(any(), any(), anyString(), anyInt()))
                .thenThrow(new DuplicateCommentException("Ya existe un comentario para este usuario"));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "productId": "%s",
                            "userId": "%s",
                            "content": "%s",
                            "rating": %d
                        }
                        """.formatted(productId, userId, sampleContent, sampleRating)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe retornar 400 Bad Request cuando el rating está fuera de rango")
        void shouldReturn400_WhenRatingOutOfRange() throws Exception {
            when(commentService.createComment(any(), any(), anyString(), anyInt()))
                .thenThrow(new InvalidCommentException("Rating debe estar entre 1 y 5"));

            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "productId": "%s",
                            "userId": "%s",
                            "content": "%s",
                            "rating": 10
                        }
                        """.formatted(productId, userId, sampleContent)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error").exists());
        }

        @Test
        @DisplayName("Debe retornar 400 Bad Request cuando el JSON está malformado")
        void shouldReturn400_WhenJsonIsMalformed() throws Exception {
            mockMvc.perform(post("/api/comments")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("{ invalid json }"))
                .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("GET /api/comments/product/{productId} - Obtener comentarios por producto")
    class GetCommentsByProduct {

        @Test
        @DisplayName("Debe retornar 200 OK con lista de comentarios")
        void shouldReturn200_WithCommentsList() throws Exception {
            List<Comment> comments = List.of(sampleComment);
            when(commentService.getCommentsByProductId(productId)).thenReturn(comments);

            mockMvc.perform(get("/api/comments/product/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].productId").value(productId.toString()));

            verify(commentService).getCommentsByProductId(productId);
        }

        @Test
        @DisplayName("Debe retornar 200 OK con lista vacía cuando no hay comentarios")
        void shouldReturn200_WithEmptyList() throws Exception {
            when(commentService.getCommentsByProductId(productId)).thenReturn(List.of());

            mockMvc.perform(get("/api/comments/product/{productId}", productId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(0));

            verify(commentService).getCommentsByProductId(productId);
        }
    }

    @Nested
    @DisplayName("GET /api/comments/user/{userId} - Obtener comentarios por usuario")
    class GetCommentsByUser {

        @Test
        @DisplayName("Debe retornar 200 OK con lista de comentarios del usuario")
        void shouldReturn200_WithUserCommentsList() throws Exception {
            List<Comment> comments = List.of(sampleComment);
            when(commentService.getCommentsByUserId(userId)).thenReturn(comments);

            mockMvc.perform(get("/api/comments/user/{userId}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].userId").value(userId.toString()));

            verify(commentService).getCommentsByUserId(userId);
        }
    }

    @Nested
    @DisplayName("PUT /api/comments/{commentId} - Actualizar comentario")
    class UpdateComment {

        @Test
        @DisplayName("Debe retornar 200 OK cuando el comentario se actualiza exitosamente")
        void shouldReturn200_WhenCommentUpdatedSuccessfully() throws Exception {
            String updatedContent = "Contenido actualizado";
            Integer updatedRating = 4;
            Comment updatedComment = sampleComment.updateContent(updatedContent, updatedRating, LocalDateTime.now());

            when(commentService.updateComment(eq(commentId), anyString(), anyInt()))
                .thenReturn(updatedComment);

            mockMvc.perform(put("/api/comments/{commentId}", commentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "content": "%s",
                            "rating": %d
                        }
                        """.formatted(updatedContent, updatedRating)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value(updatedContent))
                .andExpect(jsonPath("$.rating").value(updatedRating));

            verify(commentService).updateComment(commentId, updatedContent, updatedRating);
        }

        @Test
        @DisplayName("Debe retornar 404 Not Found cuando el comentario no existe")
        void shouldReturn404_WhenCommentNotFound() throws Exception {
            when(commentService.updateComment(eq(commentId), anyString(), anyInt()))
                .thenThrow(new RuntimeException("Comentario no encontrado"));

            mockMvc.perform(put("/api/comments/{commentId}", commentId)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content("""
                        {
                            "content": "Nuevo contenido",
                            "rating": 5
                        }
                        """))
                .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("DELETE /api/comments/{commentId} - Eliminar comentario")
    class DeleteComment {

        @Test
        @DisplayName("Debe retornar 204 No Content cuando el comentario se elimina exitosamente")
        void shouldReturn204_WhenCommentDeletedSuccessfully() throws Exception {
            doNothing().when(commentService).deleteComment(commentId);

            mockMvc.perform(delete("/api/comments/{commentId}", commentId))
                .andExpect(status().isNoContent());

            verify(commentService).deleteComment(commentId);
        }

        @Test
        @DisplayName("Debe retornar 404 Not Found cuando el comentario a eliminar no existe")
        void shouldReturn404_WhenCommentToDeleteNotFound() throws Exception {
            doThrow(new RuntimeException("Comentario no encontrado"))
                .when(commentService).deleteComment(commentId);

            mockMvc.perform(delete("/api/comments/{commentId}", commentId))
                .andExpect(status().isNotFound());
        }
    }
}