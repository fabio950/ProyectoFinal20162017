<%-- 
    Document   : agregarProducto
    Created on : 16-feb-2017, 10:36:22
    Author     : alumno
--%>

<%@page import="com.fpmislata.domain.Categoria"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir Producto</title>
    </head>
    <body>
        <h1>Añadir Producto</h1>
        <form action="AddProducto" method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" style="display: block;" />
            <label for="precio">Precio:</label>
            <input type="text" name="precio" style="display: block;" />
            <label for="categoria">Zona:</label>
            <select name="categoria" style="display: block;">
                <%
                    ArrayList<Categoria> lista = (ArrayList) session.getAttribute("listaCategorias");
                    for (Categoria categoria : lista) {
                        int id = categoria.getId();
                        String nombre = categoria.getNombre();
                %>
                <option value="<%=id%>"><%=nombre%></option>
                <% }%>
            </select>
            <input type="submit" value="Enviar" />
        </form>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
