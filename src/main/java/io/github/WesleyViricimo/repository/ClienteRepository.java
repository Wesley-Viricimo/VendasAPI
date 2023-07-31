package io.github.WesleyViricimo.repository;

import io.github.WesleyViricimo.model.Cliente;
import org.springframework.stereotype.Repository;

@Repository //Indicando para o sprint que a classe irá acessar a base de dadose
public class ClienteRepository {
    public void salvarCliente(Cliente cliente) {
        //Método acessa a base de dados e salva o cliente
    }
}
