package com.compass.Desafio2_MicroserviceB.service;

import com.compass.Desafio2_MicroserviceB.dto.PostDTO;
import com.compass.Desafio2_MicroserviceB.mapper.PostMapper;
import com.compass.Desafio2_MicroserviceB.model.Post;
import com.compass.Desafio2_MicroserviceB.repository.PostRepository;
import com.compass.Desafio2_MicroserviceB.client.JsonPlaceholderClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private JsonPlaceholderClient jsonPlaceholderClient;

    @Mock
    private PostMapper postMapper;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSyncData() {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(1L);
        postDTO.setTitle("Test Title");
        postDTO.setBody("Test Body");
        postDTO.setUserId(1L);

        when(jsonPlaceholderClient.getAllPosts()).thenReturn(Collections.singletonList(postDTO));
        when(postMapper.convertToEntity(postDTO)).thenReturn(new Post());

        postService.syncData();

        verify(postRepository, times(1)).save(any(Post.class));
    }

    @Test
    void testGetAllPosts() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");
        post.setBody("Test Body");
        post.setUserId(1L);

        PostDTO postDTO = new PostDTO();
        postDTO.setId(1L);
        postDTO.setTitle("Test Title");
        postDTO.setBody("Test Body");
        postDTO.setUserId(1L);

        when(postRepository.findAll()).thenReturn(Collections.singletonList(post));
        when(postMapper.convertToDTO(post)).thenReturn(postDTO);

        List<PostDTO> result = postService.getAllPosts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(postDTO, result.get(0));
    }

    @Test
    void testGetPostById() {
        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");
        post.setBody("Test Body");
        post.setUserId(1L);

        PostDTO postDTO = new PostDTO();
        postDTO.setId(1L);
        postDTO.setTitle("Test Title");
        postDTO.setBody("Test Body");
        postDTO.setUserId(1L);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postMapper.convertToDTO(post)).thenReturn(postDTO);

        PostDTO result = postService.getPostById(1L);

        assertNotNull(result);
        assertEquals(postDTO, result);
    }

    @Test
    void testGetPostByIdNotFound() {
        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(com.compass.Desafio2_MicroserviceB.exceptions.EntityNotFoundException.class,
                    () -> postService.getPostById(1L));
    }

    @Test
    void testCreatePost() {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Test Title");
        postDTO.setBody("Test Body");
        postDTO.setUserId(1L);

        Post post = new Post();
        post.setTitle("Test Title");
        post.setBody("Test Body");
        post.setUserId(1L);

        when(postMapper.convertToEntity(postDTO)).thenReturn(post);
        when(postRepository.save(post)).thenReturn(post);
        when(postMapper.convertToDTO(post)).thenReturn(postDTO);

        PostDTO result = postService.createPost(postDTO);

        assertNotNull(result);
        assertEquals(postDTO, result);
    }

    @Test
    void testUpdatePost() {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Updated Title");
        postDTO.setBody("Updated Body");
        postDTO.setUserId(1L);

        Post post = new Post();
        post.setId(1L);
        post.setTitle("Test Title");
        post.setBody("Test Body");
        post.setUserId(1L);

        when(postRepository.findById(1L)).thenReturn(Optional.of(post));
        when(postRepository.save(post)).thenReturn(post);
        when(postMapper.convertToDTO(post)).thenReturn(postDTO);

        PostDTO result = postService.updatePost(1L, postDTO);

        assertNotNull(result);
        assertEquals(postDTO, result);
    }

    @Test
    void testUpdatePostNotFound() {
        PostDTO postDTO = new PostDTO();
        postDTO.setTitle("Updated Title");
        postDTO.setBody("Updated Body");
        postDTO.setUserId(1L);

        when(postRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(com.compass.Desafio2_MicroserviceB.exceptions.EntityNotFoundException.class,
                    () -> postService.updatePost(1L, postDTO));
    }

    @Test
    void testDeletePost() {
        when(postRepository.existsById(1L)).thenReturn(true);

        postService.deletePost(1L);

        verify(postRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeletePostNotFound() {
        when(postRepository.existsById(1L)).thenReturn(false);

        assertThrows(com.compass.Desafio2_MicroserviceB.exceptions.EntityNotFoundException.class,
                    () -> postService.deletePost(1L));
    }
}