package io.github.WesleyViricimo.service;

import io.github.WesleyViricimo.model.Cliente;
import io.github.WesleyViricimo.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired //Anotação Autowired é responsável por injetar dependência de uma classe em uma outra, neste por exemplo injetamos a dependência da classe de repository para utilizar os métodos que estão instânciados nela
    private ClienteRepository repository;

    public void salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        this.repository.salvarCliente(cliente);
    }

    public void validarCliente(Cliente cliente) {
        //Aplica validações
    }
}
