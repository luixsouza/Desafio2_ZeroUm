package com.compass.Desafio2_MicroserviceA.controller;

import com.compass.Desafio2_MicroserviceA.dto.PostDTO;
import com.compass.Desafio2_MicroserviceA.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Operation(summary = "Retrieve all posts",
            responses = {
                    @ApiResponse(description = "List of posts",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostDTO.class)))
            })
    @GetMapping
    public ResponseEntity<List<PostDTO>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @Operation(summary = "Retrieve a post by ID",
            responses = {
                    @ApiResponse(description = "Post found",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostDTO.class))),
                    @ApiResponse(description = "Post not found",
                            responseCode = "404")
            })
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@Parameter(description = "ID of the post to be retrieved")
                                               @PathVariable Long id) {
        return ResponseEntity.ok(postService.getPostById(id));
    }

    @Operation(summary = "Create a new post",
            responses = {
                    @ApiResponse(description = "Post created",
                            responseCode = "201",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostDTO.class)))
            })
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.createPost(postDTO));
    }

    @Operation(summary = "Update a post",
            responses = {
                    @ApiResponse(description = "Post updated",
                            responseCode = "200",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = PostDTO.class))),
                    @ApiResponse(description = "Post not found",
                            responseCode = "404")
            })
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> updatePost(@Parameter(description = "ID of the post to be updated")
                                              @PathVariable Long id,
                                              @RequestBody PostDTO postDTO) {
        return ResponseEntity.ok(postService.updatePost(id, postDTO));
    }

    @Operation(summary = "Delete a post",
            responses = {
                    @ApiResponse(description = "Post deleted",
                            responseCode = "204"),
                    @ApiResponse(description = "Post not found",
                            responseCode = "404")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@Parameter(description = "ID of the post to be deleted")
                                           @PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}

