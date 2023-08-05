package io.github.WesleyViricimo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity//Indica para a aplicação que a classe é uma entidade JPA
@Table(name = "T_CLIENTE")
public class Cliente {

    @Id //Define qual é a PK ou identificador da entidade e é obrigatória
    @GeneratedValue(strategy = GenerationType.AUTO) //Indica que o campo será gerado automáticamente de forma incremental
    @Column(name = "ID")//Realiza configurações na coluna, altera tamanho, define um nome, define se é uma unique..
    private Integer id;

    @Column(name = "NOME", length = 100)
    private String nome;

    @Column(name = "CPF", length = 11)
    private String cpf;

    @JsonIgnore //Anotação para não retornar essa informação no JSON
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY) //Anotação de um para muitos, neste caso um cliente poderá ter muitos pedidos (fetchType.Lazy significa que os pedidos feitos não serão trazidos automáticamente a não ser que seja realizado um fetch)
    private Set<Pedido> pedidos;//Propriedade que irá retornar os pedidos de cada cliente

    public Cliente() {

    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
