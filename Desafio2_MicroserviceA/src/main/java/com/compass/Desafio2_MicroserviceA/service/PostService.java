package com.compass.Desafio2_MicroserviceA.service;

import com.compass.Desafio2_MicroserviceA.dto.PostDTO;
import com.compass.Desafio2_MicroserviceA.client.PostClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostClient postClient;

    public List<PostDTO> getAllPosts() {
        return postClient.getAllPosts();
    }

    public PostDTO getPostById(Long id) {
        return postClient.getPostById(id);
    }

    public PostDTO createPost(PostDTO postDTO) {
        return postClient.createPost(postDTO);
    }

    public PostDTO updatePost(Long id, PostDTO postDTO) {
        return postClient.updatePost(id, postDTO);
    }

    public void deletePost(Long id) {
        postClient.deletePost(id);
    }
}
