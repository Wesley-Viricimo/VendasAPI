package io.github.WesleyViricimo;

import io.github.WesleyViricimo.domain.entity.Cliente;
import io.github.WesleyViricimo.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication //Anotação para Spring reconhecer que a classe irá inicializar uma aplicação Spring Boot
public class Main {
    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clientes) {
        return args -> {
            Cliente cliente = new Cliente("Wesley");
            clientes.salvarCliente(cliente);

            Cliente cliente2 = new Cliente("Jéssica");
            clientes.salvarCliente(cliente2);

            List<Cliente> todosClientes = clientes.listarClientes();
            todosClientes.forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}