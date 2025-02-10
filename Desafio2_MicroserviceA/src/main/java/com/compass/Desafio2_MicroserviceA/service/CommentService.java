package com.compass.Desafio2_MicroserviceA.service;

import com.compass.Desafio2_MicroserviceA.dto.CommentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8081/api/posts";

    public List<CommentDTO> getCommentsByPostId(Long postId) {
        String url = BASE_URL + "/" + postId + "/comments";
        return List.of(restTemplate.getForObject(url, CommentDTO[].class));
    }

    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        String url = BASE_URL + "/" + postId + "/comments";
        return restTemplate.postForObject(url, commentDTO, CommentDTO.class);
    }

    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        String url = BASE_URL + "/" + postId + "/comments/" + commentId;
        ResponseEntity<CommentDTO> response = restTemplate.exchange(
                url,
                HttpMethod.PUT,
                new HttpEntity<>(commentDTO),
                CommentDTO.class
        );
        return response.getBody();
    }

    public void deleteComment(Long postId, Long commentId) {
        String url = BASE_URL + "/" + postId + "/comments/" + commentId;
        restTemplate.delete(url);
    }
}
