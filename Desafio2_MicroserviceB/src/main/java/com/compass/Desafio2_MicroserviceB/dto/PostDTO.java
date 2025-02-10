package com.compass.Desafio2_MicroserviceB.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long id;

    @NotBlank(message = "The title field cannot be empty")
    private String title;

    @NotBlank(message = "The body field cannot be empty")
    private String body;

    @NotNull(message = "The user id field cannot be null")
    @Positive(message = "The user id field cannot be less than or equal to zero")
    private Long userId;
}
