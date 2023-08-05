package io.github.WesleyViricimo.rest.controller;

import io.github.WesleyViricimo.domain.entity.Cliente;
import io.github.WesleyViricimo.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController //Definindo que a classe será uma classe de controle de requisições rest
@RequestMapping("/api/clientes")//Quando o usuário acessar este endpoint irá acessar os métodos desta classe
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping(value = "/{id}")
    public Cliente getClienteById(@PathVariable Integer id){
        return repository.findById(id).orElseThrow( () -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!");
        });
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)//Quando o cliente for criado irá retornar o status 201 created
    public Cliente save(@RequestBody Cliente cliente) { //RequestBody indica que o cliente deverá ser recebido no corpo da requisição
        return repository.save(cliente);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        repository.findById(id)
                .map( clienteExistente -> {
                    repository.delete(clienteExistente);
                    return clienteExistente;
                })
                .orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!"));
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Cliente cliente) {

         repository.findById(id)
                .map(clienteExistente -> { //Se o optional estiver populado irá entrar no método map
                    cliente.setId(clienteExistente.getId());
                    repository.save(cliente);
                    return clienteExistente;
                }).orElseThrow( () ->
                         new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado!")); //Se o optional não estiver populado significa que não encontrou o cliente e retornará not found
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher //Objeto que permite que sejam realizadas algumas configurações para que seja encontrado os clientes através das propriedades
                .matching()
                .withIgnoreCase()//Configuração para encontrar o cliente independente se estiver com caixa alta ou baixa
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING ); //Configuração para retornar o cliente a partir da string informada pelo usuário, se for digitado o valor 'al', irá retornar todos os clientes que possuam al em alguma das suas propriedades
        Example example = Example.of(filtro, matcher);

        return repository.findAll(example);
    }

}
