package edu.eci.arep;

/**
 * Hello world!
 *
 */

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        port(getPort());
        secure("keystores/ecikeystore.p12", "Hola123", "keystores/myTrustStoreLogin", "Hola123");
        get("/prueba", (req, res) -> "<p>Funciona correctamente</p>");
    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 9002;
    }

}
