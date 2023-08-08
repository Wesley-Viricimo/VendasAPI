package io.github.WesleyViricimo.rest.controller;

import io.github.WesleyViricimo.exception.PedidoNaoEncontradoException;
import io.github.WesleyViricimo.exception.RegraNegocioException;
import io.github.WesleyViricimo.rest.ApiErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(RegraNegocioException.class) //Anotação ExceptionHandler marca o método para ser um tratador de exceções
    @ResponseStatus(HttpStatus.BAD_REQUEST) //Quando este método for chamado irá retornar o status badRequest
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex) {
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }

    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
        return new ApiErrors(ex.getMessage());
    }
}
