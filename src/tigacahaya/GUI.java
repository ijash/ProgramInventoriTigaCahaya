/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigacahaya;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static tigacahaya.TableRowContent.getTableContent;

/**
 *
 * @author ijash
 */
public class GUI extends javax.swing.JFrame {

    
    /**
     * Creates new form GUI & GUI execution
     */
    public GUI() {
        initComponents();
        displayTable();
    }

    public void displayTable()
    {

        try {
            
            ArrayList<TableRowContent> list = getTableContent();
            DefaultTableModel model = (DefaultTableModel)Tabel.getModel();
            Object[] row = new Object[10];
            for (int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getIdBarang();
                row[1] = list.get(i).getJenis() ;
                row[2] = list.get(i).getMerk();
                row[3] = list.get(i).getRagam() ;
                row[4] = list.get(i).getSeri() ;
                row[5] = list.get(i).getQty() ;
                row[6] = list.get(i).getHarga_beli() ;
                row[7] = list.get(i).getSupplier() ;
                row[8] = list.get(i).getTgl_masuk();
                row[9] = list.get(i).getGaransi() ;
                model.addRow(row);
                
            }
            
        } catch (Exception ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    // execute SQL query
public void execSQLQuery(String query, String message) throws Exception
{
    Connection conn = MySQLconn.connect();
    Statement st;
    try{
      st = conn.createStatement();
      if ((st.executeUpdate(query)) == 1){
          JOptionPane.showMessageDialog(null,"Data "+message+" Successfully");
      }
      else{
          JOptionPane.showMessageDialog(null, "Data not "+message);
      }
    }catch (Exception e){
        e.printStackTrace();
    }
}
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jProgressBar1 = new javax.swing.JProgressBar();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        TabStok = new javax.swing.JPanel();
        PanelKiri = new javax.swing.JPanel();
        labelInfo = new javax.swing.JLabel();
        buttonRefreshDB = new javax.swing.JToggleButton();
        PanelBawah = new javax.swing.JPanel();
        fieldInputId_barang = new javax.swing.JTextField();
        fieldInputJenis = new javax.swing.JTextField();
        fieldInputMerk = new javax.swing.JTextField();
        fieldInputRagam = new javax.swing.JTextField();
        fieldInputSeri = new javax.swing.JTextField();
        fieldInputQty = new javax.swing.JTextField();
        fieldInputHarga_beli = new javax.swing.JTextField();
        fieldInputSupplier = new javax.swing.JTextField();
        fieldInputTanggal_masuk = new javax.swing.JTextField();
        comboBoxDistributor = new javax.swing.JComboBox<>();
        PanelAtas = new javax.swing.JPanel();
        SearchField = new javax.swing.JTextField();
        ComboBoxKolom = new javax.swing.JComboBox<>();
        LabelFilter = new javax.swing.JLabel();
        LabelKolom = new javax.swing.JLabel();
        PanelTengah = new javax.swing.JPanel();
        PanelScrollTabel = new javax.swing.JScrollPane();
        Tabel = new javax.swing.JTable();
        TabRetur = new javax.swing.JPanel();
        TabTrans = new javax.swing.JPanel();
        TabSupplier = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TabStok.setBackground(new java.awt.Color(0, 204, 255));

        PanelKiri.setBackground(new java.awt.Color(255, 204, 102));

        labelInfo.setText("Info");

        buttonRefreshDB.setText("Refresh DB");

        javax.swing.GroupLayout PanelKiriLayout = new javax.swing.GroupLayout(PanelKiri);
        PanelKiri.setLayout(PanelKiriLayout);
        PanelKiriLayout.setHorizontalGroup(
            PanelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKiriLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonRefreshDB)
                    .addComponent(labelInfo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelKiriLayout.setVerticalGroup(
            PanelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelKiriLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(buttonRefreshDB)
                .addGap(167, 167, 167)
                .addComponent(labelInfo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PanelBawah.setBackground(new java.awt.Color(0, 153, 153));

        fieldInputId_barang.setText("ID");

        fieldInputJenis.setText("Jenis");

        fieldInputMerk.setText("Merk");

        fieldInputRagam.setText("Ragam");

        fieldInputSeri.setText("Seri");

        fieldInputQty.setText("Qty");

        fieldInputHarga_beli.setText("Hrg Beli");

        fieldInputSupplier.setText("Supplier");

        fieldInputTanggal_masuk.setText("Tanggal Masuk");

        comboBoxDistributor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Distributor", "Toko", "Tidak Ada" }));

        javax.swing.GroupLayout PanelBawahLayout = new javax.swing.GroupLayout(PanelBawah);
        PanelBawah.setLayout(PanelBawahLayout);
        PanelBawahLayout.setHorizontalGroup(
            PanelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelBawahLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fieldInputId_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldInputJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldInputMerk, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldInputRagam, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldInputSeri, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldInputQty, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldInputHarga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldInputSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(fieldInputTanggal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboBoxDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        PanelBawahLayout.setVerticalGroup(
            PanelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelBawahLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(PanelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldInputId_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldInputJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldInputMerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldInputRagam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldInputSeri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldInputQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldInputHarga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldInputSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldInputTanggal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxDistributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PanelAtas.setBackground(new java.awt.Color(0, 204, 153));

        SearchField.setText("jTextField1");

        ComboBoxKolom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Coloumn", "ID Barang", "Jenis", "Merk", "Ragam", "Seri", "Qty", "Harga Beli", "Supplier", "Tanggal Masuk", "Garansi" }));

        LabelFilter.setText("Filter");

        LabelKolom.setText("Kolom");

        javax.swing.GroupLayout PanelAtasLayout = new javax.swing.GroupLayout(PanelAtas);
        PanelAtas.setLayout(PanelAtasLayout);
        PanelAtasLayout.setHorizontalGroup(
            PanelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAtasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LabelFilter)
                    .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxKolom, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(LabelKolom))
                .addContainerGap())
        );
        PanelAtasLayout.setVerticalGroup(
            PanelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanelAtasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PanelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LabelFilter)
                    .addComponent(LabelKolom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PanelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxKolom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        PanelTengah.setBackground(new java.awt.Color(102, 0, 102));

        Tabel.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Barang", "Jenis", "Merk", "Ragam", "Seri", "Qty", "Harga Beli", "Supplier", "Tanggal Masuk", "Garansi"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelMouseClicked(evt);
            }
        });
        PanelScrollTabel.setViewportView(Tabel);

        javax.swing.GroupLayout PanelTengahLayout = new javax.swing.GroupLayout(PanelTengah);
        PanelTengah.setLayout(PanelTengahLayout);
        PanelTengahLayout.setHorizontalGroup(
            PanelTengahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelScrollTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 896, Short.MAX_VALUE)
        );
        PanelTengahLayout.setVerticalGroup(
            PanelTengahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelScrollTabel, javax.swing.GroupLayout.DEFAULT_SIZE, 421, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout TabStokLayout = new javax.swing.GroupLayout(TabStok);
        TabStok.setLayout(TabStokLayout);
        TabStokLayout.setHorizontalGroup(
            TabStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanelBawah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(TabStokLayout.createSequentialGroup()
                .addComponent(PanelKiri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelTengah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(PanelAtas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        TabStokLayout.setVerticalGroup(
            TabStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(TabStokLayout.createSequentialGroup()
                .addComponent(PanelAtas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(TabStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(PanelTengah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(PanelKiri, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PanelBawah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane3.addTab("Stok", TabStok);

        javax.swing.GroupLayout TabReturLayout = new javax.swing.GroupLayout(TabRetur);
        TabRetur.setLayout(TabReturLayout);
        TabReturLayout.setHorizontalGroup(
            TabReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1022, Short.MAX_VALUE)
        );
        TabReturLayout.setVerticalGroup(
            TabReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Retur", TabRetur);

        javax.swing.GroupLayout TabTransLayout = new javax.swing.GroupLayout(TabTrans);
        TabTrans.setLayout(TabTransLayout);
        TabTransLayout.setHorizontalGroup(
            TabTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1022, Short.MAX_VALUE)
        );
        TabTransLayout.setVerticalGroup(
            TabTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Transaksi", TabTrans);

        javax.swing.GroupLayout TabSupplierLayout = new javax.swing.GroupLayout(TabSupplier);
        TabSupplier.setLayout(TabSupplierLayout);
        TabSupplierLayout.setHorizontalGroup(
            TabSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1022, Short.MAX_VALUE)
        );
        TabSupplierLayout.setVerticalGroup(
            TabSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 541, Short.MAX_VALUE)
        );

        jTabbedPane3.addTab("Supplier", TabSupplier);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane3)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelMouseClicked
        // Display selected row in tex fields
        int i = Tabel.getSelectedRow();
        TableModel model = Tabel.getModel();
        fieldInputId_barang.setText(model.getValueAt(i,0).toString());
        fieldInputJenis.setText(model.getValueAt(i,1).toString());
        fieldInputMerk.setText(model.getValueAt(i,2).toString());
        fieldInputRagam.setText(model.getValueAt(i,3).toString());
        fieldInputRagam.setCaretPosition(0);
        fieldInputSeri.setText(model.getValueAt(i,4).toString());
        fieldInputSeri.setCaretPosition(0);
        fieldInputQty.setText(model.getValueAt(i,5).toString());
        fieldInputHarga_beli.setText(model.getValueAt(i,6).toString());
        fieldInputSupplier.setText(model.getValueAt(i,7).toString());
        fieldInputTanggal_masuk.setText(model.getValueAt(i,8).toString());
        fieldInputTanggal_masuk.setCaretPosition(0);
       // comboBoxDistributor.getItemAt(i);
        
       System.out.println(model.getValueAt(i,9));
        
                
    }//GEN-LAST:event_TabelMouseClicked

    /**
     * by ijash
     */
    public static void guiStart() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        }) ;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxKolom;
    private javax.swing.JLabel LabelFilter;
    private javax.swing.JLabel LabelKolom;
    private javax.swing.JPanel PanelAtas;
    private javax.swing.JPanel PanelBawah;
    private javax.swing.JPanel PanelKiri;
    private javax.swing.JScrollPane PanelScrollTabel;
    private javax.swing.JPanel PanelTengah;
    private javax.swing.JTextField SearchField;
    private javax.swing.JPanel TabRetur;
    private javax.swing.JPanel TabStok;
    private javax.swing.JPanel TabSupplier;
    private javax.swing.JPanel TabTrans;
    private javax.swing.JTable Tabel;
    private javax.swing.JToggleButton buttonRefreshDB;
    private javax.swing.JComboBox<String> comboBoxDistributor;
    private javax.swing.JTextField fieldInputHarga_beli;
    private javax.swing.JTextField fieldInputId_barang;
    private javax.swing.JTextField fieldInputJenis;
    private javax.swing.JTextField fieldInputMerk;
    private javax.swing.JTextField fieldInputQty;
    private javax.swing.JTextField fieldInputRagam;
    private javax.swing.JTextField fieldInputSeri;
    private javax.swing.JTextField fieldInputSupplier;
    private javax.swing.JTextField fieldInputTanggal_masuk;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JLabel labelInfo;
    // End of variables declaration//GEN-END:variables
}
