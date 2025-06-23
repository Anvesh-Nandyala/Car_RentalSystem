package Controller;

import Model.Database;
import Model.Operation;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AddNewCar implements Operation {

    @Override
    public void operation(Database database, Scanner s, User user){
         System.out.println("Enter brand :");
         String brand=s.next();
         System.out.println("Enter model :");
         String model=s.next();
         System.out.println("Enter color :");
         String color=s.next();
         System.out.println("Enter year(int) :");
         int year=s.nextInt();
         System.out.println("Enter price per hour(double) :");
         double price=s.nextDouble();
         int available=0;
        String insertSQL= "INSERT INTO public.cars(brand, model, color, year, price, available) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
         try(PreparedStatement pstmt = database.getConnection().prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS)){

             pstmt.setString(1, brand);
             pstmt.setString(2, model);
             pstmt.setString(3, color);
             pstmt.setInt(4, year);
             pstmt.setDouble(5, price);
             pstmt.setInt(6, available);

             int rows = pstmt.executeUpdate();

             if (rows > 0) {
                 ResultSet rs = pstmt.getGeneratedKeys();
                 if (rs.next()) {
                     int generatedId = rs.getInt(1);
                     System.out.println("Car added successfully with ID ==> " + generatedId);
                 }
             } else {
                 System.out.println("Failed to add Car.");
             }
         }catch (SQLException e){
             e.printStackTrace();
         }

    }
}
