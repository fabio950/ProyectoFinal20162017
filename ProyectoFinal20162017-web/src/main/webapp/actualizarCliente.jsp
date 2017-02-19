<%-- 
    Document   : agregarCliente
    Created on : 19-feb-2017, 18:45:25
    Author     : fabio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Cliente</title>
    </head>
    <body>
        <h1>Actualizar Cliente</h1>
        <form action="UpdateCliente?accion=actualizar" method="post">
            <input type="hidden" name="id" value="${c.getId()}"/>
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" style="display: block;" value="${c.getNombre()}"/>
            <label for="apellidos">Apellidos:</label>
            <input type="text" name="apellidos" style="display: block;" value="${c.getApellidos()}"/>
            <label for="nif">NIF:</label>
            <input type="text" name="nif" style="display: block;" value="${c.getNif()}"/>
            <label for="telefono">Teléfono:</label>
            <input type="text" name="telefono" style="display: block;" value="${c.getTelefono()}"/>
            <label for="direccion">Dirección:</label>
            <input type="text" name="direccion" style="display: block;" value="${c.getDireccion().getDireccion()}"/>
            <label for="poblacion">Población:</label>
            <input type="text" name="poblacion" style="display: block;" value="${c.getDireccion().getPoblacion()}"/>
            <label for="provincia">Provincia:</label>
            <input type="text" name="provincia" style="display: block;" value="${c.getDireccion().getProvincia()}"/>
            <label for="cp">CP:</label>
            <input type="text" name="cp" style="display: block;" value="${c.getDireccion().getCodigopostal()}"/>
            <input type="submit" value="Enviar" />
        </form>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
