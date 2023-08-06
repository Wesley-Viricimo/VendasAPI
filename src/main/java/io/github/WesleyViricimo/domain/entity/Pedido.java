package io.github.WesleyViricimo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor //Cria construtor sem os argumentos
@AllArgsConstructor //Cria construtor com todos os argumentos
@Data //Anotação que criará getters e setters automaticamente para as propriedades
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

    @Column(name = "VALOR_PEDIDO", precision = 20, scale = 2)//Coluna terá o tamanho de 20 caracteres com 2 casas decimais
    private BigDecimal valorPedido;

    @OneToMany(mappedBy = "pedido")//Relacionamento de um para muitos, neste caso um pedido poderá ter muitos itens do pedido (mapped by deverá ser passado o atributo que representa este relacionamento)
    private List<ItensPedido> itensPedidos;

}
