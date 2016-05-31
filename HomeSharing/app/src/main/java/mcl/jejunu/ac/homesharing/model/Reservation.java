package mcl.jejunu.ac.homesharing.model;

import java.sql.Date;

/**
 * Created by neo-202 on 2016-05-31.
 */
public class Reservation {

    private int home_id;
    private int user_id;
    private Date checkIn;
    private Date checkOut;

    public int getHome_id() {
        return home_id;
    }

    public void setHome_id(int home_id) {
        this.home_id = home_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }
}
