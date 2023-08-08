/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.MostrarListado;

import static Server.MostrarListado.DataJson._identifier;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.net.ssl.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;


public class Consulta {

    private static String StringUrl = "https://obpreprod.sidesoftcorp.com/happypreprod//org.openbravo.service.json.jsonrest/MaterialMgmtStorageDetail";

    public static HttpURLConnection Conexion(String NombreUsuario, String Contrasena) {
        try {
            DesactivarSSL();
            URL url = new URL(StringUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            String encoding = Base64.getEncoder().encodeToString((NombreUsuario + ":" + Contrasena).getBytes());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            connection.connect();
            return connection;
        } catch (Exception e) {
            return null;//System.out.println("Error: " + e.toString());
        }
    }
    public static List<DataJson> ConvertirJsonList(String NombreUsuario, String Contrasena) {
        JSONObject jsonObsject = new JSONObject(ConsultaGet(NombreUsuario, Contrasena).toString());
        JSONObject OJSresponse = jsonObsject.getJSONObject("response");
        JSONArray data = OJSresponse.getJSONArray("data");
         
        List<DataJson> listaAutos = new ArrayList<>();
        
        int i = 0;
        //DataJson DataJson = new DataJson();
        while (i < data.length()) {
            DataJson DataJsons = new DataJson();
            DataJsons._identifier = "www"+i;
            listaAutos.add(DataJsons);
             i++;
        }
        DataJson DataJson1 =  listaAutos.get(1);
        DataJson DataJson2 =  listaAutos.get(2);
        System.out.println(listaAutos.get(1));
        System.out.println(listaAutos.get(2));

        return listaAutos;
    }

    
    
    public static DataJson[] ConvertirJsonArray(String NombreUsuario, String Contrasena) {
        JSONObject jsonObsject = new JSONObject(ConsultaGet(NombreUsuario, Contrasena).toString());
        JSONObject OJSresponse = jsonObsject.getJSONObject("response");
        JSONArray data = OJSresponse.getJSONArray("data");
        
        DataJson ArrayData[] = new DataJson[data.length()];
        //List<DataJson> ArrayData = new ArrayList<DataJson>();
        int i = 0;
        while (i < data.length()) {

             //DataJson Volatil = new DataJson(data.get(i).toString());
            //ArrayData.add(new DataJson(data.get(i).toString()));
            System.out.println(data.get(i).toString());
            
            ArrayData[i]._identifier = "i"+i;
            //ArrayData[i] = new DataJson(data.get(i).toString());
            //System.out.println(ArrayData[i]);
            //System.out.println(ArrayData[i]._identifier);

            //System.out.println(Volatil._identifier);
            i++;
        }
        //ArrayData[2] = new DataJson(data.get(2).toString());
        
        System.out.println("1");
        System.out.println(ArrayData[1]._identifier);
        System.out.println("2");
        System.out.println(ArrayData[2]._identifier);
        System.out.println("3");
        System.out.println(ArrayData[3]._identifier );
        /*int j = 0;
        while (j < ArrayData.length) {

            //DataJson DataJson = new DataJson(data.get(i).toString());
            // ArrayListaProductos[i] = DataJson;
            System.out.println(j);
            //DataJson DataJson2 = ArrayListaProductos.get(i);
            System.out.println(ArrayData[j]._identifier);
            j++;
        }*/

        return ArrayData;
    }

    public static JSONObject EnviarListaProductos(String NombreUsuario, String Contrasena) {
        JSONObject jsonObsject = new JSONObject(ConsultaGet(NombreUsuario, Contrasena).toString());
        return jsonObsject;
    }

    public static void DesactivarSSL() {
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }}, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        } catch (Exception e) {
        }
    }

    public static StringBuilder ConsultaGet(String NombreUsuario, String Contrasena) {
        StringBuilder informacionString = new StringBuilder();
        try {
            //connection = Conexion(NombreUsuario, Contrasena);
            Scanner scanner = new Scanner(Conexion(NombreUsuario, Contrasena).getInputStream());
            while (scanner.hasNext()) {
                informacionString.append(scanner.nextLine());
            }
            scanner.close();
            return informacionString;
        } catch (Exception e) {
            return informacionString;
        }
    }
}
