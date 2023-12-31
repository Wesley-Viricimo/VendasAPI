package io.github.WesleyViricimo.rest.controller;

import io.github.WesleyViricimo.domain.entity.Produto;
import io.github.WesleyViricimo.domain.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody @Valid Produto produto) {
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

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody @Valid Produto produto) {
        repository.findById(id)
                .map( produtoExistente -> { //Se encontrar o produto o mesmo será mapeado
                    produto.setId(produtoExistente.getId()); //Irá ser setado na atualização o mesmo id que o produto já possui mantendo assim o mesmo id
                    repository.save(produto);
                    return produtoExistente;
                }).orElseThrow( () ->
                    new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
         repository.findById(id)
                .map(produtoExistente -> {
                    repository.delete(produtoExistente);
                    return produtoExistente;
                }).orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }


    @GetMapping("/filtrar")
    public List<Produto> getFiltroProdutos(Produto filtro) {
        ExampleMatcher exampleMatcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, exampleMatcher);
        return repository.findAll(example);
    }
}
