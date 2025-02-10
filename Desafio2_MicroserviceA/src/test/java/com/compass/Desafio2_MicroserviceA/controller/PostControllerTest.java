package com.compass.Desafio2_MicroserviceA.controller;

import com.compass.Desafio2_MicroserviceA.dto.PostDTO;
import com.compass.Desafio2_MicroserviceA.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PostControllerTest {

    @Mock
    private PostService postService;

    @InjectMocks
    private PostController postController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPosts() {

        PostDTO postDTO = new PostDTO(1L, "Test Title", "Test Body", 1L);
        when(postService.getAllPosts()).thenReturn(Collections.singletonList(postDTO));

        ResponseEntity<List<PostDTO>> response = postController.getAllPosts();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(postDTO, response.getBody().get(0));
        verify(postService, times(1)).getAllPosts();
    }

    @Test
    void testGetPostById() {

        PostDTO postDTO = new PostDTO(1L, "Test Title", "Test Body", 1L);
        when(postService.getPostById(1L)).thenReturn(postDTO);

        ResponseEntity<PostDTO> response = postController.getPostById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDTO, response.getBody());
        verify(postService, times(1)).getPostById(1L);
    }

    @Test
    void testCreatePost() {

        PostDTO postDTO = new PostDTO(1L, "Test Title", "Test Body", 1L);
        when(postService.createPost(postDTO)).thenReturn(postDTO);

        ResponseEntity<PostDTO> response = postController.createPost(postDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDTO, response.getBody());
        verify(postService, times(1)).createPost(postDTO);
    }

    @Test
    void testUpdatePost() {

        PostDTO postDTO = new PostDTO(1L, "Updated Title", "Updated Body", 1L);
        when(postService.updatePost(1L, postDTO)).thenReturn(postDTO);

        ResponseEntity<PostDTO> response = postController.updatePost(1L, postDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDTO, response.getBody());
        verify(postService, times(1)).updatePost(1L, postDTO);
    }

    @Test
    void testDeletePost() {

        doNothing().when(postService).deletePost(1L);

        ResponseEntity<Void> response = postController.deletePost(1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(postService, times(1)).deletePost(1L);
    }
}