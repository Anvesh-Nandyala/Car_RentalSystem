package Model;

import Controller.*;

import java.util.Scanner;

public class Admin extends User {
    private Operation[] operations=new Operation[]{
            new AddNewCar(),
            new ViewCars(),
            new UpdateCar(),
            new DeleteCar(),
            new AddNewAccount(1),
            new ShowAllrents()
    };
    public Admin(){
        super();
    }

    @Override
    public void showList(Database database, Scanner s) {
        System.out.println("\n01. Add New Car");
        System.out.println("02. View Cars");
        System.out.println("03. Update Car");
        System.out.println("04. Delete Car");
        System.out.println("05. Add New Admin");
        System.out.println("06. Show Rents");
        System.out.println("07. Show User's Rents");
        System.out.println("08. Edit my Data");
        System.out.println("09. Change Password");
        System.out.println("10. Quit\n");
         int i=s.nextInt();
         operations[i-1].operation(database, s, this);
         showList(database,s);
    }
}
