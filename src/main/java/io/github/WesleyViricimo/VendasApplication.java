package io.github.WesleyViricimo;

import io.github.WesleyViricimo.domain.entity.Cliente;
import io.github.WesleyViricimo.domain.entity.Pedido;
import io.github.WesleyViricimo.domain.repository.ClienteRepository;
import io.github.WesleyViricimo.domain.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;


@SpringBootApplication //Anotação para Spring reconhecer que a classe irá inicializar uma aplicação Spring Boot
public class VendasApplication {
    @Bean
    public CommandLineRunner init(@Autowired ClienteRepository clientes,
                                  @Autowired PedidoRepository pedidos) {
        return args -> {
            System.out.println("Salvando clientes");
            Cliente wesley = new Cliente("Wesley");
            clientes.save(wesley);

            Pedido p = new Pedido();
            p.setCliente(wesley);
            p.setDataPedido(LocalDate.now());
            p.setValorPedido(BigDecimal.valueOf(35.32));
            pedidos.save(p);

            /*Cliente cliente = clientes.findClienteFetchPedidos(wesley.getId());
            System.out.println(cliente);
            System.out.println(cliente.getPedidos());
             */

            pedidos.findByCliente(wesley).forEach(System.out::println);

        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }
}