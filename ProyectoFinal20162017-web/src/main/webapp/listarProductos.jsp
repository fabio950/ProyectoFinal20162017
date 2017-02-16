<%-- 
    Document   : listarProductos
    Created on : 16-feb-2017, 10:03:14
    Author     : alumno
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.fpmislata.domain.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Listado de Productos</title>
    </head>
    <body>
        <h1>Listado de Productos</h1>
        <h2>Versión con controlador</h2>
        <a href="agregarProducto.jsp">Agregar Producto</a>
        <br/>
        <br/>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th>Precio</th>
                <th>Categoría</th>
                <th></th>
                <th></th>
            </tr>
            <%
                ArrayList<Producto> lista = (ArrayList) session.getAttribute("listaProductos");
                for (Producto producto : lista) {
                    int id = producto.getId();
                    String nombre = producto.getNombre();
                    Float precio = producto.getPrecio();
                    String categoria = producto.getCategoria().getNombre();
            %>                
            <tr>
                <td><%=id%></a></td>
                <td><%=nombre%></td>
                <td><%=precio%></td>
                <td><%=categoria%></td>
                <td><a href="UpdateCategoria?accion=editar&id=<%=id%>">Modificar</a></td>
                <td><a href="DeleteCategoria?id=<%=id%>">Eliminar</a></td>
            </tr>
            <% }%>
        </table>
        <br>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
