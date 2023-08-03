package io.github.WesleyViricimo.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "T_PEDIDO")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne //Anotação muitos para um, neste caso um cliente poderá ter muitos pedidos
    @JoinColumn(name = "CLIENTE_ID") //Irá ser realizado um join com a coluna CLIENTE_ID da entidade cliente
    private Cliente cliente;

    @Column(name = "DATA_PEDIDO")
    private LocalDate dataPedido;

    @Column(name = "VALOR_PEDIDO", length = 20, precision = 2)//Coluna terá o tamanho de 20 caracteres com 2 casas decimais
    private BigDecimal valorPedido;
}
