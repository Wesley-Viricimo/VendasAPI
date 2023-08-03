package io.github.WesleyViricimo.domain.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

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

    @OneToMany(mappedBy = "pedido")//Relacionamento de um para muitos, neste caso um pedido poderá ter muitos itens do pedido (mapped by deverá ser passado o atributo que representa este relacionamento)
    private Set<ItensPedido> itensPedidos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalDate getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDate dataPedido) {
        this.dataPedido = dataPedido;
    }

    public BigDecimal getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(BigDecimal valorPedido) {
        this.valorPedido = valorPedido;
    }

    public Set<ItensPedido> getItensPedidos() {
        return itensPedidos;
    }

    public void setItensPedidos(Set<ItensPedido> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }
}
