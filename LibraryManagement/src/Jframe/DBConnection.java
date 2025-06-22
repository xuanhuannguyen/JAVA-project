/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jframe;

import java.sql.*;

/**
 *
 * @author Admin
 */
public class DBConnection {
    static Connection con = null;
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/library";
        String user = "root";
        String mk = "21102005";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,user,mk);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
    
}
