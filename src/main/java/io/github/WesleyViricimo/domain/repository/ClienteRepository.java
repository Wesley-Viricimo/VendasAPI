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
        entityManager.persist(cliente); //Método persist salva informações no banco de dados
        return cliente;
    }

    @Transactional
    public Cliente atualizar(Cliente cliente) {
        entityManager.merge(cliente); //Método merge atualiza as informações em cache e posteriormente atualiza no banco de dados
        return cliente;
    }

    @Transactional
    public void deletar(Cliente cliente) {
        if(!entityManager.contains(cliente)) { //entityManager salva as alterações em cache, e se não contiver o cliente que foi recebido como parâmetro
            cliente = entityManager.merge(cliente); //Mergear as alterações
        }
        entityManager.remove(cliente); //Se contiver, remover o cliente
    }

    @Transactional
    public void deletar(Integer id) {
        Cliente cliente = entityManager.find(Cliente.class, id);
        deletar(cliente);
    }

    @Transactional
    public List<Cliente> buscarPorNome(String nome) {
       String jpql = " SELECT C FROM Cliente C WHERE C.nome LIKE :nome "; //JPQL irá buscar na tabela de cliente algum cliente que possua o nome parecido com o que foi recebido como parâmetro
       TypedQuery<Cliente> query = entityManager.createQuery(jpql, Cliente.class); //Criação da query passando como parâmetro a string com a JPQL e a classe entidade
       query.setParameter("nome", "%" + nome + "%");
       return query.getResultList(); //Retornar uma lista com o que foi encontrado
    }

    public List<Cliente> listarClientes() {
        return entityManager.createQuery(" FROM Cliente", Cliente.class).getResultList();
    }

}
