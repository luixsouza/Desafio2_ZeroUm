package com.compass.Desafio2_MicroserviceB.dto;

import lombok.Data;

@Data
public class PostDTO {
    private Long userId;
    private Long id;
    private String title;
    private String body;
}
