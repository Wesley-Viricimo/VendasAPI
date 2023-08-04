package io.github.WesleyViricimo;

import io.github.WesleyViricimo.domain.entity.Cliente;
import io.github.WesleyViricimo.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication //Anotação para Spring reconhecer que a classe irá inicializar uma aplicação Spring Boot
public class VendasApplication {

    @Bean
    public CommandLineRunner commandLineRunner(@Autowired ClienteRepository clientes){
        return args -> {
            Cliente c = new Cliente(null, "Wesley");
            clientes.save(c);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}