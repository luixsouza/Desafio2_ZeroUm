package com.compass.Desafio2_MicroserviceB.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PostDTO {

    private Long id;
    private String title;
    private String body;
    private Long userId;
}
