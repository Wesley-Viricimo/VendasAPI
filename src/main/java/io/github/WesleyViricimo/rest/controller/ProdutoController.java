package io.github.WesleyViricimo.rest.controller;

import io.github.WesleyViricimo.domain.entity.Produto;
import io.github.WesleyViricimo.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto) {
        return repository.save(produto);
    }

    @GetMapping(value = "/{id}")
    public Produto getById(@PathVariable Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!");
        });
    }

    @GetMapping
    public List<Produto> getProdutos() {
        return repository.findAll();
    }
}
