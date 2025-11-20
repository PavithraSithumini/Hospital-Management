/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;
import java.sql.Date;
/**
 *
 * @author icbt1
 */
public class Booking {
    private int bookingID;
    private int patientID;
    private int consultantID;
    private Date bookingDate;
    private String time;
    private int room;
    private String paid;

    public Booking() {
    }

    public Booking(int bookingID, int patientID, int consultantID, Date bookingDate, String time, int room, String paid) {
        this.bookingID = bookingID;
        this.patientID = patientID;
        this.consultantID = consultantID;
        this.bookingDate = bookingDate;
        this.time = time;
        this.room = room;
        this.paid = paid;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public int getConsultantID() {
        return consultantID;
    }

    public void setConsultantID(int consultantID) {
        this.consultantID = consultantID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }
     
}
