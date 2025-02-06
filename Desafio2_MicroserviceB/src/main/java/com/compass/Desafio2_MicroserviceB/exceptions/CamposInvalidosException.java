package com.compass.Desafio2_MicroserviceB.exceptions;

public class CamposInvalidosException extends RuntimeException {
    public CamposInvalidosException(String message) {
        super(message);
    }
}
