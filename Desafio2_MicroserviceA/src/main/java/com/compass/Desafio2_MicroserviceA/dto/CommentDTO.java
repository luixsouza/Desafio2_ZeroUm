package com.compass.Desafio2_MicroserviceA.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}