package io.github.WesleyViricimo.domain.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NoArgsConstructor //Cria construtor sem os argumentos
@AllArgsConstructor //Cria construtor com todos os argumentos
@Data //Anotação que criará getters e setters automaticamente para as propriedades
@Entity
@Table(name = "T_PRODUTO")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DESCRICAO")
    @NotEmpty(message = "Campo Descrição é obrigatório!")
    private String descricao;

    @Column(name = "VALOR_PRODUTO", precision = 20, scale = 2)
    @NotNull(message = "Campo Preço é obrigatório!")
    private BigDecimal valorProduto;
}
