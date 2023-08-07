package io.github.WesleyViricimo.service;

import io.github.WesleyViricimo.domain.entity.Pedido;
import io.github.WesleyViricimo.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);
}
