/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fpmislata.repository;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author fabio
 */
@Local
public interface FacturaDAOLocal {
    List listFacturas();
}
