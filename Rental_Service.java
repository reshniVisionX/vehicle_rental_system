package Vehicle_management;

import java.time.LocalDateTime;
import java.util.Date;

public class Rental_Service {
    private String user_id;
    private String numberPlate;
    private String date;
    private Boolean isRented;
    private Boolean isServiced;

    public Rental_Service(String user_id, String numberPlate, String date, Boolean isRented, Boolean isServiced) {
        this.user_id = user_id;
        this.numberPlate = numberPlate;
        this.date = date;
        this.isRented = isRented;
        this.isServiced = isServiced;
    }

    public String getUserId() {
        return user_id;
    }

    public void setUserId(String user_id) {
        this.user_id = user_id;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getIsRented() {
        return isRented;
    }

    public void setIsRented(Boolean isRented) {
        this.isRented = isRented;
    }

    public Boolean getIsServiced() {
        return isServiced;
    }

    public void setIsServiced(Boolean isServiced) {
        this.isServiced = isServiced;
    }
    
    @Override
    public String toString() {
        return "Rental Services {" +
                " email-id = '" + user_id + '\'' +
                ", numberPlate = '" + numberPlate + '\'' +
                ", Date = " + date +
                ", Is_Rented = " + isRented + '\''+
                ", Is_Serviced = "+ isServiced + 
                '}';
    }
}
