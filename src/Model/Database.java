package Model;

import java.sql.*;

public class Database extends login {
    private Connection connection;
    public Database(){
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e){
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
