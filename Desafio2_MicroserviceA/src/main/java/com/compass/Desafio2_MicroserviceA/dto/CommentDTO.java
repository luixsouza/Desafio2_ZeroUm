package com.compass.Desafio2_MicroserviceA.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CommentDTO {
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;
}