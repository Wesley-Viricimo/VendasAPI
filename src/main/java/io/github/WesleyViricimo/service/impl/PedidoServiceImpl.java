package io.github.WesleyViricimo.service.impl;

import io.github.WesleyViricimo.domain.entity.Cliente;
import io.github.WesleyViricimo.domain.entity.ItensPedido;
import io.github.WesleyViricimo.domain.entity.Pedido;
import io.github.WesleyViricimo.domain.entity.Produto;
import io.github.WesleyViricimo.domain.enums.StatusPedido;
import io.github.WesleyViricimo.domain.repository.ClienteRepository;
import io.github.WesleyViricimo.domain.repository.ItensPedidoRepository;
import io.github.WesleyViricimo.domain.repository.PedidoRepository;
import io.github.WesleyViricimo.domain.repository.ProdutoRepository;
import io.github.WesleyViricimo.exception.RegraNegocioException;
import io.github.WesleyViricimo.rest.dto.ItensPedidoDTO;
import io.github.WesleyViricimo.rest.dto.PedidoDTO;
import io.github.WesleyViricimo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItensPedidoRepository itensPedidoRepository;

    @Override
    @Transactional //Anotação que garantirá que se alguma operação no banco de dados não for realizada com sucesso será feito um rollback
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente(); //Como o pedido precisa ter um cliente, será recuperado o id do cliente que foi enviado no corpo da requisição
        Cliente cliente = clienteRepository.findById(idCliente).orElseThrow(() -> //Será verificado se o cliente enviado no corpo da requisição existe
                new RegraNegocioException("Código de cliente inválido!")); //Se não existir será disparada a exceção
        Pedido pedido = new Pedido(); //Criando novo pedido
        pedido.setValorPedido(dto.getValorPedido());
        pedido.setDataPedido(LocalDate.now()); //Setando propriedades para a entidade de pedido
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        pedidoRepository.save(pedido); //Salvando o pedido, o que fará com que seja gerado o id

        List<ItensPedido> itensPedido = converterItens(pedido, dto.getItens()); //Buscando a lista de produtos que foram vendidos no pedido

        itensPedidoRepository.saveAll(itensPedido); //Salvando os itens do pedido

        pedido.setItensPedidos(itensPedido); //Setando os itens do pedido na entidade de pedido

        return pedido;
    }

    private List<ItensPedido> converterItens(Pedido pedido, List<ItensPedidoDTO> itensDTO) {
        if(itensDTO.isEmpty()) { //Se a lista estiver vazia será disparada uma exceção
            throw new RegraNegocioException("Não é possível realizar pedido sem itens!");
        }

        return itensDTO
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtoRepository.findById(idProduto).orElseThrow( () ->
                            new RegraNegocioException("Código de produto inválido: " + idProduto));

                    ItensPedido itensPedido = new ItensPedido();
                    itensPedido.setQuantidade(dto.getQuantidade());
                    itensPedido.setPedido(pedido);
                    itensPedido.setProduto(produto);
                    return itensPedido;
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidoRepository.findByIdFetchItens(id);
    }

}
