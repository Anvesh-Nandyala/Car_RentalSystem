package Controller;

import Model.Database;
import Model.Operation;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ChangePassword implements Operation {
    @Override
    public void operation(Database database, Scanner s, User user) {
        System.out.println("Enter old password: ");
        String oldPassword=s.next();
        if(!oldPassword.equals(user.getPassword())){
            System.out.println("Password doesn't match");
            return;
        }

        System.out.println("Enter new password: ");
        String newPassword=s.next();
        System.out.println("Confirm password");
        String confirmPassword=s.next();
        while(!newPassword.equals(confirmPassword)){
            System.out.println("Password doesn't match");
            System.out.println("Enter new password: ");
            newPassword=s.next();
            System.out.println("Confirm password");
            confirmPassword=s.next();
        }

        String updatepassSQL = "UPDATE public.\"carRental\" SET \"Password\" = ? WHERE \"ID\" = ?";

        try (PreparedStatement pstmt = database.getConnection().prepareStatement(updatepassSQL)) {
            pstmt.setString(1, newPassword);
            pstmt.setInt(2, user.getID());

            int updated = pstmt.executeUpdate();

            if(updated > 0){
                System.out.println("Password changed successfully.");
                user.setPassword(newPassword);
            } else{
                System.out.println("No user found with ID: " + user.getID());
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

    }
}

