package registration;

import atm.*;
import java.awt.*;
import java.sql.*;
import java.util.*;

public class Registration extends javax.swing.JFrame {
    
    Database database = new Database();
    
    String spacingRegex = "^\\s.*|^.*\\s{2,}.*$|.*\\s$|\\w{1}";
    String emailSpecialChar ="^[a-zA-Z0-9]+@gmail\\.com";
    
    boolean validFirstName,validLastName,validPin,validEmail;
    
    public Registration() {
        initComponents();
        setBackground(new Color(0,0,0,0));
        setLocationRelativeTo(null);
        bg.requestFocusInWindow();
        pinHint();
    componentsVisibility();
    }
    
    public boolean validateEmail(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            database.connect = DriverManager.getConnection(database.url, database.user, database.pass);
            database.st = database.connect.createStatement();
            database.rs = database.st.executeQuery("SELECT Email FROM accounts");
            
            while(database.rs.next()){
                if(email.getText().toLowerCase().equals(database.rs.getString("Email").toLowerCase())){
                    return false;
                }
            }
            
        }catch(Exception e){ e.printStackTrace(); }
        return true;
    }
    
    public void componentsVisibility(){
        firstNameChecker.setVisible(false);
        lastNameChecker.setVisible(false);
        pinChecker.setVisible(false);
        emailChecker.setVisible(false);
        emailNotif.setVisible(false);
    }
    
    public void pinHint(){
        if(pin.getText().equals("Pin")){
            pin.setEchoChar((char)0);
        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new atm.Panel();
        emailNotif = new atm.Panel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        panel2 = new atm.Panel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        registerPanel = new atm.Panel2();
        register = new javax.swing.JLabel();
        pinChecker = new javax.swing.JPanel();
        panel3 = new atm.Panel();
        pin = new javax.swing.JPasswordField();
        lastNameChecker = new javax.swing.JPanel();
        panel4 = new atm.Panel();
        lastName = new javax.swing.JTextField();
        firstNameChecker = new javax.swing.JPanel();
        emailChecker = new javax.swing.JPanel();
        panel5 = new atm.Panel();
        firstName = new javax.swing.JTextField();
        panel6 = new atm.Panel();
        email = new javax.swing.JTextField();
        exit = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1240, 676));
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setMinimumSize(new java.awt.Dimension(1240, 676));
        bg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bgMouseClicked(evt);
            }
        });
        bg.setLayout(null);

        emailNotif.setBackground(new java.awt.Color(51, 51, 51,130));
        emailNotif.setMinimumSize(new java.awt.Dimension(1240, 676));
        emailNotif.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);
        jPanel2.add(jLabel5);
        jLabel5.setBounds(40, 0, 60, 150);

        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jTextPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTextPane1.setBorder(null);
        jTextPane1.setFont(new java.awt.Font("Segoe UI Variable", 1, 15)); // NOI18N
        jTextPane1.setForeground(new java.awt.Color(51, 51, 51));
        jTextPane1.setText("ACCOUNT ID HAS BEEN SENT TO YOUR EMAIL");
        jScrollPane1.setViewportView(jTextPane1);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(90, 60, 180, 40);

        emailNotif.add(jPanel2);
        jPanel2.setBounds(450, 250, 352, 150);

        bg.add(emailNotif);
        emailNotif.setBounds(-1, 0, 1259, 676);

        panel2.setBackground(new java.awt.Color(51, 51, 51));
        panel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panel2MouseClicked(evt);
            }
        });
        panel2.setLayout(null);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/logo.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        panel2.add(jLabel1);
        jLabel1.setBounds(150, 190, 200, 180);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("REGISTRATION FORM");
        panel2.add(jLabel3);
        jLabel3.setBounds(0, 370, 520, 70);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Click logo to login");
        panel2.add(jLabel4);
        jLabel4.setBounds(160, 360, 170, 30);

        bg.add(panel2);
        panel2.setBounds(0, 0, 440, 676);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));
        jPanel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 90, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        bg.add(jPanel1);
        jPanel1.setBounds(430, 0, 90, 680);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 51, 51));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("CREATE ACCOUNT");
        bg.add(jLabel2);
        jLabel2.setBounds(520, 90, 720, 70);

        registerPanel.setBackground(new java.awt.Color(51, 51, 51));
        registerPanel.setLayout(null);

        register.setFont(new java.awt.Font("Verdana", 0, 13)); // NOI18N
        register.setForeground(new java.awt.Color(255, 255, 255));
        register.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        register.setText("REGISTER");
        register.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        register.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                registerMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                registerMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                registerMouseExited(evt);
            }
        });
        registerPanel.add(register);
        register.setBounds(0, 0, 200, 50);

        bg.add(registerPanel);
        registerPanel.setBounds(780, 510, 200, 50);

        pinChecker.setBackground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout pinCheckerLayout = new javax.swing.GroupLayout(pinChecker);
        pinChecker.setLayout(pinCheckerLayout);
        pinCheckerLayout.setHorizontalGroup(
            pinCheckerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        pinCheckerLayout.setVerticalGroup(
            pinCheckerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        bg.add(pinChecker);
        pinChecker.setBounds(730, 376, 300, 3);

        panel3.setBackground(new java.awt.Color(204, 204, 204));
        panel3.setLayout(null);

        pin.setBackground(new java.awt.Color(204, 204, 204));
        pin.setForeground(new java.awt.Color(153, 153, 153));
        pin.setText("Pin");
        pin.setBorder(null);
        pin.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pinFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pinFocusLost(evt);
            }
        });
        pin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pinKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pinKeyTyped(evt);
            }
        });
        panel3.add(pin);
        pin.setBounds(10, 10, 290, 30);

        bg.add(panel3);
        panel3.setBounds(720, 330, 320, 50);

        lastNameChecker.setBackground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout lastNameCheckerLayout = new javax.swing.GroupLayout(lastNameChecker);
        lastNameChecker.setLayout(lastNameCheckerLayout);
        lastNameCheckerLayout.setHorizontalGroup(
            lastNameCheckerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        lastNameCheckerLayout.setVerticalGroup(
            lastNameCheckerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        bg.add(lastNameChecker);
        lastNameChecker.setBounds(730, 306, 300, 3);

        panel4.setBackground(new java.awt.Color(204, 204, 204));
        panel4.setLayout(null);

        lastName.setBackground(new java.awt.Color(204, 204, 204));
        lastName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        lastName.setForeground(new java.awt.Color(153, 153, 153));
        lastName.setText("Last Name");
        lastName.setBorder(null);
        lastName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                lastNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                lastNameFocusLost(evt);
            }
        });
        lastName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                lastNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lastNameKeyTyped(evt);
            }
        });
        panel4.add(lastName);
        lastName.setBounds(14, 6, 291, 38);

        bg.add(panel4);
        panel4.setBounds(720, 260, 320, 50);

        firstNameChecker.setBackground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout firstNameCheckerLayout = new javax.swing.GroupLayout(firstNameChecker);
        firstNameChecker.setLayout(firstNameCheckerLayout);
        firstNameCheckerLayout.setHorizontalGroup(
            firstNameCheckerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        firstNameCheckerLayout.setVerticalGroup(
            firstNameCheckerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        bg.add(firstNameChecker);
        firstNameChecker.setBounds(730, 236, 300, 3);

        emailChecker.setBackground(new java.awt.Color(0, 204, 51));

        javax.swing.GroupLayout emailCheckerLayout = new javax.swing.GroupLayout(emailChecker);
        emailChecker.setLayout(emailCheckerLayout);
        emailCheckerLayout.setHorizontalGroup(
            emailCheckerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
        emailCheckerLayout.setVerticalGroup(
            emailCheckerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 3, Short.MAX_VALUE)
        );

        bg.add(emailChecker);
        emailChecker.setBounds(730, 446, 300, 3);

        panel5.setBackground(new java.awt.Color(204, 204, 204));
        panel5.setLayout(null);

        firstName.setBackground(new java.awt.Color(204, 204, 204));
        firstName.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        firstName.setForeground(new java.awt.Color(153, 153, 153));
        firstName.setText("First Name");
        firstName.setBorder(null);
        firstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                firstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                firstNameFocusLost(evt);
            }
        });
        firstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                firstNameKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                firstNameKeyTyped(evt);
            }
        });
        panel5.add(firstName);
        firstName.setBounds(14, 6, 291, 38);

        bg.add(panel5);
        panel5.setBounds(720, 190, 320, 50);

        panel6.setBackground(new java.awt.Color(204, 204, 204));
        panel6.setLayout(null);

        email.setBackground(new java.awt.Color(204, 204, 204));
        email.setFont(new java.awt.Font("Verdana", 0, 12)); // NOI18N
        email.setForeground(new java.awt.Color(153, 153, 153));
        email.setText("Email");
        email.setBorder(null);
        email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                emailFocusLost(evt);
            }
        });
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailKeyReleased(evt);
            }
        });
        panel6.add(email);
        email.setBounds(14, 6, 291, 38);

        bg.add(panel6);
        panel6.setBounds(720, 400, 320, 50);

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/exit.png"))); // NOI18N
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        bg.add(exit);
        exit.setBounds(1210, 20, 30, 30);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 1259, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void registerMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseEntered
        registerPanel.setBackground(new Color(80,80,80));
    }//GEN-LAST:event_registerMouseEntered

    private void registerMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseExited
        registerPanel.setBackground(new Color(51,51,51));
    }//GEN-LAST:event_registerMouseExited

    private void firstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFocusGained
        if(firstName.getText().equals("First Name")){
            firstName.setText("");
            firstName.setForeground(new Color(51,51,51));
        }
    }//GEN-LAST:event_firstNameFocusGained

    private void firstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_firstNameFocusLost
        if(firstName.getText().equals("")){
            firstName.setText("First Name");
            firstName.setForeground(new Color(153,153,153));
            firstNameChecker.setVisible(false);
        }
    }//GEN-LAST:event_firstNameFocusLost

    private void lastNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFocusGained
        if(lastName.getText().equals("Last Name")){
            lastName.setText("");
            lastName.setForeground(new Color(51,51,51));
        }
    }//GEN-LAST:event_lastNameFocusGained

    private void lastNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_lastNameFocusLost
        if(lastName.getText().equals("")){
            lastName.setText("Last Name");
            lastName.setForeground(new Color(153,153,153));
            lastNameChecker.setVisible(false);
        }
    }//GEN-LAST:event_lastNameFocusLost

    private void pinFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pinFocusGained
        if(pin.getText().equals("Pin")){
            pin.setText("");
            pin.setForeground(new Color(51,51,51));
        }
        pin.setEchoChar('*');
    }//GEN-LAST:event_pinFocusGained

    private void pinFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pinFocusLost
        if(pin.getText().equals("")){
            pin.setText("Pin");
            pin.setEchoChar((char) 0);
            pin.setForeground(new Color(153,153,153));
            pinChecker.setVisible(false);
        }

    }//GEN-LAST:event_pinFocusLost

    private void emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusGained
        if(email.getText().equals("Email")){
            email.setText("");
            email.setForeground(new Color(51,51,51));
        }
    }//GEN-LAST:event_emailFocusGained

    private void emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_emailFocusLost
        if(email.getText().equals("")){
            email.setText("Email");
            email.setForeground(new Color(153,153,153));
            emailChecker.setVisible(false);
        }
    }//GEN-LAST:event_emailFocusLost

    private void registerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registerMouseClicked
        
        if(validFirstName == true && validLastName == true && validPin == true && validEmail == true){
            
            register.requestFocusInWindow();
            database.addAccount(firstName.getText(), lastName.getText(),email.getText(), pin.getText());
            
            emailNotif.setVisible(true);
            Timer timer = new Timer();
            timer.schedule(new TimerTask(){
                @Override
                public void run(){
                    emailNotif.setVisible(false);
                }
            },2300);
            
            timer.schedule(new TimerTask(){
                @Override
                public void run(){
                    new Registration().setVisible(true);
                    setVisible(false);
                }
            },2301);
        }
        else{
            if(firstName.getText().equals("First Name") || firstName.getText().equals("")){
                firstNameChecker.setVisible(true);
                firstNameChecker.setBackground(new Color(204,10,0));
            }
            else if(lastName.getText().equals("Last Name") || lastName.getText().equals("")){
                lastNameChecker.setVisible(true);
                lastNameChecker.setBackground(new Color(204,10,0));
            }
            else if(pin.getText().equals("Pin") || pin.getText().equals("") || !pin.getText().matches("\\d{6}")){
                pinChecker.setVisible(true);
                pinChecker.setBackground(new Color(204,10,0));
            }
            else if(email.getText().equals("Email") || email.getText().equals("")){
                emailChecker.setVisible(true);
                emailChecker.setBackground(new Color(204,10,0));
            }
            
        }
    }//GEN-LAST:event_registerMouseClicked

    private void bgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bgMouseClicked
        bg.requestFocusInWindow();
    }//GEN-LAST:event_bgMouseClicked

    private void jPanel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel1MouseClicked
        jPanel1.requestFocusInWindow();
    }//GEN-LAST:event_jPanel1MouseClicked

    private void panel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel2MouseClicked
        panel2.requestFocusInWindow();
    }//GEN-LAST:event_panel2MouseClicked

    private void firstNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstNameKeyTyped
        if(!(Character.isAlphabetic(evt.getKeyChar()) || Character.isWhitespace(evt.getKeyChar()))){
            evt.consume();
        }
    }//GEN-LAST:event_firstNameKeyTyped

    private void lastNameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameKeyTyped
        if(!(Character.isAlphabetic(evt.getKeyChar()) || Character.isWhitespace(evt.getKeyChar()))){
            evt.consume();
        }
    }//GEN-LAST:event_lastNameKeyTyped

    private void pinKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pinKeyTyped
        if(!(Character.isDigit(evt.getKeyChar()))){
            evt.consume();
        }
    }//GEN-LAST:event_pinKeyTyped

    private void firstNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_firstNameKeyReleased

        if(firstName.getText().matches(spacingRegex) || firstName.getText().isEmpty()){
            firstNameChecker.setVisible(true);
            firstNameChecker.setBackground(new Color(204,10,0));
            validFirstName = false;
            return;
        }
        firstNameChecker.setVisible(true);
        firstNameChecker.setBackground(new Color(0,204,51));
        validFirstName = true;
    }//GEN-LAST:event_firstNameKeyReleased

    private void lastNameKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lastNameKeyReleased
        
        if(lastName.getText().matches(spacingRegex) || lastName.getText().isEmpty()){
            lastNameChecker.setVisible(true);
            lastNameChecker.setBackground(new Color(204,10,0));
            validLastName = false;
            return;
        }
        lastNameChecker.setVisible(true);
        lastNameChecker.setBackground(new Color(0,204,51));
        validLastName = true;
    }//GEN-LAST:event_lastNameKeyReleased

    private void pinKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pinKeyReleased
        
        if((!pin.getText().matches("\\d{6}")) || pin.getText().isEmpty()){
            pinChecker.setVisible(true);
            pinChecker.setBackground(new Color(204,10,0));
            validPin = false;
            return;
        }
        pinChecker.setVisible(true);
        pinChecker.setBackground(new Color(0,204,51));
        validPin = true;
    }//GEN-LAST:event_pinKeyReleased

    private void emailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailKeyReleased
        boolean checkEmail = validateEmail();
        if((!email.getText().matches(emailSpecialChar)) || email.getText().equals("^@gmail\\.com") || email.getText().matches("^.*\\s.*$") || email.getText().isEmpty() || checkEmail == false){
            emailChecker.setVisible(true);
            emailChecker.setBackground(new Color(204,10,0));
            validEmail = false;
            return;
        }
        emailChecker.setVisible(true);
        emailChecker.setBackground(new Color(0,204,51));
        validEmail = true;
    }//GEN-LAST:event_emailKeyReleased

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
       Login atm = new Login();
       atm.setVisible(true);
       setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private atm.Panel bg;
    private javax.swing.JTextField email;
    private javax.swing.JPanel emailChecker;
    private atm.Panel emailNotif;
    private javax.swing.JLabel exit;
    private javax.swing.JTextField firstName;
    private javax.swing.JPanel firstNameChecker;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextField lastName;
    private javax.swing.JPanel lastNameChecker;
    private atm.Panel panel2;
    private atm.Panel panel3;
    private atm.Panel panel4;
    private atm.Panel panel5;
    private atm.Panel panel6;
    private javax.swing.JPasswordField pin;
    private javax.swing.JPanel pinChecker;
    private javax.swing.JLabel register;
    private atm.Panel2 registerPanel;
    // End of variables declaration//GEN-END:variables
}
