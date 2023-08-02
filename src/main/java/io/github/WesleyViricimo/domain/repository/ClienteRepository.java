package io.github.WesleyViricimo.domain.repository;

import io.github.WesleyViricimo.domain.entity.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository //Anotação repository faz com que aplicação entenda que a classe deseja realizar operações na base de dados
public class ClienteRepository {

    @Autowired
    private EntityManager entityManager;

    @Transactional//Como entityManager precisa de uma transação para realizar as trnasações, utilizamos esta anotação para conseguir gerar uma transação na base de dados
    public Cliente salvarCliente(Cliente cliente) {
        entityManager.persist(cliente);
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
        entityManager.merge(cliente);
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente) {
        if(!entityManager.contains(cliente)){
            cliente = entityManager.merge(cliente);
        }
        entityManager.remove(cliente);
    }

    @Transactional
    public void deletar(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }

    @Transactional
    public List<Cliente> buscarPorNome(String nome) {
       String jpql = " SELECT C FROM Cliente C WHERE C.nome LIKE :nome ";
       TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class);
       query.setParameter("nome", "%" + nome + "%");
       return query.getResultList();
    }

    public List<Cliente> listarClientes() {
        return entityManager.createQuery(" FROM Cliente", Cliente.class).getResultList();
    }

}
