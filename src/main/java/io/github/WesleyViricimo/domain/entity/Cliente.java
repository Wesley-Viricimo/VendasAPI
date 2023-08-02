package io.github.WesleyViricimo.domain.entity;

import javax.persistence.*;

@Entity//Indica para a aplicação que a classe é uma entidade JPA
public class Cliente {

    @Id //Define qual é a PK ou identificador da entidade e é obrigatória
    @GeneratedValue(strategy = GenerationType.AUTO) //Indica que o campo será gerado automáticamente de forma incremental
    @Column(name = "ID")//Realiza configurações na coluna, altera tamanho, define um nome, define se é uma unique..
    private Integer id;

    @Column(name = "NOME", length = 100)
    String nome;

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

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }
}
