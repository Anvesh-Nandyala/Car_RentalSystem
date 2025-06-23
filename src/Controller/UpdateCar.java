package Controller;

import Model.Car;
import Model.Database;
import Model.Operation;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateCar implements Operation {
    @Override
    public void operation(Database database, Scanner s, User user){
        System.out.println("Enter car ID(int):(-1 to show all cars)");
        int ID=s.nextInt();
        while(ID==-1){
            new ViewCars().operation(database,s,user);
            System.out.println("Enter car ID(int):(-1 to show all cars)");
            ID=s.nextInt();
        }

        try{
            ResultSet rs1=database.getStatement().executeQuery("SELECT * FROM public.cars WHERE \"ID\" = " + ID);
            rs1.next();
            Car car=new Car();
            car.setID(rs1.getInt("ID"));
            car.setBrand(rs1.getString("brand"));
            car.setModel(rs1.getString("model"));
            car.setColor(rs1.getString("color"));
            car.setYear(rs1.getInt("year"));
            car.setPrice(rs1.getDouble("price"));
            car.setAvailable((rs1.getInt("available")));

            if(car.isAvailable()>1){
                System.out.println("Car doesn't exist!");
                return;
            }

            System.out.println("Enter Brand:(-1:"+car.getBrand()+")");
            String brand=s.next();
            if(brand.equals("-1")) brand=car.getBrand();

            System.out.println("Enter Model:(-1:"+car.getModel()+")");
            String model =s.next();
            if(model.equals("-1")) model= car.getModel();

            System.out.println("Enter Color:(-1:"+car.getColor()+")");
            String color=s.next();
            if(color.equals("-1")) color= car.getColor();

            System.out.println("Enter Year:(-1:"+car.getYear()+")");
            int year=s.nextInt();
            if(year==-1) year= car.getYear();

            System.out.println("Enter Price(double):(-1:"+car.getPrice()+")");
            double price=s.nextDouble();
            if(price==-1) car.getPrice();

            String updateSQL= "UPDATE public.cars SET \"brand\" = ?, \"model\" = ?, \"color\" = ?, \"year\" = ?, \"price\" = ? " +
                    "WHERE \"ID\" = ?";
            PreparedStatement pstmt = database.getConnection().prepareStatement(updateSQL);
            pstmt.setString(1, brand);
            pstmt.setString(2, model);
            pstmt.setString(3, color);
            pstmt.setInt(4, year);
            pstmt.setDouble(5, price);
            pstmt.setInt(6, ID); //

            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Car updated successfully.");
            } else {
                System.out.println("No car found with ID: " + ID);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }


    }
}
