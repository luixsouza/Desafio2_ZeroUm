package com.compass.Desafio2_MicroserviceB.mapper;

import com.compass.Desafio2_MicroserviceB.dto.CommentDTO;
import com.compass.Desafio2_MicroserviceB.model.Comment;
import com.compass.Desafio2_MicroserviceB.model.Post;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {

    // Converte a entidade Comment para o DTO CommentDTO
    public CommentDTO convertToDTO(Comment comment) {
        if (comment == null) {
            return null;
        }
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setPostId(comment.getPost().getId()); // Obtém o ID do post associado
        commentDTO.setName(comment.getPost().getTitle()); // Exemplo de como preencher com o título do post (pode ser alterado conforme necessário)
        commentDTO.setEmail("example@example.com"); // Substitua com a lógica para obter o email, caso necessário
        commentDTO.setBody(comment.getText()); // Mapear o texto para o corpo do comentário
        return commentDTO;
    }

    // Converte o DTO CommentDTO para a entidade Comment
    public Comment convertToEntity(CommentDTO commentDTO, Post post) {
        if (commentDTO == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setId(commentDTO.getId());
        comment.setText(commentDTO.getBody()); // Mapear o corpo para o texto do comentário
        comment.setPost(post); // Associa o post com o comentário
        return comment;
    }
}
