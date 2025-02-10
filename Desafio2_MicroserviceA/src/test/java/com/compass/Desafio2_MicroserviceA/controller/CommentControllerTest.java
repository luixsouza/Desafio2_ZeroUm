package com.compass.Desafio2_MicroserviceA.controller;

import com.compass.Desafio2_MicroserviceA.dto.CommentDTO;
import com.compass.Desafio2_MicroserviceA.service.CommentService;
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

        CommentDTO commentDTO = CommentDTO.builder()
                .id(1L)
                .postId(1L)
                .name("Test Name")
                .email("test@example.com")
                .body("Test Text")
                .build();
        when(commentService.getCommentsByPostId(1L)).thenReturn(Collections.singletonList(commentDTO));

        ResponseEntity<List<CommentDTO>> response = commentController.getComments(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(commentDTO, response.getBody().get(0));
        verify(commentService, times(1)).getCommentsByPostId(1L);
    }

    @Test
    void testCreateComment() {

        CommentDTO commentDTO = CommentDTO.builder()
                .id(1L)
                .postId(1L)
                .name("Test Name")
                .email("test@example.com")
                .body("Test Text")
                .build();
        when(commentService.createComment(1L, commentDTO)).thenReturn(commentDTO);

        ResponseEntity<CommentDTO> response = commentController.createComment(1L, commentDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDTO, response.getBody());
        verify(commentService, times(1)).createComment(1L, commentDTO);
    }

    @Test
    void testUpdateComment() {

        CommentDTO commentDTO = CommentDTO.builder()
                .id(1L)
                .postId(1L)
                .name("Updated Name")
                .email("updated@example.com")
                .body("Updated Text")
                .build();
        when(commentService.updateComment(1L, 1L, commentDTO)).thenReturn(commentDTO);

        ResponseEntity<CommentDTO> response = commentController.updateComment(1L, 1L, commentDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDTO, response.getBody());
        verify(commentService, times(1)).updateComment(1L, 1L, commentDTO);
    }

    @Test
    void testDeleteComment() {

        doNothing().when(commentService).deleteComment(1L, 1L);

        ResponseEntity<Void> response = commentController.deleteComment(1L, 1L);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(commentService, times(1)).deleteComment(1L, 1L);
    }
}