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
                    <th>Dirección</th>
                    <th>Población</th>
                    <th>Provincia</th>
                    <th>CP</th>
                    <th>Teléfono</th>
                    <th></th>
                    <th></th>
		</tr>

                <%
                ArrayList<Cliente> lista = (ArrayList) session.getAttribute("listaClientes");
                for(Cliente cliente : lista){
                    
                    int id = cliente.getId();
                    String nombre = cliente.getNombre();
                    String apellido = cliente.getApellidos();
                    String nif = cliente.getNif();
                    String direccion = cliente.getDireccion();
                    String poblacion = cliente.getPoblacion();
                    String provincia = cliente.getProvincia();
                    String codigopostal = cliente.getCodigopostal();
                    String telefono = cliente.getTelefono();

                %>                
		<tr>
                    <td><%=id%></a></td>
                    <td><%=nombre%></td>
                    <td><%=apellido%></td>
                    <td><%=nif%></td>
                    <td><%=direccion%></td>
                    <td><%=poblacion%></td>
                    <td><%=provincia%></td>
                    <td><%=codigopostal%></td>
                    <td><%=telefono%></td>
                    <td><a href="ModificarCliente?accion=editar&id=<%=id%>">Modificar</td>
                    <td><a href="EliminarCliente?id=<%=id%>">Eliminar</a></td>
                </tr>
                <% } %>
	</table>
	<br>
	<a href="index.jsp">Regresar al Inicio</a>
</body>
</html>