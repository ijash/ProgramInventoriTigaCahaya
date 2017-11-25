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
public class TableRowContentRetur {
    private String idRetur;
    private String nama;
    private int jumlah;
    //constructor & row list
   public TableRowContentRetur(String IdRetur,String Nama,int Jumlah) {
        this.idRetur = IdRetur;
        this.nama = Nama;
        this.jumlah = Jumlah;
    }  
    //getter&setter
    public String getIdRetur() {
        return idRetur;
    }
    public void setIdRetur(String idRetur) {
        this.idRetur = idRetur;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public int getJumlah() {
        return jumlah;
    }
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    //table row methods
    
    // All table content
    public static ArrayList<TableRowContentRetur> tableContentRetur() {
        try {
            ArrayList<TableRowContentRetur> accumulatedContent = new ArrayList<TableRowContentRetur>();
            Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `id_retur`, `id_barang`, `jumlah` FROM `barang_retur`;");
            ResultSet rs = statement.executeQuery();

            
            while (rs.next()) {
                // parameter lined down for easy read
                TableRowContentRetur rowContent = new TableRowContentRetur(
                        rs.getString("idRetur"),
                        rs.getString("nama"),
                        rs.getInt("jumlah"));                      
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
