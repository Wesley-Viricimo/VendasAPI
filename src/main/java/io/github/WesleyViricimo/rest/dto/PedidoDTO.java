package io.github.WesleyViricimo.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor //Cria construtor sem os argumentos
@AllArgsConstructor //Cria construtor com todos os argumentos
@Data //Anotação que criará getters e setters automaticamente para as propriedades
public class PedidoDTO {
    private Integer cliente; //irá receber o id do cliente
    private BigDecimal valorPedido; //Irá receber o valor total do pedido
    private List<ItensPedidoDTO> itens;
}
