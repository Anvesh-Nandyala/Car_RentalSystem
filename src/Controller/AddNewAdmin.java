package Controller;

import java.sql.*;
import java.util.Scanner;
import Model.Database;
import Model.Operation;
import Model.User;


public class AddNewAdmin implements Operation {

    @Override
    public void operation(Database database,Scanner s, User user){
        System.out.println("Enter First Name");
        String firstName=s.next();
        System.out.println("Enter Last Name");
        String lastName=s.next();
        System.out.println("Enter Email");
        String email=s.next();
        System.out.println("Enter Phone Number");
        String phoneNumber=s.next();
        System.out.println("Enter Password");
        String password=s.next();
        System.out.println("Confirm Password");
        String confirmPassword=s.next();
        while(!password.equals(confirmPassword)){
            System.out.println("Password doesn't match");
            System.out.println("Enter Password");
            password=s.next();
            System.out.println("Confirm Password");
            confirmPassword=s.next();
        }
        int accType=1;
        String insertSQL = "INSERT INTO public.\"carRental\"(" +
                "\"FirstName\", \"LastName\", \"Email\", \"PhoneNumber\", \"Password\", \"Type\") " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try(PreparedStatement stmt = database.getConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)){
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, email);
            stmt.setString(4, phoneNumber);
            stmt.setString(5, password);
            stmt.setInt(6, accType);

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int generatedId = rs.getInt(1);
                    System.out.println("Admin added successfully with ID ==> " + generatedId);
                }
            } else {
                System.out.println("Failed to add Admin.");
            }


        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
