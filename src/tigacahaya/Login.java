/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tigacahaya;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.KeyEvent;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;
import java.io.InputStream;
import java.sql.*;
import static tigacahaya.ChangeServer.db;
import static tigacahaya.ChangeServer.password;
import static tigacahaya.ChangeServer.server;
import static tigacahaya.ChangeServer.userName;
import javax.swing.JOptionPane;
import java.util.concurrent.TimeUnit;

public class Login extends javax.swing.JFrame {

    Properties prop = new Properties();
    OutputStream output = null;
    InputStream input = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public Login() {

        try {
            input = new FileInputStream("config.properties");

            prop.load(input);

            userName = prop.getProperty("username");
            password = prop.getProperty("password");
            server = prop.getProperty("server");
            db = prop.getProperty("db");
        } catch (IOException e) {

        }
        initComponents();
    }

    public void loginAction() {
        
        try {  
           
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection("jdbc:mysql://" + server + ":3306/" + db + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", userName, password);
            String Sql = "Select * from karyawan where id =? and password =?";

            pst = conn.prepareStatement(Sql);
            pst.setString(1, Field_name.getText());
            pst.setString(2, Field_password.getText());
            rs = pst.executeQuery();
            if (rs.next()) {
                if(Field_name.getText().equals("admin") && Field_password.getText().equals("admin")){
                Properties prop = new Properties();
                OutputStream output = null;

                if (CheckBoxRememberMe.isSelected() == true) {
                    try {

                        output = new FileOutputStream("configUser.properties");
                        // set the properties value
                        prop.setProperty("name", Field_name.getText());
                        prop.setProperty("password", Field_password.getText());

                        // save properties to project root folder
                        prop.store(output, null);

                    } catch (IOException io) {
                        io.printStackTrace();
                    } finally {
                        if (output != null) {
                            try {
                                output.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {

                    try {

                        output = new FileOutputStream("configUser.properties");
                        // set the properties value
                        prop.setProperty("name", Field_name.getText());
                        prop.setProperty("password", "");

                        // save properties to project root folder
                        prop.store(output, null);

                    } catch (IOException io) {
                        io.printStackTrace();
                    } finally {
                        if (output != null) {
                            try {
                                output.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
                GUI gui = new GUI();
                gui.setLocationRelativeTo(null);
                gui.Level=rs.getString("level");
                gui.setVisible(true);
                
                this.dispose();
                }
                
                
                else{
                GUI gui = new GUI();
                gui.Level=rs.getString("id");
                Properties prop = new Properties();
                OutputStream output = null;

                if (CheckBoxRememberMe.isSelected() == true) {
                    try {

                        output = new FileOutputStream("configUser.properties");
                        // set the properties value
                        prop.setProperty("name", Field_name.getText());
                        prop.setProperty("password", Field_password.getText());

                        // save properties to project root folder
                        prop.store(output, null);

                    } catch (IOException io) {
                        io.printStackTrace();
                    } finally {
                        if (output != null) {
                            try {
                                output.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                } else {

                    try {

                        output = new FileOutputStream("configUser.properties");
                        // set the properties value
                        prop.setProperty("name", Field_name.getText());
                        prop.setProperty("password", "");

                        // save properties to project root folder
                        prop.store(output, null);

                    } catch (IOException io) {
                        io.printStackTrace();
                    } finally {
                        if (output != null) {
                            try {
                                output.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
                GUI menu = new GUI();
                menu.setLocationRelativeTo(null);
                menu.setVisible(true);
                menu.Level=rs.getString("level");
                this.dispose();
                }

            } else {
                JOptionPane.showMessageDialog(rootPane, "Username Atau Password Salah");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Login Gagal");

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

        jPanelUtama = new javax.swing.JPanel();
        jLabelPW = new javax.swing.JLabel();
        Field_name = new javax.swing.JTextField();
        Field_password = new javax.swing.JPasswordField();
        jSeparatorName = new javax.swing.JSeparator();
        jSeparatorPW = new javax.swing.JSeparator();
        buttonLogin = new javax.swing.JButton();
        jLabelUname = new javax.swing.JLabel();
        labelAbout = new javax.swing.JLabel();
        labelServer = new javax.swing.JLabel();
        CheckBoxRememberMe = new javax.swing.JCheckBox();
        splashLogo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(18, 104, 178));
        setLocationByPlatform(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanelUtama.setBackground(new java.awt.Color(18, 104, 178));

        jLabelPW.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPW.setText("Password");

        Field_name.setBackground(new java.awt.Color(18, 104, 178));
        Field_name.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Field_name.setForeground(new java.awt.Color(255, 255, 255));
        Field_name.setBorder(null);
        Field_name.setCaretColor(new java.awt.Color(255, 255, 255));
        Field_name.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));

        Field_password.setBackground(new java.awt.Color(18, 104, 178));
        Field_password.setForeground(new java.awt.Color(255, 255, 255));
        Field_password.setBorder(null);
        Field_password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Field_passwordKeyPressed(evt);
            }
        });

        jSeparatorName.setBackground(new java.awt.Color(12, 30, 42));
        jSeparatorName.setForeground(new java.awt.Color(12, 30, 42));

        jSeparatorPW.setBackground(new java.awt.Color(12, 30, 42));
        jSeparatorPW.setForeground(new java.awt.Color(12, 30, 42));

        buttonLogin.setBackground(new java.awt.Color(117, 179, 226));
        buttonLogin.setForeground(new java.awt.Color(12, 30, 42));
        buttonLogin.setText("Login");
        buttonLogin.setBorder(null);
        buttonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLoginActionPerformed(evt);
            }
        });

        jLabelUname.setForeground(new java.awt.Color(255, 255, 255));
        jLabelUname.setText("Name");

        labelAbout.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        labelAbout.setForeground(new java.awt.Color(117, 179, 226));
        labelAbout.setText("<html><u>About</u></html>");
        labelAbout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelAboutMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelAboutMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelAboutMouseEntered(evt);
            }
        });

        labelServer.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        labelServer.setForeground(new java.awt.Color(117, 179, 226));
        labelServer.setText("<html><u>Change Server</u></html>");
        labelServer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelServerMouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                labelServerMouseExited(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                labelServerMouseEntered(evt);
            }
        });

        CheckBoxRememberMe.setBackground(new java.awt.Color(12, 30, 42));
        CheckBoxRememberMe.setForeground(new java.awt.Color(255, 255, 255));
        CheckBoxRememberMe.setText("Ingat saya");

        splashLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/tigacahaya/res/menu_logo.png"))); // NOI18N

        javax.swing.GroupLayout jPanelUtamaLayout = new javax.swing.GroupLayout(jPanelUtama);
        jPanelUtama.setLayout(jPanelUtamaLayout);
        jPanelUtamaLayout.setHorizontalGroup(
            jPanelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelUtamaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 150, Short.MAX_VALUE)
                .addComponent(labelAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanelUtamaLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(splashLogo)
                    .addGroup(jPanelUtamaLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CheckBoxRememberMe)
                            .addComponent(jLabelUname)
                            .addComponent(Field_name, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparatorName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelPW)
                            .addComponent(Field_password, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparatorPW, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelUtamaLayout.setVerticalGroup(
            jPanelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelUtamaLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(splashLogo)
                .addGap(18, 18, 18)
                .addComponent(jLabelUname)
                .addGap(5, 5, 5)
                .addComponent(Field_name, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparatorName, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelPW)
                .addGap(5, 5, 5)
                .addComponent(Field_password, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jSeparatorPW, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(CheckBoxRememberMe)
                .addGap(27, 27, 27)
                .addComponent(buttonLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addGroup(jPanelUtamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAbout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelServer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        buttonLogin.getAccessibleContext().setAccessibleName("button_login");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelUtama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLoginActionPerformed
        loginAction();
        
    }//GEN-LAST:event_buttonLoginActionPerformed

    private void labelAboutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAboutMouseEntered

        setCursor(Cursor.HAND_CURSOR);
        labelAbout.setForeground(Color.WHITE);
    }//GEN-LAST:event_labelAboutMouseEntered

    private void labelAboutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAboutMouseExited
        Color lightblue = new Color(117, 179, 226);
        setCursor(Cursor.DEFAULT_CURSOR);
        labelAbout.setForeground(lightblue);
    }//GEN-LAST:event_labelAboutMouseExited

    private void labelServerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelServerMouseEntered
        setCursor(Cursor.HAND_CURSOR);
        labelServer.setForeground(Color.WHITE);
    }//GEN-LAST:event_labelServerMouseEntered

    private void labelServerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelServerMouseExited
        Color lightblue = new Color(117, 179, 226);
        setCursor(Cursor.DEFAULT_CURSOR);
        labelServer.setForeground(lightblue);
    }//GEN-LAST:event_labelServerMouseExited

    private void labelServerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelServerMouseClicked
        this.dispose();
        ChangeServer server = new ChangeServer();
        server.setLocationRelativeTo(null);
        server.setVisible(true);
    }//GEN-LAST:event_labelServerMouseClicked

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

        if (Field_name.getText().equals(null)) {
            Field_name.setText("");
        } else {
            try {

                input = new FileInputStream("configUser.properties");

                prop.load(input);

                // get the property value and print it out
                Field_name.setText(prop.getProperty("name"));

            } catch (IOException ex) {
                ex.printStackTrace();
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (Field_password.getText().equals(null)) {
                Field_password.setText("");
            } else {
                try {

                    input = new FileInputStream("configUser.properties");

                    prop.load(input);

                    // get the property value and print it out
                    Field_password.setText(prop.getProperty("password"));

                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    if (input != null) {
                        try {
                            input.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
        if (prop.getProperty("password").equals("")) {
            CheckBoxRememberMe.setSelected(false);
        } else {
            CheckBoxRememberMe.setSelected(true);
        }
    }//GEN-LAST:event_formWindowActivated

    private void labelAboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelAboutMouseClicked
        String message = "<html><body><div width='200px' align='right'>This is some text!</div></body></html>";
        JOptionPane.showMessageDialog(rootPane, "<html><b>ABOUT</b>\n"
                + "Program ini dibuat oleh team BURIT divisi Software engineering.\n"
                + "© 2018 Burit Software\n"
                + "<html><b><center>Team:</center></b></html>\n"
                + "Founder: Ijash\n"
                + "Co-Founder:Jose\n"
                + "Data & Beta test: Kevin\n"
                + "Data & Beta test: Simon\n"                
                + "Research Paper: Adel\n"
                + "Financial Consultant : Yuni\n"
                + "Investor: Fahmi\n"
                + "<html><b>Links:</b></html>\n"
                + "https://github.com/ijash/ProgramInventoriTigaCahaya\n"
                + "ijash1000@gmail.com\n"
                + "Donate here:\n"
                + "bitcoin: 1AV6DtBUYnNhrRoJsW3PzJkaMbkdVgnRfCubl","About Us", WIDTH);
    }//GEN-LAST:event_labelAboutMouseClicked

    private void Field_passwordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Field_passwordKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            loginAction();
        }
    }//GEN-LAST:event_Field_passwordKeyPressed

    /**
     * @param args the command line arguments
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

                    //javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login();
                login.setLocationRelativeTo(null);
                login.setVisible(true);
                //new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox CheckBoxRememberMe;
    private javax.swing.JTextField Field_name;
    private javax.swing.JPasswordField Field_password;
    private javax.swing.JButton buttonLogin;
    private javax.swing.JLabel jLabelPW;
    private javax.swing.JLabel jLabelUname;
    private javax.swing.JPanel jPanelUtama;
    private javax.swing.JSeparator jSeparatorName;
    private javax.swing.JSeparator jSeparatorPW;
    private javax.swing.JLabel labelAbout;
    private javax.swing.JLabel labelServer;
    private javax.swing.JLabel splashLogo;
    // End of variables declaration//GEN-END:variables

}
