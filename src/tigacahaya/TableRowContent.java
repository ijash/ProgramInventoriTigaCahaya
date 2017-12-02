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
 * @author ijash
 */
public class TableRowContent {
    
    private String idBarang;
    private String jenis;
    private String merk;
    private String ragam;
    private String seri;
    private int qty;
    private double harga_beli;
    private String supplier;
    private String tgl_masuk;
    private String garansi;
    //constructor & row list
   public TableRowContent(String id_barang, String jenis, String merk, String ragam, String seri, int qty, double harga_beli, String supplier, String tgl_masuk, String garansi) {
        this.idBarang = id_barang;
        this.jenis = jenis;
        this.merk = merk;
        this.ragam = ragam;
        this.seri = seri;
        this.qty = qty;
        this.harga_beli = harga_beli;
        this.supplier = supplier;
        this.tgl_masuk = tgl_masuk;
        this.garansi = garansi;
    }  
    //getter&setter
    public String getIdBarang() {
        return idBarang;
    }
    public void setIdBarang(String idBarang) {
        this.idBarang = idBarang;
    }
    public String getJenis() {
        return jenis;
    }
    public void setJenis(String jenis) {
        this.jenis = jenis;
    }
    public String getMerk() {
        return merk;
    }
    public void setMerk(String merk) {
        this.merk = merk;
    }
    public String getRagam() {
        return ragam;
    }
    public void setRagam(String ragam) {
        this.ragam = ragam;
    }
    public String getSeri() {
        return seri;
    }
    public void setSeri(String seri) {
        this.seri = seri;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }
    public double getHarga_beli() {
        return harga_beli;
    }
    public void setHarga_beli(double harga_beli) {
        this.harga_beli = harga_beli;
    }
    public String getSupplier() {
        return supplier;
    }
    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
    public String getTgl_masuk() {
        return tgl_masuk;
    }
    public void setTgl_masuk(String tgl_masuk) {
        this.tgl_masuk = tgl_masuk;
    }
    public String getGaransi() {
        return garansi;
    }
    public void setGaransi(String garansi) { 
        this.garansi = garansi;
    }
    //table row methods
    // All table content
    public static ArrayList<TableRowContent> tableContent() {
        try {
            ArrayList<TableRowContent> accumulatedContent = new ArrayList<TableRowContent>();
            Connection conn = connect();
            PreparedStatement statement = conn.prepareStatement("SELECT `id_barang`,`jenis`,`merk`,`ragam`,`seri`,`qty`,`harga_beli`,`nama` AS `supplier`,`tgl_masuk` ,`garansi`FROM `barang`INNER JOIN`supplier` ON barang.supplier=supplier.id_supplier;");
            ResultSet rs = statement.executeQuery();

            
            while (rs.next()) {
                // parameter lined down for easy read
                TableRowContent rowContent = new TableRowContent(
                        rs.getString("id_barang"),
                        rs.getString("jenis"),
                        rs.getString("merk"),
                        rs.getString("ragam"),
                        rs.getString("seri"),
                        rs.getInt("qty"),
                        rs.getDouble("harga_beli"),
                        rs.getString("supplier"),
                        rs.getString("tgl_masuk"),
                        rs.getString("garansi"));
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
