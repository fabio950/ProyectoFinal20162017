/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.service;

import com.fpmislata.repository.FacturaDAOLocal;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author fabio
 */
@Stateless
public class FacturaService implements FacturaServiceLocal {

    @EJB
    private FacturaDAOLocal facturaDAO;

    @Resource
    private SessionContext contexto;
    
    @Override
    public List listFacturas() {
        try {
            return facturaDAO.listFacturas();
        } catch(Exception e) {
            contexto.setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
