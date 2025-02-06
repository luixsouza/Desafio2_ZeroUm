package com.compass.Desafio2_MicroserviceA.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private Long postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}