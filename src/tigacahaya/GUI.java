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
import javax.swing.UIManager;
import static tigacahaya.TableRowContent.tableContent;
import static tigacahaya.TableRowContentRetur.tableContentRetur;
import static tigacahaya.TableRowContentSupplier.tableContentSupplier;
import static tigacahaya.TableRowContentTransaksi.statusInvoiceAkhir;
import static tigacahaya.TableRowContentTransaksi.tableContentTransaksi;

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
         //   System.out.println("klasOOOOOOOOOOOOOOOOOkaskaljskjas");
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
        model.setRowCount(0);
        displayTable();
    }
    public void refreshTableTransaksi() {
        DefaultTableModel model = (DefaultTableModel) tabelTransaksi.getModel();
        model.setRowCount(0);
        displayTableTransaksi();
        
    }
    public void refreshTableRetur() {
        DefaultTableModel model = (DefaultTableModel) tabelRetur.getModel();
        model.setRowCount(0);
        displayTableRetur();
    }
    public void refreshTableSupplier() {
        DefaultTableModel model = (DefaultTableModel) tabelSupplier.getModel();
        model.setRowCount(0);
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
    // execute SQL query
    public void execSQLQuery(String query, String message) {
        Connection conn = MySQLconn.connect();
        Statement st;
        try {
            st = conn.createStatement();
            if ((st.executeUpdate(query)) == 1) {
                JOptionPane.showMessageDialog(null, "Data berhasil " + message);
            } else {
                JOptionPane.showMessageDialog(null, "Data gagal " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public boolean regexIdBarang(String statement){
        if (statement.matches("\\bP[0-9][0-9][0-9][0-9][0-9][0-9][0-9]\\b")){
            return true;   
        }
        else {
            JOptionPane.showMessageDialog(null, "ID Barang salah");
            return false;   
        }
    }
    public boolean regexBilangan(String statement, String namaField){
                if ((statement.matches("\\d+"))||(statement.matches("^([+-]?\\d*\\.?\\d*)$"))){
            return true;   
        }
        else {
            JOptionPane.showMessageDialog(null, namaField+" harus angka!");
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
        buttonRefreshDB = new javax.swing.JToggleButton();
        textAreaKiri = new javax.swing.JTextArea();
        panelBawah = new javax.swing.JPanel();
        fieldInputId_barang = new javax.swing.JTextField();
        fieldInputJenis = new javax.swing.JTextField();
        fieldInputMerk = new javax.swing.JTextField();
        fieldInputRagam = new javax.swing.JTextField();
        fieldInputSeri = new javax.swing.JTextField();
        fieldInputQty = new javax.swing.JTextField();
        fieldInputHarga_beli = new javax.swing.JTextField();
        fieldInputSupplier = new javax.swing.JTextField();
        fieldInputTanggal_masuk = new javax.swing.JTextField();
        comboBoxGaransi = new javax.swing.JComboBox<>();
        tombolUbah = new javax.swing.JButton();
        tombolTambah = new javax.swing.JButton();
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
        panelAtas = new javax.swing.JPanel();
        searchField = new javax.swing.JTextField();
        comboBoxKolom = new javax.swing.JComboBox<>();
        labelFilter = new javax.swing.JLabel();
        labelKolom = new javax.swing.JLabel();
        panelTengah = new javax.swing.JPanel();
        panelScrollTabel = new javax.swing.JScrollPane();
        tabel = new javax.swing.JTable();
        tabTrans = new javax.swing.JPanel();
        panelKiriTransaksi = new javax.swing.JPanel();
        labelInfoTransaksi = new javax.swing.JLabel();
        buttonRefreshDBTransaksi = new javax.swing.JToggleButton();
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
        panelTengahTransaksi = new javax.swing.JPanel();
        scrollPaneTransaksi = new javax.swing.JScrollPane();
        tabelTransaksi = new javax.swing.JTable();
        tabSupplier = new javax.swing.JPanel();
        panelKiriSupplier = new javax.swing.JPanel();
        labelInfoSupplier = new javax.swing.JLabel();
        buttonRefreshDBSupplier = new javax.swing.JToggleButton();
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
        panelTengahSupplier = new javax.swing.JPanel();
        scrollPaneSupplier = new javax.swing.JScrollPane();
        tabelSupplier = new javax.swing.JTable();
        tabRetur = new javax.swing.JPanel();
        panelKiriRetur = new javax.swing.JPanel();
        labelInfoRetur = new javax.swing.JLabel();
        buttonRefreshDBRetur = new javax.swing.JToggleButton();
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
        panelTengahRetur = new javax.swing.JPanel();
        scrollPaneRetur = new javax.swing.JScrollPane();
        tabelRetur = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelUtama.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        tabStok.setBackground(new java.awt.Color(73, 138, 192));

        panelKiri.setBackground(new java.awt.Color(8, 75, 127));
        panelKiri.setPreferredSize(new java.awt.Dimension(154, 250));

        labelInfo.setBackground(new java.awt.Color(255, 255, 255));
        labelInfo.setForeground(new java.awt.Color(255, 255, 255));
        labelInfo.setText("Info");

        buttonRefreshDB.setText("Refresh Data");
        buttonRefreshDB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshDBActionPerformed(evt);
            }
        });

        textAreaKiri.setEditable(false);
        textAreaKiri.setBackground(new java.awt.Color(8, 75, 127));
        textAreaKiri.setColumns(20);
        textAreaKiri.setForeground(new java.awt.Color(255, 255, 255));
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
                .addGroup(panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textAreaKiri, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(labelInfo)
                    .addComponent(buttonRefreshDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKiriLayout.setVerticalGroup(
            panelKiriLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaKiri, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRefreshDB)
                .addContainerGap())
        );

        panelBawah.setBackground(new java.awt.Color(73, 139, 201));
        panelBawah.setPreferredSize(new java.awt.Dimension(285, 126));

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

        fieldInputJenis.setText("Jenis");
        fieldInputJenis.setToolTipText("Jenis atau tipe barang");
        fieldInputJenis.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputJenisFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputJenisFocusLost(evt);
            }
        });
        fieldInputJenis.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldInputJenisKeyTyped(evt);
            }
        });

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

        fieldInputRagam.setText("Ragam");
        fieldInputRagam.setToolTipText("Ragam atau sub-kategori barang");
        fieldInputRagam.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputRagamFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputRagamFocusLost(evt);
            }
        });
        fieldInputRagam.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldInputRagamKeyTyped(evt);
            }
        });

        fieldInputSeri.setText("Seri");
        fieldInputSeri.setToolTipText("Keterangan lengkap dari ragam yang ada");
        fieldInputSeri.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputSeriFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputSeriFocusLost(evt);
            }
        });
        fieldInputSeri.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldInputSeriKeyTyped(evt);
            }
        });

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

        fieldInputSupplier.setText("Supplier");
        fieldInputSupplier.setToolTipText("Pemasok, distributor atau sumber barang, kode bisa dilihat di tab supplier");
        fieldInputSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputSupplierFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputSupplierFocusLost(evt);
            }
        });
        fieldInputSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                fieldInputSupplierKeyTyped(evt);
            }
        });

        fieldInputTanggal_masuk.setText("Tanggal Masuk");
        fieldInputTanggal_masuk.setToolTipText("Otomatis sesuai penginputan barang (tidak perlu diatur/ubah)");
        fieldInputTanggal_masuk.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputTanggal_masukFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputTanggal_masukFocusLost(evt);
            }
        });

        comboBoxGaransi.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "distributor", "toko", "tidak" }));
        comboBoxGaransi.setToolTipText("ketentuan garansi");

        tombolUbah.setText("Ubah");
        tombolUbah.setToolTipText("Edit atau ubah barang sesuai dengan masukan yang ada.");
        tombolUbah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolUbahActionPerformed(evt);
            }
        });

        tombolTambah.setText("Tambah");
        tombolTambah.setToolTipText("Tambah barang sesuai dengan masukan yang ada.");
        tombolTambah.setPreferredSize(new java.awt.Dimension(80, 30));
        tombolTambah.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolTambahMouseClicked(evt);
            }
        });

        tombolAtc.setText("add to\ncart");
        tombolAtc.setToolTipText("");
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

        tombolClearField.setText("Clear");
        tombolClearField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolClearFieldActionPerformed(evt);
            }
        });

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

        javax.swing.GroupLayout panelBawahLayout = new javax.swing.GroupLayout(panelBawah);
        panelBawah.setLayout(panelBawahLayout);
        panelBawahLayout.setHorizontalGroup(
            panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBawahLayout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(fieldInputJenis)
                    .addComponent(jLabelBarangJenis)
                    .addComponent(jLabelBarangIdBarang)
                    .addComponent(fieldInputId_barang, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(fieldInputSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelBarangSupplier)
                            .addComponent(jLabelBarangRagam))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelBarangSeri)
                            .addGroup(panelBawahLayout.createSequentialGroup()
                                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabelBarangTanggal)
                                    .addComponent(fieldInputTanggal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10)
                                .addGroup(panelBawahLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelBawahLayout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addComponent(jLabelBarangGaransi))
                                    .addComponent(comboBoxGaransi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
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
                            .addComponent(fieldInputSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldInputQty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldInputHarga_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fieldInputTanggal_masuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxGaransi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                            .addComponent(fieldInputJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addContainerGap(15, Short.MAX_VALUE))
        );

        panelAtas.setBackground(new java.awt.Color(25, 104, 178));

        searchField.setText("search...");
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFieldFocusLost(evt);
            }
        });
        searchField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchFieldMouseClicked(evt);
            }
        });
        searchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldKeyReleased(evt);
            }
        });

        comboBoxKolom.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Coloumn", "ID Barang", "Jenis", "Merk", "Ragam", "Seri", "Qty", "Harga Beli", "Supplier", "Tanggal Masuk", "Garansi" }));

        labelFilter.setText("Filter");

        labelKolom.setText("Kolom");

        javax.swing.GroupLayout panelAtasLayout = new javax.swing.GroupLayout(panelAtas);
        panelAtas.setLayout(panelAtasLayout);
        panelAtasLayout.setHorizontalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelFilter)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboBoxKolom, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelKolom))
                .addContainerGap())
        );
        panelAtasLayout.setVerticalGroup(
            panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilter)
                    .addComponent(labelKolom))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAtasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxKolom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelTengah.setBackground(new java.awt.Color(73, 139, 201));

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
        });
        tabel.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tabel.setShowHorizontalLines(false);
        tabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelMouseClicked(evt);
            }
        });
        panelScrollTabel.setViewportView(tabel);
        if (tabel.getColumnModel().getColumnCount() > 0) {
            tabel.getColumnModel().getColumn(5).setMaxWidth(40);
        }

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
                    .addComponent(panelKiri, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(panelTengah, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBawah, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelUtama.addTab("Stok", tabStok);

        tabTrans.setBackground(new java.awt.Color(51, 204, 255));

        panelKiriTransaksi.setBackground(new java.awt.Color(255, 204, 102));
        panelKiriTransaksi.setPreferredSize(new java.awt.Dimension(154, 250));

        labelInfoTransaksi.setText("Info");

        buttonRefreshDBTransaksi.setText("Refresh Data");
        buttonRefreshDBTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshDBTransaksiActionPerformed(evt);
            }
        });

        textAreaKiriTransaksi.setColumns(20);
        textAreaKiriTransaksi.setLineWrap(true);
        textAreaKiriTransaksi.setRows(5);
        textAreaKiriTransaksi.setWrapStyleWord(true);

        javax.swing.GroupLayout panelKiriTransaksiLayout = new javax.swing.GroupLayout(panelKiriTransaksi);
        panelKiriTransaksi.setLayout(panelKiriTransaksiLayout);
        panelKiriTransaksiLayout.setHorizontalGroup(
            panelKiriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKiriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textAreaKiriTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(labelInfoTransaksi)
                    .addComponent(buttonRefreshDBTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKiriTransaksiLayout.setVerticalGroup(
            panelKiriTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriTransaksiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInfoTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaKiriTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRefreshDBTransaksi)
                .addContainerGap())
        );

        panelBawahTransaksi.setBackground(new java.awt.Color(0, 153, 153));
        panelBawahTransaksi.setPreferredSize(new java.awt.Dimension(285, 126));

        fieldIdBarangTransaksi.setText("ID Barang");
        fieldIdBarangTransaksi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldIdBarangTransaksiFocusGained(evt);
            }
        });

        fieldInputQtyTransaksi.setText("Qty");
        fieldInputQtyTransaksi.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputQtyTransaksiFocusGained(evt);
            }
        });

        tombolCheckoutTransaksi.setText("Checkout");
        tombolCheckoutTransaksi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolCheckoutTransaksiActionPerformed(evt);
            }
        });

        jLabelTransaksiIdBarang.setText("ID Barang");

        jLabelTransaksiQty.setText("Quantity");

        buttonTransaksiHapus.setText("Hapus");
        buttonTransaksiHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransaksiHapusActionPerformed(evt);
            }
        });

        buttonUbahQtyTransaksi.setText("Ubah qty");
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

        panelAtasTransaksi.setBackground(new java.awt.Color(0, 204, 153));

        labelTransaksiAtas.setText("Invoice:");

        javax.swing.GroupLayout panelAtasTransaksiLayout = new javax.swing.GroupLayout(panelAtasTransaksi);
        panelAtasTransaksi.setLayout(panelAtasTransaksiLayout);
        panelAtasTransaksiLayout.setHorizontalGroup(
            panelAtasTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasTransaksiLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(labelTransaksiAtas)
                .addContainerGap())
        );
        panelAtasTransaksiLayout.setVerticalGroup(
            panelAtasTransaksiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasTransaksiLayout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(labelTransaksiAtas)
                .addContainerGap())
        );

        panelTengahTransaksi.setBackground(new java.awt.Color(102, 0, 102));

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
        });
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
                    .addComponent(panelKiriTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(panelTengahTransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBawahTransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelUtama.addTab("Transaksi", tabTrans);

        tabSupplier.setBackground(new java.awt.Color(51, 204, 255));
        tabSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabSupplierMouseClicked(evt);
            }
        });

        panelKiriSupplier.setBackground(new java.awt.Color(255, 204, 102));
        panelKiriSupplier.setPreferredSize(new java.awt.Dimension(154, 250));

        labelInfoSupplier.setText("Info");

        buttonRefreshDBSupplier.setText("Refresh Data");
        buttonRefreshDBSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshDBSupplierActionPerformed(evt);
            }
        });

        textAreaKiriSupplier.setColumns(20);
        textAreaKiriSupplier.setLineWrap(true);
        textAreaKiriSupplier.setRows(5);
        textAreaKiriSupplier.setWrapStyleWord(true);

        javax.swing.GroupLayout panelKiriSupplierLayout = new javax.swing.GroupLayout(panelKiriSupplier);
        panelKiriSupplier.setLayout(panelKiriSupplierLayout);
        panelKiriSupplierLayout.setHorizontalGroup(
            panelKiriSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKiriSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textAreaKiriSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(labelInfoSupplier)
                    .addComponent(buttonRefreshDBSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKiriSupplierLayout.setVerticalGroup(
            panelKiriSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInfoSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaKiriSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRefreshDBSupplier)
                .addContainerGap())
        );

        panelBawahSupplier.setBackground(new java.awt.Color(0, 153, 153));
        panelBawahSupplier.setPreferredSize(new java.awt.Dimension(285, 126));

        tombolUbahSupplier.setText("Ubah");
        tombolUbahSupplier.setToolTipText("Edit atau ubah barang sesuai dengan masukan yang ada.");
        tombolUbahSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolUbahSupplierActionPerformed(evt);
            }
        });

        tombolTambahSupplier.setText("Tambah");
        tombolTambahSupplier.setToolTipText("Tambah barang sesuai dengan masukan yang ada.");
        tombolTambahSupplier.setPreferredSize(new java.awt.Dimension(80, 30));
        tombolTambahSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolTambahSupplierMouseClicked(evt);
            }
        });

        tombolHapusSupplier.setText("Hapus");
        tombolHapusSupplier.setToolTipText("Hapus barang sesuai dengan baris yang terpilih.");
        tombolHapusSupplier.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolHapusSupplierActionPerformed(evt);
            }
        });

        textAreaCatatan.setColumns(20);
        textAreaCatatan.setLineWrap(true);
        textAreaCatatan.setRows(5);
        textAreaCatatan.setText("Catatan...");
        textAreaCatatan.setWrapStyleWord(true);
        textAreaCatatan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                textAreaCatatanFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                textAreaCatatanFocusLost(evt);
            }
        });
        jScrollPaneSupplier.setViewportView(textAreaCatatan);

        fieldInputEmailSupplier.setText("Email");
        fieldInputEmailSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputEmailSupplierFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputEmailSupplierFocusLost(evt);
            }
        });

        fieldInputTelponSupplier.setText("Telpon");
        fieldInputTelponSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputTelponSupplierFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputTelponSupplierFocusLost(evt);
            }
        });

        fieldInputAlamatSupplier.setText("Alamat");
        fieldInputAlamatSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputAlamatSupplierFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputAlamatSupplierFocusLost(evt);
            }
        });

        fieldInputIDSupplier.setText("ID");
        fieldInputIDSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputIDSupplierFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputIDSupplierFocusLost(evt);
            }
        });

        jLabel5.setText("Alamat");

        jLabel6.setText("Telpon");

        jLabel7.setText("E-Mail");

        jLabel8.setText("ID Supplier");

        jLabel9.setText("Catatan");

        fieldInputNamaSupplier.setText("Nama");
        fieldInputNamaSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputNamaSupplierFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputNamaSupplierFocusLost(evt);
            }
        });

        jLabel4.setText("Nama");

        tombolClearSupplier.setText("Clear");
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
                .addContainerGap(82, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(fieldInputAlamatSupplier)
                            .addComponent(fieldInputTelponSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                    .addComponent(tombolUbahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tombolTambahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBawahSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tombolClearSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tombolHapusSupplier, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        panelAtasSupplier.setBackground(new java.awt.Color(0, 204, 153));

        searchFieldSupplier.setText("search...");
        searchFieldSupplier.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFieldSupplierFocusLost(evt);
            }
        });
        searchFieldSupplier.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchFieldSupplierMouseClicked(evt);
            }
        });
        searchFieldSupplier.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldSupplierKeyReleased(evt);
            }
        });

        comboBoxKolomSupplier.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Coloumn", "ID Supplier", "Nama", "Alamat", "Telpon", "Email", "Catatan" }));

        labelFilterSupplier.setText("Filter");

        labelKolomSupplier.setText("Kolom");

        javax.swing.GroupLayout panelAtasSupplierLayout = new javax.swing.GroupLayout(panelAtasSupplier);
        panelAtasSupplier.setLayout(panelAtasSupplierLayout);
        panelAtasSupplierLayout.setHorizontalGroup(
            panelAtasSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasSupplierLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasSupplierLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAtasSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilterSupplier)
                    .addComponent(labelKolomSupplier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAtasSupplierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchFieldSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxKolomSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelTengahSupplier.setBackground(new java.awt.Color(102, 0, 102));

        scrollPaneSupplier.setPreferredSize(new java.awt.Dimension(453, 60));

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
            }
        });
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
                    .addComponent(panelKiriSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(panelTengahSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBawahSupplier, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelUtama.addTab("Supplier", tabSupplier);

        tabRetur.setBackground(new java.awt.Color(51, 204, 255));

        panelKiriRetur.setBackground(new java.awt.Color(255, 204, 102));
        panelKiriRetur.setPreferredSize(new java.awt.Dimension(154, 250));

        labelInfoRetur.setText("Info");

        buttonRefreshDBRetur.setText("Refresh Data");
        buttonRefreshDBRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRefreshDBReturActionPerformed(evt);
            }
        });

        textAreaKiriRetur.setColumns(20);
        textAreaKiriRetur.setLineWrap(true);
        textAreaKiriRetur.setRows(5);
        textAreaKiriRetur.setWrapStyleWord(true);

        javax.swing.GroupLayout panelKiriReturLayout = new javax.swing.GroupLayout(panelKiriRetur);
        panelKiriRetur.setLayout(panelKiriReturLayout);
        panelKiriReturLayout.setHorizontalGroup(
            panelKiriReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriReturLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKiriReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textAreaKiriRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(labelInfoRetur)
                    .addComponent(buttonRefreshDBRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelKiriReturLayout.setVerticalGroup(
            panelKiriReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKiriReturLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelInfoRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textAreaKiriRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonRefreshDBRetur)
                .addContainerGap())
        );

        panelBawahRetur.setBackground(new java.awt.Color(0, 153, 153));
        panelBawahRetur.setPreferredSize(new java.awt.Dimension(285, 126));

        tombolTambahRetur.setText("Tambah");
        tombolTambahRetur.setToolTipText("Tambah barang sesuai dengan masukan yang ada.");
        tombolTambahRetur.setPreferredSize(new java.awt.Dimension(80, 30));
        tombolTambahRetur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tombolTambahReturMouseClicked(evt);
            }
        });

        tombolHapusRetur.setText("Hapus");
        tombolHapusRetur.setToolTipText("Hapus barang sesuai dengan baris yang terpilih.");
        tombolHapusRetur.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tombolHapusReturActionPerformed(evt);
            }
        });

        fieldInputJumlahBarangRetur.setText("Jumlah");
        fieldInputJumlahBarangRetur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputJumlahBarangReturFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputJumlahBarangReturFocusLost(evt);
            }
        });

        fieldInputIdRetur.setText("ID Retur");
        fieldInputIdRetur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputIdReturFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputIdReturFocusLost(evt);
            }
        });

        fieldInputIdBarangRetur.setText("ID");
        fieldInputIdBarangRetur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                fieldInputIdBarangReturFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                fieldInputIdBarangReturFocusLost(evt);
            }
        });

        jLabel1.setText("ID Retur");

        jLabel2.setText("ID Barang");

        jLabel3.setText("Quantity");

        tombolClearRetur.setText("Clear");
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
                        .addComponent(tombolTambahRetur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tombolHapusRetur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tombolClearRetur, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        panelBawahReturLayout.setVerticalGroup(
            panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBawahReturLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBawahReturLayout.createSequentialGroup()
                        .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldInputIdRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldInputIdBarangRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(2, 2, 2)
                        .addGroup(panelBawahReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(fieldInputJumlahBarangRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(tombolClearRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBawahReturLayout.createSequentialGroup()
                        .addComponent(tombolTambahRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tombolHapusRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        panelAtasRetur.setBackground(new java.awt.Color(0, 204, 153));

        searchFieldRetur.setText("search...");
        searchFieldRetur.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFieldReturFocusLost(evt);
            }
        });
        searchFieldRetur.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchFieldReturMouseClicked(evt);
            }
        });
        searchFieldRetur.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchFieldReturKeyReleased(evt);
            }
        });

        comboBoxKolomRetur.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All Coloumn", "ID Retur", "ID Barang", "Nama", "Jumlah" }));

        labelFilterRetur.setText("Filter");

        labelKolomRetur.setText("Kolom");

        javax.swing.GroupLayout panelAtasReturLayout = new javax.swing.GroupLayout(panelAtasRetur);
        panelAtasRetur.setLayout(panelAtasReturLayout);
        panelAtasReturLayout.setHorizontalGroup(
            panelAtasReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasReturLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAtasReturLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAtasReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFilterRetur)
                    .addComponent(labelKolomRetur))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelAtasReturLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchFieldRetur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxKolomRetur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelTengahRetur.setBackground(new java.awt.Color(102, 0, 102));

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
            }
        });
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
                    .addComponent(panelKiriRetur, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                    .addComponent(panelTengahRetur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelBawahRetur, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        panelUtama.addTab("Retur", tabRetur);

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
        fieldInputJenis.setText(model.getValueAt(i, 1).toString());
        fieldInputJenis.setCaretPosition(0);
        fieldInputMerk.setText(model.getValueAt(i, 2).toString());
        fieldInputMerk.setCaretPosition(0);
        fieldInputRagam.setText(model.getValueAt(i, 3).toString());
        fieldInputRagam.setCaretPosition(0);
        fieldInputSeri.setText(model.getValueAt(i, 4).toString());
        fieldInputSeri.setCaretPosition(0);
        fieldInputQty.setText(model.getValueAt(i, 5).toString());
        fieldInputHarga_beli.setText(model.getValueAt(i, 6).toString());
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
        System.out.println(comboBoxGaransi.getSelectedIndex());
        textAreaKiri.setText(model.getValueAt(i, 1).toString() + "\n" + model.getValueAt(i, 2).toString() + "\n" + model.getValueAt(i, 3).toString() + " " + model.getValueAt(i, 4).toString());
        fieldInputSupplier.setText(MySQLconn.executeSingleQueryResult("SELECT supplier FROM `barang` WHERE barang.id_barang='" + (model.getValueAt(i, 0).toString()) + "'", "supplier"));
    }//GEN-LAST:event_tabelMouseClicked
    private void tombolTambahMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahMouseClicked
       
        if (regexIdBarang(fieldInputId_barang.getText())){
            if(regexBilangan(fieldInputQty.getText(),"Qty")||regexBilangan(fieldInputHarga_beli.getText(),"Harga")){
        String query = "INSERT INTO `barang`(`code`, `no`, `id_barang`, `jenis`, `merk`, `ragam`, `seri`, `qty`, `harga_beli`, `supplier`, `tgl_masuk`, `garansi`) VALUES('P',NULL,NULL,'" + fieldInputJenis.getText() + "','" + fieldInputMerk.getText() + "','" + fieldInputRagam.getText() + "','" + fieldInputSeri.getText() + "','" + fieldInputQty.getText() + "','" + fieldInputHarga_beli.getText() + "','" + fieldInputSupplier.getText() + "',CURRENT_TIMESTAMP,'" + comboBoxGaransi.getSelectedItem() + "')";
        execSQLQuery(query, "ditambahkan");
        MySQLconn.executeVoidQuery("UPDATE barang SET id_barang = concat( code,LPAD(barang.`no`, 7,\"000\") );");
        System.out.println(query);
        refreshTable();
            }
        }
        
    }//GEN-LAST:event_tombolTambahMouseClicked
    private void tombolUbahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolUbahActionPerformed
if (regexIdBarang(fieldInputId_barang.getText())){
    if(regexBilangan(fieldInputQty.getText(),"Qty")||regexBilangan(fieldInputHarga_beli.getText(),"Harga")){
        String query = "UPDATE `barang` SET `jenis`= '" + fieldInputJenis.getText() + "',`merk`='" + fieldInputMerk.getText() + "',`ragam`='" + fieldInputRagam.getText() + "',`seri`='" + fieldInputSeri.getText() + "',`qty`='" + fieldInputQty.getText() + "',`harga_beli`='" + fieldInputHarga_beli.getText() + "',`supplier`='" + fieldInputSupplier.getText() + "',`garansi`='" + (String) comboBoxGaransi.getSelectedItem() + "' WHERE `id_barang`='" + fieldInputId_barang.getText() + "';";

        execSQLQuery(query, "diubah");
        MySQLconn.executeVoidQuery("UPDATE barang SET id_barang = concat( code,LPAD(barang.`no`, 7,\"000\") );");

        System.out.println(query);
        refreshTable();
}}
    }//GEN-LAST:event_tombolUbahActionPerformed
    private void tombolHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusActionPerformed
if (regexIdBarang(fieldInputId_barang.getText())){

        String query = "DELETE from `barang` WHERE `id_barang`='" + fieldInputId_barang.getText() + "';";
        execSQLQuery(query, "" + "dihapus");
        System.out.println(query);
        refreshTable();
}
    }//GEN-LAST:event_tombolHapusActionPerformed
    private void buttonRefreshDBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshDBActionPerformed

        MySQLconn.executeVoidQuery("UPDATE barang SET id_barang = concat( code,LPAD(barang.`no`, 7,\"000\"))");
        refreshTable();
        JOptionPane.showMessageDialog(null, "Data telah direfresh");
        TableRowContentTransaksi.statusInvoiceAkhir();
       // System.out.println(TableRowContentTransaksi.statusInvoiceAkhir());
    }//GEN-LAST:event_buttonRefreshDBActionPerformed
    private void fieldInputId_barangKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputId_barangKeyTyped
        if (fieldInputId_barang.getText().length() > 10) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputId_barangKeyTyped
    private void fieldInputJenisKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputJenisKeyTyped
        if (fieldInputJenis.getText().length() > 16) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputJenisKeyTyped
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
    private void fieldInputSupplierKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fieldInputSupplierKeyTyped
        if (fieldInputSupplier.getText().length() > 3) {
            evt.consume();
        }
    }//GEN-LAST:event_fieldInputSupplierKeyTyped
    private void searchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldKeyReleased
        String query = searchField.getText();
        filterTable(query, comboBoxKolom.getSelectedIndex());
    }//GEN-LAST:event_searchFieldKeyReleased
    private void searchFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchFieldMouseClicked
        if ("search...".equals(searchField.getText())) {
            searchField.setText("");
        }
    }//GEN-LAST:event_searchFieldMouseClicked
    private void searchFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldFocusLost
        if ((searchField.getText()).equals("")) {
            searchField.setText("search...");
        }
    }//GEN-LAST:event_searchFieldFocusLost
    private void buttonRefreshDBTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshDBTransaksiActionPerformed
 
        refreshTableTransaksi();
        JOptionPane.showMessageDialog(null, "Data telah direfresh");
    }//GEN-LAST:event_buttonRefreshDBTransaksiActionPerformed
    private void buttonRefreshDBSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshDBSupplierActionPerformed
        
        refreshTableSupplier();
        JOptionPane.showMessageDialog(null, "Data telah direfresh");
    }//GEN-LAST:event_buttonRefreshDBSupplierActionPerformed
    private void tombolTambahSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahSupplierMouseClicked
        if (fieldInputEmailSupplier.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")|| fieldInputEmailSupplier.getText().equals("")) {
            if (fieldInputTelponSupplier.getText().matches("\\d+")|| fieldInputTelponSupplier.getText().equals("")) {

                String query = "INSERT INTO `supplier`(`nama`, `alamat`, `telp`, `email`, `catatan`) VALUES ('" + fieldInputNamaSupplier.getText() + "','" + fieldInputAlamatSupplier.getText() + "','" + fieldInputTelponSupplier.getText() + "','" + fieldInputEmailSupplier.getText() + "','" + textAreaCatatan.getText() + "')";
                execSQLQuery(query, "ditambahkan");
                System.out.println(query);
                refreshTableSupplier();
            } else {
                JOptionPane.showMessageDialog(null, "Telpon tidak valid");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Email tidak valid");
        }

    }//GEN-LAST:event_tombolTambahSupplierMouseClicked
    private void tombolHapusSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolHapusSupplierActionPerformed
        String query = "DELETE from `supplier` WHERE `id_supplier`='" + fieldInputIDSupplier.getText() + "';";
        execSQLQuery(query, "" + "dihapus");
        System.out.println(query);
        refreshTableSupplier();
    }//GEN-LAST:event_tombolHapusSupplierActionPerformed
    private void searchFieldSupplierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFieldSupplierFocusLost
    if ((searchFieldSupplier.getText()).equals("")) {
            searchFieldSupplier.setText("search...");}
    }//GEN-LAST:event_searchFieldSupplierFocusLost
    private void searchFieldSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchFieldSupplierMouseClicked
         if ("search...".equals(searchFieldSupplier.getText())) {
            searchFieldSupplier.setText("");
         }
    }//GEN-LAST:event_searchFieldSupplierMouseClicked
    private void searchFieldSupplierKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldSupplierKeyReleased
        String query = searchFieldSupplier.getText();
        filterTableSupplier(query, comboBoxKolomSupplier.getSelectedIndex());
    }//GEN-LAST:event_searchFieldSupplierKeyReleased
    private void tombolUbahSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolUbahSupplierActionPerformed
        if (fieldInputIDSupplier.getText().matches("\\d+")) {
            if (fieldInputEmailSupplier.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")|| fieldInputEmailSupplier.getText().equals("")) {
                if (fieldInputTelponSupplier.getText().matches("\\d+")|| fieldInputTelponSupplier.getText().equals("")) {
                    String query = "UPDATE `supplier` SET `nama`= '" + fieldInputNamaSupplier.getText() + "',`alamat`='" + fieldInputAlamatSupplier.getText() + "',`telp`='" + fieldInputTelponSupplier.getText() + "',`email`='" + fieldInputEmailSupplier.getText() + "',`catatan`='" + textAreaCatatan.getText() + "' WHERE `id_supplier`='" + fieldInputIDSupplier.getText() + "';";

                    execSQLQuery(query, "diubah");
                    System.out.println(query);
                    refreshTableSupplier();
                } else {
                    JOptionPane.showMessageDialog(null, "Telpon tidak valid");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Email tidak valid");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Supplier ID salah");
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

        if (fieldInputId_barang.getText().equals("")){
       fieldInputId_barang.setText("ID");
     }
    }//GEN-LAST:event_fieldInputId_barangFocusLost
    private void fieldInputJenisFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputJenisFocusGained

        if(fieldInputJenis.getText().equals("Jenis")){
      fieldInputJenis.setText("");
      }
    }//GEN-LAST:event_fieldInputJenisFocusGained
    private void fieldInputJenisFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputJenisFocusLost

        if (fieldInputJenis.getText().equals("")){
       fieldInputJenis.setText("Jenis");
     }
    }//GEN-LAST:event_fieldInputJenisFocusLost
    private void fieldInputMerkFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputMerkFocusGained

         if(fieldInputMerk.getText().equals("Merk")){
      fieldInputMerk.setText("");
      }
    }//GEN-LAST:event_fieldInputMerkFocusGained
    private void fieldInputMerkFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputMerkFocusLost

         if (fieldInputMerk.getText().equals("")){
       fieldInputMerk.setText("Merk");
     }
    }//GEN-LAST:event_fieldInputMerkFocusLost
    private void fieldInputRagamFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputRagamFocusGained

        if(fieldInputRagam.getText().equals("Ragam")){
      fieldInputRagam.setText("");
      }
    }//GEN-LAST:event_fieldInputRagamFocusGained
    private void fieldInputRagamFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputRagamFocusLost

        if (fieldInputRagam.getText().equals("")){
       fieldInputRagam.setText("Ragam");
     }
    }//GEN-LAST:event_fieldInputRagamFocusLost
    private void fieldInputSeriFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputSeriFocusGained

        if(fieldInputSeri.getText().equals("Seri")){
      fieldInputSeri.setText("");
      }
    }//GEN-LAST:event_fieldInputSeriFocusGained
    private void fieldInputSeriFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputSeriFocusLost

        if (fieldInputSeri.getText().equals("")){
       fieldInputSeri.setText("Seri");
     }
    }//GEN-LAST:event_fieldInputSeriFocusLost
    private void fieldInputQtyFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputQtyFocusGained

         if(fieldInputQty.getText().equals("Qty")){
      fieldInputQty.setText("");
      }
    }//GEN-LAST:event_fieldInputQtyFocusGained
    private void fieldInputQtyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputQtyFocusLost

        if (fieldInputQty.getText().equals("")){
       fieldInputQty.setText("Qty");
     }
    }//GEN-LAST:event_fieldInputQtyFocusLost
    private void fieldInputHarga_beliFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputHarga_beliFocusGained

        if(fieldInputHarga_beli.getText().equals("Hrg Beli")){
      fieldInputHarga_beli.setText("");
      }
    }//GEN-LAST:event_fieldInputHarga_beliFocusGained
    private void fieldInputHarga_beliFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputHarga_beliFocusLost

        if (fieldInputHarga_beli.getText().equals("")){
       fieldInputHarga_beli.setText("Hrg Beli");
     }
    }//GEN-LAST:event_fieldInputHarga_beliFocusLost
    private void fieldInputSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputSupplierFocusGained

         if(fieldInputSupplier.getText().equals("Supplier")){
      fieldInputSupplier.setText("");
      }
    }//GEN-LAST:event_fieldInputSupplierFocusGained
    private void fieldInputSupplierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputSupplierFocusLost

         if (fieldInputSupplier.getText().equals("")){
       fieldInputSupplier.setText("Supplier");
     }
    }//GEN-LAST:event_fieldInputSupplierFocusLost
    private void fieldInputTanggal_masukFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputTanggal_masukFocusGained

         if(fieldInputTanggal_masuk.getText().equals("Tanggal Masuk")){
      fieldInputTanggal_masuk.setText("");
      }
    }//GEN-LAST:event_fieldInputTanggal_masukFocusGained
    private void fieldInputTanggal_masukFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputTanggal_masukFocusLost

        if (fieldInputTanggal_masuk.getText().equals("")){
       fieldInputTanggal_masuk.setText("Tanggal Masuk");
     }
    }//GEN-LAST:event_fieldInputTanggal_masukFocusLost
    private void fieldInputIDSupplierFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputIDSupplierFocusGained

        if(fieldInputIDSupplier.getText().equals("ID")){
      fieldInputIDSupplier.setText("");
      }
    }//GEN-LAST:event_fieldInputIDSupplierFocusGained
    private void fieldInputIDSupplierFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputIDSupplierFocusLost

        if (fieldInputIDSupplier.getText().equals("")){
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
    private void textAreaCatatanFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textAreaCatatanFocusGained

        if (textAreaCatatan.getText().equals("Catatan...")) {
            textAreaCatatan.setText("");
        }
    }//GEN-LAST:event_textAreaCatatanFocusGained
    private void textAreaCatatanFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textAreaCatatanFocusLost

        if (textAreaCatatan.getText().equals("")) {
            textAreaCatatan.setText("Catatan...");
        }
    }//GEN-LAST:event_textAreaCatatanFocusLost
    private void tabSupplierMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabSupplierMouseClicked
        // Menampilkan row yang dipilih di table ke text field
        int i = tabelSupplier.getSelectedRow();
        TableModel model = tabelSupplier.getModel();
        fieldInputIDSupplier.setText(model.getValueAt(i, 0).toString());
        fieldInputNamaSupplier.setText(model.getValueAt(i, 1).toString());
        fieldInputAlamatSupplier.setText(model.getValueAt(i, 2).toString());
        fieldInputTelponSupplier.setText(model.getValueAt(i, 3).toString());
        fieldInputEmailSupplier.setText(model.getValueAt(i, 4).toString());
        textAreaCatatan.setText(model.getValueAt(i, 5).toString());
    }//GEN-LAST:event_tabSupplierMouseClicked

    private void tombolAtcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolAtcActionPerformed
        ArrayList<TableRowContentTransaksi> list = tableContentTransaksi();
        String query = "null";
        if (("empty".equals(statusInvoiceAkhir())) || ("paid".equals(statusInvoiceAkhir()))) {
            TableRowContentTransaksi.tambahInvoice();
            query = "INSERT INTO `transaksi_cart` (`id_inv`, `id_barang`, `qty`, `harga`, `keterangan`) VALUES ('" + statusInvoiceAkhir() + "', '" + fieldInputId_barang.getText() + "', '1', '" + fieldInputHarga_beli.getText() + "', '');";
        } else {
            boolean Duplicate = false;
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getIdBarang().equals(fieldInputId_barang.getText())) {
                    query = "UPDATE `transaksi_cart` SET `qty` = `qty`+1 WHERE `transaksi_cart`.`id_barang` = '" + fieldInputId_barang.getText() + "' AND `transaksi_cart`.`id_inv` ='" + statusInvoiceAkhir() + "';";
                    Duplicate = true;
                }
            }
            if (!Duplicate) {
                query = "INSERT INTO `transaksi_cart` (`id_inv`, `id_barang`, `qty`, `harga`, `keterangan`) VALUES ('" + statusInvoiceAkhir() + "', '" + fieldInputId_barang.getText() + "', '1', '" + fieldInputHarga_beli.getText() + "', '');";
                Duplicate = false;
            }
        }
        System.out.println(query);
        MySQLconn.executeVoidQuery(query);
        refreshTableTransaksi();


    }//GEN-LAST:event_tombolAtcActionPerformed

    private void tabelTransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelTransaksiMouseClicked
        int i = tabelTransaksi.getSelectedRow();
        TableModel model = tabelTransaksi.getModel();
        i = tabelTransaksi.convertRowIndexToModel(i);
        fieldIdBarangTransaksi.setText(model.getValueAt(i, 0).toString());
        fieldIdBarangTransaksi.setCaretPosition(0);
        fieldInputQtyTransaksi.setText(model.getValueAt(i, 2).toString());
    }//GEN-LAST:event_tabelTransaksiMouseClicked

    private void tombolCheckoutTransaksiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolCheckoutTransaksiActionPerformed
        TableModel model = tabelTransaksi.getModel();
        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Cart tidak boleh kosong");
        } else {
            String qtyCol;
            String idBarangCol;
            model = (DefaultTableModel) tabelTransaksi.getModel();
            Object[] row = new Object[3];

            for (int i = 0; i < model.getRowCount(); i++) {
                qtyCol = model.getValueAt(i, 2).toString();
                idBarangCol = model.getValueAt(i, 0).toString();
                MySQLconn.executeVoidQuery("UPDATE `barang` SET `barang`.`qty` = `barang`.`qty`-'" + qtyCol + "' WHERE  `barang`.`id_barang`= '" + idBarangCol + "'");
            }
            String query = "UPDATE `transaksi_invoice` SET `lunas` = b'1' WHERE `transaksi_invoice`.`id_inv` = '" + statusInvoiceAkhir() + "';";
            MySQLconn.executeVoidQuery(query);
            TableRowContentTransaksi.tambahInvoice();

            refreshTableTransaksi();
            refreshTable();
            labelTransaksiAtas.setText("Invoice :" + TableRowContentTransaksi.statusInvoiceAkhir());
        }
    }//GEN-LAST:event_tombolCheckoutTransaksiActionPerformed

    private void tabelReturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelReturMouseClicked

        int i = tabelRetur.getSelectedRow();
        TableModel model = tabelRetur.getModel();
        fieldInputIdRetur.setText(model.getValueAt(i, 0).toString());
        fieldInputIdBarangRetur.setText(model.getValueAt(i, 1).toString());
        fieldInputJumlahBarangRetur.setText(model.getValueAt(i, 3).toString());
    }//GEN-LAST:event_tabelReturMouseClicked

    private void searchFieldReturKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchFieldReturKeyReleased
        String query = searchFieldRetur.getText();
        filterTableRetur(query, comboBoxKolomRetur.getSelectedIndex());
    }//GEN-LAST:event_searchFieldReturKeyReleased

    private void searchFieldReturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchFieldReturMouseClicked
        if ("search...".equals(searchFieldRetur.getText())) {
            searchFieldRetur.setText("");
        }
    }//GEN-LAST:event_searchFieldReturMouseClicked

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
        String query = "DELETE from `barang_retur` WHERE `id_retur`='" + fieldInputIdRetur.getText() + "';";
        execSQLQuery(query, "" + "dihapus");
        System.out.println(query);
        refreshTableRetur();
    }//GEN-LAST:event_tombolHapusReturActionPerformed

    private void tombolTambahReturMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tombolTambahReturMouseClicked
        String query = "INSERT INTO `barang_retur`(`id_barang`, `qty`) VALUES ('" + fieldInputIdBarangRetur.getText() + "','" + fieldInputJumlahBarangRetur.getText() + "')";
        execSQLQuery(query, "ditambahkan");
        System.out.println(query);
        refreshTableRetur();
    }//GEN-LAST:event_tombolTambahReturMouseClicked

    private void buttonRefreshDBReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRefreshDBReturActionPerformed

        refreshTable();
        JOptionPane.showMessageDialog(null, "Data telah direfresh");
    }//GEN-LAST:event_buttonRefreshDBReturActionPerformed

    private void buttonTransaksiHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransaksiHapusActionPerformed
        if (fieldIdBarangTransaksi.getText().matches("\\bP[0-9][0-9][0-9][0-9][0-9][0-9][0-9]\\b")) {
            MySQLconn.executeVoidQuery("DELETE FROM `transaksi_cart` WHERE `transaksi_cart`.`id_barang` ='" + fieldIdBarangTransaksi.getText() + "';");
            refreshTableTransaksi();
        } else {
            JOptionPane.showMessageDialog(null, "Pilih barang dahulu");
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
        fieldInputIDSupplier.setText(model.getValueAt(i,0).toString());
        fieldInputNamaSupplier.setText(model.getValueAt(i,1).toString());
        fieldInputAlamatSupplier.setText(model.getValueAt(i,2).toString());
        fieldInputTelponSupplier.setText(model.getValueAt(i,3).toString());
        fieldInputEmailSupplier.setText(model.getValueAt(i,4).toString());
        textAreaCatatan.setText(model.getValueAt(i,5).toString());
    }//GEN-LAST:event_tabelSupplierMouseClicked

    private void tombolClearFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolClearFieldActionPerformed
       fieldInputId_barang.setText("(Otomatis)");
       fieldInputQty.setText("");
       fieldInputHarga_beli.setText("");
       fieldInputSupplier.setText("");
       fieldInputTanggal_masuk.setText("(Otomatis)");
       fieldInputJenis.setText("");
       fieldInputMerk.setText("");
       fieldInputRagam.setText("");
       fieldInputSeri.setText("");

       fieldInputQty.requestFocus();
    }//GEN-LAST:event_tombolClearFieldActionPerformed

    private void tombolClearSupplierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolClearSupplierActionPerformed
       fieldInputIDSupplier.setText("(Otomatis)");
       fieldInputEmailSupplier.setText("");
       textAreaCatatan.setText("");
       fieldInputNamaSupplier.setText("");
       fieldInputAlamatSupplier.setText("");
       fieldInputTelponSupplier.setText("");
      
       fieldInputEmailSupplier.requestFocus();
    }//GEN-LAST:event_tombolClearSupplierActionPerformed

    private void tombolClearReturActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tombolClearReturActionPerformed
       fieldInputIdRetur.setText("(Otomatis)");
       fieldInputIdBarangRetur.setText("");
       fieldInputJumlahBarangRetur.setText("");
      
       fieldInputIdBarangRetur.requestFocus();
    }//GEN-LAST:event_tombolClearReturActionPerformed

    private void fieldIdBarangTransaksiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldIdBarangTransaksiFocusGained
        fieldIdBarangTransaksi.setText("");
    }//GEN-LAST:event_fieldIdBarangTransaksiFocusGained

    private void fieldInputQtyTransaksiFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fieldInputQtyTransaksiFocusGained
        fieldInputQtyTransaksi.setText("");
    }//GEN-LAST:event_fieldInputQtyTransaksiFocusGained

// </editor-fold>
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
                System.out.println(info.getName());
                if ("Nimbus".equals(info.getName())) {
                    
                   // javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
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
        });
    }
// <editor-fold defaultstate="collapsed" desc=" Variable Declaration ">
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton buttonRefreshDB;
    private javax.swing.JToggleButton buttonRefreshDBRetur;
    private javax.swing.JToggleButton buttonRefreshDBSupplier;
    private javax.swing.JToggleButton buttonRefreshDBTransaksi;
    private javax.swing.JButton buttonTransaksiHapus;
    private javax.swing.JButton buttonUbahQtyTransaksi;
    private javax.swing.JComboBox<String> comboBoxGaransi;
    private javax.swing.JComboBox<String> comboBoxKolom;
    private javax.swing.JComboBox<String> comboBoxKolomRetur;
    private javax.swing.JComboBox<String> comboBoxKolomSupplier;
    private javax.swing.JTextField fieldIdBarangTransaksi;
    private javax.swing.JTextField fieldInputAlamatSupplier;
    private javax.swing.JTextField fieldInputEmailSupplier;
    private javax.swing.JTextField fieldInputHarga_beli;
    private javax.swing.JTextField fieldInputIDSupplier;
    private javax.swing.JTextField fieldInputIdBarangRetur;
    private javax.swing.JTextField fieldInputIdRetur;
    private javax.swing.JTextField fieldInputId_barang;
    private javax.swing.JTextField fieldInputJenis;
    private javax.swing.JTextField fieldInputJumlahBarangRetur;
    private javax.swing.JTextField fieldInputMerk;
    private javax.swing.JTextField fieldInputNamaSupplier;
    private javax.swing.JTextField fieldInputQty;
    private javax.swing.JTextField fieldInputQtyTransaksi;
    private javax.swing.JTextField fieldInputRagam;
    private javax.swing.JTextField fieldInputSeri;
    private javax.swing.JTextField fieldInputSupplier;
    private javax.swing.JTextField fieldInputTanggal_masuk;
    private javax.swing.JTextField fieldInputTelponSupplier;
    private javax.swing.JLabel jLabel1;
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
    // End of variables declaration//GEN-END:variables
// </editor-fold>

}
