package com.compass.Desafio2_MicroserviceA.service;

import com.compass.Desafio2_MicroserviceA.dto.CommentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommentsByPostId() {

        CommentDTO commentDTO = new CommentDTO(1L, 1L, "Test Name", "test@example.com", "Test Text");
        when(restTemplate.getForObject(anyString(), eq(CommentDTO[].class)))
                .thenReturn(new CommentDTO[]{commentDTO});

        List<CommentDTO> result = commentService.getCommentsByPostId(1L);

        assertEquals(1, result.size());
        assertEquals(commentDTO, result.get(0));
    }

    @Test
    void testCreateComment() {

        CommentDTO commentDTO = new CommentDTO(1L, 1L, "Test Name", "test@example.com", "Test Text");
        when(restTemplate.postForObject(anyString(), eq(commentDTO), eq(CommentDTO.class)))
                .thenReturn(commentDTO);

        CommentDTO result = commentService.createComment(1L, commentDTO);

        assertEquals(commentDTO, result);
    }

    @Test
    void testUpdateComment() {

        CommentDTO commentDTO = new CommentDTO(1L, 1L, "Updated Name", "updated@example.com", "Updated Text");
        ResponseEntity<CommentDTO> responseEntity = ResponseEntity.ok(commentDTO);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.PUT), any(HttpEntity.class), eq(CommentDTO.class)))
                .thenReturn(responseEntity);

        CommentDTO result = commentService.updateComment(1L, 1L, commentDTO);

        assertEquals(commentDTO, result);
    }

    @Test
    void testDeleteComment() {

        doNothing().when(restTemplate).delete(anyString());

        commentService.deleteComment(1L, 1L);

        verify(restTemplate, times(1)).delete(anyString());
    }
}