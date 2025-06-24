package Controller;

import Model.Database;
import Model.Operation;
import Model.Rent;
import Model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ReturnCar implements Operation {
    @Override
    public void operation(Database database , Scanner s, User user){
        System.out.println("Enter Rent ID (int):(-1 to show all rents)");
        int ID=s.nextInt();

        while(ID==-1){
            new ShowUserRents(user.getID()).operation(database, s, user);
            System.out.println("Enter Rent ID (int):(-1 to show all rents)");
            ID=s.nextInt();
        }
        try{
            String selectSQL = "SELECT * FROM \"rents\" WHERE \"ID\" = " + ID + ";";
            ResultSet rs=database.getStatement().executeQuery(selectSQL);
            rs.next();
            Rent r=new Rent();
            r.setID(rs.getInt("ID"));
            r.setUser(user);

            r.setDataTime(rs.getString("dateTime"));
            r.setHours(rs.getInt("hours"));
            r.setTotal((int) rs.getDouble("total"));
            r.setStatus(rs.getInt("status"));

            if(r.getStatusToString().equals("Delayed")){
                System.out.println(r.getDelayedHours()+" delayed hours");
                System.out.println("You will have to pay extra for those hours and fine");
            }

            String sql = "UPDATE public.rents SET \"status\" = ? WHERE \"ID\" = ?";
            PreparedStatement pstmt = database.getConnection().prepareStatement(sql);
            pstmt.setInt(1, 1);
            pstmt.setInt(2, ID);
            int updatedRows = pstmt.executeUpdate();
            System.out.println("Car returned successfully");

        }catch(SQLException e){
            e.printStackTrace();
        }





    }
}
