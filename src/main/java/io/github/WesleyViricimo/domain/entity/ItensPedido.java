package io.github.WesleyViricimo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@NoArgsConstructor //Cria construtor sem os argumentos
@AllArgsConstructor //Cria construtor com todos os argumentos
@Data //Anotação que criará getters e setters automaticamente para as propriedades
@Entity
@Table(name = "T_ITENS_PEDIDO")
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne//Anotação muitos para um, neste caso muitos itens de pedido podem estar em apenas um pedido
    @JoinColumn(name = "PEDIDO_ID")
    private Pedido pedido;

    @ManyToOne//Anotação muitos para um, neste caso muitos itens de pedido podem estar em um produto
    @JoinColumn(name = "PRODUTO_ID")
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

}
