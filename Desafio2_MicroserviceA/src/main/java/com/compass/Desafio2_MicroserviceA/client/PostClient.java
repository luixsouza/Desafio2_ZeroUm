package com.compass.Desafio2_MicroserviceA.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.compass.Desafio2_MicroserviceA.dto.PostDTO;

import java.util.List;

@FeignClient(name = "microservico-b", url = "http://localhost:8081/api/posts")
public interface PostClient {

    @GetMapping
    List<PostDTO> getAllPosts();

    @GetMapping("/{id}")
    PostDTO getPostById(@PathVariable Long id);

    @PostMapping
    PostDTO createPost(@RequestBody PostDTO postDTO);

    @PutMapping("/{id}")
    PostDTO updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO);

    @DeleteMapping("/{id}")
    void deletePost(@PathVariable Long id);
}
