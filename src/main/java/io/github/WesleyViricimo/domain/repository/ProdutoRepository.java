package io.github.WesleyViricimo.domain.repository;

import io.github.WesleyViricimo.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
