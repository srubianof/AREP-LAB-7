package edu.eci.arep.securesparkapp;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;
import javax.net.ssl.*;

public class HttpsClient {

    public static void start() {
        File trustStoreFile = new File("keystores/myTrustStore");
        char[] trustStorePassword = "Hola123".toCharArray();
        // Load the trust store, the default type is "pkcs12", the alternative is "jks"
        KeyStore trustStore = null;
        TrustManagerFactory tmf = null;
        SSLContext sslContext = null;
        try {
            trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(new FileInputStream(trustStoreFile), trustStorePassword);
            // Get the singleton instance of the TrustManagerFactory
            tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            // Itit the TrustManagerFactory using the truststore object
            tmf.init(trustStore);
            //Set the default global SSLContext so all the connections will use it
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, tmf.getTrustManagers(), null);
            SSLContext.setDefault(sslContext);
        } catch (IOException | NoSuchAlgorithmException | CertificateException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public static String readURL(String sitetoread) throws IOException {

        // Crea el objeto que representa una URL2
        URL siteURL = new URL("https");
        // Crea el objeto que URLConnection
        HttpURLConnection urlConnection = (HttpURLConnection) siteURL.openConnection();
        // Obtiene una vista del mapa como conjunto de pares <K,V>
        // para poder navegarlo
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();

        }
    }

}
