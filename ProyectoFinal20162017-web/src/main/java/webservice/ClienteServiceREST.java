package webservice;

import com.fpmislata.domain.Cliente;
import com.fpmislata.service.ClienteServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/ClientesService")
@Produces("application/json;charset=UTF-8")
@Consumes("application/json;charset=UTF-8")
@Stateless
public class ClienteServiceREST {

    @EJB
    private ClienteServiceLocal clienteService;

    @GET
    @Produces("application/json;charset=UTF-8")
    @Path("/Clientes")
    public List<Cliente> listClientes() {
        return clienteService.listClientes();
    }

    @GET
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Clientes/FindById/{id}")
    public Cliente findClienteById(@PathParam("id") int id) {
        Cliente c = new Cliente();
        c.setId(id);
        return clienteService.findClienteById(c);
    }
    
    @POST
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Clientes/add")
    public Response addCliente(Cliente cliente) {
        try {
            clienteService.addCliente(cliente);
            return Response.ok().entity(cliente).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }
    
    /*
    @PUT
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Clientes/update/{id}")
    public Response updateCliente(@PathParam("id") int id, Cliente clienteModificado) {
        try {
            Cliente cliente = new Cliente();
            cliente.setId(id);
            cliente = clienteService.findClienteById(cliente);
            if(cliente != null) {
                cliente.setNombre(clienteModificado.getNombre());
                cliente.setApellidos(clienteModificado.getApellidos());
                cliente.setNif(clienteModificado.getNif());
                cliente.setDireccion(clienteModificado.getDireccion());
                cliente.setPoblacion(clienteModificado.getPoblacion());
                cliente.setProvincia(clienteModificado.getProvincia());
                cliente.setCodigopostal(clienteModificado.getCodigopostal());
                cliente.setTelefono(clienteModificado.getTelefono());
                clienteService.updateCliente(cliente);
                return Response.ok().entity(cliente).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }
    **/

    @DELETE
    @Produces("application/json;charset=UTF-8")
    @Consumes("application/json;charset=UTF-8")
    @Path("/Clientes/delete/{id}")
    public Response deleteCliente(@PathParam("id") int id) {
        try {
            Cliente cliente = new Cliente();
            cliente.setId(id);
            clienteService.deleteCliente(cliente);
            return Response.ok().header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        } catch (Exception ex) {
            ex.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_TYPE.withCharset("UTF-8")).build();
        }
    }
}
