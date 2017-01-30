/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservice;

import com.fpmislata.domain.Categoria;
import com.fpmislata.service.CategoriaServiceLocal;
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
@Path("/CategoriasService")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
@Stateless
public class CategoriaServiceREST {

    @EJB
    private CategoriaServiceLocal categoriaService;

    @GET
    @Produces("application/json;charset=UTF-8")
    @Path("/Categorias")
    public List<Categoria> listCategorias() {
        return categoriaService.listCategorias();
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Categorias/FindById/{id}")
    public Categoria findCategoriaById(@PathParam("id") int id) {
        Categoria c = new Categoria();
        c.setId(id);
        c = categoriaService.findCategoriaById(c);
        return c;
    }

    @POST
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Categorias/add")
    public Response addCategoria(Categoria categoria) {
        try {
            categoriaService.addCategoria(categoria);
            return Response.ok().entity(categoria).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }
}
