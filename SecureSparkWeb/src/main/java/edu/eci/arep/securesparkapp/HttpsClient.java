package edu.eci.arep.securesparkapp;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.*;
import javax.net.ssl.*;

public class HttpsClient {

    public static void start() {
        File trustStoreFile = new File("keystores/myTrustStoreLogic");
        char[] trustStorePassword = "pansito".toCharArray();
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
    public static String getInfo() throws IOException {
        URL url = new URL("https://ec2-54-160-248-141.compute-1.amazonaws.com:9002/prueba");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setDoOutput(true);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();

        }
    }

}
