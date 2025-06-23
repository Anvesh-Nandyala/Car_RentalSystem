package Controller;

import Model.Admin;
import Model.Client;
import Model.Database;
import Model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        Scanner s = new Scanner(System.in);

        System.out.println("Welcome to Car Rental System");
        System.out.println("Enter your Email :\n(-1) to create new account");
        String email = s.next();
        System.out.println("Enter password :");
        String password = s.next();

        ArrayList<User> users = new ArrayList<>();

        try {
            Statement stmt = database.getStatement();
            if (stmt == null) {
                System.out.println("Database connection failed. Exiting.");
                return;
            }

            String select = "SELECT * FROM \"carRental\";";
            ResultSet rs = stmt.executeQuery(select);
            while (rs.next()) {
                User user = null;
                int ID = rs.getInt("ID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String em = rs.getString("Email");
                String phoneNumber = rs.getString("PhoneNumber");
                String pass = rs.getString("Password");
                int type = rs.getInt("Type");

                switch (type) {
                    case 0 -> user = new Client();
                    case 1 -> user = new Admin();
                    case 2 -> user = new Client();
                }

                if (user != null) {
                    user.setID(ID);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setEmail(em);
                    user.setPhoneNumber(phoneNumber);
                    user.setPassword(pass);
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        boolean found = false;
        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
                System.out.println("Welcome " + u.getFirstName() + "!");
                u.showList(database, s);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Invalid email or password.");
        }

        s.close(); // Best practice
    }
}
