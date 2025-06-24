package Controller;

import Model.Database;
import Model.Operation;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class EditUserData implements Operation {
    @Override
    public void operation(Database database, Scanner s, User user) {
        System.out.println("Enter First Name: (-1 to keep "+ user.getFirstName()+")");
        String firstName =s.next();
        if(firstName.equals("-1")) firstName=user.getFirstName();

        System.out.println("Enter Last Name :(-1 to keep "+user.getLastName()+")");
        String lastName=s.next();
        if(lastName.equals("-1")) lastName= user.getLastName();

        System.out.println("Enter Email :(-1 to keep "+user.getEmail()+")");
        String email=s.next();
        if(email.equals("-1")) email= user.getEmail();

        System.out.println("Enter Phone Number :(-1 to keep "+user.getPhoneNumber()+")");
        String phoneNumber=s.next();
        if(phoneNumber.equals("-1")) phoneNumber=user.getPhoneNumber();

        String sql = "UPDATE public.\"carRental\" SET \"FirstName\" = ?, \"LastName\" = ?, \"Email\" = ?, \"PhoneNumber\" = ? WHERE \"ID\" = ?";


        try(PreparedStatement pstmt = database.getConnection().prepareStatement(sql);){

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, email);
            pstmt.setString(4, phoneNumber);
            pstmt.setInt(5, user.getID());

            int updated = pstmt.executeUpdate();

            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);

            if (updated > 0) {
                System.out.println("User data updated successfully for ID: " + user.getID());
            } else {
                System.out.println("No user found with ID: " + user.getID());
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
