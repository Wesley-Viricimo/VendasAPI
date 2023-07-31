package io.github.WesleyViricimo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication //Anotação para Spring reconhecer que a classe irá inicializar uma aplicação Spring Boot
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}