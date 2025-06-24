package Controller;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowUserRents implements Operation {

    private int userID;
    public  ShowUserRents(int userID){
        this.userID=userID;
    }
    @Override
    public void operation(Database database, Scanner s, User user) {
        int carID;
        try{
            String selectSQL="SELECT * FROM public.rents WHERE \"user\" = userID;";
            ResultSet rs=database.getStatement().executeQuery(selectSQL);
            ArrayList<Rent> rents=new ArrayList<>();
            while(rs.next()){
                Rent rent=new Rent();
                rent.setID(rs.getInt("ID"));
                carID=rs.getInt("car");
                rent.setDataTime(rs.getString("dateTime"));
                rent.setHours(rs.getInt("hours"));
                rent.setTotal((int) rs.getDouble("total"));
                rent.setStatus(rs.getInt("status"));
                rents.add(rent);
            }
            String selectUser="SELECT * FROM public.carRental WHERE \"ID\" = userID;";
            ResultSet rs2=database.getStatement().executeQuery(selectUser);
            rs2.next();
            User u=new Client();
            u.setID(rs2.getInt("ID"));
            u.setFirstName(rs2.getString("FirstName"));
            u.setLastName(rs2.getString("LastName"));
            u.setEmail(rs2.getString("Email"));
            u.setPhoneNumber(rs2.getString("PhoneNumber"));
            u.setPassword((rs2.getString("Password")));





            for(Rent r:rents){

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
