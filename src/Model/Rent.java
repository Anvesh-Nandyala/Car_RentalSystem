package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Rent {

    private int ID;
    private User user;
    private Car car;
    private LocalDateTime dataTime;
    private int hours;
    private double total;
    private String status;
    private DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-dd-MM  hh:mm");

    public Rent(){
        dataTime=LocalDateTime.now();
    }
    public int getID(){
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }
    public void setUser(User user){
        this.user=user;
    }

    public Car getCar(){
        return car;
    }

    public void setCar(Car car){
        this.car=car;
    }
    public String getDateTime(){
        return formatter.format(dataTime);
    }
    public void setDataTime(String dateTimeString){
        this.dateTime=LocalDateTime.parse(dateTimeString.formatter);
    }

    public int getHours(){
        return hours;
    }

    public void setHours(int hours){
        this.hours=hours;
    }
    public  double getTotal(){
        return total;
    }

    public void setTotal(int total){
        this.total=total;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status=status;
    }
}
