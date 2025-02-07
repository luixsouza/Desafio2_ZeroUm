package com.compass.Desafio2_MicroserviceB.mapper;

import jakarta.validation.Valid;
import org.springframework.stereotype.Component;

import com.compass.Desafio2_MicroserviceB.dto.PostDTO;
import com.compass.Desafio2_MicroserviceB.model.Post;

@Component
public class PostMapper {

    public PostDTO convertToDTO(Post post) {
        return new PostDTO(post.getId(), post.getTitle(), post.getBody(), post.getUserId());
    }

    public Post convertToEntity(@Valid PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setBody(postDTO.getBody());
        post.setUserId(postDTO.getUserId());
        return post;
    }
}
