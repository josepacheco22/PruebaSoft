<%-- 
    Document   : ListaProductos
    Created on : Aug 6, 2023, 5:19:53 PM
    Author     : japac



--%>

<%@page import="java.util.List"%>
<%@page import="Server.MostrarListado.DataJson"%>
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
                List<DataJson> ArrayListaProductos = Consulta.ConvertirJsonList(NombreUsuario, Contrasena);
                //DataJson[] ArrayListaProductos = Consulta.ConvertirJsonArray(NombreUsuario, Contrasena);
                /*int i = 0;
                while (i < ArrayListaProductos.length) {

                    //DataJson DataJson = new DataJson(data.get(i).toString());
                   // ArrayListaProductos[i] = DataJson;
                    
                    System.out.println(i);
                    //DataJson DataJson2 = ArrayListaProductos.get(i);
                    System.out.println(ArrayListaProductos[i]._identifier);
                    i++;
                }*/

            %>



            <h1 class="title">Lista de Productos </h1>



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

                </tbody>
            </table>

        </div>
    </body>
</html>
