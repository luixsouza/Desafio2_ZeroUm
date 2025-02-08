package com.compass.Desafio2_MicroserviceB.service;

import com.compass.Desafio2_MicroserviceB.dto.CommentDTO;
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

    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CommentDTO createComment(Long postId, CommentDTO commentDTO) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post não encontrado"));

        Comment comment = new Comment();
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setText(commentDTO.getBody());
        comment.setPost(post);
        comment = commentRepository.save(comment);
        return convertToDTO(comment);
    }

    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
        if (!comment.getPost().getId().equals(postId)) {
            throw new RuntimeException("Comentário não pertence ao post especificado");
        }
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setText(commentDTO.getBody());
        comment = commentRepository.save(comment);
        return convertToDTO(comment);
    }

    public void deleteComment(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comentário não encontrado"));
        if (!comment.getPost().getId().equals(postId)) {
            throw new RuntimeException("Comentário não pertence ao post especificado");
        }
        commentRepository.delete(comment);
    }

    private CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setId(comment.getId());
        commentDTO.setPostId(comment.getPost().getId()); // Mapeia o postId a partir do relacionamento
        commentDTO.setName(comment.getName());          // Mapeia o name
        commentDTO.setEmail(comment.getEmail());        // Mapeia o email
        commentDTO.setBody(comment.getText());          // Mapeia o text para body
        return commentDTO;
    }
}
