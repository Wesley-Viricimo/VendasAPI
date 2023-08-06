package io.github.WesleyViricimo.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor //Cria construtor sem os argumentos
@AllArgsConstructor //Cria construtor com todos os argumentos
@Data //Anotação que criará getters e setters automaticamente para as propriedades
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

    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
