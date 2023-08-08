<%-- 
    Document   : ListaProductos
    Created on : Aug 6, 2023, 5:19:53 PM
    Author     : japac



--%>
<%@page import="Server.MostrarListado.Listas"%>
<%@page import="org.json.JSONObject"%>
<%@page import="Server.MostrarListado.Consulta"%>
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
                Listas ListasJson = new Listas(ArrayListaProductos);
                String[] ArrayListaIdentificador = ListasJson.CantidadAgrupadosProducto();
                String[] ArrayListaTop10 = ListasJson.ListaTop10Productos();
                String[] ArrayListaDiferenteUNIDAD = ListasJson.ListaDiferenteUNIDAD();
                int i = 0;
            %>
            <h1 class="title">Lista de Productos </h1>
            <div class="info">
                <div  class="info-total">
                    <label class="info-total-title">Cantidad total de registros: </label>
                    <span class="info-total-valor"> <%=ListasJson.CantidadRegistros%></span>
                </div>
            </div>
            <div class="info">
                <div  class="info-total">
                    <label class="info-total-title">Cantidad total de productos diferentes en el stock (agrupar por la propiedad "product").</label>
                </div>
            </div>
            <table border="1" class="table td table-cantidad">
                <thead>
                    <tr>
                        <th class="th text-align-center p-05">Cantidad</th>
                        <th class="th p-05">Identificador</th>
                        <th class="th p-05">Producto</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        i = 0;
                        while (i < ArrayListaIdentificador.length) {%>
                    <tr>
                        <%  JSONObject ArrayListaIdentificadorJSON = new JSONObject(ArrayListaIdentificador[i]);%>
                        <td class="text-align-center p-06"><%= ArrayListaIdentificadorJSON.get("CantidadProductos")%></td>
                        <td class="p-06"><%= ArrayListaIdentificadorJSON.get("ProductoIdentificador")%></td>
                        <td class="p-06"><%= ArrayListaIdentificadorJSON.get("Producto")%></td>
                        <% i++; %>
                    </tr>
                    <%}
                    %>
                </tbody>
            </table>
            <div class="info">
                <div  class="info-total">
                    <label class="info-total-title">Lista de productos cuya unidad de medida (uOM$_identifier) es diferente a "UNIDAD" (se diferencia entre mayúsculas y minúsculas).</label>
                </div>
            </div>
                  <table border="1" class="table td table-cantidad">
                <thead>
                    <tr>
                        <th class="th p-05">Identificador</th>
                        <th class="th p-05">Producto</th>
                        <th class="th p-05">Unidad de Medida</th>
                        <th class="th p-05">Unidades Disponibles</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        i = 0;
                        while (i < ArrayListaDiferenteUNIDAD.length) {%>
                    <tr>
                        <%  JSONObject ListaDiferenteUNIDADJSON = new JSONObject(ArrayListaDiferenteUNIDAD[i]);%>
                        <td class="p-06"><%= ListaDiferenteUNIDADJSON.get("_identifier")%></td>
                        <td class="p-06"><%= ListaDiferenteUNIDADJSON.get("product")%></td>
                        <td class="p-06"><%= ListaDiferenteUNIDADJSON.get("uOM$_identifier")%></td>
                        <td class="text-align-right p-06"><%= ListaDiferenteUNIDADJSON.get("quantityOnHand")%></td>
                        <% i++; %>
                    </tr>
                    <%}
                    %>
                </tbody>
            </table>
            <div class="info">
                <div  class="info-total">
                    <label class="info-total-title">Lista de los 10 productos con mayor stock ordenados de mayor a menor.</label>
                </div>
            </div>
            <table border="1" class="table td table-cantidad">
                <thead>
                    <tr>
                        <th class="th text-align-center p-05">N°</th>
                        <th class="th text-align-center p-05">Identificador</th>
                        <th class="th p-05">Producto</th>
                        <th class="th p-05">Unidades Disponibles</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        i = 0;
                        while (i < ArrayListaTop10.length) {%>
                    <tr>
                        <%  JSONObject ArrayListaTop10JSON = new JSONObject(ArrayListaTop10[i]);%>
                        <td class="text-align-center p-06"><%= i + 1%></td>
                        <td class="p-06"><%= ArrayListaTop10JSON.get("_identifier")%></td>
                        <td class="p-06"><%= ArrayListaTop10JSON.get("product")%></td>
                        <td class="p-06"><%= ArrayListaTop10JSON.get("quantityOnHand")%></td>
                        <% i++; %>
                    </tr>
                    <%}
                    %>
                </tbody>
            </table>
        </div>
    </body>
</html>
