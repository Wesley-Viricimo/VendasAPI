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
            System.out.println("Salvando clientes");
            clientes.save(new Cliente("Wesley"));
            clientes.save(new Cliente("Jessica"));
            clientes.save(new Cliente("Ana Julia"));

            List<Cliente> result = clientes.encontrarPorNome("Wesley");
            result.forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}