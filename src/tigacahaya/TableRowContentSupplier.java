/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigacahaya;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import static tigacahaya.MySQLconn.connect;

/**
 *
 * @author jose
 */
public class TableRowContentSupplier {
    private String idSupplier;
    private String nama;
    private String alamat;
    private int telpon;
    private String email;
    private String catatan;
    //constructor & row list
   public TableRowContentSupplier(String IdSupplier,String Nama,String Alamat,int Telpon,String Email,String Catatan) {
        this.idSupplier = IdSupplier;
        this.nama = Nama;
        this.alamat = Alamat;
        this.telpon = Telpon;
        this.email = Email;
        this.catatan = Catatan;
    }  
    //getter&setter
    public String getIdSupplier() {
        return idSupplier;
    }
    public void setIdSupplier(String idSupplier) {
        this.idSupplier = idSupplier;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public int getTelpon() {
        return telpon;
    }
    public void setTelpon(int telpon) {
        this.telpon = telpon;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCatatan() {
        return catatan;
    }
    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
    //table row methods
    
    // All table content
    public static ArrayList<TableRowContentSupplier> tableContentSupplier() {
        try {
            ArrayList<TableRowContentSupplier> accumulatedContent = new ArrayList<TableRowContentSupplier>();
            Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `id_supplier`, `nama`, `alamat`, `telp`, `email`, `catatan` FROM `supplier`;");
            ResultSet rs = statement.executeQuery();

            
            while (rs.next()) {
                // parameter lined down for easy read
                TableRowContentSupplier rowContent = new TableRowContentSupplier(
                        rs.getString("id_supplier"),
                        rs.getString("nama"),
                        rs.getString("alamat"),
                        rs.getInt("telpon"),
                        rs.getString("email"),                      
                        rs.getString("catatan"));                      
                accumulatedContent.add(rowContent);

            }
            System.out.println("All records loaded");
            return accumulatedContent;
        } catch (Exception e) {
            System.out.println("e");
        }
        return null;
    }
}
