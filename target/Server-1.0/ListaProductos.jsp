<%-- 
    Document   : ListaProductos
    Created on : Aug 6, 2023, 5:19:53 PM
    Author     : japac



--%>

<%@page import="java.util.List"%>
<%@page import="org.json.JSONArray"%>
<%@page import="Server.MostrarListado.Consulta"%>
<%@page import="org.json.JSONObject"%>
<%@page import="jdk.nashorn.internal.parser.JSONParser"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/style.css">
        <title>Listado</title>
    </head>
    <body class="body list">

        <div class="contenedor-list">







            <%
                String NombreUsuario = request.getParameter("NombreUsuario");
                String Contrasena = request.getParameter("Contrasena");

                if (NombreUsuario.length() < 3 || Contrasena.length() < 3) {
                    request.setAttribute("Error", "si");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
                String[] ArrayListaProductos = Consulta.ConvertirJsonArray(NombreUsuario, Contrasena);
                int i = 0;

            %>



            <h1 class="title">Lista de Productos </h1>

            <div class="info">
                <div  class="info-total">
                    <label class="info-total-title">Cantidad de Productos: </label>
                    <span class="info-total-valor"> <%=ArrayListaProductos.length%></span>
                </div>
            </div>
                <form action="Ordenar" method="post">
            <table border="1" class="table td">
                
                    <thead>
                        <tr>

                            <th class="th text-align-center p-05">N°</th>
                            <th class="th p-05">Descripción</th>
                            <th class="th p-05">Identificador</th>
                            <th class="th p-05">Unidades Disponibles</th>
                            <th class="th p-05">Tienda</th>
                        </tr>
                    </thead>
                
                <tbody>

                    <%
                        i = 0;
                        while (i < ArrayListaProductos.length) {%>
                    <tr>


                        <%  JSONObject variableVolatil = new JSONObject(ArrayListaProductos[i]);%>


                        <td class="text-align-center p-06"><%= i + 1%></td>
                        <td class="p-06"><%= variableVolatil.get("_identifier")%></td>
                        <td class="p-06"><%= variableVolatil.get("product")%></td>
                        <td class="text-align-right p-06"><%= variableVolatil.get("quantityOnHand")%></td>
                        <td class="p-06"><%= variableVolatil.get("client$_identifier")%></td>

                        <% i++; %>
                    </tr>
                    <%}


                    %>


                </tbody>
            </table>
                    
                    </form>

        </div>
    </body>
</html>
