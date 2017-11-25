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
public class TableRowContentTransaksi {

    private String idBarang;
    private String idInv;
    private String namaBarang;
    private int qty;

    //getter setter
    public String getIdBarang() {
        return idBarang;
    }
    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }
    public String getIdInv() {
        return idInv;
    }
    public void setIdInv(String idInv) {
        this.idInv = idInv;
    }
    public String getNamaBarang() {
        return namaBarang;
    }
    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    //constructor & row list
    public TableRowContentTransaksi(String idBarang, String idInv, String namaBarang, int qty) {
        this.idBarang = idBarang;
        this.idInv = idInv;
        this.namaBarang = namaBarang;
        this.qty = qty;
    }
    //table row methods

    // All table content
    public static ArrayList<TableRowContentTransaksi> tableContentTransaksi() {
        try {
            ArrayList<TableRowContentTransaksi> accumulatedContent = new ArrayList<TableRowContentTransaksi>();
            Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `id_inv`,transaksi_cart.id_barang ,CONCAT(barang.jenis,' ',barang.merk,' ',barang.ragam,' ',barang.seri) AS nama_barang, transaksi_cart.qty FROM transaksi_cart INNER JOIN barang ON barang.id_barang=transaksi_cart.id_barang");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                // parameter lined down for easy read
                TableRowContentTransaksi rowContent = new TableRowContentTransaksi(
                        rs.getString("id_inv"),
                        rs.getString("id_barang"),
                        rs.getString("nama"),
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
