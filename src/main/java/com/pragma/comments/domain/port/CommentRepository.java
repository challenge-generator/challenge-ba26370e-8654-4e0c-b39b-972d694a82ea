package com.pragma.comments.domain.port;

import com.pragma.comments.domain.model.Comment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentRepository {
    Optional<Comment> findById(UUID id);
    List<Comment> findByProductId(UUID productId);
    List<Comment> findByUserId(UUID userId);
    Optional<Comment> findByUserIdAndCreatedAt(UUID userId, java.time.LocalDateTime createdAt);
    Comment save(Comment comment);
    void delete(UUID id);
    boolean existsByUserIdAndCreatedAt(UUID userId, java.time.LocalDateTime createdAt);
}
"