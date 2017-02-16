<%-- 
    Document   : agregarCategoria
    Created on : 16-feb-2017, 8:51:31
    Author     : alumno
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Añadir Categoría</title>
    </head>
    <body>
        <h1>Añadir Categoría</h1>
        <form action="AddCategoria" method="post">
		<label for="nombre">Nombre:</label>
		<input type="text" name="nombre" style="display: block;" />
		<input type="submit" value="Enviar" />
	</form>
        <a href="index.jsp">Regresar al Inicio</a>
    </body>
</html>
