<%-- 
    Document   : index
    Created on : Aug 6, 2023, 4:25:36 PM
    Author     : japac
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>ConsultaProductos</title>
    </head>
    <body class="body index">
        <div class="login">
            <div class="contenedor-titulo">
                <h1 class="titulo">Credenciales</h1>
            </div>
            <form action="Listado" method="post" class="formulario">
                <hr class="formulario-hr">
                <input class="formulario-input" placeholder="Usuario" type="text" name="NombreUsuario" id="NombreUsuario">
                <input class="formulario-input" placeholder="Contraseña" type="password" name="Contrasena" id="Contrasena">
                <hr class="formulario-hr">
                <input class="formulario-submit" type="submit" name="Entrar" id="Entrar" value="Entrar">
            </form>
        </div>
    </body>
</html>
