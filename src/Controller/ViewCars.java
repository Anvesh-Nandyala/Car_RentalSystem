package Controller;

import Model.Car;
import Model.Database;
import Model.Operation;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ViewCars implements Operation {
    @Override
    public void operation(Database database , Scanner s, User user){

        System.out.println();
        String selectSQL="SELECT * FROM public.cars";
        ArrayList<Car> cars=new ArrayList<>();
        try{
            ResultSet rs=database.getStatement().executeQuery(selectSQL);
            while(rs.next()){
                Car car= new Car();
                car.setID(rs.getInt("ID"));
                car.setBrand(rs.getString("brand"));
                car.setModel(rs.getString("model"));
                car.setColor(rs.getString("color"));
                car.setYear(rs.getInt("year"));
                car.setPrice(rs.getDouble("price"));
                car.setAvailable((rs.getInt("available")));
                cars.add(car);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        for(Car c:cars){
            if(c.isAvailable()<2){

                System.out.println("ID: "+c.getId());
                System.out.println("Brand:\t "+c.getBrand());
                System.out.println("Model:\t "+c.getModel());
                System.out.println("Color:\t "+c.getColor());
                System.out.println("Year:\t "+c.getYear());
                System.out.println("Price: "+c.getPrice()+" Rs");
                if(c.isAvailable()==0){
                    System.out.println("Status:\tAvailable");

                }else {
                    System.out.println("Status:\tNot Available");
                }
                System.out.println("-----------------------");
            }
        }
        System.out.println();

    }
}
