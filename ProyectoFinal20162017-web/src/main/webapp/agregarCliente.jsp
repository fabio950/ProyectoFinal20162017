<%-- 
    Document   : agregarCliente
    Created on : 19-feb-2017, 18:45:25
    Author     : fabio
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.fpmislata.domain.Producto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir Cliente</title>
    </head>
    <body>
        <h1>Añadir Cliente</h1>
        <form action="AddCliente" method="post">
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" style="display: block;" />
            <label for="apellidos">Apellidos:</label>
            <input type="text" name="apellidos" style="display: block;" />
            <label for="nif">NIF:</label>
            <input type="text" name="nif" style="display: block;" />
            <label for="telefono">Teléfono:</label>
            <input type="text" name="telefono" style="display: block;" />
            <label for="direccion">Dirección:</label>
            <input type="text" name="direccion" style="display: block;" />
            <label for="poblacion">Población:</label>
            <input type="text" name="poblacion" style="display: block;" />
            <label for="provincia">Provincia:</label>
            <input type="text" name="provincia" style="display: block;" />
            <label for="cp">CP:</label>
            <input type="text" name="cp" style="display: block;" />
            <label for="productos">Productos:</label>
            <br>
            <div>
                <%
                    ArrayList<Producto> lista = (ArrayList) session.getAttribute("listaProductos");
                    for (Producto p : lista) {
                        int id = p.getId();
                        String nombre = p.getNombre();
                %>
                <input type="checkbox" name="productos" value="<%=id%>"><%=nombre%>
                <% }%>
            </div>
                <input type="submit" value="Enviar" />
        </form>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
