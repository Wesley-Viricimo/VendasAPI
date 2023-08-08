package io.github.WesleyViricimo.rest.dto;

import io.github.WesleyViricimo.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor //Cria construtor sem os argumentos
@AllArgsConstructor //Cria construtor com todos os argumentos
@Data //Anotação que criará getters e setters automaticamente para as propriedades
public class PedidoDTO {
    @NotNull(message = "Informe o código do cliente!")
    private Integer cliente; //irá receber o id do cliente
    @NotNull(message = "O Campo total do pedido é obrigatório!")
    private BigDecimal valorPedido; //Irá receber o valor total do pedido
    @NotEmptyList(message = "Pedido não pode ser realizado sem itens!")
    private List<ItensPedidoDTO> itens;
}
