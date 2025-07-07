package Model;

public class creds {
    // Set to "docker" when running inside Docker
    private static final String ENV = "local";

    // Local
    private static final String LOCAL_HOST = "localhost";
    private static final String LOCAL_PORT = "1234";

    // Docker
    private static final String DOCKER_HOST = "postgres-db";
    private static final String DOCKER_PORT = "1234";

    // Common
    private static final String DB_NAME = "CarRental";
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "Anvesh@23";

    private static final String HOST = ENV.equals("docker") ? DOCKER_HOST : LOCAL_HOST;
    private static final String PORT = ENV.equals("docker") ? DOCKER_PORT : LOCAL_PORT;

    public static final String DB_URL = "jdbc:postgresql://" + HOST + ":" + PORT + "/" + DB_NAME;
}
