/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;
import business.Login;
import data.LoginDB;
import javax.swing.JOptionPane;
import user.MainUI;
/**
 *
 * @author acer
 */
public class LoginController {
    public static void login(String userID, String password) {
        LoginDB loginDB = new LoginDB();
        Login user = loginDB.get(userID);

        if (user == null) {
            JOptionPane.showMessageDialog(null, "Invalid Username");
        } else if (!user.getPassword().equals(password)) {
            JOptionPane.showMessageDialog(null, "Incorrect Password");
        } else {
            String role = user.getRole();
            MainUI mainUI = new MainUI(role); // Pass role to MainUI
            mainUI.setVisible(true);    
        
    
}
    }
}
