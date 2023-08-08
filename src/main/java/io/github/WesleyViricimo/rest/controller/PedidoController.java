package io.github.WesleyViricimo.rest.controller;

import io.github.WesleyViricimo.domain.entity.ItensPedido;
import io.github.WesleyViricimo.domain.entity.Pedido;
import io.github.WesleyViricimo.domain.enums.StatusPedido;
import io.github.WesleyViricimo.domain.repository.PedidoRepository;
import io.github.WesleyViricimo.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.WesleyViricimo.rest.dto.InformacoesItensPedidoDTO;
import io.github.WesleyViricimo.rest.dto.InformacoesPedidoDTO;
import io.github.WesleyViricimo.rest.dto.PedidoDTO;
import io.github.WesleyViricimo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
        return service.obterPedidoCompleto(id)
                .map(pedido -> converterPedido(pedido))
                .orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
    }

    private InformacoesPedidoDTO converterPedido(Pedido pedido) {
        return InformacoesPedidoDTO.builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .valorPedido(pedido.getValorPedido())
                .status(pedido.getStatus().name()) //.name irá pegar o enumerador e transformar em string
                .itens(converterItensPedido(pedido.getItensPedidos()))
                .build();
    }

    private List<InformacoesItensPedidoDTO> converterItensPedido(List<ItensPedido> itens) {
        if(CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }

        return itens.stream().map( item -> InformacoesItensPedidoDTO
                .builder()
                .descricaoProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getValorProduto())
                .quantidade(item.getQuantidade())
                .build()
        ).collect(Collectors.toList());
    }

    @PatchMapping("/{id}") //PutMapping atualiza todos os campos da entidade por completo e caso algum campo não for informado o mesmo receberá o valor nulo, mas com o PatchMapping é possível alterar apenas alguns campos
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatusPedido(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
        String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }

}
