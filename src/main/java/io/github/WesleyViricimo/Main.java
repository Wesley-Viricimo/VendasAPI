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

            List<Cliente> todosClientes = clientes.findAll();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> { //Para cada elemento da lista
                c.setNome(c.getNome() + " atualizado!");
                clientes.save(c);
                System.out.println(c);
            });

            System.out.println("Buscando clientes por nome");
            clientes.findByNomeLike("Wesley").forEach(System.out::println);

            boolean existe = clientes.existsByNome("Wesley atualizado!");
            System.out.println("Existe por nome " + existe);

            System.out.println("Deletando clientes");
            clientes.findAll().forEach(c -> {
                clientes.delete(c);
            });

            todosClientes = clientes.findAll();

            if(todosClientes.isEmpty()){
                System.out.println("Nenhum cliente encontrado!");
            } else {
                todosClientes.forEach(System.out::println);
            }

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}