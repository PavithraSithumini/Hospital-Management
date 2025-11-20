/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import business.Login;
import java.util.ArrayList;
import java.sql.*;
/**
 *
 * @author icbt1
 */
public class LoginDB {
    private Connection cn;
    
    public LoginDB() {
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
    
    public boolean add(Login log){
        String insert="insert into Login(userID, password, role) values(?,?)";
        try{
            PreparedStatement ps=cn.prepareStatement(insert);
            ps.setString(1, log.getUserID());
            ps.setString(2, log.getPassword());
            ps.setString(3, log.getRole());
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
    }    
       public ArrayList<Login> getAll(){
        ArrayList<Login> logList=new ArrayList<>();
        String select="select * from Login";
        try{
            PreparedStatement ps=cn.prepareStatement(select);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){               
               String userID=rs.getString("userID");
               String password=rs.getString("password");
               String role = rs.getString("role"); // Fetch role
               Login log=new Login(userID, password, role);
               logList.add(log);
            }
            return logList;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
        
    }
    public boolean delete(String userID){
        String delete="delete from Login where userID=?";
        try{
            PreparedStatement ps=cn.prepareStatement(delete);
            ps.setString(1, userID);
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return false;
        }
      
    }
    
       public Login get(String userID){
       Login log=null;
       String select="select * from Login where userID=?";
       try{
           PreparedStatement ps=cn.prepareStatement(select);
           ps.setString(1, userID);
           ResultSet rs=ps.executeQuery();
           if(rs.next()){               
               String uID=rs.getString("userID");
               String password=rs.getString("password");
               String role = rs.getString("role"); // Fetch role
               log=new Login(uID, password, role);
           }
           rs.close();
           ps.close();
           return log;
       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
       return log;
    
   }
       
       public boolean update(Login log){
        String update="update Login set password=? where userID=?";
        try{
            PreparedStatement ps=cn.prepareStatement(update);
            ps.setString(1, log.getPassword());
            ps.setString(2, log.getUserID());
            ps.setString(3, log.getRole());
            ps.executeUpdate();
            ps.close();
            return true;
        }catch(SQLException e){
           System.out.println(e.getMessage());
           return false;
       }
    
    }
}
