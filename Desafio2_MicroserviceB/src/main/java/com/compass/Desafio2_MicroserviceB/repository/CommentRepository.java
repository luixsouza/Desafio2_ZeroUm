package com.compass.Desafio2_MicroserviceB.repository;

import com.compass.Desafio2_MicroserviceB.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(Long postId);
    Comment findByPostIdAndId(Long postId, Long id);
}
