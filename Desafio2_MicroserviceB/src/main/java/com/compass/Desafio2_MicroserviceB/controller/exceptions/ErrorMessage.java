package com.compass.Desafio2_MicroserviceB.controller.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Getter
@ToString
public class ErrorMessage {

    private String path; // qual recurso que gerou a excessão
    private String method; // qual metodo http que foi enviado e gerou a excessão
    private int status; // código do http status
    private String statusText; // mensagem do status
    private String message; // mensagem de erro

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Map<String, String> errors;

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String message) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = message;
    }

    public ErrorMessage(HttpServletRequest request, HttpStatus status, String mensagem, BindingResult result) {
        this.path = request.getRequestURI();
        this.method = request.getMethod();
        this.status = status.value();
        this.statusText = status.getReasonPhrase();
        this.message = mensagem;
        addErrors(result);

    }

    private void addErrors(BindingResult result) {
        this.errors = new HashMap<>();
        for (FieldError error : result.getFieldErrors()) {
            this.errors.put(error.getField(), error.getDefaultMessage());
        }
    }
}
