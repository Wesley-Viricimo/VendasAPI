package io.github.WesleyViricimo.domain.repository;

import io.github.WesleyViricimo.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> { //Para a utilização do spring data JPA, deve ser extendida da classe JpaRepository e deve ser informada a classe entidade e o tipo da variável que é o ID

    @Query(value = " select * from t_cliente c where c.nome like '%:nome%' ", nativeQuery = true)//Utilizando @Query para realizar consultas com sql nativo, no value adicionar a consulta e no nativeQuery setar o valor true
    List<Cliente> encontrarPorNome(@Param("nome") String nome); //Deverá ser adicionada a anotação param caso for recebido algum parâmetro

    @Query(value = " delete from t_cliente where nome = ':nome' ", nativeQuery = true)
    @Modifying //Precisa adicionar anotação modifying para utilizar a anotação @Query para métodos de crud
    void deleteByNome(String nome);

    boolean existsByNome(String nome);//QueryMethod para retornar se existe um cliente com o nome que foi recebido como parâmetro

}
