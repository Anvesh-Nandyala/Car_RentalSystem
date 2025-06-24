package Controller;

import Model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class ShowAllrents implements Operation {

    @Override
    public void operation(Database database, Scanner s, User user) {

        ArrayList<Rent> rents=new ArrayList<>();
        ArrayList<Integer> carIDs=new ArrayList<>();
        ArrayList<Integer> userIDs=new ArrayList<>();

        try{
            String selectSQL="SELECT * FROM public.rents";
            ResultSet rs=database.getStatement().executeQuery(selectSQL);

            while(rs.next()){
                Rent rent=new Rent();
                rent.setID(rs.getInt("ID"));
                userIDs.add(rs.getInt("user"));
                carIDs.add(rs.getInt("car"));
                rent.setDataTime(rs.getString("dateTime"));
                rent.setHours(rs.getInt("hours"));
                rent.setTotal((int) rs.getDouble("total"));
                rent.setStatus(rs.getInt("status"));
                rents.add(rent);
            }


            for(int j=0;j<rents.size();j++){
                Rent r=rents.get(j);


                String selectUser = "SELECT * FROM public.\"carRental\" WHERE \"ID\" = " + userIDs.get(j);
                ResultSet rs2=database.getStatement().executeQuery(selectUser);

                if (rs2.next()) {
                    User u = new Client();
                    u.setID(rs2.getInt("ID"));
                    u.setFirstName(rs2.getString("FirstName"));
                    u.setLastName(rs2.getString("LastName"));
                    u.setEmail(rs2.getString("Email"));
                    u.setPhoneNumber(rs2.getString("PhoneNumber"));
                    u.setPassword(rs2.getString("Password"));
                    r.setUser(u);
                } else {
                    System.out.println("User not found for ID: " + userIDs.get(j));
                    continue; // skip this rent
                }


                ResultSet rs3=database.getStatement()
                        .executeQuery("SELECT * FROM public.cars WHERE \"ID\" ="+ carIDs.get(j));

                if (rs3.next()) {
                    Car car = new Car();
                    car.setID(rs3.getInt("ID"));
                    car.setBrand(rs3.getString("brand"));
                    car.setModel(rs3.getString("model"));
                    car.setYear(rs3.getInt("year"));
                    car.setPrice(rs3.getDouble("price"));
                    car.setColor(rs3.getString("color"));
                    car.setAvailable(rs3.getInt("available"));
                    r.setCar(car);
                } else {
                    System.out.println("Car not found for ID: " + carIDs.get(j));
                    continue; // skip this rent
                }


                System.out.println("ID:\t"+r.getID());
                System.out.println("Name:\t"+r.getUser().getFirstName()+" "+ r.getUser().getLastName());
                System.out.println("Email:\t"+r.getUser().getEmail());
                System.out.println("Phone Number:\t"+r.getUser().getPhoneNumber());
                System.out.println("Car ID:\t"+r.getCar().getId());
                System.out.println("Car:\t"+r.getCar().getBrand()+" "+r.getCar().getModel()+" "+r.getCar().getColor());
                System.out.println("Date Time:\t"+r.getDateTime());
                System.out.println("Hours:\t"+r.getHours());
                System.out.println("Total:\t"+r.getTotal());
                System.out.println("Status:\t"+r.getStatusToString());
                System.out.println("-------------------\n");



            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
