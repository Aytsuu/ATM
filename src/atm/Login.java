// This file will show the login frame of the project

package atm;

// Importing necessary packages for the program
import java.awt.*;
import java.sql.*;
import registration.*;

public class Login extends javax.swing.JFrame{
    
    // Creates objects for the main frame and the database
    Database db = new Database();
    Frame frame = new Frame();
    
    // Initizilation of variables that are used in the program
    int accountNo = 0;
    int accountNoLimit = 10;
    int[] accountArr = new int[10];
    int accountIndex = 0;
    
    int pinNo = 0;
    int pinNoLimit = 6;
    int[] pinArr = new int[6];
    int pinIndex = 0;
    
    double overAllDeposits = 0, overAllWithdrawals = 0;
    
    // This is the constructor of the class
    public Login(){
        initComponents(); // Initializes all of the java swing components in the GUI Builder
        setLocationRelativeTo(null); // Automatically sets the position of the frame in the center
        setBackground(new Color(0,0,0,0)); // This sets the transparency of the background to 0
        
        // This section hides certain panels on the screen when the program executes
        loginPanel.setVisible(false);
        enterpanel.setVisible(false);
        invalidAcc.setVisible(false);
        invalidPin.setVisible(false);
    }
    
    // This method shows the enter button on the screen when called
    public void enter(){
        if(pinNo == pinNoLimit){ // If the number of pin entered by user reachers the set limit
            enterpanel.setVisible(true); // The enter button will appear
        }
    }
    
    //  This method is called when a user clicked on any of the numbers 
    public void type_number(int num){
        
        //This section is for the account number the user enters
        if(accountNo < accountNoLimit){ // A condition to check if the account number is still below the maximum inputs
            
            // If the condition above is true, this statements will be executed
          if(account.getText().equals("Account No.")){ // This checks if the user hasn't entered any number preceding the current user input
              
              // Hides the invalid notice
              invalidAcc.setVisible(false);
              invalidPin.setVisible(false);
              
              account.setText(String.valueOf(num)); // Sets the texts in the textfield to the number(s) the user entered
              accountArr[accountIndex] = num; // Storing the number to an array (Used for the undo functionality)
              accountIndex ++; // Increments the index of the array
              accountNo++; // Increments the account number (to check the number of inputs)
              return;
          }
          
          // If the condition above is false, this statements will be executed
          account.setText(account.getText().concat(String.valueOf(num))); // Concatenates the current input to the preceding account numbers
          accountNo++; // Incremeneets the count of account number entered
          accountArr[accountIndex] = num; // Stores the number to the array
          accountIndex++; // And increments the index of the array
       }
        // This section is for the pin the user enters 
       else if(pinNo < pinNoLimit){ // A condition to check if the pin is still below the maximum inputs
          
           // If the condition above is true, the statements below will be executed
           //Hides the invalid notice
          invalidAcc.setVisible(false); 
          invalidPin.setVisible(false);
          
          pin.setText(pin.getText().concat(String.valueOf(num))); // Concatenates the current input to the precediing pin numbers 
          pinNo++; // Increments the count of pin entered
          pinArr[pinIndex] = num; // Stores the pin to an array (Used for undo functionality)
          pinIndex++; // Increments the index of the array
          
       }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel = new atm.Panel();
        enterpanel = new javax.swing.JPanel();
        enter = new javax.swing.JLabel();
        loginPanel = new atm.Panel();
        panel12 = new atm.Panel1();
        n8 = new javax.swing.JLabel();
        panel13 = new atm.Panel1();
        n9 = new javax.swing.JLabel();
        panel15 = new atm.Panel1();
        n0 = new javax.swing.JLabel();
        panel16 = new atm.Panel1();
        n5 = new javax.swing.JLabel();
        panel17 = new atm.Panel1();
        n6 = new javax.swing.JLabel();
        panel18 = new atm.Panel1();
        n4 = new javax.swing.JLabel();
        panel19 = new atm.Panel1();
        n2 = new javax.swing.JLabel();
        panel20 = new atm.Panel1();
        n3 = new javax.swing.JLabel();
        panel21 = new atm.Panel1();
        n1 = new javax.swing.JLabel();
        panel22 = new atm.Panel1();
        cancel = new javax.swing.JLabel();
        panel23 = new atm.Panel1();
        undo = new javax.swing.JLabel();
        panel14 = new atm.Panel1();
        n7 = new javax.swing.JLabel();
        panel24 = new atm.Panel2();
        invalidPin = new atm.Panel2();
        pin = new javax.swing.JPasswordField();
        panel25 = new atm.Panel2();
        invalidAcc = new atm.Panel2();
        account = new javax.swing.JTextField();
        exit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        swipeCard = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        panel1 = new atm.Panel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel.setBackground(new java.awt.Color(255, 255, 255));
        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        enterpanel.setBackground(new java.awt.Color(204, 204, 204));

        enter.setForeground(new java.awt.Color(153, 153, 153));
        enter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/enter.png"))); // NOI18N
        enter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout enterpanelLayout = new javax.swing.GroupLayout(enterpanel);
        enterpanel.setLayout(enterpanelLayout);
        enterpanelLayout.setHorizontalGroup(
            enterpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(enter, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        enterpanelLayout.setVerticalGroup(
            enterpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(enter, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        panel.add(enterpanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 560, 70, 40));

        loginPanel.setBackground(new java.awt.Color(102, 102, 102));
        loginPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel12.setBackground(new java.awt.Color(255, 255, 255));

        n8.setBackground(new java.awt.Color(255, 255, 255));
        n8.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        n8.setForeground(new java.awt.Color(51, 51, 51));
        n8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n8.setText("8");
        n8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel12Layout = new javax.swing.GroupLayout(panel12);
        panel12.setLayout(panel12Layout);
        panel12Layout.setHorizontalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n8, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel12Layout.setVerticalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n8, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        loginPanel.add(panel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 70, 70));

        panel13.setBackground(new java.awt.Color(255, 255, 255));

        n9.setBackground(new java.awt.Color(255, 255, 255));
        n9.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        n9.setForeground(new java.awt.Color(51, 51, 51));
        n9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n9.setText("9");
        n9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel13Layout = new javax.swing.GroupLayout(panel13);
        panel13.setLayout(panel13Layout);
        panel13Layout.setHorizontalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n9, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel13Layout.setVerticalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n9, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        loginPanel.add(panel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 70, 70));

        panel15.setBackground(new java.awt.Color(255, 255, 255));

        n0.setBackground(new java.awt.Color(255, 255, 255));
        n0.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        n0.setForeground(new java.awt.Color(51, 51, 51));
        n0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n0.setText("0");
        n0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n0MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel15Layout = new javax.swing.GroupLayout(panel15);
        panel15.setLayout(panel15Layout);
        panel15Layout.setHorizontalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n0, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel15Layout.setVerticalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n0, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        loginPanel.add(panel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 440, 70, 70));

        panel16.setBackground(new java.awt.Color(255, 255, 255));

        n5.setBackground(new java.awt.Color(255, 255, 255));
        n5.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        n5.setForeground(new java.awt.Color(51, 51, 51));
        n5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n5.setText("5");
        n5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel16Layout = new javax.swing.GroupLayout(panel16);
        panel16.setLayout(panel16Layout);
        panel16Layout.setHorizontalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel16Layout.setVerticalGroup(
            panel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        loginPanel.add(panel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, 70, 70));

        panel17.setBackground(new java.awt.Color(255, 255, 255));

        n6.setBackground(new java.awt.Color(255, 255, 255));
        n6.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        n6.setForeground(new java.awt.Color(51, 51, 51));
        n6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n6.setText("6");
        n6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel17Layout = new javax.swing.GroupLayout(panel17);
        panel17.setLayout(panel17Layout);
        panel17Layout.setHorizontalGroup(
            panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n6, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel17Layout.setVerticalGroup(
            panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n6, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        loginPanel.add(panel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 70, 70));

        panel18.setBackground(new java.awt.Color(255, 255, 255));

        n4.setBackground(new java.awt.Color(255, 255, 255));
        n4.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        n4.setForeground(new java.awt.Color(51, 51, 51));
        n4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n4.setText("4");
        n4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel18Layout = new javax.swing.GroupLayout(panel18);
        panel18.setLayout(panel18Layout);
        panel18Layout.setHorizontalGroup(
            panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n4, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel18Layout.setVerticalGroup(
            panel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n4, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        loginPanel.add(panel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 280, 70, 70));

        panel19.setBackground(new java.awt.Color(255, 255, 255));

        n2.setBackground(new java.awt.Color(255, 255, 255));
        n2.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        n2.setForeground(new java.awt.Color(51, 51, 51));
        n2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n2.setText("2");
        n2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel19Layout = new javax.swing.GroupLayout(panel19);
        panel19.setLayout(panel19Layout);
        panel19Layout.setHorizontalGroup(
            panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel19Layout.setVerticalGroup(
            panel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n2, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        loginPanel.add(panel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 360, 70, 70));

        panel20.setBackground(new java.awt.Color(255, 255, 255));

        n3.setBackground(new java.awt.Color(255, 255, 255));
        n3.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        n3.setForeground(new java.awt.Color(51, 51, 51));
        n3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n3.setText("3");
        n3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel20Layout = new javax.swing.GroupLayout(panel20);
        panel20.setLayout(panel20Layout);
        panel20Layout.setHorizontalGroup(
            panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n3, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel20Layout.setVerticalGroup(
            panel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n3, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        loginPanel.add(panel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 360, 70, 70));

        panel21.setBackground(new java.awt.Color(255, 255, 255));

        n1.setBackground(new java.awt.Color(255, 255, 255));
        n1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        n1.setForeground(new java.awt.Color(51, 51, 51));
        n1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n1.setText("1");
        n1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel21Layout = new javax.swing.GroupLayout(panel21);
        panel21.setLayout(panel21Layout);
        panel21Layout.setHorizontalGroup(
            panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel21Layout.setVerticalGroup(
            panel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n1, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        loginPanel.add(panel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 70, 70));

        panel22.setBackground(new java.awt.Color(51, 51, 51));

        cancel.setBackground(new java.awt.Color(255, 255, 255));
        cancel.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        cancel.setForeground(new java.awt.Color(255, 255, 255));
        cancel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cancel.setText("×");
        cancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel22Layout = new javax.swing.GroupLayout(panel22);
        panel22.setLayout(panel22Layout);
        panel22Layout.setHorizontalGroup(
            panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel22Layout.setVerticalGroup(
            panel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel22Layout.createSequentialGroup()
                .addComponent(cancel, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
                .addContainerGap())
        );

        loginPanel.add(panel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 440, 70, 70));

        panel23.setBackground(new java.awt.Color(51, 51, 51));

        undo.setBackground(new java.awt.Color(255, 255, 255));
        undo.setFont(new java.awt.Font("Segoe UI", 1, 35)); // NOI18N
        undo.setForeground(new java.awt.Color(255, 255, 255));
        undo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        undo.setText("<");
        undo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        undo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                undoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel23Layout = new javax.swing.GroupLayout(panel23);
        panel23.setLayout(panel23Layout);
        panel23Layout.setHorizontalGroup(
            panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel23Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(undo, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panel23Layout.setVerticalGroup(
            panel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel23Layout.createSequentialGroup()
                .addComponent(undo, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        loginPanel.add(panel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 440, 70, 70));

        panel14.setBackground(new java.awt.Color(255, 255, 255));

        n7.setBackground(new java.awt.Color(255, 255, 255));
        n7.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        n7.setForeground(new java.awt.Color(51, 51, 51));
        n7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n7.setText("7");
        n7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel14Layout = new javax.swing.GroupLayout(panel14);
        panel14.setLayout(panel14Layout);
        panel14Layout.setHorizontalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n7, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );
        panel14Layout.setVerticalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n7, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE)
        );

        loginPanel.add(panel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 200, 70, 70));

        panel24.setBackground(new java.awt.Color(255, 255, 255));
        panel24.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        invalidPin.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout invalidPinLayout = new javax.swing.GroupLayout(invalidPin);
        invalidPin.setLayout(invalidPinLayout);
        invalidPinLayout.setHorizontalGroup(
            invalidPinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        invalidPinLayout.setVerticalGroup(
            invalidPinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        panel24.add(invalidPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, -1, -1));

        pin.setEditable(false);
        pin.setBackground(new java.awt.Color(255, 255, 255));
        pin.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        pin.setForeground(new java.awt.Color(0, 0, 0));
        pin.setBorder(null);
        pin.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pin.setFocusable(false);
        panel24.add(pin, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 0, 200, 50));

        loginPanel.add(panel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 120, 250, 50));

        panel25.setBackground(new java.awt.Color(255, 255, 255));
        panel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        invalidAcc.setBackground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout invalidAccLayout = new javax.swing.GroupLayout(invalidAcc);
        invalidAcc.setLayout(invalidAccLayout);
        invalidAccLayout.setHorizontalGroup(
            invalidAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );
        invalidAccLayout.setVerticalGroup(
            invalidAccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 10, Short.MAX_VALUE)
        );

        panel25.add(invalidAcc, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 20, 10, 10));

        account.setEditable(false);
        account.setBackground(new java.awt.Color(255, 255, 255));
        account.setFont(new java.awt.Font("Yu Gothic UI", 0, 14)); // NOI18N
        account.setForeground(new java.awt.Color(0, 0, 0));
        account.setText("Account No.");
        account.setToolTipText("");
        account.setBorder(null);
        account.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        account.setFocusable(false);
        panel25.add(account, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 0, 200, 50));

        loginPanel.add(panel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, 250, 50));

        panel.add(loginPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 350, 560));

        exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/exit.png"))); // NOI18N
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        panel.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 20, -1, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/atmicon.png"))); // NOI18N
        panel.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 90, -1, -1));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        swipeCard.setBackground(new java.awt.Color(51, 51, 51));
        swipeCard.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        swipeCard.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                swipeCardMouseDragged(evt);
            }
        });
        swipeCard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                swipeCardMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout swipeCardLayout = new javax.swing.GroupLayout(swipeCard);
        swipeCard.setLayout(swipeCardLayout);
        swipeCardLayout.setHorizontalGroup(
            swipeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 280, Short.MAX_VALUE)
        );
        swipeCardLayout.setVerticalGroup(
            swipeCardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 7, Short.MAX_VALUE)
        );

        jPanel2.add(swipeCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(66, 106, 280, 7));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 51, 51));
        jLabel4.setText("SWIPE CARD");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 30, -1, -1));

        panel.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 320, 410, 220));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 51, 51));
        jLabel3.setText("ATM");
        panel.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 10, -1, -1));

        panel1.setBackground(new java.awt.Color(51, 51, 51));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/logo.png"))); // NOI18N
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        panel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(276, 225, -1, -1));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 15, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        panel1.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(733, 0, -1, 676));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Click logo to register");
        panel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 410, 170, 30));

        panel.add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1259, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
       System.exit(0); // Terminates the program, when the exit button is clicked
    }//GEN-LAST:event_exitMouseClicked

    private void swipeCardMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swipeCardMouseReleased
    }//GEN-LAST:event_swipeCardMouseReleased
        
    private void cancelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelMouseClicked
        
        // When the X button or cancel button is clicked, this statements will execute
        loginPanel.setVisible(false); // Hides the login panel
        enterpanel.setVisible(false); // Hides the panel of the enter button
        
        // Resets the necessary components to their initial value
        swipeCard.setBackground(Color.black);
        accountIndex = 0;
        pinIndex = 0;
        accountNo = 0;
        pinNo = 0;
        account.setText("Account No.");
        pin.setText("");
    }//GEN-LAST:event_cancelMouseClicked

    private void swipeCardMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_swipeCardMouseDragged
        
        // If card is swiped, shows the login panel and turns the swipe section color to green
        loginPanel.setVisible(true);
        panel.setEnabled(false);
        swipeCard.setBackground(Color.green);
    }//GEN-LAST:event_swipeCardMouseDragged

    private void n0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n0MouseClicked
        type_number(0); // Calls the type_number method, passing the number entered
        enter(); // Calls the enter method
    }//GEN-LAST:event_n0MouseClicked

    private void n1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n1MouseClicked
        type_number(1); // Calls the type_number method, passing the number entered
        enter(); // Calls the enter method
    }//GEN-LAST:event_n1MouseClicked

    private void n2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n2MouseClicked
        type_number(2); // Calls the type_number method, passing the number entered
        enter(); // Calls the enter method
    }//GEN-LAST:event_n2MouseClicked

    private void n3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n3MouseClicked
       type_number(3); // Calls the type_number method, passing the number entered
        enter(); // Calls the enter method
    }//GEN-LAST:event_n3MouseClicked

    private void n4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n4MouseClicked
        type_number(4); // Calls the type_number method, passing the number entered
        enter(); // Calls the enter method
    }//GEN-LAST:event_n4MouseClicked

    private void n5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n5MouseClicked
        type_number(5); // Calls the type_number method, passing the number entered
        enter(); // Calls the enter method
    }//GEN-LAST:event_n5MouseClicked

    private void n6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n6MouseClicked
        type_number(6); // Calls the type_number method, passing the number entered
        enter(); // Calls the enter method
    }//GEN-LAST:event_n6MouseClicked

    private void n7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n7MouseClicked
        type_number(7); // Calls the type_number method, passing the number entered
        enter(); // Calls the enter method
    }//GEN-LAST:event_n7MouseClicked

    private void n8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n8MouseClicked
        type_number(8); // Calls the type_number method, passing the number entered
        enter(); // Calls the enter method
    }//GEN-LAST:event_n8MouseClicked

    private void n9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n9MouseClicked
        type_number(9); // Calls the type_number method, passing the number entered
        enter(); // Calls the enter method
    }//GEN-LAST:event_n9MouseClicked

    private void undoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_undoMouseClicked
        // If undo button is clicked, the statements below will be executed
        
        if(account.getText().equals("") || account.getText().equals("Account No.")) // Checks if there are no changes in the the textfield
            return; // If condition is true we don't perforn undo, returning no value
        
        // This section is for account number 
        if(accountNo <= accountNoLimit && pinNo == 0){ // A condition to check if account number filled and pin is not filled
            
            // Hides the invalid notice
            invalidAcc.setVisible(false);
            invalidPin.setVisible(false);
            
            account.setText(""); // Sets the account textfield to blank 
            for(int i=0; i < accountIndex-1; i++){ // Iterates through the array of the account number, excluding the last number
               account.setText(account.getText().concat(String.valueOf(accountArr[i]))); // Concates each number back to the textfield
            }
            accountIndex--; // Decrements the index of the account number array
            accountNo--; // Decrements the count of the number entered
        }
        // This section is for the pin
        else if(pinNo <= pinNoLimit){ // Checks if pin is filled
            
            // Hides the invalid notice
            invalidAcc.setVisible(false);
            invalidPin.setVisible(false);
            
            pin.setText(""); // Sets the account textfield to blank 
            enterpanel.setVisible(false); // Hides the enter button
            for(int i=0; i < pinIndex-1; i++){ // Iterates through the array of the pin number, excluding the last number
                pin.setText(pin.getText().concat(String.valueOf(pinArr[i]))); // Concates each number back to the password field
            }
            pinIndex--; // Decrements the index of the pin array
            pinNo--; // Decrements the count of the pin entered
        }
    }//GEN-LAST:event_undoMouseClicked

    private void enterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterMouseClicked
        
        // If enter button is clicked, enters the database connected to the program
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            db.connect = DriverManager.getConnection(db.url,db.user,db.pass);
            db.st = db.connect.createStatement();
            db.rs = db.st.executeQuery("SELECT * FROM atm.accounts");
            
            while(db.rs.next()){ // Iterates through each row in the the database
                
                if(account.getText().equals(db.rs.getString("AccountID")) && pin.getText().equals(db.rs.getString("Pin"))){ // Checks if the account and pin entered exists
                    frame.overAllDeposits = overAllDeposits;
                    frame.overAllWithdrawals = overAllWithdrawals;
                    frame.accountId = account.getText(); // Assigns the entered account to the variable accoundId in the Frame file
                    frame.setVisible(true); // Shows the frame of the Frame file
                    setVisible(false); // And hides the frame of the Login file
                }
                else{ // If account number or pin doesn't exist
                    
                    // Invalid notices will appear
                    invalidAcc.setVisible(true);
                    invalidPin.setVisible(true);
                }
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_enterMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        Registration register = new Registration();
        register.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField account;
    private javax.swing.JLabel cancel;
    private javax.swing.JLabel enter;
    private javax.swing.JPanel enterpanel;
    private javax.swing.JLabel exit;
    private atm.Panel2 invalidAcc;
    private atm.Panel2 invalidPin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private atm.Panel loginPanel;
    private javax.swing.JLabel n0;
    private javax.swing.JLabel n1;
    private javax.swing.JLabel n2;
    private javax.swing.JLabel n3;
    private javax.swing.JLabel n4;
    private javax.swing.JLabel n5;
    private javax.swing.JLabel n6;
    private javax.swing.JLabel n7;
    private javax.swing.JLabel n8;
    private javax.swing.JLabel n9;
    private atm.Panel panel;
    private atm.Panel panel1;
    private atm.Panel1 panel12;
    private atm.Panel1 panel13;
    private atm.Panel1 panel14;
    private atm.Panel1 panel15;
    private atm.Panel1 panel16;
    private atm.Panel1 panel17;
    private atm.Panel1 panel18;
    private atm.Panel1 panel19;
    private atm.Panel1 panel20;
    private atm.Panel1 panel21;
    private atm.Panel1 panel22;
    private atm.Panel1 panel23;
    private atm.Panel2 panel24;
    private atm.Panel2 panel25;
    private javax.swing.JPasswordField pin;
    private javax.swing.JPanel swipeCard;
    private javax.swing.JLabel undo;
    // End of variables declaration//GEN-END:variables
}
