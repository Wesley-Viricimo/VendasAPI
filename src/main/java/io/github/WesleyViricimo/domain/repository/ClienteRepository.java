package io.github.WesleyViricimo.domain.repository;

import io.github.WesleyViricimo.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Iterable<Object> findByNomeLike(String nome); //Para a utilização do spring data JPA, deve ser extendida da classe JpaRepository e deve ser informada a classe entidade e o tipo da variável que é o ID



}
