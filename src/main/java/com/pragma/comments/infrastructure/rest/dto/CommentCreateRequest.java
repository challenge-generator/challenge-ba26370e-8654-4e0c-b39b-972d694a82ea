package com.pragma.comments.infrastructure.rest.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public record CommentCreateRequest(
        @NotNull(message = "Product ID is required")
        UUID productId,
        @NotBlank(message = "Content cannot be blank")
        @Size(min = 1, max = 2000, message = "Content must be between 1 and 2000 characters")
        String content,
        @NotNull(message = "Rating is required")
        @Min(value = 1, message = "Rating must be at least 1")
        @Max(value = 5, message = "Rating must be at most 5")
        Integer rating
) {}