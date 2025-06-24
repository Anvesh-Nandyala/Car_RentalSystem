package Controller;

import Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class RentCar implements Operation {
    @Override
    public void operation(Database database, Scanner s, User user) {

        System.out.println("Enter Car ID(int) :(-1 to show all cars)");
        int carID=s.nextInt();
        while(carID==-1){
            new ViewCars().operation(database,s,user);
            System.out.println("Enter Car ID(int) :(-1 to show all cars)");
            carID=s.nextInt();
        }
        System.out.println("Enter hours (int):");
        int hours =s.nextInt();

        try{
            ResultSet rs1=database.getStatement().executeQuery("SELECT * FROM public.cars WHERE \"ID\" = " + carID);
            rs1.next();
            Car car=new Car();
            car.setID(rs1.getInt("ID"));
            car.setBrand(rs1.getString("brand"));
            car.setModel(rs1.getString("model"));
            car.setColor(rs1.getString("color"));
            car.setYear(rs1.getInt("year"));
            car.setPrice(rs1.getDouble("price"));
            car.setAvailable((rs1.getInt("available")));

            if(car.isAvailable()!=0){
                System.out.println("Car isn't available");
                return;
            }

            double total = car.getPrice()*hours;
            Rent rent= new Rent();
            String updateSQL = "UPDATE public.cars SET \"available\" = 1 WHERE \"ID\" = ?";
            PreparedStatement updatePstmt = database.getConnection().prepareStatement(updateSQL);
            updatePstmt.setInt(1, carID);
            updatePstmt.executeUpdate();

            String insertSQL = "INSERT INTO public.rents(" +
                    "\"user\", car, \"dateTime\", hours, total, status) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = database.getConnection().prepareStatement(insertSQL);
            pstmt.setInt(1, user.getID());
            pstmt.setInt(2, car.getId());
            pstmt.setString(3, rent.getDateTime());
            pstmt.setInt(4, hours);
            pstmt.setDouble(5, total);
            pstmt.setInt(6, 0);
            pstmt.executeUpdate();
            System.out.println("Car rented successfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



    }
}
