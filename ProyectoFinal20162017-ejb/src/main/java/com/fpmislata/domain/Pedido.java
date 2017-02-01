/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author alumno
 */
@Entity
@Table(name = "pedidos")
@NamedQueries({
    @NamedQuery(name = "Pedido.findAll", query = "SELECT p FROM Pedido p ORDER BY p.id")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pedido implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private int id;
    
    @Column(nullable = false, length = 50)
    private String descripcion;
    
    @Column(nullable = false)
    private int estado;
    
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Linea_Pedido> lineas;

    public Pedido() {
        lineas = new HashSet<Linea_Pedido>();
    }

    public Pedido(String descripcion, int estado) {
        this.descripcion = descripcion;
        this.estado = estado;
        lineas = new HashSet<Linea_Pedido>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Set<Linea_Pedido> getLineas() {
        return lineas;
    }

    public void setLineas(Set<Linea_Pedido> lineas) {
        this.lineas = lineas;
    }
}
