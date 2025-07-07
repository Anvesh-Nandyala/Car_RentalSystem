package Model;

import io.github.cdimascio.dotenv.Dotenv;

public class creds {
    private static final Dotenv dotenv = Dotenv.load(); // No filename()

    private static final String ENV = dotenv.get("ENV") != null ? dotenv.get("ENV") : "local";


    // Local
    private static final String LOCAL_HOST = "localhost";
    private static final String LOCAL_PORT = "1234";

    // Docker
    private static final String DOCKER_HOST = "postgres-db";
    private static final String DOCKER_PORT = "1234";

    // Common
    private static final String DB_NAME = "CarRental";
    public static final String DB_USERNAME = dotenv.get("DB_USERNAME");
    public static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");

    private static final String HOST = ENV.equals("docker") ? DOCKER_HOST : LOCAL_HOST;
    private static final String PORT = ENV.equals("docker") ? DOCKER_PORT : LOCAL_PORT;

    public static final String DB_URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB_NAME;
}
