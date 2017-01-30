package com.fpmislata.service;

import com.fpmislata.domain.Cliente;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ClienteServiceLocal {
    
    List listClientes();
    
    Cliente findClienteById(Cliente cliente);
}
