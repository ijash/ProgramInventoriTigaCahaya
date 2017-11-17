/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigacahaya;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class MySQLconn {

    public static String userName = "root";
    public static String password = "ijasabb35";
    public static String server = "192.168.0.11";
    public static String db = "3cahaya";
    public static String connString = "jdbc:mysql://" + server + ":3306/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    public static Connection connect() {
        try {
            Connection conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(connString, userName, password);
            System.out.println("MySQL Connected to: " + server);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
        }
        return null;
    }


}

