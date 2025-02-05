package com.compass.Desafio2_MicroserviceB.controller.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ErrorMessage {

    private String path; // qual recurso que gerou a excessão
    private String method; // qual metodo http que foi enviado e gerou a excessão
    private int status; // código do http status
    private String statusText; // mensagem do status
    private String message; // mensagem de erro


    public ErrorMessage(){}

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message){
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
    }

}
