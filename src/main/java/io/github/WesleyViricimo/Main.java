package io.github.WesleyViricimo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"io.github.WesleyViricimo.repository", "io.github.WesleyViricimo.repository"}) //Anotação para indicar para o spring quais são os componentes, classes de configurações e objetos que deverão ser escaneados, mas o spring já escaneia todos os pacotes que estão no pacote principal e é mais utilizada esta anotação quando utilizadas bibliotecas de terceiros
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