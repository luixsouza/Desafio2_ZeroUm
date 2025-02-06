package com.compass.Desafio2_MicroserviceB.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDTO {

    private Long id;

    @NotBlank(message = "esse campo não pode ser vazio")
    private String title;

    @NotBlank(message = "esse campo não pode ser vazio")
    private String body;

    @NotBlank(message = "esse campo não pode ser vazio")
    private Long userId;
}
