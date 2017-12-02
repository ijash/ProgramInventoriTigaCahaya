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
    private int idRetur;
    private String idBarang;
    private String nama;
    private int qty;
    //constructor & row list
   public TableRowContentRetur(int IdRetur,String IdBarang,String Nama,int Qty) {
        this.idRetur = IdRetur;
        this.idBarang = IdBarang;
        this.nama = Nama;
        this.qty = Qty;
    }  
    //getter&setter
    public int getIdRetur() {
        return idRetur;
    }
    public void setIdRetur(int idRetur) {
        this.idRetur = idRetur;
    }
    public String getIdBarang() {
        return idBarang;
    }
    public void setIdBarang(String IdBarang) {
        this.idBarang = idBarang;
    }
    public String getNama() {
        return nama;
    }
    public void setNama(String nama) {
        this.nama = nama;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    //table row methods
    // All table content
    public static ArrayList<TableRowContentRetur> tableContentRetur() {
        try {
            ArrayList<TableRowContentRetur> accumulatedContent = new ArrayList<TableRowContentRetur>();
            Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `id_retur`, barang_retur.id_barang ,CONCAT(barang.jenis,' ',barang.merk,' ',barang.ragam,' ',barang.seri) AS nama_barang, barang_retur.qty FROM barang INNER JOIN barang_retur ON barang.id_barang=barang_retur.id_barang;");
            ResultSet rs = statement.executeQuery();

            
            while (rs.next()) {
                // parameter lined down for easy read
                TableRowContentRetur rowContent = new TableRowContentRetur(
                        rs.getInt("id_retur"),
                        rs.getString("id_barang"),
                        rs.getString("nama_barang"),
                        rs.getInt("qty"));         
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
