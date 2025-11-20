/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import business.Consultant;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author icbt1
 */
public class ConsultantDB {
    private ArrayList<Consultant> consultantList;
    private Connection cn;

    
    public ConsultantDB() {
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
    
     public boolean add(Consultant c){
        String insert="insert into Consultant(cID, firstName, lastName, category, hospital, telephone) values(?,?,?,?,?,?)";
        try{
            PreparedStatement ps=cn.prepareStatement(insert);
            ps.setInt(1, c.getcID());
            ps.setString(2, c.getFirstName());
            ps.setString(3, c.getLastName());
            ps.setString(4, c.getCategory());
            ps.setString(5, c.getHospital());
            ps.setString(6, c.getTelephone());
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
        
        
    }
    
    public ArrayList<Consultant> getAll(){
        ArrayList<Consultant> cList=new ArrayList<>();
        String select="select * from Consultant";
        try{
            PreparedStatement ps=cn.prepareStatement(select);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                int cID=rs.getInt("cID");
               String firstName=rs.getString("firstName");
               String lastName=rs.getString("lastName");
               String category=rs.getString("category");
               String hospital=rs.getString("hospital");
               String telephone=rs.getString("telephone");
               
               Consultant c=new Consultant(cID, firstName, lastName, category, hospital, telephone);
               cList.add(c);
            }
            return cList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    
    public boolean delete(int cID){
        String delete="delete from Consultant where ConsultantID=?";
        try{
            PreparedStatement ps=cn.prepareStatement(delete);
            ps.setInt(1, cID);
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
      
    }
    
    public Consultant get(int cID){
       Consultant p=null;
       String select="select * from Consultant where ConsultantID=?";
       try{
           PreparedStatement ps=cn.prepareStatement(select);
           ps.setInt(1, cID);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){
               int patID=rs.getInt("cID");
               String firstName=rs.getString("firstName");
               String lastName=rs.getString("lastName");
               String category=rs.getString("category");
               String hospital=rs.getString("hospital");
               String telephone=rs.getString("telephone");
               
               Consultant c=new Consultant(cID, firstName, lastName, category, hospital,telephone);
           }
           rs.close();
           ps.close();
           return p;
       }catch(SQLException e){
           System.out.println(e.getMessage());
           return null;
       }
    
}
    public boolean update(Consultant c){
        String update="update Consultant set firstName=?, lastName=?, category=?, hospital=?, telephone=? where patientID=?";
        try{
            PreparedStatement ps=cn.prepareStatement(update);
            ps.setString(1, c.getFirstName());
            ps.setString(2, c.getLastName());
            ps.setString(3, c.getCategory());
            ps.setString(4, c.getHospital());
            ps.setString(5, c.getTelephone());
            ps.setInt(6, c.getcID());
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
           System.out.println(e.getMessage());
           return false;
       }
    
    }
}
