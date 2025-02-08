package com.compass.Desafio2_MicroserviceB.mapper;

import com.compass.Desafio2_MicroserviceB.dto.CommentDTO;
import com.compass.Desafio2_MicroserviceB.model.Comment;
import com.compass.Desafio2_MicroserviceB.model.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    public CommentDTO convertToDTO(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setPostId(comment.getPost().getId());
        commentDTO.setName(comment.getPost().getTitle());
        commentDTO.setEmail("example@example.com");
        commentDTO.setBody(comment.getText());
        return commentDTO;
    }


    public Comment convertToEntity(CommentDTO commentDTO, Post post) {
        if (commentDTO == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getBody());
        comment.setPost(post);
        return comment;
    }
}
