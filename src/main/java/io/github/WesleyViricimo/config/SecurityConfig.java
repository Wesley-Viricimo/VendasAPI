package io.github.WesleyViricimo.config;

import io.github.WesleyViricimo.security.jwt.JwtAuthFilter;
import io.github.WesleyViricimo.security.jwt.JwtService;
import io.github.WesleyViricimo.service.impl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity //Não é necessário adicionar a anotação @Configuration nesta classe, pois a anotação @EnableWebSecurity já possui a mesma
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { //Método irá validar o tipo de configuração que será realizada e tem a responsabilidade de autenticar o usuário
        auth.userDetailsService(usuarioService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { //Método que irá validar a autorização (Se o usuário tem acesso ao endpoint que está fazendo a requisição)
        http
                .csrf().disable()//Desabilitando csrf(configuração que permite a segurança entre uma aplicação web e o backend, mas como não se trata de uma aplicação web e sim uma api rest então não se faz necessário)
                .authorizeRequests()//Autorizando requisições
                    .antMatchers("/api/clientes/**") //Para que seja feita requisições neste endpoint
                        .hasAnyRole("USER", "ADMIN")//Se o usuário possuir o perfil user ou admin poderá acessar o endpoint de clientes
                    .antMatchers("/api/produtos/**")
                        .hasRole("ADMIN")//Para o crud de produtos o usuário deverá possuir o perfil de admin
                    .antMatchers("/api/pedidos/**")
                        .hasAnyRole("USER", "ADMIN")
                    .antMatchers(HttpMethod.POST,"/api/usuarios/**")//Para a criação de novos usuários, qualquer pessoa poderá acessar o endpoint
                        .permitAll()
                        .anyRequest().authenticated() //Para todos os outros endpoints não mapeados acima, será necessário estar autenticado para acessar
                .and() //Retorna para o verbo http
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);//Adicionando usuario do token dentro do contexto do spring security
    }
}
