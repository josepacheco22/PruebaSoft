/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.MostrarListado;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

/**
 *
 * @author japac
 */
public class Listas {
    
    public static int CantidadRegistros;
    public static String[] ListaProductos;

    public Listas(String[] Lista) {
        ListaProductos = Lista;
        CantidadRegistros = ListaProductos.length;
    }

    public int CantidadTotalRegistros() {
        return CantidadRegistros;
    }

    public static String[] CantidadAgrupadosProducto() {
        List<String> ProductoTotal = new ArrayList();
        List<String> ProductoIdentificadorTotal = new ArrayList();
        int i = 0;
        while (i < ListaProductos.length) {
            JSONObject ListaData = new JSONObject(ListaProductos[i]);
            ProductoTotal.add(ListaData.get("product").toString());
            ProductoIdentificadorTotal.add(ListaData.get("product$_identifier").toString());
            i++;
        }
        List<String> Producto = new ArrayList();
        List<String> ProductoIdentificador = new ArrayList();
        List<String> CantidadProductos = new ArrayList();
        Producto = ProductoTotal.stream().distinct().collect(Collectors.toList());
        i = 0;
        int j = 0;
        int k = 0;
        int Contador = 0;
        String StringProductoIdentificador = "";
        while (i < Producto.size()) {
            j = 0;
            Contador = 0;
            StringProductoIdentificador = "";
            while (j < ProductoTotal.size()) {
                if (Producto.get(i).equals(ProductoTotal.get(j))) {
                    Contador++;
                    StringProductoIdentificador = ProductoIdentificadorTotal.get(j);
                }
                j++;
            }
            ProductoIdentificador.add(StringProductoIdentificador);
            CantidadProductos.add(Contador + "");
            i++;
        }
        String[] CantidadProducto = new String[Producto.size()];
        i = 0;
        while (i < Producto.size()) {
            JSONObject ObjetoJSON = new JSONObject();
            ObjetoJSON.put("Producto", Producto.get(i));
            ObjetoJSON.put("ProductoIdentificador", ProductoIdentificador.get(i));
            ObjetoJSON.put("CantidadProductos", Integer.parseInt(CantidadProductos.get(i)));
            CantidadProducto[i] = ObjetoJSON.toString();
            i++;
        }
        return CantidadProducto;
    }

    public static String[] ListaTop10Productos() {
        int[] UnidadesDisponiblesTotal = new int[ListaProductos.length];
        int i = 0;
        while (i < ListaProductos.length) {
            JSONObject ListaData = new JSONObject(ListaProductos[i]);
            UnidadesDisponiblesTotal[i] = Integer.parseInt(ListaData.get("quantityOnHand").toString());
            i++;
        }
        Arrays.sort(UnidadesDisponiblesTotal);
        List<String> ListaMayorAMenorTotal = new ArrayList();
        i = UnidadesDisponiblesTotal.length;
        while (i > 0) {
            ListaMayorAMenorTotal.add(UnidadesDisponiblesTotal[i - 1] + "");
            i--;
        }
        List<String> ListaMayorAMenor = new ArrayList();
        ListaMayorAMenor = ListaMayorAMenorTotal.stream().distinct().collect(Collectors.toList());
        String[] Top10Producto = new String[10];
        i = 0;
        int j = 0;
        int k = 0;
        while (i < ListaMayorAMenor.size()) {
            j = 0;
            while (j < ListaProductos.length) {
                JSONObject ListaData = new JSONObject(ListaProductos[j]);
                if (Integer.parseInt(ListaMayorAMenor.get(i)) == Integer.parseInt(ListaData.get("quantityOnHand").toString())) {
                    Top10Producto[k] = ListaProductos[j].toString();
                    k++;
                }
                if (k == 10) {
                    break;
                }
                j++;
            }
            if (k == 10) {
                break;
            }
            i++;
        }
        return Top10Producto;
    }

    public static String[] ListaDiferenteUNIDAD() {
        List<String> CantidadProductos = new ArrayList();
        int i = 0;
        while (i < ListaProductos.length) {
            JSONObject ListaData = new JSONObject(ListaProductos[i]);
            if (!"UNIDAD".equals(ListaData.get("uOM$_identifier").toString())) {
                CantidadProductos.add(ListaProductos[i]);
            }
            i++;
        }
        String[] ListaDiferenteUNIDAD = new String[CantidadProductos.size()];
        i = 0;
        while (i < CantidadProductos.size()) {
            ListaDiferenteUNIDAD[i] = CantidadProductos.get(i);
            i++;
        }
        return ListaDiferenteUNIDAD;
    }
}
