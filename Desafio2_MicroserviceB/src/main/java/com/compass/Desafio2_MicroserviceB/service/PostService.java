package com.compass.Desafio2_MicroserviceB.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.compass.Desafio2_MicroserviceB.client.JsonPlaceholderClient;
import com.compass.Desafio2_MicroserviceB.dto.PostDTO;
import com.compass.Desafio2_MicroserviceB.model.Post;
import com.compass.Desafio2_MicroserviceB.repository.PostRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final JsonPlaceholderClient jsonPlaceholderClient;
    private final PostRepository postRepository;

    public void syncData() {
        List<PostDTO> postDTOList = jsonPlaceholderClient.getAllPosts();
        
        for (PostDTO postDTO : postDTOList) {

            Post post = new Post();
            post.setTitle(postDTO.getTitle());
            post.setBody(postDTO.getBody());
            post.setUserId(postDTO.getUserId());
            
            postRepository.save(post);
        }
    }

    public List<PostDTO> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
    }
    
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Post não encontrado"));
        return convertToDTO(post);
    }
    
    public PostDTO createPost(PostDTO postDTO) {
        Post post = convertToEntity(postDTO);
        post = postRepository.save(post);
        return convertToDTO(post);
    }
    
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = postRepository.findById(id)
                                  .orElseThrow(() -> new RuntimeException("Post não encontrado"));
        
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setUserId(postDTO.getUserId());
    
        post = postRepository.save(post);
        return convertToDTO(post);
    }
    
    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }


    private PostDTO convertToDTO(Post post) {
        return new PostDTO(post.getId(), post.getTitle(), post.getBody(), post.getUserId());
    }

    private Post convertToEntity(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setUserId(postDTO.getUserId());
        
        return post;
    }   
}
