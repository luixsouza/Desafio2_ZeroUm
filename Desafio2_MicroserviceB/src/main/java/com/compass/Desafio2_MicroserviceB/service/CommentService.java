package com.compass.Desafio2_MicroserviceB.service;

import com.compass.Desafio2_MicroserviceB.dto.CommentDTO;
import com.compass.Desafio2_MicroserviceB.exceptions.EntityNotFoundException;
import com.compass.Desafio2_MicroserviceB.model.Comment;
import com.compass.Desafio2_MicroserviceB.model.Post;
import com.compass.Desafio2_MicroserviceB.repository.CommentRepository;
import com.compass.Desafio2_MicroserviceB.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    // Obter todos os comentários de um post específico
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        // Converter a lista de Comentário para CommentDTO
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Criar um novo comentário para um post
    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        // Buscar o post no banco
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("Post not found"));

        // Criar a entidade Comment a partir do DTO
        Comment comment = new Comment();
        comment.setText(commentDTO.getBody());  // Usar o 'body' do DTO como texto do comentário
        comment.setPost(post);

        // Salvar o comentário no banco de dados
        comment = commentRepository.save(comment);

        // Converter a entidade Comment de volta para DTO
        return convertToDTO(comment);
    }

    // Atualizar um comentário existente de um post
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        // Buscar o comentário pelo ID e postId
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId);


        // Atualizar o texto do comentário com os dados fornecidos
        comment.setText(commentDTO.getBody());  // Usar o 'body' do DTO como texto do comentário

        // Salvar o comentário atualizado no banco de dados
        comment = commentRepository.save(comment);

        // Converter a entidade Comment de volta para DTO
        return convertToDTO(comment);
    }

    // Deletar um comentário de um post
    public void deleteComment(Long postId, Long commentId) {
        // Buscar o comentário pelo ID e postId
        Comment comment = commentRepository.findByPostIdAndId(postId, commentId);


        // Deletar o comentário do banco de dados
        commentRepository.delete(comment);
    }


    // Método para converter a entidade Comment para DTO
    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setPostId(comment.getPost().getId());
        commentDTO.setBody(comment.getText());  // Mapeando o texto para 'body'
        return commentDTO;
    }
}
