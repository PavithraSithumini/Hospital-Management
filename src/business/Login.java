/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

/**
 *
 * @author icbt1
 */
public class Login {
    private String userID;
    private String password;
    private String role;

    public Login() {
    }

    public Login(String userID, String password, String role) {
        this.userID = userID;
        this.password = password;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setrole(String role) {
        this.role = role;
    }
}
