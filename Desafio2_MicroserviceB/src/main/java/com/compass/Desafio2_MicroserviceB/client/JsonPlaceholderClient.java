package com.compass.Desafio2_MicroserviceB.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.compass.Desafio2_MicroserviceB.dto.CommentDTO;
import com.compass.Desafio2_MicroserviceB.dto.PostDTO;

@FeignClient(name = "jsonPlaceholderClient", url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceholderClient {

    @GetMapping("/posts")
    List<PostDTO> getAllPosts();

    @GetMapping("/posts/{id}")
    PostDTO getPostById(@PathVariable("id") Long id);

    @GetMapping("/posts/{id}/comments")
    List<CommentDTO> getCommentsByPostId(@PathVariable("id") Long id);
}
