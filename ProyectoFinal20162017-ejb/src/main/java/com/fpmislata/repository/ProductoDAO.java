/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.repository;

import com.fpmislata.domain.Producto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author alumno
 */
@Stateless
public class ProductoDAO implements ProductoDAOLocal {

    @PersistenceContext(unitName = "proyectofinal20162017PU")
    EntityManager em;
    
    @Override
    public List listProductos() {
        List<Producto> lista = em.createNamedQuery("Producto.findAll").getResultList();
        return lista;
    }
    
    @Override
    public Producto findProductoById(Producto producto) {
        producto = em.find(Producto.class, producto.getId());
        return producto;
    }
    
    @Override
    public void addProducto(Producto producto) {
        em.persist(producto);
    }
    
    @Override
    public void updateProducto(Producto producto) {
        em.merge(producto);
    }
    
    @Override
    public void deleteProducto(Producto producto) {
        producto = findProductoById(producto);
        em.remove(producto);
    }
}
