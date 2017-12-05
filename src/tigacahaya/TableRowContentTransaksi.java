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
            if ((statusInvoiceAkhir() != "paid") || (statusInvoiceAkhir() != "lunas")) {
                PreparedStatement statement = conn.prepareStatement("SELECT `id_inv`,transaksi_cart.id_barang ,CONCAT(barang.jenis,' ',barang.merk,' ',barang.ragam,' ',barang.seri) AS nama_barang, transaksi_cart.qty FROM transaksi_cart INNER JOIN barang ON barang.id_barang=transaksi_cart.id_barang Where transaksi_cart.id_inv = '" + statusInvoiceAkhir() + "'");
                ResultSet rs = statement.executeQuery();
                while (rs.next()) {
                    // parameter lined down for easy read
                    TableRowContentTransaksi rowContent = new TableRowContentTransaksi(
                            rs.getString("id_barang"),
                            rs.getString("id_inv"),
                            rs.getString("nama_barang"),
                            rs.getInt("qty"));
                    accumulatedContent.add(rowContent);
                }
            System.out.println("All records loaded");
            return accumulatedContent;
        }} catch (Exception e) {
            System.out.println("e");
            
                }
        
        return null;
    }
     // to check invoice, return (paid,no_inv,empty)
    public static String statusInvoiceAkhir() {
       
        try {
            String query = "SELECT `id_inv`,`lunas`FROM `transaksi_invoice`";
            Connection conn = connect();
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                rs.last();
                if (rs.getInt("lunas") == 1) {
                    return "paid";
                    // rs= st.executeQuery("INSERT INTO `3cahaya`.`transaksi_invoice` (`prefix_inv`,`lunas`) VALUES ('TC', b'0');\n" + "UPDATE transaksi_invoice SET `id_inv` = concat(`prefix_inv`,LPAD(transaksi_invoice.no_inv, 8,\"0\") );");
                } else if (rs.getInt("lunas") == 0) {
                    return rs.getString("id_inv");
                }
            } else {
                return "empty";
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableRowContentTransaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public static void tambahInvoice(){
        if ((statusInvoiceAkhir()=="empty")||(statusInvoiceAkhir()=="paid")){
        MySQLconn.executeVoidQuery("INSERT INTO `transaksi_invoice` (`prefix_inv`) VALUES ('TC')");
        MySQLconn.executeVoidQuery("UPDATE transaksi_invoice SET `id_inv` = concat( `prefix_inv`,LPAD(transaksi_invoice.no_inv, 8,\"0\") );");
    }}
}
// buat get value tabel buat di post        
