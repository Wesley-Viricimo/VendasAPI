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
            clientes.salvarCliente(new Cliente("Wesley"));
            clientes.salvarCliente(new Cliente("Jessica"));
            clientes.salvarCliente(new Cliente("Ana Julia"));

            List<Cliente> todosClientes = clientes.listarClientes();
            todosClientes.forEach(System.out::println);

            System.out.println("Atualizando clientes");
            todosClientes.forEach(c -> { //Para cada elemento da lista
                c.setNome(c.getNome() + " atualizado!");
                clientes.atualizar(c);
                System.out.println(c);
            });

            System.out.println("Buscando clientes por nome");
            clientes.buscarPorNome("Wes").forEach(System.out::println);


            System.out.println("Deletando clientes");
            clientes.listarClientes().forEach(c -> {
                clientes.deletar(c);
            });

            todosClientes = clientes.listarClientes();

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