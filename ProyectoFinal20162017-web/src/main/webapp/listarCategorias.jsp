<%@page import="com.fpmislata.domain.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Listado Categorias</title>
    </head>
    <body>
        <h1>Listado de Categorias</h1>
        <h2>Versión con controlador</h2>
        <a href="agregarCategoria.jsp">Agregar Categoria</a>
        <br/>
        <br/>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Nombre</th>
                <th></th>
                <th></th>
                <th></th>
            </tr>
            <%
                ArrayList<Categoria> lista = (ArrayList) session.getAttribute("listaCategorias");
                for (Categoria categoria : lista) {
                    int id = categoria.getId();
                    String nombre = categoria.getNombre();
            %>                
            <tr>
                <td><%=id%></td>
                <td><%=nombre%></td>
                <td><a href="UpdateCategoria?accion=editar&id=<%=id%>">Modificar</a></td>
                <td><a href="DeleteCategoria?id=<%=id%>">Eliminar</a></td>
                <td><a href="ListarProductosPorCategoria?id=<%=id%>">Visualizar productos</a></td>
            </tr>
            <% }%>
        </table>
        <br>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>