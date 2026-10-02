package com.pragma.comments.infrastructure.rest.dto;

import com.pragma.comments.domain.model.Comment;

import java.time.LocalDateTime;
import java.util.UUID;

public record CommentResponse(
        UUID id,
        UUID productId,
        UUID userId,
        String content,
        Integer rating,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CommentResponse fromDomain(Comment comment) {
        return new CommentResponse(
                comment.id(),
                comment.productId(),
                comment.userId(),
                comment.content(),
                comment.rating(),
                comment.createdAt(),
                comment.updatedAt()
        );
    }
}