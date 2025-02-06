package com.compass.Desafio2_MicroserviceB.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.compass.Desafio2_MicroserviceB.client.JsonPlaceholderClient;
import com.compass.Desafio2_MicroserviceB.dto.PostDTO;
import com.compass.Desafio2_MicroserviceB.mapper.PostMapper;
import com.compass.Desafio2_MicroserviceB.model.Post;
import com.compass.Desafio2_MicroserviceB.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final JsonPlaceholderClient jsonPlaceholderClient;
    private final PostRepository postRepository;
    private final PostMapper postMapper; // Injeção do Mapper

    public void syncData() {
        List<PostDTO> postDTOList = jsonPlaceholderClient.getAllPosts();
        postDTOList.forEach(this::savePostFromDTO);
    }

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(postMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public PostDTO getPostById(Long id) {
        return postRepository.findById(id)
                .map(postMapper::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));
    }

    public PostDTO createPost(PostDTO postDTO) {
        Post post = postMapper.convertToEntity(postDTO);
        return postMapper.convertToDTO(postRepository.save(post));
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));
        updatePostEntity(post, postDTO);
        return postMapper.convertToDTO(postRepository.save(post));
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    private void updatePostEntity(Post post, PostDTO postDTO) {
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setUserId(postDTO.getUserId());
    }

    private void savePostFromDTO(PostDTO postDTO) {
        Post post = postMapper.convertToEntity(postDTO);
        postRepository.save(post);
    }
}