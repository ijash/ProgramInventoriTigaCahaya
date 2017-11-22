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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLconn {

    public static String userName = "root";
    public static String password = "ijasabb35";
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
    public static String executeSingleQueryResult(String queries,String coloumn) {
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
    public static void executeVoidQuery(String query){
        try {
            Connection conn = connect();
            PreparedStatement st = conn.prepareStatement(query);
            int executeUpdate = st.executeUpdate();
        } catch (Exception ex) {
            Logger.getLogger(MySQLconn.class.getName()).log(Level.SEVERE, null, ex);
        }
    
}
    public static void post(String jenis, String merk, String ragam, String seri, String qty, String harga_beli, String supplier, String garansi) {
        try {
            Connection conn = connect();
            PreparedStatement posted = conn.prepareStatement("INSERT INTO `barang`(`id_barang`,`jenis`, `merk`, `ragam`, `seri`, `qty`, `harga_beli`, `supplier`, `garansi`) VALUES ('no','" + jenis + "', '" + merk + "', '" + ragam + "', '" + seri + "', '" + qty + "', '" + harga_beli + "', '" + supplier + "', '" + garansi + "')");
            posted.executeUpdate();
            
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println("insert complete");
        }

    }
    public static ArrayList<String> get() {
    try{
        Connection conn = connect();
        PreparedStatement statement = conn.prepareStatement("SELECT `id_barang`,`jenis`,`merk`,`ragam`,`seri`,`qty`,`harga_beli`,`supplier`,`tgl_masuk` ,`garansi`FROM `barang`");
        ResultSet rs = statement.executeQuery();
        
    ArrayList<String> array = new ArrayList<String>();
    while(rs.next()){
//        System.out.print(rs.getString("id_barang"));System.out.print("\t\t");
//        System.out.print(rs.getString("jenis"));System.out.print("\t\t");
//        System.out.print(rs.getString("merk"));System.out.print("\t\t");
//        System.out.print(rs.getString("ragam"));System.out.print("\t\t");
//        System.out.print(rs.getString("seri"));System.out.print("\t\t");
//        System.out.print(rs.getString("qty"));System.out.print("\t\t");
//        System.out.print(rs.getString("harga_beli"));System.out.print("\t\t");
//        System.out.print(rs.getString("supplier"));System.out.print("\t\t");
//        System.out.print(rs.getString("tgl_masuk"));System.out.print("\t\t");
//        System.out.println(rs.getString("garansi"));

        array.add(rs.getString("id_barang"));
        array.add(rs.getString("jenis"));
        array.add(rs.getString("merk"));
        array.add(rs.getString("ragam"));
        array.add(rs.getString("seri"));
        array.add(rs.getString("qty"));
        array.add(rs.getString("harga_beli"));
        array.add(rs.getString("supplier"));
        array.add(rs.getString("tgl_masuk"));
        array.add(rs.getString("garansi"));
        
    }
        System.out.println("All records loaded");
        return array;
    }catch (Exception e){System.out.println("e");
}
    return null;
}



















}
