package Model;

import java.sql.*;

public class Database {
    private Statement statement;
    private Connection connection;

    public Database() {
        try {
            connection = DriverManager.getConnection(creds.DB_URL, creds.DB_USERNAME, creds.DB_PASSWORD);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }

    public Connection getConnection() {
        return connection;
    }
}
