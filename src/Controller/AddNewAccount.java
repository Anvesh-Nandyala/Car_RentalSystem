package Controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import Model.*;


public class AddNewAccount implements Operation {
    private int accType;
    public AddNewAccount(int accType){
        this.accType=accType;
    }


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
        while(!password.equals(confirmPassword)) {
            System.out.println("Password doesn't match");
            System.out.println("Enter Password");
            password = s.next();
            System.out.println("Confirm Password");
            confirmPassword = s.next();
        }
        String insertSQL = "INSERT INTO \"carRental\" (" +
                "\"FirstName\", \"LastName\", \"Email\", \"PhoneNumber\", \"Password\", \"Type\") " +
                "VALUES (?, ?, ?, ?, ?, ?);";

        try(PreparedStatement stmt = database.getConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)){
            ArrayList<String> emails =new ArrayList<>();
            ResultSet rs0=database.getStatement().executeQuery("SELECT \"Email\" FROM public.\"carRental\";");
            while(rs0.next()){
                emails.add(rs0.getString("Email"));
            }
            if(emails.contains(email)){
                System.out.println("This email is used before");
                return;
            }
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
                    System.out.println("Account created successfully with ID ==> " + generatedId);
                }
            } else {
                System.out.println("Failed to add Admin.");
            }
            if(accType==0){
                user=new Client();
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPhoneNumber(phoneNumber);
                user.setPassword(password);
                user.showList(database,s);
            }



        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}
