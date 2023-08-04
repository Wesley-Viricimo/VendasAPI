package io.github.WesleyViricimo.rest.controller;

import io.github.WesleyViricimo.domain.entity.Cliente;
import io.github.WesleyViricimo.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller //Definindo que a classe será uma classe de controle de requisições rest
@RequestMapping("/api/clientes")//Quando o usuário acessar este endpoint irá acessar os métodos desta classe
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity getClienteById(@PathVariable Integer id){
        Optional<Cliente> cliente = repository.findById(id);

        if(cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save(@RequestBody Cliente cliente) { //RequestBody indica que o cliente deverá ser recebido no corpo da requisição
        Cliente clienteSalvo = repository.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        Optional<Cliente> cliente = repository.findById(id);

        if(cliente.isPresent()) {
            repository.delete(cliente.get());
            return ResponseEntity.noContent().build(); //Se encontrou o cliente irá deletá-lo e retornar uma mensagem de sucesso
        }
        return ResponseEntity.notFound().build();
    }
}
