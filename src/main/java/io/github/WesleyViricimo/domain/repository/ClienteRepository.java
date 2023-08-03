package io.github.WesleyViricimo.domain.repository;

import io.github.WesleyViricimo.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByNomeLike(String nome); //Para a utilização do spring data JPA, deve ser extendida da classe JpaRepository e deve ser informada a classe entidade e o tipo da variável que é o ID

    boolean existsByNome(String nome);//QueryMethod para retornar se existe um cliente com o nome que foi recebido como parâmetro

}
