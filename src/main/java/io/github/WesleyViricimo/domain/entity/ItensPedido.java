package io.github.WesleyViricimo.domain.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_ITENS_PEDIDO")
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
