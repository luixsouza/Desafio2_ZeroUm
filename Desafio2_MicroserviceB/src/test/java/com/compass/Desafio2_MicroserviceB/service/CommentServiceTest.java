package com.compass.Desafio2_MicroserviceB.service;

import com.compass.Desafio2_MicroserviceB.dto.CommentDTO;
import com.compass.Desafio2_MicroserviceB.exceptions.EntityNotFoundException;
import com.compass.Desafio2_MicroserviceB.model.Comment;
import com.compass.Desafio2_MicroserviceB.model.Post;
import com.compass.Desafio2_MicroserviceB.repository.CommentRepository;
import com.compass.Desafio2_MicroserviceB.repository.PostRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommentsByPostId() {
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setName("Test Name");
        comment.setEmail("test@example.com");
        comment.setText("Test Text");
    
        Post post = new Post();
        post.setId(1L);
        comment.setPost(post);
    
        when(commentRepository.findByPostId(1L)).thenReturn(Collections.singletonList(comment));
    
        List<CommentDTO> result = commentService.getCommentsByPostId(1L);
    
        assertNotNull(result);
        assertEquals(1, result.size());
    
        CommentDTO commentDTO = result.get(0);
        assertEquals(1L, commentDTO.getId());
        assertEquals(1L, commentDTO.getPostId());
        assertEquals("Test Name", commentDTO.getName());
        assertEquals("test@example.com", commentDTO.getEmail());
        assertEquals("Test Text", commentDTO.getBody());
    }

    @Test
    void testCreateComment() {

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName("Test Name");
        commentDTO.setEmail("test@example.com");
        commentDTO.setBody("Test Text");
    
        Post post = new Post();
        post.setId(1L);
    
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setName("Test Name");
        comment.setEmail("test@example.com");
        comment.setText("Test Text");
        comment.setPost(post);
    
        when(postRepository.findById(1L)).thenReturn(Optional.of(post));

        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        CommentDTO result = commentService.createComment(1L, commentDTO);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getPostId());
        assertEquals("Test Name", result.getName());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("Test Text", result.getBody());
    }

    @Test
    void testCreateCommentPostNotFound() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName("Test Name");
        commentDTO.setEmail("test@example.com");
        commentDTO.setBody("Test Text");

        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                    () -> commentService.createComment(1L, commentDTO));
    }

    @Test
    void testUpdateComment() {

        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName("Updated Name");
        commentDTO.setEmail("updated@example.com");
        commentDTO.setBody("Updated Text");
    
        Post post = new Post();
        post.setId(1L);
    
        Comment comment = new Comment();
        comment.setId(1L);
        comment.setName("Test Name");
        comment.setEmail("test@example.com");
        comment.setText("Test Text");
        comment.setPost(post);
    
        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));
        when(commentRepository.save(any(Comment.class))).thenReturn(comment);
    
        CommentDTO result = commentService.updateComment(1L, 1L, commentDTO);
    
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(1L, result.getPostId());
        assertEquals("Updated Name", result.getName());
        assertEquals("updated@example.com", result.getEmail());
        assertEquals("Updated Text", result.getBody());
    }

    @Test
    void testUpdateCommentNotFound() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName("Updated Name");
        commentDTO.setEmail("updated@example.com");
        commentDTO.setBody("Updated Text");

        when(commentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, 
                    () -> commentService.updateComment(1L, 1L, commentDTO));
    }

    @Test
    void testUpdateCommentNotBelongingToPost() {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName("Updated Name");
        commentDTO.setEmail("updated@example.com");
        commentDTO.setBody("Updated Text");

        Post post = new Post();
        post.setId(2L);

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setName("Test Name");
        comment.setEmail("test@example.com");
        comment.setText("Test Text");
        comment.setPost(post);

        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        assertThrows(RuntimeException.class, 
                    () -> commentService.updateComment(1L, 1L, commentDTO));
    }

    @Test
    void testDeleteComment() {
        Post post = new Post();
        post.setId(1L);

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setName("Test Name");
        comment.setEmail("test@example.com");
        comment.setText("Test Text");
        comment.setPost(post);

        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        commentService.deleteComment(1L, 1L);

        verify(commentRepository, times(1)).delete(comment);
    }

    @Test
    void testDeleteCommentNotFound() {
        when(commentRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class,
                    () -> commentService.deleteComment(1L, 1L));
    }

    @Test
    void testDeleteCommentNotBelongingToPost() {
        Post post = new Post();
        post.setId(2L);

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setName("Test Name");
        comment.setEmail("test@example.com");
        comment.setText("Test Text");
        comment.setPost(post);

        when(commentRepository.findById(1L)).thenReturn(Optional.of(comment));

        assertThrows(RuntimeException.class, 
                    () -> commentService.deleteComment(1L, 1L));
    }
}