package io.github.WesleyViricimo.rest.dto;

import java.math.BigDecimal;
import java.util.List;

public class PedidoDTO {
    private Integer cliente; //irá receber o id do cliente
    private BigDecimal valorPedido; //Irá receber o valor total do pedido
    private List<ItensPedidoDTO> itens;
}
