/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.repository;

import com.fpmislata.domain.Factura;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author fabio
 */
@Stateless
public class FacturaDAO implements FacturaDAOLocal {

    @PersistenceContext(unitName = "proyectofinal20162017PU")
    EntityManager em;
    
    @Override
    public List listFacturas() {
        List<Factura> lista = em.createNamedQuery("Factura.findAll").getResultList();
        return lista;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
