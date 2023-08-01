package io.github.WesleyViricimo.domain.repository;

import io.github.WesleyViricimo.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository //Anotação repository faz com que aplicação entenda que a classe deseja realizar operações na base de dados
public class ClienteRepository {

    private static String INSERT = "INSERT INTO T_CLIENTE (NOME) VALUES (?) ";
    private static String SELECIONAR_TODOS = "SELECT * FROM T_CLIENTE";
    private static String UPDATE = "UPDATE T_CLIENTE SET NOME = ? WHERE ID = ?";
    private static String DELETE = "DELETE FROM T_CLIENTE WHERE ID = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Cliente salvarCliente(Cliente cliente) {
        //Método update serve para fazer operações de inserção, atualização e deleção de registros
        jdbcTemplate.update(INSERT, new Object[]{cliente.getNome()});
        return cliente;
    }

    public Cliente atualizar(Cliente cliente) {
        jdbcTemplate.update(UPDATE, new Object[]{ cliente.getNome(), cliente.getId() });
        return cliente;
    }

    public void deletar(Cliente cliente) {
       deletar(cliente.getId());
    }

    public void deletar(Integer id) {
        jdbcTemplate.update(DELETE, new Object[]{ id });
    }

    public List<Cliente> buscarPorNome(String nome) {
        return jdbcTemplate.query(SELECIONAR_TODOS.concat(" WHERE NOME LIKE ? ") , new Object[]{"%" + nome + "%"} , getMapper());
    }

    public List<Cliente> listarClientes() {
        return jdbcTemplate.query(SELECIONAR_TODOS, getMapper());
    }

    private static RowMapper<Cliente> getMapper() {
        return new RowMapper<Cliente>() {
            @Override
            public Cliente mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer id = rs.getInt("ID");
                String nome = rs.getString("NOME");
                return new Cliente(id, nome);
            }
        };
    }
}
