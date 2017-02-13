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
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public List<Producto> listProductos() {
        return productoService.listProductos();
    }
    
    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Productos/FindById/{id}")
    public Producto findProductoById(@PathParam("id") int id) {
        Producto producto = new Producto();
        producto.setId(id);
        producto = productoService.findProductoById(producto);
        return producto;
    }
    
    @POST
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Productos/add")
    public Response addProducto(Producto producto) {
        try {
            productoService.addProducto(producto);
            return Response.ok().entity(producto).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }
    
    @PUT
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Productos/update/{id}")
    public Response updateProducto(@PathParam("id") int id, Producto productoModificado) {
        try {
            Producto producto = new Producto();
            producto.setId(id);
            producto = productoService.findProductoById(producto);
            if (producto != null) {
                producto.setNombre(productoModificado.getNombre());
                producto.setPrecio(productoModificado.getPrecio());
                producto.setCategoria(productoModificado.getCategoria());
                productoService.updateProducto(producto);
                return Response.ok().entity(producto).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }
    
    @DELETE
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Productos/delete/{id}")
    public Response deleteProducto(@PathParam("id") int id) {
        try {
            Producto producto = new Producto();
            producto.setId(id);
            productoService.deleteProducto(producto);
            return Response.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }
}