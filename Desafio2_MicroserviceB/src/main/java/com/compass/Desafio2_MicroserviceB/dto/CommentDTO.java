package com.compass.Desafio2_MicroserviceB.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CommentDTO {

    private Long id;

    private Long postId;

    @NotBlank(message = "The name field cannot be empty")
    private String name;

    @Email(message = "O formato do e-mail está inválido", regexp = "^[a-z0-9.+-]+@[a-z0-9.-]+\\.[a-z]{2,}$")
    private String email;

    @NotBlank(message = "The body field cannot be empty")
    private String body;
}
