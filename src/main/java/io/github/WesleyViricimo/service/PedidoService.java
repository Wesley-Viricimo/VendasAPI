package io.github.WesleyViricimo.service;

import io.github.WesleyViricimo.domain.entity.Pedido;
import io.github.WesleyViricimo.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
