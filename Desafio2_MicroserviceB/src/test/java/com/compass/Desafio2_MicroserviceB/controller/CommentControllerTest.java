package com.compass.Desafio2_MicroserviceB.controller;

import com.compass.Desafio2_MicroserviceB.dto.CommentDTO;
import com.compass.Desafio2_MicroserviceB.service.CommentService;
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

class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetComments() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(1L);
        commentDTO.setName("Test Name");
        commentDTO.setEmail("test@example.com");
        commentDTO.setBody("Test Text");

        when(commentService.getCommentsByPostId(1L)).thenReturn(Collections.singletonList(commentDTO));

        ResponseEntity<List<CommentDTO>> response = commentController.getComments(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(commentDTO, response.getBody().get(0));
    }

    @Test
    void testCreateComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName("Test Name");
        commentDTO.setEmail("test@example.com");
        commentDTO.setBody("Test Text");

        when(commentService.createComment(1L, commentDTO)).thenReturn(commentDTO);

        ResponseEntity<CommentDTO> response = commentController.createComment(1L, commentDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDTO, response.getBody());
    }

    @Test
    void testUpdateComment() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName("Updated Name");
        commentDTO.setEmail("updated@example.com");
        commentDTO.setBody("Updated Text");

        when(commentService.updateComment(1L, 1L, commentDTO)).thenReturn(commentDTO);

        ResponseEntity<CommentDTO> response = commentController.updateComment(1L, 1L, commentDTO);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDTO, response.getBody());
    }

    @Test
    void testDeleteComment() {
        doNothing().when(commentService).deleteComment(1L, 1L);

        ResponseEntity<Void> response = commentController.deleteComment(1L, 1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}