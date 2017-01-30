/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import com.fpmislata.domain.Producto;
import com.fpmislata.service.ProductoServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author alumno
 */
@Path("/ProductosService")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
@Stateless
public class ProductoServiceREST {

    @EJB
    private ProductoServiceLocal productoService;
    
    @GET
    @Produces("application/json;charset=UTF-8")
    @Path("/Productos")
    public List<Producto> listProductos(){
        return productoService.listProductos();
    }
}
