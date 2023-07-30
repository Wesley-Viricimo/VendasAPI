package io.github.WesleyViricimo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication //Anotação para Spring reconhecer que a classe irá inicializar uma aplicação Spring Boot
public class Main {

    @Autowired
    @Qualifier("applicationName") //Anotação Qualifier é utilizada para identificar o Bean que será injetado na variável
    private String applicationName;

    public String getApplicationName() {
        return applicationName;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}