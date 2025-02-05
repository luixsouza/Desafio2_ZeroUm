package com.compass.Desafio2_MicroserviceB.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.compass.Desafio2_MicroserviceB.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}