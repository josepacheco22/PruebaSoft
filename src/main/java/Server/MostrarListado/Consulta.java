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

public class Consulta {

    private static String StringUrl = "https://obpreprod.sidesoftcorp.com/happypreprod//org.openbravo.service.json.jsonrest/MaterialMgmtStorageDetail";
    private static HttpURLConnection connection;

    public Consulta(String NombreUsuario, String Contrasena) {
        try {
            DesactivarSSL();
            URL url = new URL(StringUrl);
            String encoding = Base64.getEncoder().encodeToString((NombreUsuario + ":" + Contrasena).getBytes());
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", "Basic " + encoding);
            connection.connect();
        } catch (Exception e) {
            System.out.println("Error: " + e.toString());
        }
    }
    
    public static JSONObject EnviarListaProductos() {
        JSONObject jsonObsject = new JSONObject(ConsultaGet().toString());
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

    public static StringBuilder ConsultaGet() {
        StringBuilder informacionString = new StringBuilder();
        try {
            DesactivarSSL();
            Scanner scanner = new Scanner(connection.getInputStream());
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
