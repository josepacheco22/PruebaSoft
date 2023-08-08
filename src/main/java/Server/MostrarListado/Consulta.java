/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Server.MostrarListado;

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
            return null;
        }
    }
    
    public static String[] ConvertirJsonArray(String NombreUsuario, String Contrasena) {
        JSONObject jsonObsject = new JSONObject(ConsultaGet(NombreUsuario, Contrasena).toString());
        JSONObject OJSresponse = jsonObsject.getJSONObject("response");
        JSONArray data = OJSresponse.getJSONArray("data");
        String ArrayArray[] = new String[data.length()];
        int i = 0;
        while (i < data.length()) {
            ArrayArray[i] = data.get(i).toString();
            i++;
        }
        return ArrayArray;
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
