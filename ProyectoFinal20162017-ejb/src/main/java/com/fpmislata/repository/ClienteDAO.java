package com.fpmislata.repository;

import com.fpmislata.domain.Cliente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class ClienteDAO implements ClienteDAOLocal {

    @PersistenceContext(unitName = "proyectofinal20162017PU")
    EntityManager em;

    @Override
    public List listClientes() {
        List<Cliente> lista = em.createNamedQuery("Cliente.findAll").getResultList();
        return lista;
    }

    @Override
    public Cliente findClienteById(Cliente cliente) {
        cliente = em.find(Cliente.class, cliente.getId());
        return cliente;
    }
    
    @Override
    public void addCliente(Cliente cliente) {
        em.persist(cliente);
    }
    
    @Override
    public void updateCliente(Cliente cliente) {
        em.merge(cliente);
    }
    
    @Override
    public void deleteCliente(Cliente cliente) {
        cliente = findClienteById(cliente);
        em.remove(cliente);
    }
}
