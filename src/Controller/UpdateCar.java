package Controller;

import Model.Database;
import Model.Operation;
import Model.User;

import java.util.Scanner;

public class UpdateCar implements Operation {
    @Override
    public void operation(Database database, Scanner s, User user){
        System.out.println("Enter car ID(int):(-1 to show all cars)");
        String updateSQL= "UPDATE public.cars SET \"Brand\" = ?, \"Model\" = ?, \"Color\" = ?, \"Year\" = ?, \"Price\" = ?, \"Available\" = ? " +
                "WHERE \"ID\" = ?";
    }
}
