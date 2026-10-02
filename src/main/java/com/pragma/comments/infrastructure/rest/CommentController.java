package com.pragma.comments.infrastructure.rest;

import com.pragma.comments.application.service.CommentService;
import com.pragma.comments.domain.model.Comment;
import com.pragma.comments.domain.port.CommentRepository;
import com.pragma.comments.infrastructure.rest.dto.CommentCreateRequest;
import com.pragma.comments.infrastructure.rest.dto.CommentResponse;
import com.pragma.comments.infrastructure.rest.dto.CommentUpdateRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/comments")
@Tag(name = "Comments", description = "API for managing product comments")
public class CommentController {

    private static final Logger log = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;
    private final CommentRepository commentRepository;

    public CommentController(CommentService commentService, CommentRepository commentRepository) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
    }

    @PostMapping
    @Operation(summary = "Create a new comment", description = "Creates a new comment for a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid comment data", content = @Content),
            @ApiResponse(responseCode = "409", description = "Duplicate comment (idempotency conflict)", content = @Content),
            @ApiResponse(responseCode = "503", description = "Authentication service unavailable", content = @Content)
    })
    public ResponseEntity<CommentResponse> createComment(
            @Valid @RequestBody CommentCreateRequest request,
            @RequestHeader(value = "X-User-Id", required = true) UUID userId,
            @RequestHeader(value = "X-User-Role", required = false) String userRole) {
        log.info("Creating comment for product {} by user {}", request.productId(), userId);
        
        Comment comment = commentService.createComment(
                request.productId(),
                userId,
                request.content(),
                request.rating()
        );
        
        return ResponseEntity.status(HttpStatus.CREATED).body(CommentResponse.fromDomain(comment));
    }

    @GetMapping("/product/{productId}")
    @Operation(summary = "Get comments by product", description = "Retrieves all comments for a specific product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid product ID", content = @Content)
    })
    public ResponseEntity<List<CommentResponse>> getCommentsByProduct(
            @Parameter(description = "Product ID") @PathVariable UUID productId) {
        log.debug("Fetching comments for product {}", productId);
        
        List<Comment> comments = commentRepository.findByProductId(productId);
        List<CommentResponse> responses = comments.stream()
                .map(CommentResponse::fromDomain)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get comments by user", description = "Retrieves all comments made by a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user ID", content = @Content)
    })
    public ResponseEntity<List<CommentResponse>> getCommentsByUser(
            @Parameter(description = "User ID") @PathVariable UUID userId) {
        log.debug("Fetching comments for user {}", userId);
        
        List<Comment> comments = commentRepository.findByUserId(userId);
        List<CommentResponse> responses = comments.stream()
                .map(CommentResponse::fromDomain)
                .collect(Collectors.toList());
        
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{commentId}")
    @Operation(summary = "Get comment by ID", description = "Retrieves a specific comment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    public ResponseEntity<CommentResponse> getCommentById(
            @Parameter(description = "Comment ID") @PathVariable UUID commentId) {
        log.debug("Fetching comment {}", commentId);
        
        return commentRepository.findById(commentId)
                .map(comment -> ResponseEntity.ok(CommentResponse.fromDomain(comment)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{commentId}")
    @Operation(summary = "Update a comment", description = "Updates an existing comment's content and rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid comment data", content = @Content),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    public ResponseEntity<CommentResponse> updateComment(
            @Parameter(description = "Comment ID") @PathVariable UUID commentId,
            @Valid @RequestBody CommentUpdateRequest request,
            @RequestHeader(value = "X-User-Id", required = true) UUID userId) {
        log.info("Updating comment {} by user {}", commentId, userId);
        
        Comment updatedComment = commentService.updateComment(
                commentId,
                userId,
                request.content(),
                request.rating()
        );
        
        return ResponseEntity.ok(CommentResponse.fromDomain(updatedComment));
    }

    @DeleteMapping("/{commentId}")
    @Operation(summary = "Delete a comment", description = "Deletes a comment by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Comment not found", content = @Content)
    })
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "Comment ID") @PathVariable UUID commentId,
            @RequestHeader(value = "X-User-Id", required = true) UUID userId) {
        log.info("Deleting comment {} by user {}", commentId, userId);
        
        commentService.deleteComment(commentId, userId);
        
        return ResponseEntity.noContent().build();
    }
}