/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import business.Patient;
import java.util.ArrayList;
import java.sql.*;
import java.sql.Date;
/**
 *
 * @author icbt1
 */
public class PatientDB {
    private ArrayList<Patient> patientList;
    private Connection cn;
    
    public PatientDB() {
        try{            
            String url="jdbc:mysql://localhost:3307/hospitaldb";
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
    
    public boolean add(Patient p){
        String insert="insert into Patient(patientID, firstName, lastName, gender,dob,tel,category) values(?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps=cn.prepareStatement(insert);
            ps.setInt(1, p.getPatientID());
            ps.setString(2, p.getFirstName());
            ps.setString(3, p.getLastName());
            ps.setString(4, p.getGender());
            ps.setDate(5, p.getDob());
            ps.setString(6, p.getTel());
            ps.setString(7, p.getCategory());
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        
        
    }
    
    public ArrayList<Patient> getAll(){
        ArrayList<Patient> pList=new ArrayList<>();
        String select="select * from Patient";
        try{
            PreparedStatement ps=cn.prepareStatement(select);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int patID=rs.getInt("patientID");
               String firstName=rs.getString("firstName");
               String lastName=rs.getString("lastName");
               String gender=rs.getString("gender");
               Date dob=rs.getDate("dob");
               String tel=rs.getString("tel");
               String category=rs.getString("category");
               Patient p=new Patient(patID, firstName, lastName, gender, dob, tel, category);
               pList.add(p);
            }
            return pList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public boolean delete(int patientID){
        String delete="delete from Patient where patientID=?";
        try{
            PreparedStatement ps=cn.prepareStatement(delete);
            ps.setInt(1, patientID);
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
      
    }
    
    public Patient get(int patientID){
       Patient p=null;
       String select="select * from Patient where patientID=?";
       try{
           PreparedStatement ps=cn.prepareStatement(select);
           ps.setInt(1, patientID);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
               int patID=rs.getInt("patientID");
               String firstName=rs.getString("firstName");
               String lastName=rs.getString("lastName");
               String gender=rs.getString("gender");
               Date dob=rs.getDate("dob");
               String tel=rs.getString("tel");
               String category=rs.getString("category");
               p=new Patient(patID, firstName, lastName, gender, dob, tel, category);
           }
           rs.close();
           ps.close();
           return p;
       }catch(SQLException e){
           System.out.println(e.getMessage());
           return null;
       }
    
}
    public boolean update(Patient p){
        String update="update Patient set firstName=?, lastName=?, gender=?, dob=?, tel=?, category=? where patientID=?";
        try{
            PreparedStatement ps=cn.prepareStatement(update);
            ps.setString(1, p.getFirstName());
            ps.setString(2, p.getLastName());
            ps.setString(3, p.getGender());
            ps.setDate(4, p.getDob());
            ps.setString(5, p.getTel());
            ps.setString(5, p.getCategory());
            ps.setInt(6, p.getPatientID());
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
           System.out.println(e.getMessage());
           return false;
       }
    
    }
}