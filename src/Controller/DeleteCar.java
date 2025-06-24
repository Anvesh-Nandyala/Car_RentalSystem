package Controller;

import Model.Database;
import Model.Operation;
import Model.User;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Scanner;

public class DeleteCar implements Operation {

    @Override
    public void operation(Database database ,Scanner s, User user){
        System.out.println("Enter ID(int): (-1 to show all cars)");
        int ID=s.nextInt();
        while(ID==-1){
            new ViewCars().operation(database,s,user);
            System.out.println("Enter ID (int): (-1 to show all cars)");
            ID=s.nextInt();
        }
        try{
            String updateSQL="UPDATE public.cars SET \"available\" = 2 WHERE \"ID\" = " + ID;
            database.getStatement().execute(updateSQL);
            System.out.println("Car deleted successfully");
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

}
