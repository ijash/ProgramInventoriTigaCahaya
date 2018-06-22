/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigacahaya;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.table.TableRowSorter;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import static tigacahaya.TableRowContent.tableContent;
import static tigacahaya.TableRowContentRetur.tableContentRetur;
import static tigacahaya.TableRowContentSupplier.tableContentSupplier;
import static tigacahaya.TableRowContentTransaksi.statusInvoiceAkhir;
import static tigacahaya.TableRowContentTransaksi.tableContentTransaksi;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.text.SimpleDateFormat;
import java.io.File;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author ijash
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form GUI & GUI execution
     */
    MySQLconn conn = new MySQLconn();
    JasperReport jasperReport;
    JasperDesign jasperDesign;
    JasperPrint jasperPrint;
    public static String levelOperator;
    public static String namaOperator;
    public static String idOperator;

    public static String getIdOperator() {
        return idOperator;
    }

    public static void setIdOperator(String idOperator) {
        GUI.idOperator = idOperator;
    }

    public static String getNamaOperator() {
        return namaOperator;
    }

    public static void setNamaOperator(String namaOperator) {
        GUI.namaOperator = namaOperator;
    }

    public static void setLevelOperator(String level) {
        levelOperator = level;
    }

    public static String getLevelOperator() {
        return levelOperator;
    }

    public GUI() {

        initComponents();
//      setLevelAdmin();
//      labelLevel.setVisible(false);
//      labelLevel.setText(Level);
        admin();
        displayTable();
        displaySupplierComboBox();
        displayJenisComboBox();
        sortTable();
        displayTableTransaksi();
        sortTableTransaksi();
        displayTableSupplier();
        sortTableSupplier();
        displayTableRetur();
        sortTableRetur();
    }

    // back-end methods......................................

    public void displayTable() {

//        labelTest.setText(Level);
        System.out.println("admin status adalah: " + this.levelOperator + " berarti admin = " + (this.levelOperator.equals("1")));
        if (this.levelOperator.equals("1")) {
            ArrayList<TableRowContent> list = tableContent();
            DefaultTableModel model = (DefaultTableModel) tabel.getModel();
            Object[] row = new Object[10];
            for (int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getIdBarang();
                row[1] = list.get(i).getJenis();
                row[2] = list.get(i).getMerk();
                row[3] = list.get(i).getRagam();
                row[4] = list.get(i).getSeri();
                row[5] = list.get(i).getQty();
                row[6] = list.get(i).getHarga_beli();
                row[7] = list.get(i).getSupplier();
                row[8] = list.get(i).getTgl_masuk();
                row[9] = list.get(i).getGaransi();
                model.addRow(row);

            }
        } else {
            ArrayList<TableRowContent> list = tableContent();
            DefaultTableModel model = (DefaultTableModel) tabel.getModel();
            Object[] row = new Object[10];
            for (int i = 0; i < list.size(); i++) {
                row[0] = list.get(i).getIdBarang();
                row[1] = list.get(i).getJenis();
                row[2] = list.get(i).getMerk();
                row[3] = list.get(i).getRagam();
                row[4] = list.get(i).getSeri();
                row[5] = list.get(i).getQty();
                row[6] = "Private";
                row[7] = list.get(i).getSupplier();
                row[8] = list.get(i).getTgl_masuk();
                row[9] = list.get(i).getGaransi();
                model.addRow(row);

            }
        }

    }

    public void sortTable() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) tabel.getModel());
        tabel.setRowSorter(sorter);
    }

    public void displayTableTransaksi() {
        ArrayList<TableRowContentTransaksi> list = tableContentTransaksi();
        DefaultTableModel model = (DefaultTableModel) tabelTransaksi.getModel();
        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdBarang();
            row[1] = list.get(i).getNamaBarang();
            row[2] = list.get(i).getQty();
            model.addRow(row);
            labelTransaksiAtas.setText("Invoice :" + TableRowContentTransaksi.statusInvoiceAkhir());
        }
    }

    public void sortTableTransaksi() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) tabelTransaksi.getModel());
        tabelTransaksi.setRowSorter(sorter);
    }

    public void displayTableRetur() {
        ArrayList<TableRowContentRetur> list = tableContentRetur();
        DefaultTableModel model = (DefaultTableModel) tabelRetur.getModel();
        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdRetur();
            row[1] = list.get(i).getIdBarang();
            row[2] = list.get(i).getNama();
            row[3] = list.get(i).getQty();
            model.addRow(row);
        }
    }

    public void sortTableRetur() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) tabelRetur.getModel());
        tabelRetur.setRowSorter(sorter);
    }

    public void displayTableSupplier() {
        ArrayList<TableRowContentSupplier> list = tableContentSupplier();
        DefaultTableModel model = (DefaultTableModel) tabelSupplier.getModel();
        Object[] row = new Object[6];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getIdSupplier();
            row[1] = list.get(i).getNama();
            row[2] = list.get(i).getAlamat();
            row[3] = list.get(i).getTelp();
            row[4] = list.get(i).getEmail();
            row[5] = list.get(i).getCatatan();
            model.addRow(row);
        }
    }

    public void sortTableSupplier() {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>((DefaultTableModel) tabelSupplier.getModel());
        tabelSupplier.setRowSorter(sorter);
    }

    public void refreshTable() {

        DefaultTableModel model = (DefaultTableModel) tabel.getModel();
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
        displayTable();
        displaySupplierComboBox();
        displayJenisComboBox();

    }

    public void refreshTableTransaksi() {
        DefaultTableModel model = (DefaultTableModel) tabelTransaksi.getModel();
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
        displayTableTransaksi();

    }

    public void refreshTableRetur() {
        DefaultTableModel model = (DefaultTableModel) tabelRetur.getModel();
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
        displayTableRetur();

    }

    public void refreshTableSupplier() {
        DefaultTableModel model = (DefaultTableModel) tabelSupplier.getModel();
        if (model.getRowCount() > 0) {
            for (int i = model.getRowCount() - 1; i > -1; i--) {
                model.removeRow(i);
            }
        }
        displayTableSupplier();
    }

    public void filterTable(String query, int column) {
        TableRowSorter<DefaultTableModel> tf = new TableRowSorter<>((DefaultTableModel) tabel.getModel());
        tabel.setRowSorter(tf);
        if (0 == column) {
            tf.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        } else {
            tf.setRowFilter(RowFilter.regexFilter("(?i)" + query, column - 1));
        }
    }

    public void filterTableSupplier(String query, int column) {
        TableRowSorter<DefaultTableModel> tf = new TableRowSorter<>((DefaultTableModel) tabelSupplier.getModel());
        tabelSupplier.setRowSorter(tf);
        if (0 == column) {
            tf.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        } else {
            tf.setRowFilter(RowFilter.regexFilter("(?i)" + query, column - 1));
        }
    }

    public void filterTableRetur(String query, int column) {
        TableRowSorter<DefaultTableModel> tf = new TableRowSorter<>((DefaultTableModel) tabelRetur.getModel());
        tabelRetur.setRowSorter(tf);
        if (0 == column) {
            tf.setRowFilter(RowFilter.regexFilter("(?i)" + query));
        } else {
            tf.setRowFilter(RowFilter.regexFilter("(?i)" + query, column - 1));
        }
    }

    public void displaySupplierComboBox() {
        comboBoxSupplier.removeAllItems();
        ArrayList<String> list;
        list = TableRowContentSupplier.comboBoxSupplierData();
        for (String i : list) {
            comboBoxSupplier.addItem(i);
        }
    }

    public void displayJenisComboBox() {
        comboBoxJenis.removeAllItems();
        ArrayList<String> list;
        list = TableRowContentJenis.comboBoxJenisData();
        for (String i : list) {
            comboBoxJenis.addItem(i);
        }
    }

    public void enable() {
        comboBoxGaransi.setEnabled(true);
        comboBoxJenis.setEnabled(true);
        comboBoxSupplier.setEnabled(true);
        fieldInputQty.setEditable(true);
        fieldInputHarga_beli.setEditable(true);
        fieldInputMerk.setEditable(true);
        fieldInputRagam.setEditable(true);
        fieldInputSeri.setEditable(true);
    }

    public void enableSupplier() {
        fieldInputNamaSupplier.setEditable(true);
        fieldInputEmailSupplier.setEditable(true);
        fieldInputAlamatSupplier.setEditable(true);
        fieldInputTelponSupplier.setEditable(true);
        textAreaCatatan.setEditable(true);
    }

    public void disable() {
        comboBoxGaransi.setEnabled(false);
        comboBoxJenis.setEnabled(false);
        comboBoxSupplier.setEnabled(false);
        fieldInputQty.setEditable(false);
        fieldInputHarga_beli.setEditable(false);
        fieldInputMerk.setEditable(false);
        fieldInputRagam.setEditable(false);
        fieldInputSeri.setEditable(false);
    }

    public void disableSupplier() {
        fieldInputNamaSupplier.setEditable(false);
        fieldInputEmailSupplier.setEditable(false);
        fieldInputAlamatSupplier.setEditable(false);
        fieldInputTelponSupplier.setEditable(false);
        textAreaCatatan.setEditable(false);
    }

    public void admin() {
        if (this.levelOperator.equals("2")) {
            tombolHapus.setEnabled(false);
            tombolUbah.setEnabled(false);
            tombolTambah.setEnabled(false);
            tombolUbahSupplier.setEnabled(false);
            tombolTambahSupplier.setEnabled(false);
            tombolHapusSupplier.setEnabled(false);
        }
    }

    public void clearField() {
        fieldInputId_barang.setText("ID");
        fieldInputQty.setText("");
        fieldInputHarga_beli.setText("Hrg Beli");
        comboBoxSupplier.setSelectedIndex(0);
        comboBoxJenis.setSelectedIndex(0);
        fieldInputTanggal_masuk.setText("Hari Ini");
        fieldInputMerk.setText("Merk");
        fieldInputRagam.setText("Ragam");
        fieldInputSeri.setText("Seri");
        tombolTambah.setEnabled(true);
        tombolHapus.setEnabled(true);
        tombolUbah.setEnabled(true);
        fieldInputQty.requestFocus();
    }

    public void clearSupplier() {
        fieldInputIDSupplier.setText("ID");
        fieldInputEmailSupplier.setText("Email");
        fieldInputNamaSupplier.setText("Nama");
        textAreaCatatan.setText("");
        fieldInputAlamatSupplier.setText("Alamat");
        fieldInputTelponSupplier.setText("Telpon");

        fieldInputNamaSupplier.requestFocus();
    }

    // execute SQL query
    public void execSQLQuery(String query, String message) {
        Connection conn = MySQLconn.connect();
        Statement st;
        try {
            st = conn.createStatement();
            if ((st.executeUpdate(query)) == 1) {
                JOptionPane.showMessageDialog(rootPane, "Data berhasil " + message);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Data gagal " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(rootPane, "<html><body><p style='width: 250px;'><b>Error:<br></b><br><i>" + e + "</i><br><br>Cari petunjuk di catatan penggunaan tentang error diatas atau kontak admin anda mengenai masalah ini</p></body></html>", "Aduh! ada yang salah..", JOptionPane.ERROR_MESSAGE);

        }
    }

    public boolean regexIdBarang(String statement) {
        if (statement.matches("\\bP[0-9][0-9][0-9][0-9][0-9][0-9][0-9]\\b")) {
            return true;
        } else {
            JOptionPane.showMessageDialog(rootPane, "ID Barang salah");
            return false;
        }
    }

    public boolean regexBilangan(String statement, String namaField) {
        if ((statement.matches("\\d+")) || (statement.matches("^([+-]?\\d*\\.?\\d*)$"))) {
            return true;
        } else {
            JOptionPane.showMessageDialog(rootPane, namaField + " harus angka!");
            return false;
        }

    }
    // <editor-fold defaultstate="collapsed" desc=" IDE Generated Code ">

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelUtama = new javax.swing.JTabbedPane();
        tabStok = new javax.swing.JPanel();
        panelKiri = new javax.swing.JPanel();
        labelInfo = new javax.swing.JLabel();
        textAreaKiri = new javax.swing.JTextArea();
        panelBawah = new javax.swing.JPanel();
        fieldInputId_barang = new javax.swing.JTextField();
        fieldInputMerk = new javax.swing.JTextField();
        fieldInputRagam = new javax.swing.JTextField();
        fieldInputSeri = new javax.swing.JTextField();
        fieldInputQty = new javax.swing.JTextField();
        fieldInputHarga_beli = new javax.swing.JTextField();
        fieldInputTanggal_masuk = new javax.swing.JTextField();
        comboBoxGaransi = new javax.swing.JComboBox<>();
        tombolTambah = new javax.swing.JButton();
        tombolUbah = new javax.swing.JButton();
        tombolAtc = new javax.swing.JButton();
        jLabelBarangIdBarang = new javax.swing.JLabel();
        jLabelBarangJenis = new javax.swing.JLabel();
        jLabelBarangMerk = new javax.swing.JLabel();
        jLabelBarangQty = new javax.swing.JLabel();
        jLabelBarangHargaBeli = new javax.swing.JLabel();
        jLabelBarangSupplier = new javax.swing.JLabel();
        jLabelBarangTanggal = new javax.swing.JLabel();
        jLabelBarangGaransi = new javax.swing.JLabel();
        jLabelBarangRagam = new javax.swing.JLabel();
        jLabelBarangSeri = new javax.swing.JLabel();
        tombolClearField = new javax.swing.JButton();
        tombolHapus = new javax.swing.JButton();
        comboBoxSupplier = new javax.swing.JComboBox<>();
        comboBoxJenis = new javax.swing.JComboBox<>();
        panelAtas = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        comboBoxKolom = new javax.swing.JComboBox<>();
        labelFilter = new javax.swing.JLabel();
        labelKolom = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelTest = new javax.swing.JLabel();
        panelTengah = new javax.swing.JPanel();
        panelScrollTabel = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        tabTrans = new javax.swing.JPanel();
        panelKiriTransaksi = new javax.swing.JPanel();
        labelInfoTransaksi = new javax.swing.JLabel();
        textAreaKiriTransaksi = new javax.swing.JTextArea();
        panelBawahTransaksi = new javax.swing.JPanel();
        fieldIdBarangTransaksi = new javax.swing.JTextField();
        fieldInputQtyTransaksi = new javax.swing.JTextField();
        tombolCheckoutTransaksi = new javax.swing.JButton();
        jLabelTransaksiIdBarang = new javax.swing.JLabel();
        jLabelTransaksiQty = new javax.swing.JLabel();
        buttonTransaksiHapus = new javax.swing.JButton();
        buttonUbahQtyTransaksi = new javax.swing.JButton();
        panelAtasTransaksi = new javax.swing.JPanel();
        labelTransaksiAtas = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        panelTengahTransaksi = new javax.swing.JPanel();
        scrollPaneTransaksi = new javax.swing.JScrollPane();
        tabelTransaksi = new javax.swing.JTable();
        tabSupplier = new javax.swing.JPanel();
        panelKiriSupplier = new javax.swing.JPanel();
        labelInfoSupplier = new javax.swing.JLabel();
        textAreaKiriSupplier = new javax.swing.JTextArea();
        panelBawahSupplier = new javax.swing.JPanel();
        tombolUbahSupplier = new javax.swing.JButton();
        tombolTambahSupplier = new javax.swing.JButton();
        tombolHapusSupplier = new javax.swing.JButton();
        jScrollPaneSupplier = new javax.swing.JScrollPane();
        textAreaCatatan = new javax.swing.JTextArea();
        fieldInputEmailSupplier = new javax.swing.JTextField();
        fieldInputTelponSupplier = new javax.swing.JTextField();
        fieldInputAlamatSupplier = new javax.swing.JTextField();
        fieldInputIDSupplier = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        fieldInputNamaSupplier = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tombolClearSupplier = new javax.swing.JButton();
        panelAtasSupplier = new javax.swing.JPanel();
        searchFieldSupplier = new javax.swing.JTextField();
        comboBoxKolomSupplier = new javax.swing.JComboBox<>();
        labelFilterSupplier = new javax.swing.JLabel();
        labelKolomSupplier = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        panelTengahSupplier = new javax.swing.JPanel();
        scrollPaneSupplier = new javax.swing.JScrollPane();
        tabelSupplier = new javax.swing.JTable();
        tabRetur = new javax.swing.JPanel();
        panelKiriRetur = new javax.swing.JPanel();
        labelInfoRetur = new javax.swing.JLabel();
        textAreaKiriRetur = new javax.swing.JTextArea();
        panelBawahRetur = new javax.swing.JPanel();
        tombolTambahRetur = new javax.swing.JButton();
        tombolHapusRetur = new javax.swing.JButton();
        fieldInputJumlahBarangRetur = new javax.swing.JTextField();
        fieldInputIdRetur = new javax.swing.JTextField();
        fieldInputIdBarangRetur = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tombolClearRetur = new javax.swing.JButton();
        panelAtasRetur = new javax.swing.JPanel();
        searchFieldRetur = new javax.swing.JTextField();
        comboBoxKolomRetur = new javax.swing.JComboBox<>();
        labelFilterRetur = new javax.swing.JLabel();
        labelKolomRetur = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        panelTengahRetur = new javax.swing.JPanel();
        scrollPaneRetur = new javax.swing.JScrollPane();
        tabelRetur = new javax.swing.JTable();
        tabReport = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        dateSampaiTanggal = new com.toedter.calendar.JDateChooser();
        jLabel16 = new javax.swing.JLabel();
        dateDariTanggal = new com.toedter.calendar.JDateChooser();
        buttonCetakAtas = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        buttonCetakBawah = new javax.swing.JButton();
        yearChooser = new com.toedter.calendar.JYearChooser();
        comboBoxBulan = new javax.swing.JComboBox<>();
        buttonKeluar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inventori Tiga Cahaya ["+GUI.namaOperator+"]");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        panelUtama.setForeground(new java.awt.Color(12, 30, 42));
        panelUtama.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tabStok.setBackground(new java.awt.Color(117, 179, 226));

        panelKiri.setBackground(new java.awt.Color(117, 179, 226));
        panelKiri.setPreferredSize(new java.awt.Dimension(154, 250));

        labelInfo.setBackground(new java.awt.Color(12, 30, 42));
        labelInfo.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelInfo.setForeground(new java.awt.Color(12, 30, 42));
        labelInfo.setText("Info");

        textAreaKiri.setEditable(false);
        textAreaKiri.setBackground(new java.awt.Color(117, 179, 226));
        textAreaKiri.setColumns(20);
        textAreaKiri.setForeground(new java.awt.Color(12, 30, 42));
        textAreaKiri.setLineWrap(true);
        textAreaKiri.setRows(5);
        textAreaKiri.setWrapStyleWord(true);
        textAreaKiri.setBorder(null);
        textAreaKiri.setCaretColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelKiriLayout = new javax.swing.GroupLayout(panelKiri);
        panelKiri.setLayout(panelKiriLayout);
        panelKiriLayout.setHorizontalGroup(
            panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textAreaKiri, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelInfo))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKiriLayout.setVerticalGroup(
            panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaKiri, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addGap(43, 43, 43))
        );

        panelBawah.setBackground(new java.awt.Color(25, 104, 178));
        panelBawah.setPreferredSize(new java.awt.Dimension(285, 126));

        fieldInputId_barang.setEditable(false);
        fieldInputId_barang.setForeground(new java.awt.Color(12, 30, 42));
        fieldInputId_barang.setText("ID");
        fieldInputId_barang.setToolTipText("ID Barang");
        fieldInputId_barang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputId_barangFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputId_barangFocusLost(evt);
            }
        });
        fieldInputId_barang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldInputId_barangKeyTyped(evt);
            }
        });

        fieldInputMerk.setEditable(false);
        fieldInputMerk.setForeground(new java.awt.Color(12, 30, 42));
        fieldInputMerk.setText("Merk");
        fieldInputMerk.setToolTipText("Merk barang");
        fieldInputMerk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputMerkFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputMerkFocusLost(evt);
            }
        });
        fieldInputMerk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldInputMerkKeyTyped(evt);
            }
        });

        fieldInputRagam.setEditable(false);
        fieldInputRagam.setForeground(new java.awt.Color(12, 30, 42));
        fieldInputRagam.setText("Ragam");
        fieldInputRagam.setToolTipText("Ragam atau sub-kategori barang");
        fieldInputRagam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputRagamFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputRagamFocusGained(evt);
            }
        });
        fieldInputRagam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldInputRagamKeyTyped(evt);
            }
        });

        fieldInputSeri.setEditable(false);
        fieldInputSeri.setForeground(new java.awt.Color(12, 30, 42));
        fieldInputSeri.setText("Seri");
        fieldInputSeri.setToolTipText("Keterangan lengkap dari ragam yang ada");
        fieldInputSeri.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputSeriFocusLost(evt);
            }
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputSeriFocusGained(evt);
            }
        });
        fieldInputSeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldInputSeriKeyTyped(evt);
            }
        });

        fieldInputQty.setEditable(false);
        fieldInputQty.setForeground(new java.awt.Color(12, 30, 42));
        fieldInputQty.setText("Qty");
        fieldInputQty.setToolTipText("Banyaknya barang");
        fieldInputQty.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputQtyFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputQtyFocusLost(evt);
            }
        });
        fieldInputQty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldInputQtyKeyTyped(evt);
            }
        });

        fieldInputHarga_beli.setEditable(false);
        fieldInputHarga_beli.setForeground(new java.awt.Color(12, 30, 42));
        fieldInputHarga_beli.setText("Hrg Beli");
        fieldInputHarga_beli.setToolTipText("Harga modal awal");
        fieldInputHarga_beli.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputHarga_beliFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputHarga_beliFocusLost(evt);
            }
        });
        fieldInputHarga_beli.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldInputHarga_beliKeyTyped(evt);
            }
        });

        fieldInputTanggal_masuk.setEditable(false);
        fieldInputTanggal_masuk.setForeground(new java.awt.Color(12, 30, 42));
        fieldInputTanggal_masuk.setText("Tanggal Masuk");
        fieldInputTanggal_masuk.setToolTipText("Otomatis sesuai penginputan barang (tidak perlu diatur/ubah)");

        comboBoxGaransi.setForeground(new java.awt.Color(12, 30, 42));
        comboBoxGaransi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "distributor", "toko", "tidak" }));
        comboBoxGaransi.setToolTipText("ketentuan garansi");

        tombolTambah.setForeground(new java.awt.Color(12, 30, 42));
        tombolTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/add_plus.png"))); // NOI18N
        tombolTambah.setText("Tambah");
        tombolTambah.setToolTipText("Tambah barang sesuai dengan masukan yang ada.");
        tombolTambah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tombolTambah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        tombolTambah.setIconTextGap(6);
        tombolTambah.setPreferredSize(new java.awt.Dimension(80, 30));
        tombolTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolTambahActionPerformed(evt);
            }
        });

        tombolUbah.setForeground(new java.awt.Color(12, 30, 42));
        tombolUbah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/edit_pencil.png"))); // NOI18N
        tombolUbah.setText("Ubah");
        tombolUbah.setToolTipText("Edit atau ubah barang sesuai dengan masukan yang ada.");
        tombolUbah.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tombolUbah.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        tombolUbah.setIconTextGap(11);
        tombolUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolUbahActionPerformed(evt);
            }
        });

        tombolAtc.setForeground(new java.awt.Color(12, 30, 42));
        tombolAtc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/cart_25px.png"))); // NOI18N
        tombolAtc.setToolTipText("Masukan barang ke tabel transaksi terakhir");
        tombolAtc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolAtcActionPerformed(evt);
            }
        });

        jLabelBarangIdBarang.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangIdBarang.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBarangIdBarang.setText("ID Barang");

        jLabelBarangJenis.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangJenis.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBarangJenis.setText("Jenis");

        jLabelBarangMerk.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangMerk.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBarangMerk.setText("Merk");

        jLabelBarangQty.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangQty.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBarangQty.setText("Qty");

        jLabelBarangHargaBeli.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangHargaBeli.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBarangHargaBeli.setText("Harga Beli");

        jLabelBarangSupplier.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangSupplier.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBarangSupplier.setText("Supplier");

        jLabelBarangTanggal.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangTanggal.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBarangTanggal.setText("Tanggal Masuk");

        jLabelBarangGaransi.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangGaransi.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBarangGaransi.setText("Garansi");

        jLabelBarangRagam.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangRagam.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBarangRagam.setText("Ragam");

        jLabelBarangSeri.setBackground(new java.awt.Color(255, 255, 255));
        jLabelBarangSeri.setForeground(new java.awt.Color(255, 255, 255));
        jLabelBarangSeri.setText("Seri");

        tombolClearField.setForeground(new java.awt.Color(12, 30, 42));
        tombolClearField.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/delete_x_big.png"))); // NOI18N
        tombolClearField.setText("Cancel");
        tombolClearField.setToolTipText("Hapus text dari field pengisian");
        tombolClearField.setIconTextGap(8);
        tombolClearField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolClearFieldActionPerformed(evt);
            }
        });

        tombolHapus.setForeground(new java.awt.Color(12, 30, 42));
        tombolHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/delete.png"))); // NOI18N
        tombolHapus.setText("Hapus");
        tombolHapus.setToolTipText("Hapus barang sesuai dengan baris yang terpilih.");
        tombolHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        tombolHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        tombolHapus.setIconTextGap(10);
        tombolHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolHapusActionPerformed(evt);
            }
        });

        comboBoxSupplier.setToolTipText("Supllier atau pemasok  barang");

        comboBoxJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelBawahLayout = new javax.swing.GroupLayout(panelBawah);
        panelBawah.setLayout(panelBawahLayout);
        panelBawahLayout.setHorizontalGroup(
            panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBawahLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabelBarangJenis)
                    .addComponent(jLabelBarangIdBarang)
                    .addComponent(fieldInputId_barang, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                    .addComponent(comboBoxJenis, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBawahLayout.createSequentialGroup()
                        .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBarangMerk)
                            .addGroup(panelBawahLayout.createSequentialGroup()
                                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fieldInputQty, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelBarangQty))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(fieldInputHarga_beli)
                                    .addComponent(jLabelBarangHargaBeli, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBarangSupplier)
                            .addComponent(jLabelBarangRagam)
                            .addComponent(comboBoxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBarangSeri)
                            .addGroup(panelBawahLayout.createSequentialGroup()
                                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelBarangTanggal)
                                    .addComponent(fieldInputTanggal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxGaransi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabelBarangGaransi)))))
                    .addGroup(panelBawahLayout.createSequentialGroup()
                        .addComponent(fieldInputMerk, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldInputRagam, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fieldInputSeri, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tombolAtc, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                    .addComponent(tombolClearField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tombolUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tombolTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tombolHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelBawahLayout.setVerticalGroup(
            panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBawahLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBawahLayout.createSequentialGroup()
                        .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelBarangIdBarang)
                            .addComponent(jLabelBarangQty)
                            .addComponent(jLabelBarangHargaBeli)
                            .addComponent(jLabelBarangSupplier)
                            .addComponent(jLabelBarangTanggal)
                            .addComponent(jLabelBarangGaransi))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldInputId_barang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldInputQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldInputHarga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldInputTanggal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxGaransi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelBarangJenis)
                            .addComponent(jLabelBarangMerk)
                            .addComponent(jLabelBarangRagam)
                            .addComponent(jLabelBarangSeri))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldInputMerk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldInputRagam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldInputSeri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBawahLayout.createSequentialGroup()
                        .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(panelBawahLayout.createSequentialGroup()
                                .addComponent(tombolUbah, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tombolTambah, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tombolAtc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tombolClearField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tombolHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        panelAtas.setBackground(new java.awt.Color(25, 104, 178));

        searchField.setForeground(new java.awt.Color(12, 30, 42));
        searchField.setText("search...");
        searchField.setToolTipText("Cari dari tabel dibawah");
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                searchFieldFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFieldFocusLost(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        comboBoxKolom.setForeground(new java.awt.Color(12, 30, 42));
        comboBoxKolom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Coloumn", "ID Barang", "Jenis", "Merk", "Ragam", "Seri", "Qty", "Harga Beli", "Supplier", "Tanggal Masuk", "Garansi" }));
        comboBoxKolom.setToolTipText("Pencarian berdasarkan kolom yang ada");
        comboBoxKolom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxKolomActionPerformed(evt);
            }
        });

        labelFilter.setBackground(new java.awt.Color(255, 255, 255));
        labelFilter.setForeground(new java.awt.Color(255, 255, 255));
        labelFilter.setText("Filter");

        labelKolom.setBackground(new java.awt.Color(255, 255, 255));
        labelKolom.setForeground(new java.awt.Color(255, 255, 255));
        labelKolom.setText("Kolom");

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/tiga_cahaya_logo.png"))); // NOI18N

        javax.swing.GroupLayout panelAtasLayout = new javax.swing.GroupLayout(panelAtas);
        panelAtas.setLayout(panelAtasLayout);
        panelAtasLayout.setHorizontalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(65, 65, 65)
                .addComponent(labelTest, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelFilter))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelKolom)
                    .addComponent(comboBoxKolom, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelAtasLayout.setVerticalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAtasLayout.createSequentialGroup()
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAtasLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAtasLayout.createSequentialGroup()
                                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelFilter)
                                    .addComponent(labelKolom))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxKolom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel10)))
                    .addGroup(panelAtasLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(labelTest, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelTengah.setBackground(new java.awt.Color(73, 138, 192));

        tabel.setForeground(new java.awt.Color(12, 30, 42));
        tabel.setModel(new javax.swing.table.DefaultTableModel(
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
            @Override
            public Class getColumnClass(int column) {
                Class returnValue;

                // Verifying that the column exists (index > 0 && index < number of columns

                    if ((column >= 0) && (column < getColumnCount())) {
                        returnValue = getValueAt(0, column).getClass();
                    } else {

                        // Returns the class for the item in the column

                        returnValue = Object.class;
                    }
                    return returnValue;
                };

            });
            tabel.setSelectionBackground(new java.awt.Color(25, 104, 178));
            tabel.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            tabel.setShowHorizontalLines(false);
            tabel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    tabelMouseClicked(evt);
                }
            });
            panelScrollTabel.setViewportView(tabel);

            javax.swing.GroupLayout panelTengahLayout = new javax.swing.GroupLayout(panelTengah);
            panelTengah.setLayout(panelTengahLayout);
            panelTengahLayout.setHorizontalGroup(
                panelTengahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelScrollTabel, javax.swing.GroupLayout.Alignment.TRAILING)
            );
            panelTengahLayout.setVerticalGroup(
                panelTengahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panelScrollTabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            );

            javax.swing.GroupLayout tabStokLayout = new javax.swing.GroupLayout(tabStok);
            tabStok.setLayout(tabStokLayout);
            tabStokLayout.setHorizontalGroup(
                tabStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabStokLayout.createSequentialGroup()
                    .addComponent(panelKiri, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelTengah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addComponent(panelAtas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelBawah, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
            );
            tabStokLayout.setVerticalGroup(
                tabStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(tabStokLayout.createSequentialGroup()
                    .addComponent(panelAtas, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(tabStokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(panelKiri, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                        .addComponent(panelTengah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(panelBawah, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
            );

            panelUtama.addTab("Stok", tabStok);

            tabTrans.setBackground(new java.awt.Color(117, 179, 226));

            panelKiriTransaksi.setBackground(new java.awt.Color(117, 179, 226));
            panelKiriTransaksi.setForeground(new java.awt.Color(117, 179, 226));
            panelKiriTransaksi.setPreferredSize(new java.awt.Dimension(154, 250));

            labelInfoTransaksi.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
            labelInfoTransaksi.setForeground(new java.awt.Color(12, 30, 42));
            labelInfoTransaksi.setText("Info");

            textAreaKiriTransaksi.setEditable(false);
            textAreaKiriTransaksi.setBackground(new java.awt.Color(117, 179, 226));
            textAreaKiriTransaksi.setColumns(20);
            textAreaKiriTransaksi.setForeground(new java.awt.Color(12, 30, 42));
            textAreaKiriTransaksi.setLineWrap(true);
            textAreaKiriTransaksi.setRows(5);
            textAreaKiriTransaksi.setWrapStyleWord(true);
            textAreaKiriTransaksi.setBorder(null);

            javax.swing.GroupLayout panelKiriTransaksiLayout = new javax.swing.GroupLayout(panelKiriTransaksi);
            panelKiriTransaksi.setLayout(panelKiriTransaksiLayout);
            panelKiriTransaksiLayout.setHorizontalGroup(
                panelKiriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelKiriTransaksiLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelKiriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(textAreaKiriTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(labelInfoTransaksi))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
            panelKiriTransaksiLayout.setVerticalGroup(
                panelKiriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelKiriTransaksiLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelInfoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textAreaKiriTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                    .addGap(43, 43, 43))
            );

            panelBawahTransaksi.setBackground(new java.awt.Color(25, 104, 178));
            panelBawahTransaksi.setPreferredSize(new java.awt.Dimension(285, 126));

            fieldIdBarangTransaksi.setForeground(new java.awt.Color(12, 30, 42));
            fieldIdBarangTransaksi.setText("ID Barang");
            fieldIdBarangTransaksi.setToolTipText("ID Barang, tidak perlu diubah.");

            fieldInputQtyTransaksi.setForeground(new java.awt.Color(12, 30, 42));
            fieldInputQtyTransaksi.setText("Qty");
            fieldInputQtyTransaksi.setToolTipText("banyaknya barang");
            fieldInputQtyTransaksi.addFocusListener(new java.awt.event.FocusAdapter() {
                public void focusGained(java.awt.event.FocusEvent evt) {
                    fieldInputQtyTransaksiFocusGained(evt);
                }
            });

            tombolCheckoutTransaksi.setForeground(new java.awt.Color(12, 30, 42));
            tombolCheckoutTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/box_check_25px.png"))); // NOI18N
            tombolCheckoutTransaksi.setToolTipText("Checkout barang, otomatis mengurangi barang stok");
            tombolCheckoutTransaksi.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    tombolCheckoutTransaksiActionPerformed(evt);
                }
            });

            jLabelTransaksiIdBarang.setForeground(new java.awt.Color(255, 255, 255));
            jLabelTransaksiIdBarang.setText("ID Barang");

            jLabelTransaksiQty.setForeground(new java.awt.Color(255, 255, 255));
            jLabelTransaksiQty.setText("Quantity");

            buttonTransaksiHapus.setForeground(new java.awt.Color(12, 30, 42));
            buttonTransaksiHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/delete.png"))); // NOI18N
            buttonTransaksiHapus.setText("Hapus");
            buttonTransaksiHapus.setToolTipText("Hapus barang transaksi");
            buttonTransaksiHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            buttonTransaksiHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            buttonTransaksiHapus.setIconTextGap(10);
            buttonTransaksiHapus.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonTransaksiHapusActionPerformed(evt);
                }
            });

            buttonUbahQtyTransaksi.setForeground(new java.awt.Color(12, 30, 42));
            buttonUbahQtyTransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/edit_pencil.png"))); // NOI18N
            buttonUbahQtyTransaksi.setText("Ubah");
            buttonUbahQtyTransaksi.setToolTipText("ubah banyak barang transaksi");
            buttonUbahQtyTransaksi.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
            buttonUbahQtyTransaksi.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            buttonUbahQtyTransaksi.setIconTextGap(11);
            buttonUbahQtyTransaksi.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    buttonUbahQtyTransaksiActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout panelBawahTransaksiLayout = new javax.swing.GroupLayout(panelBawahTransaksi);
            panelBawahTransaksi.setLayout(panelBawahTransaksiLayout);
            panelBawahTransaksiLayout.setHorizontalGroup(
                panelBawahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBawahTransaksiLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBawahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBawahTransaksiLayout.createSequentialGroup()
                            .addGroup(panelBawahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabelTransaksiQty, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabelTransaksiIdBarang, javax.swing.GroupLayout.Alignment.TRAILING))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelBawahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(fieldInputQtyTransaksi)
                                .addComponent(fieldIdBarangTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelBawahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(buttonTransaksiHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(buttonUbahQtyTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(tombolCheckoutTransaksi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
            );
            panelBawahTransaksiLayout.setVerticalGroup(
                panelBawahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panelBawahTransaksiLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelBawahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldIdBarangTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelTransaksiIdBarang)
                        .addComponent(buttonTransaksiHapus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGap(3, 3, 3)
                    .addGroup(panelBawahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fieldInputQtyTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabelTransaksiQty)
                        .addComponent(buttonUbahQtyTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(tombolCheckoutTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addContainerGap())
            );

            panelAtasTransaksi.setBackground(new java.awt.Color(25, 104, 178));

            labelTransaksiAtas.setForeground(new java.awt.Color(255, 255, 255));
            labelTransaksiAtas.setText("Invoice:");
            labelTransaksiAtas.setToolTipText("Nomer invoice transaksi saat ini");

            jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/tiga_cahaya_logo.png"))); // NOI18N

            javax.swing.GroupLayout panelAtasTransaksiLayout = new javax.swing.GroupLayout(panelAtasTransaksi);
            panelAtasTransaksi.setLayout(panelAtasTransaksiLayout);
            panelAtasTransaksiLayout.setHorizontalGroup(
                panelAtasTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasTransaksiLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel11)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTransaksiAtas)
                    .addContainerGap())
            );
            panelAtasTransaksiLayout.setVerticalGroup(
                panelAtasTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasTransaksiLayout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelTransaksiAtas)
                    .addGap(14, 14, 14))
                .addGroup(panelAtasTransaksiLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel11)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );

            panelTengahTransaksi.setBackground(new java.awt.Color(73, 138, 192));

            tabelTransaksi.setForeground(new java.awt.Color(12, 30, 42));
            tabelTransaksi.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                    "ID Barang", "Nama Barang", "Quantity"
                }
            ) {
                boolean[] canEdit = new boolean [] {
                    false, false, false
                };

                public boolean isCellEditable(int rowIndex, int columnIndex) {
                    return canEdit [columnIndex];
                }
                @Override
                public Class getColumnClass(int column) {
                    Class returnValue;

                    // Verifying that the column exists (index > 0 && index < number of columns

                        if ((column >= 0) && (column < getColumnCount())) {
                            returnValue = getValueAt(0, column).getClass();
                        } else {

                            // Returns the class for the item in the column

                            returnValue = Object.class;
                        }
                        return returnValue;
                    };
                });
                tabelTransaksi.setSelectionBackground(new java.awt.Color(25, 104, 178));
                tabelTransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
                    public void mouseClicked(java.awt.event.MouseEvent evt) {
                        tabelTransaksiMouseClicked(evt);
                    }
                });
                scrollPaneTransaksi.setViewportView(tabelTransaksi);

                javax.swing.GroupLayout panelTengahTransaksiLayout = new javax.swing.GroupLayout(panelTengahTransaksi);
                panelTengahTransaksi.setLayout(panelTengahTransaksiLayout);
                panelTengahTransaksiLayout.setHorizontalGroup(
                    panelTengahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                );
                panelTengahTransaksiLayout.setVerticalGroup(
                    panelTengahTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPaneTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                );

                javax.swing.GroupLayout tabTransLayout = new javax.swing.GroupLayout(tabTrans);
                tabTrans.setLayout(tabTransLayout);
                tabTransLayout.setHorizontalGroup(
                    tabTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabTransLayout.createSequentialGroup()
                        .addComponent(panelKiriTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelTengahTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelAtasTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBawahTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                );
                tabTransLayout.setVerticalGroup(
                    tabTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tabTransLayout.createSequentialGroup()
                        .addComponent(panelAtasTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(tabTransLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelKiriTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                            .addComponent(panelTengahTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(panelBawahTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                panelUtama.addTab("Transaksi", tabTrans);

                tabSupplier.setBackground(new java.awt.Color(117, 179, 226));

                panelKiriSupplier.setBackground(new java.awt.Color(117, 179, 226));
                panelKiriSupplier.setPreferredSize(new java.awt.Dimension(154, 250));

                labelInfoSupplier.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                labelInfoSupplier.setForeground(new java.awt.Color(12, 30, 42));
                labelInfoSupplier.setText("Info");

                textAreaKiriSupplier.setEditable(false);
                textAreaKiriSupplier.setBackground(new java.awt.Color(117, 179, 226));
                textAreaKiriSupplier.setColumns(20);
                textAreaKiriSupplier.setForeground(new java.awt.Color(12, 30, 42));
                textAreaKiriSupplier.setLineWrap(true);
                textAreaKiriSupplier.setRows(5);
                textAreaKiriSupplier.setWrapStyleWord(true);
                textAreaKiriSupplier.setBorder(null);

                javax.swing.GroupLayout panelKiriSupplierLayout = new javax.swing.GroupLayout(panelKiriSupplier);
                panelKiriSupplier.setLayout(panelKiriSupplierLayout);
                panelKiriSupplierLayout.setHorizontalGroup(
                    panelKiriSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKiriSupplierLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelKiriSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textAreaKiriSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelInfoSupplier))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );
                panelKiriSupplierLayout.setVerticalGroup(
                    panelKiriSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKiriSupplierLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(labelInfoSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textAreaKiriSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                        .addGap(43, 43, 43))
                );

                panelBawahSupplier.setBackground(new java.awt.Color(25, 104, 178));
                panelBawahSupplier.setPreferredSize(new java.awt.Dimension(285, 126));

                tombolUbahSupplier.setForeground(new java.awt.Color(12, 30, 42));
                tombolUbahSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/edit_pencil.png"))); // NOI18N
                tombolUbahSupplier.setText("Ubah");
                tombolUbahSupplier.setToolTipText("Edit atau ubah barang sesuai dengan masukan yang ada.");
                tombolUbahSupplier.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                tombolUbahSupplier.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                tombolUbahSupplier.setIconTextGap(11);
                tombolUbahSupplier.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        tombolUbahSupplierActionPerformed(evt);
                    }
                });

                tombolTambahSupplier.setForeground(new java.awt.Color(12, 30, 42));
                tombolTambahSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/add_plus.png"))); // NOI18N
                tombolTambahSupplier.setText("Tambah");
                tombolTambahSupplier.setToolTipText("Tambah barang sesuai dengan masukan yang ada.");
                tombolTambahSupplier.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                tombolTambahSupplier.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                tombolTambahSupplier.setIconTextGap(6);
                tombolTambahSupplier.setPreferredSize(new java.awt.Dimension(80, 30));
                tombolTambahSupplier.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        tombolTambahSupplierActionPerformed(evt);
                    }
                });

                tombolHapusSupplier.setForeground(new java.awt.Color(12, 30, 42));
                tombolHapusSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/delete.png"))); // NOI18N
                tombolHapusSupplier.setText("Hapus");
                tombolHapusSupplier.setToolTipText("Hapus barang sesuai dengan baris yang terpilih.");
                tombolHapusSupplier.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                tombolHapusSupplier.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                tombolHapusSupplier.setIconTextGap(10);
                tombolHapusSupplier.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        tombolHapusSupplierActionPerformed(evt);
                    }
                });

                textAreaCatatan.setEditable(false);
                textAreaCatatan.setColumns(20);
                textAreaCatatan.setForeground(new java.awt.Color(12, 30, 42));
                textAreaCatatan.setLineWrap(true);
                textAreaCatatan.setRows(5);
                textAreaCatatan.setToolTipText("Catatan tambahan untuk supplier");
                textAreaCatatan.setWrapStyleWord(true);
                jScrollPaneSupplier.setViewportView(textAreaCatatan);

                fieldInputEmailSupplier.setEditable(false);
                fieldInputEmailSupplier.setForeground(new java.awt.Color(12, 30, 42));
                fieldInputEmailSupplier.setText("Email");
                fieldInputEmailSupplier.setToolTipText("E-mail supplier. Harus diisi sesuai dengan format email");
                fieldInputEmailSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        fieldInputEmailSupplierFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        fieldInputEmailSupplierFocusLost(evt);
                    }
                });
                fieldInputEmailSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        fieldInputEmailSupplierKeyTyped(evt);
                    }
                });

                fieldInputTelponSupplier.setEditable(false);
                fieldInputTelponSupplier.setForeground(new java.awt.Color(12, 30, 42));
                fieldInputTelponSupplier.setText("Telpon");
                fieldInputTelponSupplier.setToolTipText("Telpon/hp supplier. hanya bisa di isi dengan angka");
                fieldInputTelponSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        fieldInputTelponSupplierFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        fieldInputTelponSupplierFocusLost(evt);
                    }
                });
                fieldInputTelponSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        fieldInputTelponSupplierKeyTyped(evt);
                    }
                });

                fieldInputAlamatSupplier.setEditable(false);
                fieldInputAlamatSupplier.setForeground(new java.awt.Color(12, 30, 42));
                fieldInputAlamatSupplier.setText("Alamat");
                fieldInputAlamatSupplier.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
                fieldInputAlamatSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        fieldInputAlamatSupplierFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        fieldInputAlamatSupplierFocusLost(evt);
                    }
                });
                fieldInputAlamatSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        fieldInputAlamatSupplierKeyTyped(evt);
                    }
                });

                fieldInputIDSupplier.setEditable(false);
                fieldInputIDSupplier.setForeground(new java.awt.Color(12, 30, 42));
                fieldInputIDSupplier.setText("ID");
                fieldInputIDSupplier.setToolTipText("ID Supplier. tidak perlu diubah");
                fieldInputIDSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        fieldInputIDSupplierFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        fieldInputIDSupplierFocusLost(evt);
                    }
                });

                jLabel5.setForeground(new java.awt.Color(255, 255, 255));
                jLabel5.setText("Alamat");

                jLabel6.setForeground(new java.awt.Color(255, 255, 255));
                jLabel6.setText("Telpon");

                jLabel7.setForeground(new java.awt.Color(255, 255, 255));
                jLabel7.setText("E-Mail");

                jLabel8.setForeground(new java.awt.Color(255, 255, 255));
                jLabel8.setText("ID Supplier");

                jLabel9.setForeground(new java.awt.Color(255, 255, 255));
                jLabel9.setText("Catatan");

                fieldInputNamaSupplier.setEditable(false);
                fieldInputNamaSupplier.setForeground(new java.awt.Color(12, 30, 42));
                fieldInputNamaSupplier.setText("Nama");
                fieldInputNamaSupplier.setToolTipText("Nama supplier");
                fieldInputNamaSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        fieldInputNamaSupplierFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        fieldInputNamaSupplierFocusLost(evt);
                    }
                });
                fieldInputNamaSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyTyped(java.awt.event.KeyEvent evt) {
                        fieldInputNamaSupplierKeyTyped(evt);
                    }
                });

                jLabel4.setForeground(new java.awt.Color(255, 255, 255));
                jLabel4.setText("Nama");

                tombolClearSupplier.setForeground(new java.awt.Color(12, 30, 42));
                tombolClearSupplier.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/delete_x_big.png"))); // NOI18N
                tombolClearSupplier.setText("Clear");
                tombolClearSupplier.setToolTipText("Hapus semua text field");
                tombolClearSupplier.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                tombolClearSupplier.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                tombolClearSupplier.setIconTextGap(9);
                tombolClearSupplier.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        tombolClearSupplierActionPerformed(evt);
                    }
                });

                javax.swing.GroupLayout panelBawahSupplierLayout = new javax.swing.GroupLayout(panelBawahSupplier);
                panelBawahSupplier.setLayout(panelBawahSupplierLayout);
                panelBawahSupplierLayout.setHorizontalGroup(
                    panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBawahSupplierLayout.createSequentialGroup()
                        .addContainerGap(34, Short.MAX_VALUE)
                        .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBawahSupplierLayout.createSequentialGroup()
                                .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBawahSupplierLayout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fieldInputNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBawahSupplierLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fieldInputTelponSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelBawahSupplierLayout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(fieldInputAlamatSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBawahSupplierLayout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldInputIDSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fieldInputEmailSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPaneSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tombolUbahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tombolTambahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tombolClearSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tombolHapusSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                );
                panelBawahSupplierLayout.setVerticalGroup(
                    panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBawahSupplierLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPaneSupplier, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBawahSupplierLayout.createSequentialGroup()
                                .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(tombolUbahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9)
                                    .addComponent(fieldInputEmailSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(fieldInputIDSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(tombolClearSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tombolTambahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tombolHapusSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelBawahSupplierLayout.createSequentialGroup()
                                        .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(fieldInputAlamatSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel5)
                                            .addComponent(fieldInputNamaSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(fieldInputTelponSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))))
                                .addGap(10, 10, 10)))
                        .addContainerGap(18, Short.MAX_VALUE))
                );

                panelAtasSupplier.setBackground(new java.awt.Color(25, 104, 178));

                searchFieldSupplier.setForeground(new java.awt.Color(12, 30, 42));
                searchFieldSupplier.setText("search...");
                searchFieldSupplier.setToolTipText("Cari dari tabel dibawah");
                searchFieldSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
                    public void focusGained(java.awt.event.FocusEvent evt) {
                        searchFieldSupplierFocusGained(evt);
                    }
                    public void focusLost(java.awt.event.FocusEvent evt) {
                        searchFieldSupplierFocusLost(evt);
                    }
                });
                searchFieldSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
                    public void keyReleased(java.awt.event.KeyEvent evt) {
                        searchFieldSupplierKeyReleased(evt);
                    }
                });

                comboBoxKolomSupplier.setForeground(new java.awt.Color(12, 30, 42));
                comboBoxKolomSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Coloumn", "ID Supplier", "Nama", "Alamat", "Telpon", "Email", "Catatan" }));
                comboBoxKolomSupplier.setToolTipText("Pencarian berdasarkan kolom yang ada");
                comboBoxKolomSupplier.addActionListener(new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        comboBoxKolomSupplierActionPerformed(evt);
                    }
                });

                labelFilterSupplier.setForeground(new java.awt.Color(255, 255, 255));
                labelFilterSupplier.setText("Filter");

                labelKolomSupplier.setBackground(new java.awt.Color(255, 255, 255));
                labelKolomSupplier.setForeground(new java.awt.Color(255, 255, 255));
                labelKolomSupplier.setText("Kolom");

                jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/tiga_cahaya_logo.png"))); // NOI18N

                javax.swing.GroupLayout panelAtasSupplierLayout = new javax.swing.GroupLayout(panelAtasSupplier);
                panelAtasSupplier.setLayout(panelAtasSupplierLayout);
                panelAtasSupplierLayout.setHorizontalGroup(
                    panelAtasSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasSupplierLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(panelAtasSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelFilterSupplier)
                            .addComponent(searchFieldSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAtasSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxKolomSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelKolomSupplier))
                        .addContainerGap())
                );
                panelAtasSupplierLayout.setVerticalGroup(
                    panelAtasSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAtasSupplierLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelAtasSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAtasSupplierLayout.createSequentialGroup()
                                .addGroup(panelAtasSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelFilterSupplier)
                                    .addComponent(labelKolomSupplier))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelAtasSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(searchFieldSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxKolomSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel12))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                );

                panelTengahSupplier.setBackground(new java.awt.Color(73, 138, 192));

                scrollPaneSupplier.setPreferredSize(new java.awt.Dimension(453, 60));

                tabelSupplier.setForeground(new java.awt.Color(12, 30, 42));
                tabelSupplier.setModel(new javax.swing.table.DefaultTableModel(
                    new Object [][] {

                    },
                    new String [] {
                        "ID Supplier", "Nama", "Alamat", "Telp", "E-Mail", "Catatan"
                    }
                ) {
                    boolean[] canEdit = new boolean [] {
                        false, false, false, false, true, true
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }@Override
                    public Class getColumnClass(int column) {
                        Class returnValue;

                        // Verifying that the column exists (index > 0 && index < number of columns

                            if ((column >= 0) && (column < getColumnCount())) {
                                returnValue = getValueAt(0, column).getClass();
                            } else {

                                // Returns the class for the item in the column

                                returnValue = Object.class;
                            }
                            return returnValue;
                        };
                    });
                    tabelSupplier.setSelectionBackground(new java.awt.Color(25, 104, 178));
                    tabelSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                            tabelSupplierMouseClicked(evt);
                        }
                    });
                    scrollPaneSupplier.setViewportView(tabelSupplier);

                    javax.swing.GroupLayout panelTengahSupplierLayout = new javax.swing.GroupLayout(panelTengahSupplier);
                    panelTengahSupplier.setLayout(panelTengahSupplierLayout);
                    panelTengahSupplierLayout.setHorizontalGroup(
                        panelTengahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPaneSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );
                    panelTengahSupplierLayout.setVerticalGroup(
                        panelTengahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPaneSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    );

                    javax.swing.GroupLayout tabSupplierLayout = new javax.swing.GroupLayout(tabSupplier);
                    tabSupplier.setLayout(tabSupplierLayout);
                    tabSupplierLayout.setHorizontalGroup(
                        tabSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tabSupplierLayout.createSequentialGroup()
                            .addComponent(panelKiriSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelTengahSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(panelAtasSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelBawahSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                    );
                    tabSupplierLayout.setVerticalGroup(
                        tabSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(tabSupplierLayout.createSequentialGroup()
                            .addComponent(panelAtasSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(tabSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(panelKiriSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                .addComponent(panelTengahSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(panelBawahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    );

                    panelUtama.addTab("Supplier", tabSupplier);

                    tabRetur.setBackground(new java.awt.Color(117, 179, 226));

                    panelKiriRetur.setBackground(new java.awt.Color(117, 179, 226));
                    panelKiriRetur.setPreferredSize(new java.awt.Dimension(154, 250));

                    labelInfoRetur.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
                    labelInfoRetur.setForeground(new java.awt.Color(12, 30, 42));
                    labelInfoRetur.setText("Info");

                    textAreaKiriRetur.setEditable(false);
                    textAreaKiriRetur.setBackground(new java.awt.Color(117, 179, 226));
                    textAreaKiriRetur.setColumns(20);
                    textAreaKiriRetur.setForeground(new java.awt.Color(12, 30, 42));
                    textAreaKiriRetur.setLineWrap(true);
                    textAreaKiriRetur.setRows(5);
                    textAreaKiriRetur.setWrapStyleWord(true);
                    textAreaKiriRetur.setBorder(null);

                    javax.swing.GroupLayout panelKiriReturLayout = new javax.swing.GroupLayout(panelKiriRetur);
                    panelKiriRetur.setLayout(panelKiriReturLayout);
                    panelKiriReturLayout.setHorizontalGroup(
                        panelKiriReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelKiriReturLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panelKiriReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textAreaKiriRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelInfoRetur))
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );
                    panelKiriReturLayout.setVerticalGroup(
                        panelKiriReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelKiriReturLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(labelInfoRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(textAreaKiriRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                            .addGap(43, 43, 43))
                    );

                    panelBawahRetur.setBackground(new java.awt.Color(25, 104, 178));
                    panelBawahRetur.setForeground(new java.awt.Color(255, 255, 255));
                    panelBawahRetur.setPreferredSize(new java.awt.Dimension(285, 126));

                    tombolTambahRetur.setForeground(new java.awt.Color(12, 30, 42));
                    tombolTambahRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/add_plus.png"))); // NOI18N
                    tombolTambahRetur.setText("Tambah");
                    tombolTambahRetur.setToolTipText("Tambah barang sesuai dengan masukan yang ada.");
                    tombolTambahRetur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    tombolTambahRetur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    tombolTambahRetur.setIconTextGap(6);
                    tombolTambahRetur.setPreferredSize(new java.awt.Dimension(80, 30));
                    tombolTambahRetur.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            tombolTambahReturActionPerformed(evt);
                        }
                    });

                    tombolHapusRetur.setForeground(new java.awt.Color(12, 30, 42));
                    tombolHapusRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/delete.png"))); // NOI18N
                    tombolHapusRetur.setText("Hapus");
                    tombolHapusRetur.setToolTipText("Hapus barang sesuai dengan baris yang terpilih.");
                    tombolHapusRetur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    tombolHapusRetur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    tombolHapusRetur.setIconTextGap(10);
                    tombolHapusRetur.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            tombolHapusReturActionPerformed(evt);
                        }
                    });

                    fieldInputJumlahBarangRetur.setForeground(new java.awt.Color(12, 30, 42));
                    fieldInputJumlahBarangRetur.setText("Jumlah");
                    fieldInputJumlahBarangRetur.setToolTipText("banyaknya barang retur");
                    fieldInputJumlahBarangRetur.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusLost(java.awt.event.FocusEvent evt) {
                            fieldInputJumlahBarangReturFocusLost(evt);
                        }
                        public void focusGained(java.awt.event.FocusEvent evt) {
                            fieldInputJumlahBarangReturFocusGained(evt);
                        }
                    });

                    fieldInputIdRetur.setEditable(false);
                    fieldInputIdRetur.setForeground(new java.awt.Color(12, 30, 42));
                    fieldInputIdRetur.setText("ID Retur");
                    fieldInputIdRetur.setToolTipText("ID tidak perlu diubah");
                    fieldInputIdRetur.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusLost(java.awt.event.FocusEvent evt) {
                            fieldInputIdReturFocusLost(evt);
                        }
                        public void focusGained(java.awt.event.FocusEvent evt) {
                            fieldInputIdReturFocusGained(evt);
                        }
                    });

                    fieldInputIdBarangRetur.setForeground(new java.awt.Color(12, 30, 42));
                    fieldInputIdBarangRetur.setText("ID");
                    fieldInputIdBarangRetur.setToolTipText("ID Barang, Masukan dari tabel barang yang tersedia");
                    fieldInputIdBarangRetur.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusGained(java.awt.event.FocusEvent evt) {
                            fieldInputIdBarangReturFocusGained(evt);
                        }
                        public void focusLost(java.awt.event.FocusEvent evt) {
                            fieldInputIdBarangReturFocusLost(evt);
                        }
                    });

                    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel1.setText("ID Retur");

                    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel2.setText("ID Barang");

                    jLabel3.setForeground(new java.awt.Color(255, 255, 255));
                    jLabel3.setText("Quantity");

                    tombolClearRetur.setForeground(new java.awt.Color(12, 30, 42));
                    tombolClearRetur.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/delete_x_big.png"))); // NOI18N
                    tombolClearRetur.setText("Clear");
                    tombolClearRetur.setToolTipText("Hapus semua text field");
                    tombolClearRetur.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
                    tombolClearRetur.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
                    tombolClearRetur.setIconTextGap(9);
                    tombolClearRetur.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            tombolClearReturActionPerformed(evt);
                        }
                    });

                    javax.swing.GroupLayout panelBawahReturLayout = new javax.swing.GroupLayout(panelBawahRetur);
                    panelBawahRetur.setLayout(panelBawahReturLayout);
                    panelBawahReturLayout.setHorizontalGroup(
                        panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBawahReturLayout.createSequentialGroup()
                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel1))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(fieldInputIdRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fieldInputJumlahBarangRetur, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(fieldInputIdBarangRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tombolTambahRetur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(tombolHapusRetur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(tombolClearRetur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap())
                    );
                    panelBawahReturLayout.setVerticalGroup(
                        panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelBawahReturLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelBawahReturLayout.createSequentialGroup()
                                    .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fieldInputIdRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(panelBawahReturLayout.createSequentialGroup()
                                            .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(tombolHapusRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(3, 3, 3))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBawahReturLayout.createSequentialGroup()
                                            .addComponent(fieldInputIdBarangRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                    .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fieldInputJumlahBarangRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(tombolClearRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addComponent(tombolTambahRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap(25, Short.MAX_VALUE))
                    );

                    panelAtasRetur.setBackground(new java.awt.Color(25, 104, 178));

                    searchFieldRetur.setForeground(new java.awt.Color(12, 30, 42));
                    searchFieldRetur.setText("search...");
                    searchFieldRetur.setToolTipText("Cari dari tabel dibawah");
                    searchFieldRetur.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusGained(java.awt.event.FocusEvent evt) {
                            searchFieldReturFocusGained(evt);
                        }
                        public void focusLost(java.awt.event.FocusEvent evt) {
                            searchFieldReturFocusLost(evt);
                        }
                    });
                    searchFieldRetur.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                            searchFieldReturKeyReleased(evt);
                        }
                    });

                    comboBoxKolomRetur.setForeground(new java.awt.Color(12, 30, 42));
                    comboBoxKolomRetur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Coloumn", "ID Retur", "ID Barang", "Nama", "Jumlah" }));
                    comboBoxKolomRetur.setToolTipText("Pencarian berdasarkan kolom yang ada");
                    comboBoxKolomRetur.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            comboBoxKolomReturActionPerformed(evt);
                        }
                    });

                    labelFilterRetur.setForeground(new java.awt.Color(255, 255, 255));
                    labelFilterRetur.setText("Filter");

                    labelKolomRetur.setForeground(new java.awt.Color(255, 255, 255));
                    labelKolomRetur.setText("Kolom");

                    jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/tiga_cahaya_logo.png"))); // NOI18N

                    javax.swing.GroupLayout panelAtasReturLayout = new javax.swing.GroupLayout(panelAtasRetur);
                    panelAtasRetur.setLayout(panelAtasReturLayout);
                    panelAtasReturLayout.setHorizontalGroup(
                        panelAtasReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasReturLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel13)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(panelAtasReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelFilterRetur)
                                .addComponent(searchFieldRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelAtasReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(comboBoxKolomRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(labelKolomRetur))
                            .addContainerGap())
                    );
                    panelAtasReturLayout.setVerticalGroup(
                        panelAtasReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelAtasReturLayout.createSequentialGroup()
                            .addContainerGap()
                            .addGroup(panelAtasReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(panelAtasReturLayout.createSequentialGroup()
                                    .addComponent(jLabel13)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addGroup(panelAtasReturLayout.createSequentialGroup()
                                    .addGroup(panelAtasReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelFilterRetur)
                                        .addComponent(labelKolomRetur))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(panelAtasReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(searchFieldRetur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboBoxKolomRetur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addContainerGap())
                    );

                    panelTengahRetur.setBackground(new java.awt.Color(73, 138, 192));

                    tabelRetur.setForeground(new java.awt.Color(12, 30, 42));
                    tabelRetur.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                            "ID Retur", "ID Barang", "Nama", "Jumlah"
                        }
                    ) {
                        boolean[] canEdit = new boolean [] {
                            false, false, false, false
                        };

                        public boolean isCellEditable(int rowIndex, int columnIndex) {
                            return canEdit [columnIndex];
                        }@Override
                        public Class getColumnClass(int column) {
                            Class returnValue;

                            // Verifying that the column exists (index > 0 && index < number of columns

                                if ((column >= 0) && (column < getColumnCount())) {
                                    returnValue = getValueAt(0, column).getClass();
                                } else {

                                    // Returns the class for the item in the column

                                    returnValue = Object.class;
                                }
                                return returnValue;
                            };
                        });
                        tabelRetur.setSelectionBackground(new java.awt.Color(25, 104, 178));
                        tabelRetur.addMouseListener(new java.awt.event.MouseAdapter() {
                            public void mouseClicked(java.awt.event.MouseEvent evt) {
                                tabelReturMouseClicked(evt);
                            }
                        });
                        scrollPaneRetur.setViewportView(tabelRetur);
                        if (tabelRetur.getColumnModel().getColumnCount() > 0) {
                            tabelRetur.getColumnModel().getColumn(0).setHeaderValue("ID Retur");
                            tabelRetur.getColumnModel().getColumn(1).setHeaderValue("ID Barang");
                            tabelRetur.getColumnModel().getColumn(2).setHeaderValue("Nama");
                            tabelRetur.getColumnModel().getColumn(3).setHeaderValue("Jumlah");
                        }

                        javax.swing.GroupLayout panelTengahReturLayout = new javax.swing.GroupLayout(panelTengahRetur);
                        panelTengahRetur.setLayout(panelTengahReturLayout);
                        panelTengahReturLayout.setHorizontalGroup(
                            panelTengahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPaneRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                        );
                        panelTengahReturLayout.setVerticalGroup(
                            panelTengahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollPaneRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        );

                        javax.swing.GroupLayout tabReturLayout = new javax.swing.GroupLayout(tabRetur);
                        tabRetur.setLayout(tabReturLayout);
                        tabReturLayout.setHorizontalGroup(
                            tabReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabReturLayout.createSequentialGroup()
                                .addComponent(panelKiriRetur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelTengahRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(panelAtasRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBawahRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
                        );
                        tabReturLayout.setVerticalGroup(
                            tabReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabReturLayout.createSequentialGroup()
                                .addComponent(panelAtasRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(tabReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(panelKiriRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE)
                                    .addComponent(panelTengahRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelBawahRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        );

                        panelUtama.addTab("Retur", tabRetur);

                        jLabel14.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
                        jLabel14.setText("Laporan Transaksi");

                        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Per Periode"));
                        jPanel1.setPreferredSize(new java.awt.Dimension(405, 142));

                        jLabel15.setText("Dari Tanggal");

                        jLabel16.setText("Sampai Tanggal");

                        buttonCetakAtas.setText("Cetak");
                        buttonCetakAtas.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                buttonCetakAtasActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
                        jPanel1.setLayout(jPanel1Layout);
                        jPanel1Layout.setHorizontalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(dateDariTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(dateSampaiTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonCetakAtas))
                                .addContainerGap(23, Short.MAX_VALUE))
                        );
                        jPanel1Layout.setVerticalGroup(
                            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateDariTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel16)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateSampaiTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonCetakAtas)
                                .addContainerGap(47, Short.MAX_VALUE))
                        );

                        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Per Bulan"));

                        jLabel17.setText("Bulan");

                        jLabel18.setText("Tahun");

                        buttonCetakBawah.setText("Cetak");
                        buttonCetakBawah.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                buttonCetakBawahActionPerformed(evt);
                            }
                        });

                        comboBoxBulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

                        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
                        jPanel2.setLayout(jPanel2Layout);
                        jPanel2Layout.setHorizontalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel17)
                                    .addComponent(comboBoxBulan, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel18)
                                    .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonCetakBawah)))
                        );
                        jPanel2Layout.setVerticalGroup(
                            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxBulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yearChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonCetakBawah)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        );

                        buttonKeluar.setText("Keluar");
                        buttonKeluar.addActionListener(new java.awt.event.ActionListener() {
                            public void actionPerformed(java.awt.event.ActionEvent evt) {
                                buttonKeluarActionPerformed(evt);
                            }
                        });

                        javax.swing.GroupLayout tabReportLayout = new javax.swing.GroupLayout(tabReport);
                        tabReport.setLayout(tabReportLayout);
                        tabReportLayout.setHorizontalGroup(
                            tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabReportLayout.createSequentialGroup()
                                .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(tabReportLayout.createSequentialGroup()
                                        .addGap(379, 379, 379)
                                        .addComponent(jLabel14))
                                    .addGroup(tabReportLayout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(buttonKeluar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addGroup(tabReportLayout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(122, 122, 122)))
                                .addContainerGap(423, Short.MAX_VALUE))
                        );
                        tabReportLayout.setVerticalGroup(
                            tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(tabReportLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel14)
                                .addGap(18, 18, 18)
                                .addGroup(tabReportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonKeluar)
                                .addContainerGap(182, Short.MAX_VALUE))
                        );

                        panelUtama.addTab("Report", tabReport);

                        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                        getContentPane().setLayout(layout);
                        layout.setHorizontalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelUtama)
                        );
                        layout.setVerticalGroup(
                            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelUtama)
                        );

                        pack();
                    }// </editor-fold>//GEN-END:initComponents
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc=" Action Event ">
    // action event methods.
    private void tabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelMouseClicked
        // Display selected row in text fields
        String comb = "Tidak";
        int i = tabel.getSelectedRow();
        TableModel model = tabel.getModel();
        //Get index from sorted table
        i = tabel.convertRowIndexToModel(i);
        //get values of the cells
        fieldInputId_barang.setText(model.getValueAt(i, 0).toString());
        comboBoxJenis.setSelectedItem(model.getValueAt(i, 1).toString());
        fieldInputMerk.setText(model.getValueAt(i, 2).toString());
        fieldInputMerk.setCaretPosition(0);
        fieldInputRagam.setText(model.getValueAt(i, 3).toString());
        fieldInputRagam.setCaretPosition(0);
        fieldInputSeri.setText(model.getValueAt(i, 4).toString());
        fieldInputSeri.setCaretPosition(0);
        fieldInputQty.setText(model.getValueAt(i, 5).toString());
        fieldInputHarga_beli.setText(model.getValueAt(i, 6).toString());
        comboBoxSupplier.setSelectedItem(model.getValueAt(i, 7).toString());
        fieldInputTanggal_masuk.setText(model.getValueAt(i, 8).toString());
        fieldInputTanggal_masuk.setCaretPosition(0);
        comb = (String) model.getValueAt(i, 9);
        switch (comb) {
            case "Distributor":
                comboBoxGaransi.setSelectedIndex(0);
                break;
            case "Tidak":
                comboBoxGaransi.setSelectedIndex(2);
                break;
            case "Toko":
                comboBoxGaransi.setSelectedIndex(1);
                break;
        }
        textAreaKiri.setText(model.getValueAt(i, 1).toString() + "\n" + model.getValueAt(i, 2).toString() + "\n" + model.getValueAt(i, 3).toString() + " " + model.getValueAt(i, 4).toString());
    }//GEN-LAST:event_tabelMouseClicked
    private void tombolUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolUbahActionPerformed

        if (tombolUbah.getText().equals("Ubah")) {
            if (fieldInputId_barang.getText().equals("ID")) {
                JOptionPane.showMessageDialog(rootPane, "Pilih barang yang ingin diubah", "Ada Yang Salah", WIDTH);
                clearField();
            } else {
                tombolUbah.setText("Simpan");
                tombolClearField.setVisible(true);
                tombolTambah.setEnabled(false);
                tombolHapus.setEnabled(false);
                enable();

            }
        } else if (tombolUbah.getText().equals("Simpan")) {
            if (regexIdBarang(fieldInputId_barang.getText())) {
                if (regexBilangan(fieldInputQty.getText(), "Qty") || regexBilangan(fieldInputHarga_beli.getText(), "Harga")) {

                    int supplierIndex = Integer.parseInt(MySQLconn.executeSingleQueryResult("SELECT `id_supplier` FROM `supplier` WHERE nama = '" + comboBoxSupplier.getSelectedItem() + "'", "id_supplier"));

                    String query = "UPDATE `barang` SET `jenis`= '" + comboBoxJenis.getSelectedItem() + "',`merk`='" + fieldInputMerk.getText() + "',`ragam`='" + fieldInputRagam.getText() + "',`seri`='" + fieldInputSeri.getText() + "',`qty`='" + fieldInputQty.getText() + "',`harga_beli`='" + fieldInputHarga_beli.getText() + "',`supplier`='" + supplierIndex + "',`garansi`='" + (String) comboBoxGaransi.getSelectedItem() + "' WHERE `id_barang`='" + fieldInputId_barang.getText() + "';";

                    Object[] pilihan = {"Ubah", "Batal"};
                    int n = JOptionPane.showOptionDialog(rootPane, "Yakin ingin mengubah?", "Ubah barang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, pilihan, pilihan[0]);
                    if (n == JOptionPane.YES_OPTION) {
                        execSQLQuery(query, "diubah");
                        MySQLconn.executeVoidQuery("UPDATE barang SET id_barang = concat( code,LPAD(barang.`no`, 7,\"000\") );");

                        System.out.println(query);
                        refreshTable();
                    } else if (n == JOptionPane.NO_OPTION) {
                        clearField();
                        tombolClearField.setVisible(false);
                    } else {
                        JOptionPane.getRootFrame().dispose();
                    }

                }
            }
            tombolUbah.setText("Ubah");
            disable();
            clearField();
            tombolClearField.setVisible(false);
            tombolTambah.setEnabled(true);
            tombolHapus.setEnabled(true);
        }
    }//GEN-LAST:event_tombolUbahActionPerformed
    private void tombolHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusActionPerformed
        Object[] pilihan = {"Hapus", "Batal"};
        int n = JOptionPane.showOptionDialog(rootPane, "Yakin ingin menghapus?", "Hapus barang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, pilihan, pilihan[0]);
        if (n == JOptionPane.YES_OPTION) {
            if (regexIdBarang(fieldInputId_barang.getText())) {

                String query = "DELETE from `barang` WHERE `id_barang`='" + fieldInputId_barang.getText() + "';";
                execSQLQuery(query, "" + "dihapus");
                System.out.println(query);
                refreshTable();
            }
        } else {
            JOptionPane.getRootFrame().dispose();
        }

    }//GEN-LAST:event_tombolHapusActionPerformed
    private void fieldInputId_barangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputId_barangKeyTyped
        if (fieldInputId_barang.getText().length() > 10) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputId_barangKeyTyped
    private void fieldInputMerkKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputMerkKeyTyped
        if (fieldInputMerk.getText().length() > 20) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputMerkKeyTyped
    private void fieldInputRagamKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputRagamKeyTyped
        if (fieldInputRagam.getText().length() > 30) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputRagamKeyTyped
    private void fieldInputSeriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputSeriKeyTyped
        if (fieldInputSeri.getText().length() > 300) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputSeriKeyTyped
    private void fieldInputQtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputQtyKeyTyped
        if (fieldInputQty.getText().length() > 11) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputQtyKeyTyped
    private void fieldInputHarga_beliKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputHarga_beliKeyTyped
        if (fieldInputHarga_beli.getText().length() > 15) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputHarga_beliKeyTyped
    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        String query = searchField.getText();
        filterTable(query, comboBoxKolom.getSelectedIndex());
    }//GEN-LAST:event_searchFieldKeyReleased
    private void searchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusLost
        if ((searchField.getText()).equals("")) {
            searchField.setText("search...");
        }
    }//GEN-LAST:event_searchFieldFocusLost
    private void tombolHapusSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusSupplierActionPerformed
        Object[] pilihan = {"Hapus", "Batal"};
        int n = JOptionPane.showOptionDialog(rootPane, "Yakin ingin menghapus?", "Hapus barang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, pilihan, pilihan[0]);
        if (n == JOptionPane.YES_OPTION) {
            String query = "DELETE from `supplier` WHERE `id_supplier`='" + fieldInputIDSupplier.getText() + "';";
            execSQLQuery(query, "" + "dihapus");
            System.out.println(query);
            refreshTableSupplier();
            displaySupplierComboBox();
        } else {
            JOptionPane.getRootFrame().dispose();
        }
    }//GEN-LAST:event_tombolHapusSupplierActionPerformed
    private void searchFieldSupplierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldSupplierFocusLost
        if ((searchFieldSupplier.getText()).equals("")) {
            searchFieldSupplier.setText("search...");
        }
    }//GEN-LAST:event_searchFieldSupplierFocusLost
    private void searchFieldSupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldSupplierKeyReleased
        String query = searchFieldSupplier.getText();
        filterTableSupplier(query, comboBoxKolomSupplier.getSelectedIndex());
    }//GEN-LAST:event_searchFieldSupplierKeyReleased
    private void tombolUbahSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolUbahSupplierActionPerformed
        if (tombolUbahSupplier.getText().equals("Ubah")) {
            if (fieldInputIDSupplier.getText().equals("ID")) {
                JOptionPane.showMessageDialog(rootPane, "Pilih barang yang ingin diubah", "Ada Yang Salah", WIDTH);
                clearSupplier();

            } else {
                tombolUbahSupplier.setText("Simpan");
                tombolClearSupplier.setVisible(true);
                enableSupplier();

            }
        } else if (tombolUbahSupplier.getText().equals("Simpan")) {

            Object[] pilihan = {"Ubah", "Batal"};
            int n = JOptionPane.showOptionDialog(rootPane, "Yakin ingin mengubah?", "Ubah barang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, pilihan, pilihan[0]);
            if (n == JOptionPane.YES_OPTION) {
                if (fieldInputIDSupplier.getText().matches("\\d+")) {
                    if (fieldInputEmailSupplier.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") || fieldInputEmailSupplier.getText().equals("")) {
                        if (fieldInputTelponSupplier.getText().matches("\\d+") || fieldInputTelponSupplier.getText().equals("")) {
                            String query = "UPDATE `supplier` SET `nama`= '" + fieldInputNamaSupplier.getText() + "',`alamat`='" + fieldInputAlamatSupplier.getText() + "',`telp`='" + fieldInputTelponSupplier.getText() + "',`email`='" + fieldInputEmailSupplier.getText() + "',`catatan`='" + textAreaCatatan.getText() + "' WHERE `id_supplier`='" + fieldInputIDSupplier.getText() + "';";
                            execSQLQuery(query, "diubah");
                            System.out.println(query);
                            refreshTableSupplier();

                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Telpon tidak valid");
                            disableSupplier();
                            clearSupplier();
                            tombolClearSupplier.setVisible(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Email tidak valid");
                        disableSupplier();
                        clearSupplier();
                        tombolClearSupplier.setVisible(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Supplier ID salah");
                    disableSupplier();
                    clearSupplier();
                    tombolClearSupplier.setVisible(false);
                }

            } else if (n == JOptionPane.NO_OPTION) {
                clearField();
                tombolClearSupplier.setVisible(false);
            } else {
                JOptionPane.getRootFrame().dispose();
                tombolClearSupplier.setVisible(false);
            }

            tombolUbahSupplier.setText("Ubah");
            disableSupplier();
            clearSupplier();
            tombolClearSupplier.setVisible(false);

            displaySupplierComboBox();
        }
    }//GEN-LAST:event_tombolUbahSupplierActionPerformed
    private void fieldInputId_barangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputId_barangFocusGained
        if (fieldInputId_barang.getText().equals("ID")) {
            fieldInputId_barang.setText("");
        } else {
            fieldInputId_barang.selectAll();
        }
    }//GEN-LAST:event_fieldInputId_barangFocusGained
    private void fieldInputId_barangFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputId_barangFocusLost

        if (fieldInputId_barang.getText().equals("")) {
            fieldInputId_barang.setText("ID");
        }
    }//GEN-LAST:event_fieldInputId_barangFocusLost
    private void fieldInputMerkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputMerkFocusGained

        if (fieldInputMerk.getText().equals("Merk")) {
            fieldInputMerk.setText("");
        }
    }//GEN-LAST:event_fieldInputMerkFocusGained
    private void fieldInputMerkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputMerkFocusLost

        if (fieldInputMerk.getText().equals("")) {
            fieldInputMerk.setText("Merk");
        }
    }//GEN-LAST:event_fieldInputMerkFocusLost
    private void fieldInputRagamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputRagamFocusGained

        if (fieldInputRagam.getText().equals("Ragam")) {
            fieldInputRagam.setText("");
        }
    }//GEN-LAST:event_fieldInputRagamFocusGained
    private void fieldInputRagamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputRagamFocusLost

        if (fieldInputRagam.getText().equals("")) {
            fieldInputRagam.setText("Ragam");
        }
    }//GEN-LAST:event_fieldInputRagamFocusLost
    private void fieldInputSeriFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputSeriFocusGained

        if (fieldInputSeri.getText().equals("Seri")) {
            fieldInputSeri.setText("");
        }
    }//GEN-LAST:event_fieldInputSeriFocusGained
    private void fieldInputSeriFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputSeriFocusLost

        if (fieldInputSeri.getText().equals("")) {
            fieldInputSeri.setText("Seri");
        }
    }//GEN-LAST:event_fieldInputSeriFocusLost
    private void fieldInputQtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputQtyFocusGained

        if (fieldInputQty.getText().equals("Qty")) {
            fieldInputQty.setText("");
        }
    }//GEN-LAST:event_fieldInputQtyFocusGained
    private void fieldInputQtyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputQtyFocusLost

        if (fieldInputQty.getText().equals("")) {
            fieldInputQty.setText("Qty");
        }
    }//GEN-LAST:event_fieldInputQtyFocusLost
    private void fieldInputHarga_beliFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputHarga_beliFocusGained

        if (fieldInputHarga_beli.getText().equals("Hrg Beli")) {
            fieldInputHarga_beli.setText("");
        }
    }//GEN-LAST:event_fieldInputHarga_beliFocusGained
    private void fieldInputHarga_beliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputHarga_beliFocusLost

        if (fieldInputHarga_beli.getText().equals("")) {
            fieldInputHarga_beli.setText("Hrg Beli");
        }
    }//GEN-LAST:event_fieldInputHarga_beliFocusLost
    private void fieldInputIDSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputIDSupplierFocusGained

        if (fieldInputIDSupplier.getText().equals("ID")) {
            fieldInputIDSupplier.setText("");
        }
    }//GEN-LAST:event_fieldInputIDSupplierFocusGained
    private void fieldInputIDSupplierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputIDSupplierFocusLost

        if (fieldInputIDSupplier.getText().equals("")) {
            fieldInputIDSupplier.setText("ID");
        }
    }//GEN-LAST:event_fieldInputIDSupplierFocusLost
    private void fieldInputNamaSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputNamaSupplierFocusGained

        if (fieldInputNamaSupplier.getText().equals("Nama")) {
            fieldInputNamaSupplier.setText("");
        }
    }//GEN-LAST:event_fieldInputNamaSupplierFocusGained
    private void fieldInputNamaSupplierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputNamaSupplierFocusLost

        if (fieldInputNamaSupplier.getText().equals("")) {
            fieldInputNamaSupplier.setText("Nama");
        }
    }//GEN-LAST:event_fieldInputNamaSupplierFocusLost
    private void fieldInputAlamatSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputAlamatSupplierFocusGained

        if (fieldInputAlamatSupplier.getText().equals("Alamat")) {
            fieldInputAlamatSupplier.setText("");
        }
    }//GEN-LAST:event_fieldInputAlamatSupplierFocusGained
    private void fieldInputAlamatSupplierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputAlamatSupplierFocusLost

        if (fieldInputAlamatSupplier.getText().equals("")) {
            fieldInputAlamatSupplier.setText("Alamat");
        }
    }//GEN-LAST:event_fieldInputAlamatSupplierFocusLost
    private void fieldInputTelponSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputTelponSupplierFocusGained

        if (fieldInputTelponSupplier.getText().equals("Telpon")) {
            fieldInputTelponSupplier.setText("");
        }
    }//GEN-LAST:event_fieldInputTelponSupplierFocusGained
    private void fieldInputTelponSupplierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputTelponSupplierFocusLost

        if (fieldInputTelponSupplier.getText().equals("")) {
            fieldInputTelponSupplier.setText("Telpon");
        }
    }//GEN-LAST:event_fieldInputTelponSupplierFocusLost
    private void fieldInputEmailSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputEmailSupplierFocusGained

        if (fieldInputEmailSupplier.getText().equals("Email")) {
            fieldInputEmailSupplier.setText("");
        }
    }//GEN-LAST:event_fieldInputEmailSupplierFocusGained
    private void fieldInputEmailSupplierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputEmailSupplierFocusLost

        if (fieldInputEmailSupplier.getText().equals("")) {
            fieldInputEmailSupplier.setText("Email");
        }
    }//GEN-LAST:event_fieldInputEmailSupplierFocusLost
    private void tombolAtcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolAtcActionPerformed
        if (fieldInputId_barang.getText().equals("ID")) {
            JOptionPane.showMessageDialog(rootPane, "Pilih barang terlebih dahulu", "Ada Yang Salah", WIDTH);
        } else {
            try {

                int jumlah = Integer.parseInt(JOptionPane.showInputDialog(rootPane, "Input jumlah yang ingin dibeli", "Masukan jumlah", JOptionPane.QUESTION_MESSAGE));

                if (jumlah <= 0) {
                    JOptionPane.showMessageDialog(rootPane, "Input salah");
                } else {
                    ArrayList<TableRowContentTransaksi> list = tableContentTransaksi();
                    String query = "null";
                    if (("empty".equals(statusInvoiceAkhir())) || ("paid".equals(statusInvoiceAkhir()))) {
                        TableRowContentTransaksi.tambahInvoice();
                        query = "INSERT INTO `transaksi_cart` (`id_inv`, `id_barang`, `qty`, `harga`, `keterangan`) VALUES ('" + statusInvoiceAkhir() + "', '" + fieldInputId_barang.getText() + "', '" + jumlah + "',(SELECT `harga_beli` FROM `barang` WHERE `id_barang` = '" + fieldInputId_barang.getText() + "'), '');";
                    } else {
                        boolean Duplicate = false;
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).getIdBarang().equals(fieldInputId_barang.getText())) {
                                query = "UPDATE `transaksi_cart` SET `qty` = '" + jumlah + "'  WHERE `transaksi_cart`.`id_barang` = '" + fieldInputId_barang.getText() + "' AND `transaksi_cart`.`id_inv` ='" + statusInvoiceAkhir() + "';";
                                Duplicate = true;
                            }
                        }
                        if (!Duplicate) {
                            query = "INSERT INTO `transaksi_cart` (`id_inv`, `id_barang`, `qty`, `harga`, `keterangan`) VALUES ('" + statusInvoiceAkhir() + "', '" + fieldInputId_barang.getText() + "', '" + jumlah + "',(SELECT `harga_beli` FROM `barang` WHERE `id_barang` = '" + fieldInputId_barang.getText() + "'), '');";
                            Duplicate = false;
                        }

                    }

                    System.out.println(query);
                    MySQLconn.executeVoidQuery(query);

                    refreshTableTransaksi();
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(rootPane, "Input salah");
            }
        }


    }//GEN-LAST:event_tombolAtcActionPerformed
    private void tabelTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTransaksiMouseClicked
        int i = tabelTransaksi.getSelectedRow();
        TableModel model = tabelTransaksi.getModel();
        i = tabelTransaksi.convertRowIndexToModel(i);
        fieldIdBarangTransaksi.setText(model.getValueAt(i, 0).toString());
        fieldIdBarangTransaksi.setCaretPosition(0);
        fieldInputQtyTransaksi.setText(model.getValueAt(i, 2).toString());
        textAreaKiriTransaksi.setText(model.getValueAt(i, 1).toString());
    }//GEN-LAST:event_tabelTransaksiMouseClicked
    private void tombolCheckoutTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolCheckoutTransaksiActionPerformed
        //disini nantinya dikasih failsafe if, untuk cek barang supaya ga ngurangin sampe minus.
        TableModel model = tabelTransaksi.getModel();
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Cart tidak boleh kosong");
        } else {
            boolean isMinus = false;
            String qtyCol;
            String idBarangCol;
            model = (DefaultTableModel) tabelTransaksi.getModel();
            Object[] row = new Object[3];
            // failsafe check

            for (int i = 0; i < model.getRowCount(); i++) {
                String queryCek = "SELECT `qty` FROM `barang` WHERE `id_barang` = '" + model.getValueAt(i, 0).toString() + "';";
                int hasil = Integer.parseInt(MySQLconn.executeSingleQueryResult(queryCek, "qty"));
                hasil = hasil - Integer.parseInt(model.getValueAt(i, 2).toString());

                if (hasil < 0) {
                    JOptionPane.showMessageDialog(rootPane, "Barang " + model.getValueAt(i, 1) + " Habis!");
                    isMinus = true;
                }
            }

            if (isMinus) {
                JOptionPane.showMessageDialog(rootPane, "Pastikan semua barang tersedia");
            } else {
                // execute checkout function
                for (int i = 0; i < model.getRowCount(); i++) {
                    qtyCol = model.getValueAt(i, 2).toString();
                    idBarangCol = model.getValueAt(i, 0).toString();
                    MySQLconn.executeVoidQuery("UPDATE `barang` SET `barang`.`qty` = `barang`.`qty`-'" + qtyCol + "' WHERE  `barang`.`id_barang`= '" + idBarangCol + "'");
                }

                String query = "UPDATE `transaksi_invoice` SET `lunas` = b'1', id_karyawan = '" + idOperator + "', tanggal = CURRENT_TIMESTAMP WHERE `transaksi_invoice`.`id_inv` = '" + statusInvoiceAkhir() + "';";
                System.out.println(query);
                MySQLconn.executeVoidQuery(query);
                TableRowContentTransaksi.tambahInvoice();

                refreshTableTransaksi();
                refreshTable();
                labelTransaksiAtas.setText("Invoice :" + TableRowContentTransaksi.statusInvoiceAkhir());
            }
        }
        fieldIdBarangTransaksi.setText("ID Barang");
        fieldInputQtyTransaksi.setText("Qty");
    }//GEN-LAST:event_tombolCheckoutTransaksiActionPerformed
    private void tabelReturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelReturMouseClicked

        int i = tabelRetur.getSelectedRow();
        TableModel model = tabelRetur.getModel();
        i = tabelRetur.convertRowIndexToModel(i);
        fieldInputIdRetur.setText(model.getValueAt(i, 0).toString());
        fieldInputIdBarangRetur.setText(model.getValueAt(i, 1).toString());
        fieldInputJumlahBarangRetur.setText(model.getValueAt(i, 3).toString());
        textAreaKiriRetur.setText(model.getValueAt(i, 2).toString());
    }//GEN-LAST:event_tabelReturMouseClicked
    private void searchFieldReturKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldReturKeyReleased
        String query = searchFieldRetur.getText();
        filterTableRetur(query, comboBoxKolomRetur.getSelectedIndex());
    }//GEN-LAST:event_searchFieldReturKeyReleased
    private void searchFieldReturFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldReturFocusLost
        if ((searchFieldRetur.getText()).equals("")) {
            searchFieldRetur.setText("search...");
        }
    }//GEN-LAST:event_searchFieldReturFocusLost
    private void fieldInputIdBarangReturFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputIdBarangReturFocusGained

        if (fieldInputIdBarangRetur.getText().equals("ID")) {
            fieldInputIdBarangRetur.setText("");
        }
    }//GEN-LAST:event_fieldInputIdBarangReturFocusGained
    private void fieldInputIdBarangReturFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputIdBarangReturFocusLost

        if (fieldInputIdBarangRetur.getText().equals("")) {
            fieldInputIdBarangRetur.setText("ID");
        }
    }//GEN-LAST:event_fieldInputIdBarangReturFocusLost
    private void fieldInputIdReturFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputIdReturFocusGained

        if (fieldInputIdRetur.getText().equals("ID Retur")) {
            fieldInputIdRetur.setText("");
        }
    }//GEN-LAST:event_fieldInputIdReturFocusGained
    private void fieldInputIdReturFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputIdReturFocusLost

        if (fieldInputIdRetur.getText().equals("")) {
            fieldInputIdRetur.setText("ID Retur");
        }
    }//GEN-LAST:event_fieldInputIdReturFocusLost
    private void fieldInputJumlahBarangReturFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputJumlahBarangReturFocusGained

        if (fieldInputJumlahBarangRetur.getText().equals("Jumlah")) {
            fieldInputJumlahBarangRetur.setText("");
        }
    }//GEN-LAST:event_fieldInputJumlahBarangReturFocusGained
    private void fieldInputJumlahBarangReturFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputJumlahBarangReturFocusLost

        if (fieldInputJumlahBarangRetur.getText().equals("")) {
            fieldInputJumlahBarangRetur.setText("Jumlah");
        }
    }//GEN-LAST:event_fieldInputJumlahBarangReturFocusLost
    private void tombolHapusReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusReturActionPerformed
        Object[] pilihan = {"Hapus", "Batal"};
        int n = JOptionPane.showOptionDialog(rootPane, "Yakin ingin menghapus?", "Hapus barang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, pilihan, pilihan[0]);
        if (n == JOptionPane.YES_OPTION) {
            String query = "DELETE from `barang_retur` WHERE `id_retur`='" + fieldInputIdRetur.getText() + "';";
            execSQLQuery(query, "" + "dihapus");
            System.out.println(query);
            refreshTableRetur();
        } else {
            JOptionPane.getRootFrame().dispose();
        }

    }//GEN-LAST:event_tombolHapusReturActionPerformed
    private void buttonTransaksiHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransaksiHapusActionPerformed
        Object[] pilihan = {"Hapus", "Batal"};
        int n = JOptionPane.showOptionDialog(rootPane, "Yakin ingin menghapus?", "Hapus barang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, pilihan, pilihan[0]);
        if (n == JOptionPane.YES_OPTION) {
            if (fieldIdBarangTransaksi.getText().matches("\\bP[0-9][0-9][0-9][0-9][0-9][0-9][0-9]\\b")) {
                MySQLconn.executeVoidQuery("DELETE FROM `transaksi_cart` WHERE `transaksi_cart`.`id_barang` ='" + fieldIdBarangTransaksi.getText() + "';");
                refreshTableTransaksi();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Pilih barang dahulu");
            }
        } else {
            JOptionPane.getRootFrame().dispose();
        }


    }//GEN-LAST:event_buttonTransaksiHapusActionPerformed
    private void buttonUbahQtyTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonUbahQtyTransaksiActionPerformed
        if (fieldIdBarangTransaksi.getText().matches("\\bP[0-9][0-9][0-9][0-9][0-9][0-9][0-9]\\b")) {
            if (regexBilangan(fieldInputQtyTransaksi.getText(), "Qty")) {
                String query = "UPDATE `transaksi_cart` SET `qty`= ' " + fieldInputQtyTransaksi.getText() + "' WHERE `transaksi_cart`.`id_barang`='" + fieldIdBarangTransaksi.getText() + "';";
                // execSQLQuery(query, "diubah");
                MySQLconn.executeVoidQuery(query);
                System.out.println(query);
                refreshTableTransaksi();
            }
        }
    }//GEN-LAST:event_buttonUbahQtyTransaksiActionPerformed
    private void tabelSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelSupplierMouseClicked
        // Menampilkan row yang dipilih di table ke text field
        int i = tabelSupplier.getSelectedRow();
        TableModel model = tabelSupplier.getModel();
        i = tabelSupplier.convertRowIndexToModel(i);
        fieldInputIDSupplier.setText(model.getValueAt(i, 0).toString());
        fieldInputNamaSupplier.setText(model.getValueAt(i, 1).toString());
        fieldInputAlamatSupplier.setText(model.getValueAt(i, 2).toString());
        fieldInputTelponSupplier.setText(model.getValueAt(i, 3).toString());
        fieldInputEmailSupplier.setText(model.getValueAt(i, 4).toString());
        textAreaCatatan.setText(model.getValueAt(i, 5).toString());
        textAreaKiriSupplier.setText(model.getValueAt(i, 1).toString() + "\nAlamat:\n" + model.getValueAt(i, 2).toString() + "\nCatatan:\n" + model.getValueAt(i, 5).toString());
    }//GEN-LAST:event_tabelSupplierMouseClicked
    private void tombolClearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolClearFieldActionPerformed
        disable();
        clearField();
        tombolTambah.setText("Tambah");
        tombolUbah.setText("Ubah");
        tombolClearField.setVisible(false);
    }//GEN-LAST:event_tombolClearFieldActionPerformed
    private void tombolClearSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolClearSupplierActionPerformed
        disableSupplier();
        clearSupplier();
        tombolTambahSupplier.setText("Tambah");
        tombolUbahSupplier.setText("Ubah");
        tombolClearSupplier.setVisible(false);
    }//GEN-LAST:event_tombolClearSupplierActionPerformed
    private void tombolClearReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolClearReturActionPerformed
        fieldInputIdRetur.setText("ID Retur");
        fieldInputIdBarangRetur.setText("ID");
        fieldInputJumlahBarangRetur.setText("Jumlah");

        fieldInputIdBarangRetur.requestFocus();
    }//GEN-LAST:event_tombolClearReturActionPerformed
    private void fieldInputQtyTransaksiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputQtyTransaksiFocusGained
        fieldInputQtyTransaksi.setText("");
    }//GEN-LAST:event_fieldInputQtyTransaksiFocusGained
    private void fieldInputNamaSupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputNamaSupplierKeyTyped
        if (fieldInputNamaSupplier.getText().length() > 50) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputNamaSupplierKeyTyped
    private void fieldInputAlamatSupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputAlamatSupplierKeyTyped
        if (fieldInputAlamatSupplier.getText().length() > 180) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputAlamatSupplierKeyTyped
    private void fieldInputTelponSupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputTelponSupplierKeyTyped
        if (fieldInputTelponSupplier.getText().length() > 15) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputTelponSupplierKeyTyped
    private void fieldInputEmailSupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputEmailSupplierKeyTyped
        if (fieldInputEmailSupplier.getText().length() > 35) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputEmailSupplierKeyTyped
    private void tombolTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolTambahActionPerformed
        if (tombolTambah.getText().equals("Tambah")) {
            tombolTambah.setText("Simpan");
            tombolClearField.setVisible(true);
            enable();
            clearField();
            tombolHapus.setEnabled(false);
            tombolUbah.setEnabled(false);
        } else if (tombolTambah.getText().equals("Simpan")) {

            if (fieldInputMerk.getText().equals("Merk") || fieldInputRagam.getText().equals("Ragam") || fieldInputSeri.getText().equals("Seri")) {
                JOptionPane.showMessageDialog(rootPane, "Cek Kembali Data Yang Baru Diisi", "Ada Yang Salah", WIDTH);
                clearField();
                tombolClearField.setVisible(false);
            } else {
                Object[] pilihan = {"Tambah", "Batal"};
                int n = JOptionPane.showOptionDialog(rootPane, "Yakin ingin Tambah?", "Tambah barang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, pilihan, pilihan[0]);
                if (n == JOptionPane.YES_OPTION) {
                    if ((regexBilangan(fieldInputQty.getText(), "Qty")) && (regexBilangan(fieldInputHarga_beli.getText(), "Harga"))) {
                        int supplierIndex = Integer.parseInt(MySQLconn.executeSingleQueryResult("SELECT `id_supplier` FROM `supplier` WHERE nama = '" + comboBoxSupplier.getSelectedItem() + "'", "id_supplier"));
                        String query = "INSERT INTO `barang`(`code`, `no`, `id_barang`, `jenis`, `merk`, `ragam`, `seri`, `qty`, `harga_beli`, `supplier`, `tgl_masuk`, `garansi`) VALUES('P',NULL,NULL,'" + comboBoxJenis.getSelectedItem() + "','" + fieldInputMerk.getText() + "','" + fieldInputRagam.getText() + "','" + fieldInputSeri.getText() + "','" + fieldInputQty.getText() + "','" + fieldInputHarga_beli.getText() + "','" + supplierIndex + "',CURRENT_TIMESTAMP,'" + comboBoxGaransi.getSelectedItem() + "')";
                        execSQLQuery(query, "ditambahkan");
                        MySQLconn.executeVoidQuery("UPDATE barang SET id_barang = concat( code,LPAD(barang.`no`, 7,\"000\") );");
                        System.out.println(query);
                        refreshTable();
                    }
                } else if (n == JOptionPane.NO_OPTION) {
                    clearField();
                    tombolClearField.setVisible(false);
                } else {
                    JOptionPane.getRootFrame().dispose();
                }

            }

            tombolTambah.setText("Tambah");
            disable();
            clearField();
            tombolHapus.setEnabled(true);
            tombolUbah.setEnabled(true);
            tombolClearField.setVisible(false);
        }

    }//GEN-LAST:event_tombolTambahActionPerformed

    private void comboBoxKolomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxKolomActionPerformed
        if (!(searchField.getText().equals("search..."))) {

            String query = searchField.getText();
            filterTable(query, comboBoxKolom.getSelectedIndex());
        }
    }//GEN-LAST:event_comboBoxKolomActionPerformed

    private void comboBoxKolomSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxKolomSupplierActionPerformed
        if (!(searchFieldSupplier.getText().equals("search..."))) {
            String query = searchFieldSupplier.getText();
            filterTableSupplier(query, comboBoxKolomSupplier.getSelectedIndex());
        }
    }//GEN-LAST:event_comboBoxKolomSupplierActionPerformed

    private void comboBoxKolomReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxKolomReturActionPerformed
        if (!(searchFieldRetur.getText().equals("search..."))) {
            String query = searchFieldRetur.getText();
            filterTableRetur(query, comboBoxKolomRetur.getSelectedIndex());
        }
    }//GEN-LAST:event_comboBoxKolomReturActionPerformed

    private void tombolTambahSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolTambahSupplierActionPerformed
        if (tombolTambahSupplier.getText().equals("Tambah")) {
            tombolTambahSupplier.setText("Simpan");
            tombolClearSupplier.setVisible(true);
            enableSupplier();
            clearSupplier();
        } else if (tombolTambahSupplier.getText().equals("Simpan")) {

            if (fieldInputNamaSupplier.getText().equals("Nama") || fieldInputEmailSupplier.getText().equals("Email") || fieldInputAlamatSupplier.getText().equals("Alamat") || fieldInputTelponSupplier.getText().equals("Telpon") || textAreaCatatan.getText().equals("Catatan...")) {
                JOptionPane.showMessageDialog(rootPane, "Cek Kembali Data Yang Baru Diisi", "Ada Yang Salah", WIDTH);
                clearField();
                tombolClearSupplier.setVisible(false);
            } else {
                Object[] pilihan = {"Tambah", "Batal"};
                int n = JOptionPane.showOptionDialog(rootPane, "Yakin ingin Tambah?", "Tambah barang", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, pilihan, pilihan[0]);
                if (n == JOptionPane.YES_OPTION) {
                    if (fieldInputEmailSupplier.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$") || fieldInputEmailSupplier.getText().equals("")) {
                        if (fieldInputTelponSupplier.getText().matches("\\d+") || fieldInputTelponSupplier.getText().equals("")) {

                            String query = "INSERT INTO `supplier`(`nama`, `alamat`, `telp`, `email`, `catatan`) VALUES ('" + fieldInputNamaSupplier.getText() + "','" + fieldInputAlamatSupplier.getText() + "','" + fieldInputTelponSupplier.getText() + "','" + fieldInputEmailSupplier.getText() + "','" + textAreaCatatan.getText() + "')";
                            execSQLQuery(query, "ditambahkan");
                            System.out.println(query);
                            refreshTableSupplier();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Telpon tidak valid");
                            clearSupplier();
                            tombolClearSupplier.setVisible(false);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Email tidak valid");
                        clearSupplier();
                        tombolClearSupplier.setVisible(false);
                    }
                } else if (n == JOptionPane.NO_OPTION) {
                    clearSupplier();
                    tombolClearSupplier.setVisible(false);
                } else {
                    JOptionPane.getRootFrame().dispose();
                    tombolClearSupplier.setVisible(false);
                }

            }

            tombolTambahSupplier.setText("Tambah");
            disableSupplier();
            clearSupplier();
            tombolClearSupplier.setVisible(false);
        }

        displaySupplierComboBox();
    }//GEN-LAST:event_tombolTambahSupplierActionPerformed

    private void tombolTambahReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolTambahReturActionPerformed
        if (fieldInputIdBarangRetur.getText().equals("ID") || fieldInputJumlahBarangRetur.getText().equals("Jumlah")) {
            JOptionPane.showMessageDialog(rootPane, "Cek Kembali Data Yang Baru Diisi", "Ada Yang Salah", WIDTH);
        } else {
            String query = "INSERT INTO `barang_retur`(`id_barang`, `qty`) VALUES ('" + fieldInputIdBarangRetur.getText() + "','" + fieldInputJumlahBarangRetur.getText() + "')";
            execSQLQuery(query, "ditambahkan");
            System.out.println(query);
            refreshTableRetur();
        }
    }//GEN-LAST:event_tombolTambahReturActionPerformed

    private void searchFieldSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldSupplierFocusGained
        if (searchFieldSupplier.getText().equals("search...")) {
            searchFieldSupplier.setText("");
        }
    }//GEN-LAST:event_searchFieldSupplierFocusGained

    private void searchFieldReturFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldReturFocusGained
        if (searchFieldRetur.getText().equals("search...")) {
            searchFieldRetur.setText("");
        }
    }//GEN-LAST:event_searchFieldReturFocusGained

    private void searchFieldFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusGained
        if (searchField.getText().equals("search...")) {
            searchField.setText("");
        }
    }//GEN-LAST:event_searchFieldFocusGained

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        tombolClearField.setVisible(false);
        tombolClearRetur.setVisible(false);
        tombolClearSupplier.setVisible(false);
        comboBoxGaransi.setEnabled(false);
        comboBoxJenis.setEnabled(false);
        comboBoxSupplier.setEnabled(false);
    }//GEN-LAST:event_formWindowActivated

    private void buttonCetakAtasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCetakAtasActionPerformed
        String tanggalAwal = new SimpleDateFormat("yyyy-MM-dd").format(dateDariTanggal.getDate());
        String tanggalAkhir = new SimpleDateFormat("yyyy-MM-dd").format(dateSampaiTanggal.getDate());
        try{
            File file = new File("/home/jose/Documents/Materi Belajar/3Cahaya/ProgramInventoriTigaCahaya/src/tigacahaya/Invoice.jrxml");
            jasperDesign = JRXmlLoader.load(file);
            HashMap param = new HashMap();
            param.put("tanggalAwal",tanggalAwal);
            param.put("tanggalAkhir", tanggalAkhir);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            jasperPrint = JasperFillManager.fillReport(jasperReport, param, conn.connect());
            JasperViewer.viewReport(jasperPrint,false);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e);
        }
    }//GEN-LAST:event_buttonCetakAtasActionPerformed

    private void buttonCetakBawahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCetakBawahActionPerformed
        try{
            String Namafile = "/home/jose/Documents/Materi Belajar/3Cahaya/ProgramInventoriTigaCahaya/src/tigacahaya/invoiceBulanan.jasper";
            HashMap parameter = new HashMap();
            parameter.put("Bulan", comboBoxBulan.getSelectedItem());
            parameter.put("Tahun", yearChooser.getYear());
            JasperPrint print = JasperFillManager.fillReport(Namafile, parameter, conn.connect());
            JasperViewer.viewReport(print,false);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_buttonCetakBawahActionPerformed

    private void buttonKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonKeluarActionPerformed
// </editor-fold>

    public static void guiStart() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                System.out.println(info.getName());
                if ("Nimbus".equals(info.getName())) {

                    //javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
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
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GUI gui = new GUI();
                gui.setLocationRelativeTo(null);
                gui.setVisible(true);
                //new GUI().setVisible(true);
            }
        });
    }
// <editor-fold defaultstate="collapsed" desc=" Variable Declaration ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCetakAtas;
    private javax.swing.JButton buttonCetakBawah;
    private javax.swing.JButton buttonKeluar;
    private javax.swing.JButton buttonTransaksiHapus;
    private javax.swing.JButton buttonUbahQtyTransaksi;
    private javax.swing.JComboBox<String> comboBoxBulan;
    private javax.swing.JComboBox<String> comboBoxGaransi;
    private javax.swing.JComboBox<String> comboBoxJenis;
    private javax.swing.JComboBox<String> comboBoxKolom;
    private javax.swing.JComboBox<String> comboBoxKolomRetur;
    private javax.swing.JComboBox<String> comboBoxKolomSupplier;
    private javax.swing.JComboBox<String> comboBoxSupplier;
    private com.toedter.calendar.JDateChooser dateDariTanggal;
    private com.toedter.calendar.JDateChooser dateSampaiTanggal;
    private javax.swing.JTextField fieldIdBarangTransaksi;
    private javax.swing.JTextField fieldInputAlamatSupplier;
    private javax.swing.JTextField fieldInputEmailSupplier;
    private javax.swing.JTextField fieldInputHarga_beli;
    private javax.swing.JTextField fieldInputIDSupplier;
    private javax.swing.JTextField fieldInputIdBarangRetur;
    private javax.swing.JTextField fieldInputIdRetur;
    private javax.swing.JTextField fieldInputId_barang;
    private javax.swing.JTextField fieldInputJumlahBarangRetur;
    private javax.swing.JTextField fieldInputMerk;
    private javax.swing.JTextField fieldInputNamaSupplier;
    private javax.swing.JTextField fieldInputQty;
    private javax.swing.JTextField fieldInputQtyTransaksi;
    private javax.swing.JTextField fieldInputRagam;
    private javax.swing.JTextField fieldInputSeri;
    private javax.swing.JTextField fieldInputTanggal_masuk;
    private javax.swing.JTextField fieldInputTelponSupplier;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelBarangGaransi;
    private javax.swing.JLabel jLabelBarangHargaBeli;
    private javax.swing.JLabel jLabelBarangIdBarang;
    private javax.swing.JLabel jLabelBarangJenis;
    private javax.swing.JLabel jLabelBarangMerk;
    private javax.swing.JLabel jLabelBarangQty;
    private javax.swing.JLabel jLabelBarangRagam;
    private javax.swing.JLabel jLabelBarangSeri;
    private javax.swing.JLabel jLabelBarangSupplier;
    private javax.swing.JLabel jLabelBarangTanggal;
    private javax.swing.JLabel jLabelTransaksiIdBarang;
    private javax.swing.JLabel jLabelTransaksiQty;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPaneSupplier;
    private javax.swing.JLabel labelFilter;
    private javax.swing.JLabel labelFilterRetur;
    private javax.swing.JLabel labelFilterSupplier;
    private javax.swing.JLabel labelInfo;
    private javax.swing.JLabel labelInfoRetur;
    private javax.swing.JLabel labelInfoSupplier;
    private javax.swing.JLabel labelInfoTransaksi;
    private javax.swing.JLabel labelKolom;
    private javax.swing.JLabel labelKolomRetur;
    private javax.swing.JLabel labelKolomSupplier;
    private javax.swing.JLabel labelTest;
    private javax.swing.JLabel labelTransaksiAtas;
    private javax.swing.JPanel panelAtas;
    private javax.swing.JPanel panelAtasRetur;
    private javax.swing.JPanel panelAtasSupplier;
    private javax.swing.JPanel panelAtasTransaksi;
    private javax.swing.JPanel panelBawah;
    private javax.swing.JPanel panelBawahRetur;
    private javax.swing.JPanel panelBawahSupplier;
    private javax.swing.JPanel panelBawahTransaksi;
    private javax.swing.JPanel panelKiri;
    private javax.swing.JPanel panelKiriRetur;
    private javax.swing.JPanel panelKiriSupplier;
    private javax.swing.JPanel panelKiriTransaksi;
    private javax.swing.JScrollPane panelScrollTabel;
    private javax.swing.JPanel panelTengah;
    private javax.swing.JPanel panelTengahRetur;
    private javax.swing.JPanel panelTengahSupplier;
    private javax.swing.JPanel panelTengahTransaksi;
    private javax.swing.JTabbedPane panelUtama;
    private javax.swing.JScrollPane scrollPaneRetur;
    private javax.swing.JScrollPane scrollPaneSupplier;
    private javax.swing.JScrollPane scrollPaneTransaksi;
    private javax.swing.JTextField searchField;
    private javax.swing.JTextField searchFieldRetur;
    private javax.swing.JTextField searchFieldSupplier;
    private javax.swing.JPanel tabReport;
    private javax.swing.JPanel tabRetur;
    private javax.swing.JPanel tabStok;
    private javax.swing.JPanel tabSupplier;
    private javax.swing.JPanel tabTrans;
    private javax.swing.JTable tabel;
    private javax.swing.JTable tabelRetur;
    private javax.swing.JTable tabelSupplier;
    private javax.swing.JTable tabelTransaksi;
    private javax.swing.JTextArea textAreaCatatan;
    private javax.swing.JTextArea textAreaKiri;
    private javax.swing.JTextArea textAreaKiriRetur;
    private javax.swing.JTextArea textAreaKiriSupplier;
    private javax.swing.JTextArea textAreaKiriTransaksi;
    private javax.swing.JButton tombolAtc;
    private javax.swing.JButton tombolCheckoutTransaksi;
    private javax.swing.JButton tombolClearField;
    private javax.swing.JButton tombolClearRetur;
    private javax.swing.JButton tombolClearSupplier;
    private javax.swing.JButton tombolHapus;
    private javax.swing.JButton tombolHapusRetur;
    private javax.swing.JButton tombolHapusSupplier;
    private javax.swing.JButton tombolTambah;
    private javax.swing.JButton tombolTambahRetur;
    private javax.swing.JButton tombolTambahSupplier;
    private javax.swing.JButton tombolUbah;
    private javax.swing.JButton tombolUbahSupplier;
    private com.toedter.calendar.JYearChooser yearChooser;
    // End of variables declaration//GEN-END:variables
// </editor-fold>

}
