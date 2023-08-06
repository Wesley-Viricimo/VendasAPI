package io.github.WesleyViricimo.service.impl;

import io.github.WesleyViricimo.domain.repository.PedidoRepository;
import io.github.WesleyViricimo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository repository;

}
