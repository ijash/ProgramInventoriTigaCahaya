/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigacahaya;

/**
 *
 * @author ijash
 */
public class TableLogic {

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

    public TableLogic(String id_barang, String jenis, String merk, String ragam, String seri, int qty, double harga_beli, String supplier, String tgl_masuk, String garansi) {
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
    
}
