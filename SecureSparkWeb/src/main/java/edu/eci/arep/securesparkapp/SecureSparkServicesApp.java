package edu.eci.arep.securesparkapp;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import edu.eci.arep.model.AuthenticationDetails;
import spark.staticfiles.StaticFilesConfiguration;

import java.security.MessageDigest;

import static spark.Spark.*;

public class SecureSparkServicesApp {


    public static void main(String[] args) {
        Gson gson = new Gson();
        port(getPort());
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath,truststorePassword);
        secure("keystores/ecikeystore.p12", "Hola123", null, null);

        before("/auth/*", (req, res) -> {
            if (req.session().isNew()) {
                req.session().attribute("Logged", false);
            }
            boolean auth = req.session().attribute("Logged");
            if (!auth) {
                halt(401, "<h1>401 Unauthorized</h1>");
            }
        });
        before("/login.html", ((req, res) -> {
            req.session(true);
            if (req.session().isNew()) {
                req.session().attribute("Logged", false);
            }
            boolean auth = req.session().attribute("Logged");
            if (auth) {
                res.redirect("/auth/redirect.html");
            }
        }));

        StaticFilesConfiguration staticHandler = new StaticFilesConfiguration();
        staticHandler.configure("/static");
        before((request, response) ->
                staticHandler.consume(request.raw(), response.raw()));
        get("/", (req, res) -> {
            res.redirect("/login.html");
            res.status(200);
            return null;
        });
        get("/prueba", ((request, response) -> {
            return HttpsClient.getInfo();
        }));
        post("/login", ((req, res) -> {
            req.session(true);
            AuthenticationDetails authenticationDetails = gson.fromJson(req.body(), AuthenticationDetails.class);
            if (sha256(authenticationDetails.getPassword()).equals(sha256("admin"))) {
                req.session().attribute("User", authenticationDetails.getUsername());
                req.session().attribute("Logged", true);
                return "si";

            } else {
                return "Error : Usuario o contraseña incorrecta";
            }
        }));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 9001;
    }

    public static String sha256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
