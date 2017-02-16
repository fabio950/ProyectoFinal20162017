<%-- 
    Document   : actualizarCategoria
    Created on : 16-feb-2017, 9:19:34
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Categoría</title>
    </head>
    <body>
        <h1>Actualizar Categoría</h1>
        <form action="UpdateCategoria?accion=actualizar" method="post">
            <input type="hidden" name="id" value="${c.getId()}"/>
            <label for="nombre">Nombre:</label>
            <input type="text" name="nombre" style="display: block;" value="${c.getNombre()}"/>
            <input type="submit" value="Enviar" />
        </form>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
