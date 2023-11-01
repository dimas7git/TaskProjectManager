package com.backend.tarefa_projeto.projetos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Esta anotação define que quando esta exceção for lançada, a resposta HTTP será um status 400 (Bad Request)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    // Construtor que aceita uma mensagem como parâmetro
    public BadRequestException(String message) {
        // Chama o construtor da superclasse RuntimeException com a mensagem fornecida
        super(message);
    }
}
