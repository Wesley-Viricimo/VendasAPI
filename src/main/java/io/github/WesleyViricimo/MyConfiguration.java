package io.github.WesleyViricimo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfiguration {

    @Bean(name = "applicationName") //Anotação @Bean cria o método no contexto da aplicação e poderá ser utilizado em qualquer parte da aplicação e se não for passado um nome para o bean o nome será o próprio nome do método;
    public String applicationName(){
        return "Sistema de Vendas";
    }

}
