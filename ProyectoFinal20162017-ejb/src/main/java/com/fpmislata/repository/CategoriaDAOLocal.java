/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.repository;

import com.fpmislata.domain.Categoria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author alumno
 */
@Local
public interface CategoriaDAOLocal {
    
    List listCategorias();
    
    void addCategoria(Categoria categoria);
    
    void deleteCategoria(Categoria categoria);
    
    Categoria findCategoriaById(Categoria categoria);
    
    void updateCategoria(Categoria categoria);
}
