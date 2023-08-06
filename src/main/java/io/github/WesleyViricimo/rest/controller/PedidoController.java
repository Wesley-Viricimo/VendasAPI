package io.github.WesleyViricimo.rest.controller;

import io.github.WesleyViricimo.domain.entity.Pedido;
import io.github.WesleyViricimo.domain.repository.PedidoRepository;
import io.github.WesleyViricimo.rest.dto.PedidoDTO;
import io.github.WesleyViricimo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @Autowired
    private PedidoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody PedidoDTO dto) { //Corpo da requisição de pedido será recebido na forma de DTO
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }

}
