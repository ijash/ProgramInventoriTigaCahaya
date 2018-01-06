/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigacahaya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static tigacahaya.MySQLconn.connect;

/**
 *
 * @author jose
 */
public class TableRowContentSupplier {
    private int idSupplier;
    private String nama;
    private String alamat;
    private String telp;
    private String email;
    private String catatan;
    //constructor & row list
   public TableRowContentSupplier(int IdSupplier,String Nama,String Alamat,String Telp,String Email,String Catatan) {
        this.idSupplier = IdSupplier;
        this.nama = Nama;
        this.alamat = Alamat;
        this.telp = Telp;
        this.email = Email;
        this.catatan = Catatan;
    }  
    //getter&setter
   public int getIdSupplier()
    {
        return idSupplier;
    }
    public String getNama()
    {
        return nama;
    }
    public String getAlamat()
    {
        return alamat;
    }
    public String getTelp()
    {
        return telp;
    }
    public String getEmail()
    {
        return email;
    }
    public String getCatatan()
    {
        return catatan;
    }
    //table row methods
    // All table content
    public static ArrayList<TableRowContentSupplier> tableContentSupplier() {
        try {
            ArrayList<TableRowContentSupplier> accumulatedContentSupplier = new ArrayList<TableRowContentSupplier>();
            Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `id_supplier`, `nama`, `alamat`, `telp`, `email`, `catatan` FROM `supplier`;");
            ResultSet rs = statement.executeQuery();

            
            while (rs.next()) {
                // parameter lined down for easy read
                TableRowContentSupplier rowContentSupplier = new TableRowContentSupplier(
                        rs.getInt("id_supplier"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getString("telp"),
                        rs.getString("email"),                      
                        rs.getString("catatan"));                      
                accumulatedContentSupplier.add(rowContentSupplier);

            }
            System.out.println("All records loaded");
            return accumulatedContentSupplier;
        } catch (Exception e) {
            System.out.println("a");
        }
        return null;
    }
    public static ArrayList<String> comboBoxSupplierData() {
        try {
            ArrayList<String> accumulatedContentSupplier = new ArrayList<String>();
            Connection conn = connect();
            PreparedStatement statement;
            statement = conn.prepareStatement("SELECT `nama` AS supplier FROM `supplier` ORDER BY id_supplier ASC;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                accumulatedContentSupplier.add(rs.getString("supplier"));
          
            }
            return accumulatedContentSupplier;
            
        } catch (SQLException ex) {
            Logger.getLogger(TableRowContentSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
    }
}
