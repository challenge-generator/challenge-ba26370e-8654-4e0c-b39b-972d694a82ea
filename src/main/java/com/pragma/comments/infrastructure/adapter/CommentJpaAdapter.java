package com.pragma.comments.infrastructure.adapter;

import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.CommentRepository;
import jakarta.persistence.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class CommentJpaAdapter implements CommentRepository {

    private final CommentJpaRepository jpaRepository;

    public CommentJpaAdapter(CommentJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<Comment> findById(UUID id) {
        return jpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public List<Comment> findByProductId(UUID productId) {
        return jpaRepository.findByProductId(productId).stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public List<Comment> findByUserId(UUID userId) {
        return jpaRepository.findByUserId(userId).stream()
            .map(this::toDomain)
            .collect(Collectors.toList());
    }

    @Override
    public Optional<Comment> findByUserIdAndCreatedAt(UUID userId, LocalDateTime createdAt) {
        return jpaRepository.findByUserIdAndCreatedAt(userId, createdAt).map(this::toDomain);
    }

    @Override
    public Comment save(Comment comment) {
        CommentEntity entity = toEntity(comment);
        CommentEntity saved = jpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public void delete(UUID id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public boolean existsByUserIdAndCreatedAt(UUID userId, LocalDateTime createdAt) {
        return jpaRepository.existsByUserIdAndCreatedAt(userId, createdAt);
    }

    private Comment toDomain(CommentEntity entity) {
        return new Comment(
            entity.getId(),
            entity.getContent(),
            entity.getRating(),
            entity.getProductId(),
            entity.getUserId(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }

    private CommentEntity toEntity(Comment comment) {
        CommentEntity entity = new CommentEntity();
        if (comment.id() != null) {
            entity.setId(comment.id());
        }
        entity.setContent(comment.content());
        entity.setRating(comment.rating());
        entity.setProductId(comment.productId());
        entity.setUserId(comment.userId());
        entity.setCreatedAt(comment.createdAt());
        entity.setUpdatedAt(comment.updatedAt());
        return entity;
    }

    @Entity
    @Table(name = "comments")
    public static class CommentEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.UUID)
        private UUID id;

        @Column(nullable = false, length = 1000)
        private String content;

        @Column(nullable = false)
        private Integer rating;

        @Column(name = "product_id", nullable = false)
        private UUID productId;

        @Column(name = "user_id", nullable = false)
        private UUID userId;

        @Column(name = "created_at", nullable = false)
        private LocalDateTime createdAt;

        @Column(name = "updated_at")
        private LocalDateTime updatedAt;

        public UUID getId() {
            return id;
        }

        public void setId(UUID id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public UUID getProductId() {
            return productId;
        }

        public void setProductId(UUID productId) {
            this.productId = productId;
        }

        public UUID getUserId() {
            return userId;
        }

        public void setUserId(UUID userId) {
            this.userId = userId;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
        }

        public LocalDateTime getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
        }
    }

    interface CommentJpaRepository extends org.springframework.data.jpa.repository.JpaRepository<CommentEntity, UUID> {
        List<CommentEntity> findByProductId(UUID productId);
        List<CommentEntity> findByUserId(UUID userId);
        Optional<CommentEntity> findByUserIdAndCreatedAt(UUID userId, LocalDateTime createdAt);
        boolean existsByUserIdAndCreatedAt(UUID userId, LocalDateTime createdAt);
    }
}