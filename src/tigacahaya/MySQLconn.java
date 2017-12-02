/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigacahaya;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLconn {
    public static String userName = "3cahaya";
    public static String password = "burit";
    public static String server = "192.168.0.11";
    public static String db = "3cahaya";
    public static String connString = "jdbc:mysql://" + server + ":3306/" + db + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(connString, userName, password);
            Class.forName("com.mysql.cj.jdbc.Driver");
            //  System.out.println("MySQL Connected to: " + server);
            return conn;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
    public static String executeSingleQueryResult(String queries, String coloumn) {
        try {
            Connection conn = connect();
            PreparedStatement queryStatement = conn.prepareStatement(queries);
            ResultSet rs = queryStatement.executeQuery();
            rs.next();
            //buat print nomer index supplier
            //System.out.print(rs.getString(coloumn));
            return rs.getString(coloumn);

        } catch (Exception e) {
            System.out.println(e);
            return "NULL";
        }
    }
    public static void executeVoidQuery(String query) {
        try {
            Connection conn = connect();
            PreparedStatement st = conn.prepareStatement(query);
            int executeUpdate = st.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(MySQLconn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
