package io.github.WesleyViricimo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity //Não é necessário adicionar a anotação @Configuration nesta classe, pois a anotação @EnableWebSecurity já possui a mesma
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //Método irá validar o tipo de configuração que será realizada e tem a responsabilidade de autenticar o usuário
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())//Criando um usuário em memória para testes e adicionando o tipo de encoder que será utilizado
                .withUser("wesley")//Usuário que será utilizado
                .password(passwordEncoder().encode("123"))//Senha encodada que será utilizada
                .roles("USER");//Adicionando a role que o usuário irá possuir
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //Método que irá validar a autorização (Se o usuário tem acesso ao endpoint que está fazendo a requisição)
        super.configure(http);
    }
}
