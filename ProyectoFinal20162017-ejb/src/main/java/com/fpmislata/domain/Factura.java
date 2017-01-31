/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.domain;

import java.io.Serializable;
import javax.persistence.*;
import javax.xml.bind.annotation.*;

/**
 *
 * @author fabio
 */
@Entity
@Table(name = "facturas")
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f ORDER BY f.id")
})
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Factura implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_factura")
    private int id;
    
    @Column(nullable = false, length = 50)
    private String fecha;
    
    @Column(nullable = false, length = 11)
    private Float total;
    
    @ManyToOne
    @JoinColumn(name = "cliente")
    private Cliente cliente;

    public Factura() {
    }

    public Factura(String fecha, Float total, Cliente cliente) {
        this.fecha = fecha;
        this.total = total;
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
