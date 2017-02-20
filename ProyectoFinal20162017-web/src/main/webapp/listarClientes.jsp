<%@page import="com.fpmislata.domain.Cliente"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Listado Clientes</title>
</head>
<body>

	<h1>Listado de Clientes</h1>
        <h2>Versión con controlador</h2>
        
	<a href="agregarCliente.jsp">Agregar Cliente</a>
	<br/>
	<br/>

	<table border="1">
		<tr>
                    <th>Id</th>
                    <th>Nombre</th>
                    <th>Apellidos</th>
                    <th>NIF</th>
                    <th>Teléfono</th>
                    <th> --- </th>
                    <th>Dirección</th>
                    <th>Población</th>
                    <th>Provincia</th>
                    <th>CP</th>
                    <th></th>
                    <th></th>
                    <th></th>
		</tr>
                <%
                ArrayList<Cliente> lista = (ArrayList) session.getAttribute("listaClientes");
                for(Cliente cliente : lista){
                    
                    int id = cliente.getId();
                    String nombre = cliente.getNombre();
                    String apellidos = cliente.getApellidos();
                    String nif = cliente.getNif();
                    String direccion = cliente.getDireccion().getDireccion();
                    String poblacion = cliente.getDireccion().getPoblacion();
                    String provincia = cliente.getDireccion().getProvincia();
                    String codigopostal = cliente.getDireccion().getCodigopostal();
                    String telefono = cliente.getTelefono();

                %>                
		<tr>
                    <td><%=id%></td>
                    <td><%=nombre%></td>
                    <td><%=apellidos%></td>
                    <td><%=nif%></td>
                    <td><%=telefono%></td>
                    <td></td>
                    <td><%=direccion%></td>
                    <td><%=poblacion%></td>
                    <td><%=provincia%></td>
                    <td><%=codigopostal%></td>
                    <td><a href="UpdateCliente?accion=editar&id=<%=id%>">Modificar</a></td>
                    <td><a href="DeleteCliente?id=<%=id%>">Eliminar</a></td>
                    <td><a href="ListarProductosPorCliente?id=<%=id%>">Visualizar productos</a></td>
                </tr>
                <% } %>
	</table>
	<br>
	<a href="index.jsp">Regresar al Inicio</a>
</body>
</html>