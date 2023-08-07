package io.github.WesleyViricimo.domain.repository;

import io.github.WesleyViricimo.domain.entity.Cliente;
import io.github.WesleyViricimo.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    @Query("select p from Pedido p left join fetch p.itensPedidos where p.id = :id ")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}
