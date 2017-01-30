package com.fpmislata.repository;

import com.fpmislata.domain.Cliente;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ClienteDAOLocal {

    List listClientes();

    Cliente findClienteById(Cliente cliente);
}
