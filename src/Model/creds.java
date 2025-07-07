package Model;

import io.github.cdimascio.dotenv.Dotenv;

public class creds {
    private static final Dotenv dotenv = Dotenv.load();
    protected static final String DB_USERNAME = dotenv.get("DB_USERNAME");
    protected static final String DB_PASSWORD = dotenv.get("DB_PASSWORD");
    protected static final String DB_URL = dotenv.get("DB_URL");
}

