package webservice;

import com.fpmislata.domain.Cliente;
import com.fpmislata.service.ClienteServiceLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/ClientesService")
@Stateless
public class ClienteServiceREST {

    @EJB
    private ClienteServiceLocal clienteService;

    @GET
    @Produces({"application/xml", "application/json"})
    @Path("/Clientes")
    public List<Cliente> listClientes() {
        return clienteService.listClientes();
    }

    @GET
    @Produces({"application/xml", "application/json"})
    @Consumes({"application/xml", "application/json"})
    @Path("/Cliente/FindById/{id}")
    public Cliente findClienteById(@PathParam("id") int id) {
        Cliente c = new Cliente();
        c.setId(id);
        c = clienteService.findClienteById(c);
        return c;
    }
}
