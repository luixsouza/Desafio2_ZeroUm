package com.compass.Desafio2_MicroserviceB.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.compass.Desafio2_MicroserviceB.client.JsonPlaceholderClient;
import com.compass.Desafio2_MicroserviceB.dto.CommentDTO;
import com.compass.Desafio2_MicroserviceB.dto.PostDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {

    private final JsonPlaceholderClient jsonPlaceholderClient;

    public List<PostDTO> getAllPosts() {
        return jsonPlaceholderClient.getAllPosts();
    }

    public PostDTO getPostById(Long id) {
        return jsonPlaceholderClient.getPostById(id);
    }

    public List<CommentDTO> getCommentsByPostId(Long id) {
        return jsonPlaceholderClient.getCommentsByPostId(id);
    }
}
