<%-- 
    Document   : ListaProductos
    Created on : Aug 6, 2023, 5:19:53 PM
    Author     : japac



--%>

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
                Consulta Consulta = new Consulta();
        
        
                JSONObject jsonObsject = Consulta.EnviarListaProductos(NombreUsuario, Contrasena);
                JSONObject OJSresponse = jsonObsject.getJSONObject("response");
                JSONArray data = OJSresponse.getJSONArray("data");
                int status = Integer.parseInt(OJSresponse.get("status").toString());
                int totalRows = Integer.parseInt(OJSresponse.get("totalRows").toString());
                int startRow = Integer.parseInt(OJSresponse.get("startRow").toString());
                int endRow = Integer.parseInt(OJSresponse.get("endRow").toString());
            %>



            <h1 class="title">Lista de Productos </h1>

            
            <div class="info">
                <div  class="info-total">
                    <label class="info-total-title">Cantidad de Productos: </label>
                    <span class="info-total-valor"> <%= data.length()%></span>
                </div>
            </div>

            <%! int i = 0;%>
            <table border="1">
                <thead>
                    <tr>
                        <th class="text-align-center p-05">N°</th>
                        <th class="p-05">Descripción</th>
                        <th class="p-05">Identificador</th>
                        <th class="p-05">Unidades Disponibles</th>
                        <th class="p-05">Tienda</th>
                    </tr>
                </thead>
                <tbody>



                    <%
                    while (i < data.length()) {%>
                    <tr>


                        <%  JSONObject variableVolatil = new JSONObject(data.get(i).toString());%>


                        <td class="text-align-center px-06"><%= i + 1%></td>
                        <td class="px-06"><%= variableVolatil.get("_identifier")%></td>
                        <td class="px-06"><%= variableVolatil.get("product")%></td>
                        <td class="text-align-right px-06"><%= variableVolatil.get("quantityOnHand")%></td>
                        <td class="px-06"><%= variableVolatil.get("client$_identifier")%></td>

                        <% i++; %>
                    </tr>
                    <%}%>

                </tbody>
            </table>

        </div>
    </body>
</html>
