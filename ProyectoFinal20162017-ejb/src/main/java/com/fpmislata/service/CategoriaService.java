/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.service;

import com.fpmislata.domain.Categoria;
import com.fpmislata.repository.CategoriaDAOLocal;
import java.util.List;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

/**
 *
 * @author alumno
 */
@Stateless
public class CategoriaService implements CategoriaServiceLocal {

    @EJB
    private CategoriaDAOLocal categoriaDAO;

    @Resource
    private SessionContext contexto;
    
    @Override
    public List listCategorias() {
        try{
            return categoriaDAO.listCategorias();
        } catch(Exception e){
            contexto.setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    public void addCategoria(Categoria categoria) {
        try {
            categoriaDAO.addCategoria(categoria);
        } catch (Exception e){
            contexto.setRollbackOnly();
            e.printStackTrace();
        }
    }
    
    @Override
    public void deleteCategoria(Categoria categoria) {
        try {
            categoriaDAO.deleteCategoria(categoria);
        } catch (Exception e){
            contexto.setRollbackOnly();
            e.printStackTrace();
        }
    }
    
    @Override
    public void updateCategoria(Categoria categoria){
        try {
            categoriaDAO.updateCategoria(categoria);
        } catch (Exception e){
            contexto.setRollbackOnly();
            e.printStackTrace();
        }
    }
    
    @Override
    public Categoria findCategoriaById(Categoria categoria) {
        try{
            return categoriaDAO.findCategoriaById(categoria);
        } catch(Exception e){
            contexto.setRollbackOnly();
            e.printStackTrace();
            return null;
        }
    }
    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
