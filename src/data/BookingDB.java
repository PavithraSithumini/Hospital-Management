/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import business.Booking;
import java.util.ArrayList;
import java.sql.*;
import java.sql.Date;
/**
 *
 * @author acer
 */
public class BookingDB {
    private ArrayList<Booking> bookingList;
    private Connection cn;
    
    public BookingDB() {
        try{            
            String url="jdbc:mysql://localhost:3306/nursingdb";
            String user="root";
            String password="";
            cn=DriverManager.getConnection(url, user, password);
            if(cn!=null){
                System.out.println("Database connected sucessfully");
            }else{
                 System.out.println("Database connection failed");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    public boolean add(Booking b){
        String insert="insert into Booking(bookingID, patientID, consultantID, bookingDate, time, room, paidAmount) values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps=cn.prepareStatement(insert);
            ps.setInt(1, b.getBookingID());
            ps.setInt(2, b.getPatientID());
            ps.setInt(3, b.getConsultantID());
            ps.setDate(4, b.getBookingDate());
            ps.setString(5, b.getTime());
            ps.setInt(6, b.getRoom());
            ps.setString(7, b.getPaidAmount());
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        
        
    }
    public ArrayList<Booking> getAll(){
        ArrayList<Booking> bList=new ArrayList<>();
        String select="select * from Booking";
        try{
            PreparedStatement ps=cn.prepareStatement(select);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
               int bookingID=rs.getInt("bookingID");
               int patientID=rs.getInt("PatientID");
               int consultantID=rs.getInt("consultantID");
               Date bookingDate=rs.getDate("bookingDate");
               String time=rs.getString("time");
               int room=rs.getInt("room");
               String paidAmount=rs.getString("paidAmount");
               
               Booking b=new Booking(bookingID, patientID, consultantID, bookingDate, time, room, paidAmount);
               bList.add(b);
            }
            return bList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public boolean delete(int bookingID){
        String delete="delete from Booking where bookingID=?";
        try{
            PreparedStatement ps=cn.prepareStatement(delete);
            ps.setInt(1, bookingID);
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
      
    }
    
    public Booking get(int bookingID){
       Booking b=null;
       String select="select * from Booking where BookingID=?";
       try{
           PreparedStatement ps=cn.prepareStatement(select);
           ps.setInt(1, bookingID);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
               int patientID=rs.getInt("PatientID");
               int consultantID=rs.getInt("consultantID");
               Date bookingDate=rs.getDate("bookingDate");
               String time=rs.getString("time");
               int room=rs.getInt("room");
               String paidAmount=rs.getString("paidAmount");
               
               b=new Booking(bookingID, patientID, consultantID, bookingDate, time, room, paidAmount );
           }
           rs.close();
           ps.close();
           return b;
       }catch(SQLException e){
           System.out.println(e.getMessage());
           return null;
       }
    
}
    public boolean update(Booking b){
        String update="update Booking set patientID=?, consultantID=?, bookingDate=?, time=?, room=?, paidAmount=? where bookingID=?";
        try{
            PreparedStatement ps=cn.prepareStatement(update);
            ps.setInt(1, b.getPatientID());
            ps.setInt(2, b.getConsultantID());
            ps.setDate(3, b.getBookingDate());
            ps.setString(4, b.getTime());
            ps.setInt(5, b.getRoom());
            ps.setString(6, b.getPaidAmount());
            ps.setInt(7, b.getBookingID());
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
           System.out.println(e.getMessage());
           return false;
       }
    
    }
}
