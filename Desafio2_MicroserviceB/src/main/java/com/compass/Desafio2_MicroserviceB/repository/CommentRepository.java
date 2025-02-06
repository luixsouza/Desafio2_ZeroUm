package com.compass.Desafio2_MicroserviceB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.compass.Desafio2_MicroserviceB.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
