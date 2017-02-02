package com.fpmislata.service;

import com.fpmislata.domain.Cliente;
import com.fpmislata.repository.ClienteDAOLocal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Stateless
public class ClienteService implements ClienteServiceLocal {

    @EJB
    private ClienteDAOLocal clienteDAO;

    @Resource
    private SessionContext contexto;

    @Override
    public List listClientes() {
        try {
            return clienteDAO.listClientes();
        } catch (Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Cliente findClienteById(Cliente cliente) {
        try {
            return clienteDAO.findClienteById(cliente);
        } catch (Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void addCliente(Cliente cliente) {
        try {
            clienteDAO.addCliente(cliente);
        } catch(Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateCliente(Cliente cliente) {
        try {
            clienteDAO.updateCliente(cliente);
        } catch(Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteCliente(Cliente cliente) {
        try {
            clienteDAO.deleteCliente(cliente);
        } catch(Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
        }
    }
}
