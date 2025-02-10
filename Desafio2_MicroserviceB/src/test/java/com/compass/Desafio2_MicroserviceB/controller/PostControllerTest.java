package com.compass.Desafio2_MicroserviceB.controller;

import com.compass.Desafio2_MicroserviceB.dto.PostDTO;
import com.compass.Desafio2_MicroserviceB.service.PostService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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
    void testSyncData() {
        doNothing().when(postService).syncData();

        postController.syncData();

        verify(postService, times(1)).syncData();
    }

    @Test
    void testGetAllPosts() {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(1L);
        postDTO.setTitle("Test Title");
        postDTO.setBody("Test Body");
        postDTO.setUserId(1L);

        when(postService.getAllPosts()).thenReturn(Collections.singletonList(postDTO));

        ResponseEntity<List<PostDTO>> response = postController.getAllPosts();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(postDTO, response.getBody().get(0));
    }

    @Test
    void testGetPostById() {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(1L);
        postDTO.setTitle("Test Title");
        postDTO.setBody("Test Body");
        postDTO.setUserId(1L);

        when(postService.getPostById(1L)).thenReturn(postDTO);

        ResponseEntity<PostDTO> response = postController.getPostById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDTO, response.getBody());
    }

    @Test
    void testCreatePost() {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Test Title");
        postDTO.setBody("Test Body");
        postDTO.setUserId(1L);

        when(postService.createPost(postDTO)).thenReturn(postDTO);

        ResponseEntity<PostDTO> response = postController.createPost(postDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDTO, response.getBody());
    }

    @Test
    void testUpdatePost() {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Updated Title");
        postDTO.setBody("Updated Body");
        postDTO.setUserId(1L);

        when(postService.updatePost(1L, postDTO)).thenReturn(postDTO);

        ResponseEntity<PostDTO> response = postController.updatePost(1L, postDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(postDTO, response.getBody());
    }

    @Test
    void testDeletePost() {
        doNothing().when(postService).deletePost(1L);

        ResponseEntity<Void> response = postController.deletePost(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}