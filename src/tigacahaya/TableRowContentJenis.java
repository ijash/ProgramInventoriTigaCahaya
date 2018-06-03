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
public class TableRowContentJenis {
    private int idJJenis;
    private String namaJenis;
    private String deskripsi;
    
     public TableRowContentJenis(int IdJJenis,String NamaJenis,String Deskripsi) {
        this.idJJenis = IdJJenis;
        this.namaJenis = NamaJenis;
        this.deskripsi = Deskripsi;
}
     
      public int getIdJJenis()
    {
        return idJJenis;
    }
    public String getNamaJenis()
    {
        return namaJenis;
    }
    public String getDeskripsi()
    {
        return deskripsi;
    }
    
    public static ArrayList<String> comboBoxJenisData() {
        try {
            ArrayList<String> accumulatedContentJenis = new ArrayList<String>();
            Connection conn = connect();
            PreparedStatement statement;
            statement = conn.prepareStatement("SELECT `nama_jenis` AS jenis FROM `jenis` ORDER BY id_jenis ASC;");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                accumulatedContentJenis.add(rs.getString("jenis"));
          
            }
            return accumulatedContentJenis;
            
        } catch (SQLException ex) {
            Logger.getLogger(TableRowContentSupplier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return  null;
    }
}
