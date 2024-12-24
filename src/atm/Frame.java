// This file shows the main frame of the project

package atm;

// Imports necessary packages for the program
import java.awt.*;
import java.sql.*;
import java.time.*;
import java.time.format.*;
import javax.swing.table.*;

public class Frame extends javax.swing.JFrame {
    
    // Instantiating objects used in the program
    DefaultTableModel model = new DefaultTableModel();
    Database db = new Database();
    ZonedDateTime now = ZonedDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    
    //  Initializing variables 
    int count = 0;
    String[] amountArr = new String[5];
    String accountId;
    double accBalance = 0;
    double maintainingBal = 500.00;
    
    // Initializing necessary variables for withdrawal section
    final double withdrawCapacity = 3000000.00;
    final double userWithdrawLimit = 50000.00;
    double overAllWithdrawals = 0.00;
    double currWithdraw = 0.00;
    
    // Initializing necessary variables for deposit section
    final double depositCapacity = 3000000.00;
    final double userDepositLimit = 50000.00;
    double overAllDeposits = 0.00;
    double currDeposit = 0.00;
    
    // Constructor of the class
    public Frame() {
        initComponents(); // Initializes all of the java swing components in the GUI Builder
        
        // Sets location to center and frame opacity to 0
        setLocationRelativeTo(null);
        setBackground(new Color(0,0,0,0));
        
        // Hides certain components of the frame at the time of execution
        choice1.setVisible(false); choice2.setVisible(false); choice3.setVisible(false); choice4.setVisible(false);
        screen.setVisible(false); numContainer.setVisible(false);
        type1.setVisible(false); prompt.setVisible(false); amount.setVisible(false); dec.setVisible(false); line.setVisible(false); dec.setVisible(false);
        invalidAmount.setVisible(false); notEnoughBal.setVisible(false); maxDepositMsg.setVisible(false); maxWithdrawMsg.setVisible(false);
        successful.setVisible(false); 
        balPanel.setVisible(false); statementPanel.setVisible(false); dispenser.setVisible(false); dispenser1.setVisible(false);
    }
    
    // This method handles the number typing
    public void typeNumber(String num){
        String currAmount = amount.getText();
        
        // Condition that limits the number of digits to 5
        if(count < 5){
            if(!(currAmount.equals("") && num.equals("0"))){ // Checks if label is not blank and entered num is not 0
                
                // Hide notices 
                successful.setVisible(false);
                maxDepositMsg.setVisible(false);
                maxWithdrawMsg.setVisible(false);
                invalidAmount.setVisible(false);
                notEnoughBal.setVisible(false);
                dec.setVisible(true); // Shows decimal (.00)
                
                // Resets the color of the cash dispenser and receipt dispenser to black
                cash.setBackground(Color.black); receipt.setBackground(Color.black);
                amount.setText(currAmount.concat(num)); // Concatenates the entered number to the label
                amountArr[count] = num; // Stores the number to an array
                count++; // Increments the index of the array
            }
        }
    }
    
    // This method handles the undo of the number entered
    public void undo(){
        
        // Resets the label to blank, hides notices and resets the color of the cash and receipt dispenser
        amount.setText("");
        notEnoughBal.setVisible(false);
        maxDepositMsg.setVisible(false);
        maxWithdrawMsg.setVisible(false);
        cash.setBackground(Color.black); receipt.setBackground(Color.black);
        
        // Iterate through the count, excluding the last number
        for(int i = 0; i < count-1; i++){          
            amount.setText(amount.getText().concat(amountArr[i])); // Concatenates the digits back to the label
        }
        count -=1; // Decrements the count
        
        // If count reaches 0, hides decimal
        if(count == 0){
            dec.setVisible(false);
        }
    }
    
    // This method checks the current balance of the account
    public void accountBalance(){
        
        // Fetching data from database
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            db.connect = DriverManager.getConnection(db.url,db.user,db.pass);
            db.st = db.connect.createStatement();
            db.rs = db.st.executeQuery("SELECT * FROM accounts WHERE AccountID = '" + accountId + "'");
            while(db.rs.next()){
                accBalance = Double.parseDouble(db.rs.getString("Balance")); // Retrieves the balance of the specified account
            }
                
            db.st.close();
            db.connect.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return;
    }
    
    // This method handles the withdrawal section
    public void withdraw(){
        
        // Initialize local variables 
        double withdrawAmount = Double.parseDouble(amount.getText()); // The amount the user wants to withdraw
        double newBalance = accBalance - withdrawAmount; // For updating the balance of the user
        
        // Connecting to database to perform the update
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            db.connect = DriverManager.getConnection(db.url,db.user,db.pass); 
            db.ps = db.connect.prepareStatement("UPDATE accounts SET Balance = '"+ String.format("%.2f",newBalance)+"'");
            db.ps.executeUpdate();
            db.ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        currWithdraw += withdrawAmount; // Adds the amount withdraw to the current withdrawals (per account)
        overAllWithdrawals += withdrawAmount; // Adds the amount withdraw to the over all withdrawals (All accounts)
        
        // Shows notices and hides certain components after transaction is completed
        successful.setVisible(true);
        amount.setText(""); 
        dec.setVisible(false);
        count = 0; // Resets the count of the entered digits
        
        // Calls the class database and method statement to perform adding of transaction record to the table statements
        db.statement(accountId,String.format("%.2f",withdrawAmount),"Withdraw",now.format(formatter));
        cash.setBackground(Color.green); receipt.setBackground(Color.green); // Sets the dispensers to green (Signifying successful transaction)
    }
    
    // This method handles the deposit section
    public void deposit(){
        
        // Initializing local variables  
        double depositAmount = Double.parseDouble(amount.getText()); // The amount the user wants to deposit
        double newBalance = depositAmount + accBalance; // For updating the users balance
        
        currDeposit += depositAmount; // Adds the deposit amount to the current deposit (per account)
        overAllDeposits += depositAmount; // Adds the deposit amount to the over all deposits (All accounts)
        
        // Connects to the database to perform the update 
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            db.connect = DriverManager.getConnection(db.url,db.user,db.pass);
            db.ps = db.connect.prepareStatement("UPDATE accounts SET Balance = '" + String.format("%.2f", newBalance) + "' WHERE AccountID = '" + accountId + "'");
            db.ps.executeUpdate();
            db.ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
        // Shows notices and hides certain components after the transaction is completed
        successful.setVisible(true);
        amount.setText("");
        dec.setVisible(false);
        count = 0; // Resets the oount of entered degits to 0
        
        // Calls the class database and method statement to perform adding of transaction record to the table statements
        db.statement(accountId,String.format("%.2f",depositAmount),"Deposit",now.format(formatter));
        receipt.setBackground(Color.green);
    }
    
    // This method displays all the transaction records in the frame table
    public void statement(){
        
        // Initializing variables 
        model = (DefaultTableModel) statementTable.getModel();
        String[] column = new String[3];
        
        // Connecting to the database to perform data fetching
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            db.connect = DriverManager.getConnection(db.url,db.user,db.pass);
            db.st = db.connect.createStatement();
            db.rs = db.st.executeQuery("SELECT * FROM statements WHERE AccountID = '" + accountId + "'");
            
            while(db.rs.next()){
                
                // Fetches the data of each columns in the database
                column[0] = db.rs.getString("Amount");
                column[1] = db.rs.getString("Transaction");
                column[2] = db.rs.getString("Date");
                
                // Add them to the row of the frame table
                model.addRow(column);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new atm.Panel();
        statementPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        statementTable = new rojerusan.RSTableMetro();
        balPanel = new atm.Panel();
        panel17 = new atm.Panel();
        currBalance = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        screen = new javax.swing.JPanel();
        type1 = new javax.swing.JLabel();
        prompt = new javax.swing.JLabel();
        line = new javax.swing.JLabel();
        dec = new javax.swing.JLabel();
        amount = new javax.swing.JLabel();
        maxWithdrawMsg = new javax.swing.JLabel();
        type2 = new javax.swing.JLabel();
        maxDepositMsg = new javax.swing.JLabel();
        successful = new javax.swing.JLabel();
        invalidAmount = new javax.swing.JLabel();
        notEnoughBal = new javax.swing.JLabel();
        panel2 = new atm.Panel();
        withdraw = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        deposit = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        balance = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        statement = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        choice1 = new javax.swing.JPanel();
        choice2 = new javax.swing.JPanel();
        choice3 = new javax.swing.JPanel();
        choice4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        exit = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        title = new javax.swing.JLabel();
        logo = new javax.swing.JLabel();
        numContainer = new javax.swing.JPanel();
        panel4 = new atm.Panel();
        n7 = new javax.swing.JLabel();
        panel6 = new atm.Panel();
        n8 = new javax.swing.JLabel();
        panel9 = new atm.Panel();
        n9 = new javax.swing.JLabel();
        panel7 = new atm.Panel();
        n4 = new javax.swing.JLabel();
        panel8 = new atm.Panel();
        n5 = new javax.swing.JLabel();
        panel5 = new atm.Panel();
        n6 = new javax.swing.JLabel();
        panel10 = new atm.Panel();
        n2 = new javax.swing.JLabel();
        panel11 = new atm.Panel();
        n3 = new javax.swing.JLabel();
        panel12 = new atm.Panel();
        panel13 = new atm.Panel();
        n1 = new javax.swing.JLabel();
        panel14 = new atm.Panel();
        n0 = new javax.swing.JLabel();
        panel15 = new atm.Panel();
        enter = new javax.swing.JLabel();
        panel3 = new atm.Panel();
        undo = new javax.swing.JLabel();
        dispenser1 = new atm.Panel();
        jPanel2 = new javax.swing.JPanel();
        dispenser = new javax.swing.JPanel();
        cash = new javax.swing.JPanel();
        receipt = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panel1.setBackground(new java.awt.Color(51, 51, 51));
        panel1.setMinimumSize(new java.awt.Dimension(1240, 676));
        panel1.setPreferredSize(new java.awt.Dimension(1240, 676));
        panel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        statementPanel.setBackground(new java.awt.Color(255, 255, 255));
        statementPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        statementTable.setBackground(new java.awt.Color(240, 240, 240));
        statementTable.setForeground(new java.awt.Color(255, 255, 255));
        statementTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Amount", "Transaction", "Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        statementTable.setColorBackgoundHead(new java.awt.Color(51, 51, 51));
        statementTable.setColorBordeFilas(new java.awt.Color(240, 240, 240));
        statementTable.setColorBordeHead(new java.awt.Color(240, 240, 240));
        statementTable.setColorFilasBackgound1(new java.awt.Color(240, 240, 240));
        statementTable.setColorFilasBackgound2(new java.awt.Color(240, 240, 240));
        statementTable.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        statementTable.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        statementTable.setColorSelBackgound(new java.awt.Color(240, 240, 240));
        statementTable.setColorSelForeground(new java.awt.Color(0, 0, 0));
        statementTable.setFocusable(false);
        statementTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statementTable.setFuenteFilas(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        statementTable.setFuenteHead(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        statementTable.setGridColor(new java.awt.Color(240, 240, 240));
        statementTable.setRowHeight(40);
        statementTable.setRowSelectionAllowed(false);
        statementTable.setShowGrid(false);
        statementTable.setUpdateSelectionOnSort(false);
        statementTable.setVerifyInputWhenFocusTarget(false);
        jScrollPane2.setViewportView(statementTable);

        statementPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 520, 610));

        panel1.add(statementPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 520, 610));

        balPanel.setBackground(new java.awt.Color(255, 255, 255));

        panel17.setBackground(new java.awt.Color(0, 0, 0));

        currBalance.setBackground(new java.awt.Color(0, 0, 0));
        currBalance.setFont(new java.awt.Font("Segoe UI Variable", 0, 18)); // NOI18N
        currBalance.setForeground(new java.awt.Color(255, 255, 255));
        currBalance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout panel17Layout = new javax.swing.GroupLayout(panel17);
        panel17.setLayout(panel17Layout);
        panel17Layout.setHorizontalGroup(
            panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currBalance, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );
        panel17Layout.setVerticalGroup(
            panel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(currBalance, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 20)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("ACCOUNT BALANCE");

        javax.swing.GroupLayout balPanelLayout = new javax.swing.GroupLayout(balPanel);
        balPanel.setLayout(balPanelLayout);
        balPanelLayout.setHorizontalGroup(
            balPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(balPanelLayout.createSequentialGroup()
                .addGroup(balPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(balPanelLayout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(panel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(balPanelLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(jLabel1)))
                .addContainerGap(35, Short.MAX_VALUE))
        );
        balPanelLayout.setVerticalGroup(
            balPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, balPanelLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        panel1.add(balPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 300, 190));

        screen.setBackground(new java.awt.Color(0, 0, 0));
        screen.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        type1.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        type1.setForeground(new java.awt.Color(255, 255, 255));
        type1.setText("CASH WITHDRAW");
        screen.add(type1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 70, -1, -1));

        prompt.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        prompt.setForeground(new java.awt.Color(255, 255, 255));
        prompt.setText("ENTER AMOUNT");
        screen.add(prompt, new org.netbeans.lib.awtextra.AbsoluteConstraints(229, 113, -1, -1));

        line.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        line.setForeground(new java.awt.Color(255, 255, 255));
        line.setText("___________");
        screen.add(line, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, -1, -1));

        dec.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        dec.setForeground(new java.awt.Color(255, 255, 255));
        dec.setText("  . 00");
        screen.add(dec, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 70, 30));

        amount.setBackground(new java.awt.Color(255, 255, 255));
        amount.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        amount.setForeground(new java.awt.Color(255, 255, 255));
        amount.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        amount.setText("50000");
        screen.add(amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, 60, 30));

        maxWithdrawMsg.setFont(new java.awt.Font("Sitka Text", 0, 15)); // NOI18N
        maxWithdrawMsg.setForeground(new java.awt.Color(255, 0, 0));
        maxWithdrawMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maxWithdrawMsg.setText("Withdraw Limit Reached.");
        screen.add(maxWithdrawMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 308, 30));

        type2.setFont(new java.awt.Font("Segoe UI", 0, 26)); // NOI18N
        type2.setForeground(new java.awt.Color(255, 255, 255));
        type2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        type2.setText("CASH DEPOSIT");
        screen.add(type2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 70, -1, -1));

        maxDepositMsg.setFont(new java.awt.Font("Sitka Text", 0, 15)); // NOI18N
        maxDepositMsg.setForeground(new java.awt.Color(255, 0, 0));
        maxDepositMsg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        maxDepositMsg.setText("Deposit Limit Reached.");
        screen.add(maxDepositMsg, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 310, 308, 30));

        successful.setFont(new java.awt.Font("Sitka Text", 0, 15)); // NOI18N
        successful.setForeground(new java.awt.Color(0, 204, 0));
        successful.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        successful.setText("Successful Transaction.");
        screen.add(successful, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 308, 30));

        invalidAmount.setFont(new java.awt.Font("Sitka Text", 0, 15)); // NOI18N
        invalidAmount.setForeground(new java.awt.Color(255, 0, 0));
        invalidAmount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        invalidAmount.setText("Invalid! Amount must be in hundred(s).");
        screen.add(invalidAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 308, 30));

        notEnoughBal.setFont(new java.awt.Font("Sitka Text", 0, 15)); // NOI18N
        notEnoughBal.setForeground(new java.awt.Color(255, 0, 0));
        notEnoughBal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        notEnoughBal.setText("Not Enough Balance.");
        screen.add(notEnoughBal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 310, 308, 30));

        panel1.add(screen, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 40, 620, 460));

        panel2.setBackground(new java.awt.Color(255, 255, 255));
        panel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        withdraw.setBackground(new java.awt.Color(0, 0, 0));
        withdraw.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        withdraw.setForeground(new java.awt.Color(51, 51, 51));
        withdraw.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        withdraw.setText("             WITHDRAW");
        withdraw.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        withdraw.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                withdrawMouseClicked(evt);
            }
        });
        panel2.add(withdraw, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 200, 100));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/withdraw.png"))); // NOI18N
        panel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, -1, -1));

        deposit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        deposit.setForeground(new java.awt.Color(51, 51, 51));
        deposit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        deposit.setText("             DEPOSIT");
        deposit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deposit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                depositMouseClicked(evt);
            }
        });
        panel2.add(deposit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 100));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/deposit.png"))); // NOI18N
        panel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, -1, -1));

        balance.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        balance.setForeground(new java.awt.Color(51, 51, 51));
        balance.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        balance.setText("             BALANCE");
        balance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        balance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                balanceMouseClicked(evt);
            }
        });
        panel2.add(balance, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 200, 100));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/balance.png"))); // NOI18N
        panel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, -1, -1));

        statement.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        statement.setForeground(new java.awt.Color(51, 51, 51));
        statement.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statement.setText("             STATEMENT");
        statement.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        statement.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                statementMouseClicked(evt);
            }
        });
        panel2.add(statement, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 200, 100));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/statement.png"))); // NOI18N
        panel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 400, -1, -1));

        choice1.setBackground(new java.awt.Color(153, 153, 153));
        choice1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel2.add(choice1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 200, 100));

        choice2.setBackground(new java.awt.Color(153, 153, 153));
        choice2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel2.add(choice2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 200, 100));

        choice3.setBackground(new java.awt.Color(153, 153, 153));
        choice3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel2.add(choice3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 200, 100));

        choice4.setBackground(new java.awt.Color(153, 153, 153));
        choice4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panel2.add(choice4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 200, 100));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 680, Short.MAX_VALUE)
        );

        panel2.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 0, 30, 680));

        exit.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        exit.setForeground(new java.awt.Color(51, 51, 51));
        exit.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        exit.setText("       EXIT");
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        panel2.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 590, 100, 40));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/exit1.png"))); // NOI18N
        panel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 600, -1, -1));

        panel1.add(panel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 678));

        title.setFont(new java.awt.Font("Rockwell Extra Bold", 0, 60)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setText("AYTSUU");
        panel1.add(title, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, -1, -1));

        logo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/atm/logo.png"))); // NOI18N
        panel1.add(logo, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 250, -1, -1));

        numContainer.setBackground(new java.awt.Color(51, 51, 51));
        numContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel4.setBackground(new java.awt.Color(102, 102, 102));

        n7.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        n7.setForeground(new java.awt.Color(255, 255, 255));
        n7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n7.setText("7");
        n7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel4Layout = new javax.swing.GroupLayout(panel4);
        panel4.setLayout(panel4Layout);
        panel4Layout.setHorizontalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n7, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel4Layout.setVerticalGroup(
            panel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n7, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 60, 60));

        panel6.setBackground(new java.awt.Color(102, 102, 102));

        n8.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        n8.setForeground(new java.awt.Color(255, 255, 255));
        n8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n8.setText("8");
        n8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel6Layout = new javax.swing.GroupLayout(panel6);
        panel6.setLayout(panel6Layout);
        panel6Layout.setHorizontalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n8, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel6Layout.setVerticalGroup(
            panel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n8, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 60, 60));

        panel9.setBackground(new java.awt.Color(102, 102, 102));

        n9.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        n9.setForeground(new java.awt.Color(255, 255, 255));
        n9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n9.setText("9");
        n9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel9Layout = new javax.swing.GroupLayout(panel9);
        panel9.setLayout(panel9Layout);
        panel9Layout.setHorizontalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n9, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel9Layout.setVerticalGroup(
            panel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n9, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 60, 60));

        panel7.setBackground(new java.awt.Color(102, 102, 102));

        n4.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        n4.setForeground(new java.awt.Color(255, 255, 255));
        n4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n4.setText("4");
        n4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel7Layout = new javax.swing.GroupLayout(panel7);
        panel7.setLayout(panel7Layout);
        panel7Layout.setHorizontalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel7Layout.setVerticalGroup(
            panel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n4, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 60, 60));

        panel8.setBackground(new java.awt.Color(102, 102, 102));

        n5.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        n5.setForeground(new java.awt.Color(255, 255, 255));
        n5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n5.setText("5");
        n5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel8Layout = new javax.swing.GroupLayout(panel8);
        panel8.setLayout(panel8Layout);
        panel8Layout.setHorizontalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n5, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel8Layout.setVerticalGroup(
            panel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n5, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 60, 60));

        panel5.setBackground(new java.awt.Color(102, 102, 102));

        n6.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        n6.setForeground(new java.awt.Color(255, 255, 255));
        n6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n6.setText("6");
        n6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel5Layout = new javax.swing.GroupLayout(panel5);
        panel5.setLayout(panel5Layout);
        panel5Layout.setHorizontalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n6, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel5Layout.setVerticalGroup(
            panel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n6, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, 60, 60));

        panel10.setBackground(new java.awt.Color(102, 102, 102));

        n2.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        n2.setForeground(new java.awt.Color(255, 255, 255));
        n2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n2.setText("2");
        n2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel10Layout = new javax.swing.GroupLayout(panel10);
        panel10.setLayout(panel10Layout);
        panel10Layout.setHorizontalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel10Layout.setVerticalGroup(
            panel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n2, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, 60, 60));

        panel11.setBackground(new java.awt.Color(102, 102, 102));

        n3.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        n3.setForeground(new java.awt.Color(255, 255, 255));
        n3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n3.setText("3");
        n3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel11Layout = new javax.swing.GroupLayout(panel11);
        panel11.setLayout(panel11Layout);
        panel11Layout.setHorizontalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n3, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel11Layout.setVerticalGroup(
            panel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n3, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 60, 60));

        panel12.setBackground(new java.awt.Color(102, 102, 102));
        panel12.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panel12Layout = new javax.swing.GroupLayout(panel12);
        panel12.setLayout(panel12Layout);
        panel12Layout.setHorizontalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );
        panel12Layout.setVerticalGroup(
            panel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 270, 60, 60));

        panel13.setBackground(new java.awt.Color(102, 102, 102));
        panel13.setForeground(new java.awt.Color(0, 0, 0));

        n1.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        n1.setForeground(new java.awt.Color(255, 255, 255));
        n1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n1.setText("1");
        n1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel13Layout = new javax.swing.GroupLayout(panel13);
        panel13.setLayout(panel13Layout);
        panel13Layout.setHorizontalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel13Layout.setVerticalGroup(
            panel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n1, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 60, 60));

        panel14.setBackground(new java.awt.Color(102, 102, 102));
        panel14.setForeground(new java.awt.Color(0, 0, 0));

        n0.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        n0.setForeground(new java.awt.Color(255, 255, 255));
        n0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        n0.setText("0");
        n0.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        n0.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                n0MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel14Layout = new javax.swing.GroupLayout(panel14);
        panel14.setLayout(panel14Layout);
        panel14Layout.setHorizontalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n0, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel14Layout.setVerticalGroup(
            panel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(n0, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 270, 60, 60));

        panel15.setBackground(new java.awt.Color(102, 102, 102));

        enter.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        enter.setForeground(new java.awt.Color(255, 255, 255));
        enter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        enter.setText("ENTER");
        enter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        enter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                enterMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel15Layout = new javax.swing.GroupLayout(panel15);
        panel15.setLayout(panel15Layout);
        panel15Layout.setHorizontalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(enter, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
        );
        panel15Layout.setVerticalGroup(
            panel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(enter, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
        );

        numContainer.add(panel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, 220, 50));

        panel3.setBackground(new java.awt.Color(102, 102, 102));
        panel3.setForeground(new java.awt.Color(0, 0, 0));

        undo.setFont(new java.awt.Font("Segoe UI", 1, 25)); // NOI18N
        undo.setForeground(new java.awt.Color(255, 255, 255));
        undo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        undo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        undo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                undoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panel3Layout = new javax.swing.GroupLayout(panel3);
        panel3.setLayout(panel3Layout);
        panel3Layout.setHorizontalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(undo, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        panel3Layout.setVerticalGroup(
            panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(undo, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        numContainer.add(panel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 270, 60, 60));

        panel1.add(numContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 60, 280, 440));

        dispenser1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout dispenser1Layout = new javax.swing.GroupLayout(dispenser1);
        dispenser1.setLayout(dispenser1Layout);
        dispenser1Layout.setHorizontalGroup(
            dispenser1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dispenser1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        dispenser1Layout.setVerticalGroup(
            dispenser1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dispenser1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 30, Short.MAX_VALUE))
        );

        panel1.add(dispenser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 550, -1, 130));

        dispenser.setBackground(new java.awt.Color(255, 255, 255));
        dispenser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cash.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout cashLayout = new javax.swing.GroupLayout(cash);
        cash.setLayout(cashLayout);
        cashLayout.setHorizontalGroup(
            cashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 450, Short.MAX_VALUE)
        );
        cashLayout.setVerticalGroup(
            cashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        dispenser.add(cash, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 450, 5));

        receipt.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout receiptLayout = new javax.swing.GroupLayout(receipt);
        receipt.setLayout(receiptLayout);
        receiptLayout.setHorizontalGroup(
            receiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        receiptLayout.setVerticalGroup(
            receiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        dispenser.add(receipt, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 70, 240, 5));

        panel1.add(dispenser, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 550, 1020, 130));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void withdrawMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_withdrawMouseClicked
       
        // If the withdraw label is clicked, some components in the frame will be hidden, and some will be shown to the user
        choice1.setVisible(true); choice2.setVisible(false); choice3.setVisible(false); choice4.setVisible(false); title.setVisible(false); logo.setVisible(false);
        screen.setVisible(true); numContainer.setVisible(true); dispenser.setVisible(true); dispenser1.setVisible(true);
        type1.setVisible(true); type2.setVisible(false);  prompt.setVisible(true); amount.setVisible(true); dec.setVisible(false); line.setVisible(true);
        notEnoughBal.setVisible(false); successful.setVisible(false); maxDepositMsg.setVisible(false); maxWithdrawMsg.setVisible(false);
        
        // Resets the color of dispensers to black and enter amount section to blank
        cash.setBackground(Color.black); receipt.setBackground(Color.black);
        amount.setText("");
        count = 0; // Resets the count of the entered digit to 0
    }//GEN-LAST:event_withdrawMouseClicked

    private void depositMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_depositMouseClicked
        
        // If the deposit label is clicked, some components in the frame will be hidden, and some will be shown to the user
        choice1.setVisible(false); choice2.setVisible(true); choice3.setVisible(false); choice4.setVisible(false); title.setVisible(false); logo.setVisible(false);
        screen.setVisible(true); numContainer.setVisible(true); dispenser.setVisible(true); dispenser1.setVisible(true);
        type1.setVisible(false); type2.setVisible(true); prompt.setVisible(true); amount.setVisible(true); dec.setVisible(false); line.setVisible(true);
        notEnoughBal.setVisible(false); successful.setVisible(false); maxDepositMsg.setVisible(false); maxWithdrawMsg.setVisible(false);
        
        // Resets the color of dispensers to black and enter amount section to blank
        cash.setBackground(Color.black); receipt.setBackground(Color.black);
        amount.setText("");
        count = 0; // Resets the count of the entered digit to 0
    }//GEN-LAST:event_depositMouseClicked

    private void balanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_balanceMouseClicked
        
        // When balance label is clicked, a frame showing the balance of the account will appear
        if(balPanel.isVisible() == true){
            balPanel.setVisible(false);
            return;
        }
        balPanel.setVisible(true);
        
        // Calls the method which checks the balance of the specified account
        accountBalance();
        currBalance.setText(String.format("%.2f",accBalance));
    }//GEN-LAST:event_balanceMouseClicked

    private void statementMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_statementMouseClicked
        
        // When statement label is clicked, a frame showing the transaction records of the account will appear
        if(statementPanel.isVisible() == true){
            statementPanel.setVisible(false);
            model = (DefaultTableModel) statementTable.getModel();
            model.setRowCount(0);
            return;
        }
        statementPanel.setVisible(true);
        statement(); // Calls the method which displays the records
    }//GEN-LAST:event_statementMouseClicked

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // If exit button is clicked, returns to the log in frame, passing values necessary for tracking of overall atm capacity
        Login login = new Login();
        login.overAllDeposits = overAllDeposits;
        login.overAllWithdrawals = overAllWithdrawals;
        login.setVisible(true);
        setVisible(false);
    }//GEN-LAST:event_exitMouseClicked

    private void undoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_undoMouseClicked
        // Performs undo, if count of entered digits is not 0
        if(count > 0){
            undo();
        }    
        invalidAmount.setVisible(false); // Hides a notice
    }//GEN-LAST:event_undoMouseClicked

    private void n0MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n0MouseClicked
        
        // If 0 is clicked 
        String num = "0";
        typeNumber(num); // Calls the method to concatenate the entered number to the label
    }//GEN-LAST:event_n0MouseClicked

    private void n1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n1MouseClicked
        
        // If 1 is clicked 
        String num = "1";
        typeNumber(num); // Calls the method to concatenate the entered number to the label
    }//GEN-LAST:event_n1MouseClicked

    private void n2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n2MouseClicked
        
        // If 2 is clicked
        String num = "2";
        typeNumber(num); // Calls the method to concatenate the entered number to the label
    }//GEN-LAST:event_n2MouseClicked

    private void n3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n3MouseClicked
        
        // If 3 is clicked
        String num = "3";
        typeNumber(num); // Calls the method to concatenate the entered number to the label
    }//GEN-LAST:event_n3MouseClicked

    private void n4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n4MouseClicked
        
        // If 4 is clicked
        String num = "4";
        typeNumber(num); // Calls the method to concatenate the entered number to the label
    }//GEN-LAST:event_n4MouseClicked

    private void n5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n5MouseClicked
        
        // If 5 is clicked
        String num = "5";
        typeNumber(num); // Calls the method to concatenate the entered number to the label
    }//GEN-LAST:event_n5MouseClicked

    private void n6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n6MouseClicked
        
        // If 6 is clicked
        String num = "6";
        typeNumber(num); // Calls the method to concatenate the entered number to the label
    }//GEN-LAST:event_n6MouseClicked

    private void n7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n7MouseClicked
        
        // If 7 is clicked
        String num = "7";
        typeNumber(num); // Calls the method to concatenate the entered number to the label
    }//GEN-LAST:event_n7MouseClicked

    private void n8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n8MouseClicked
        
        // If 8 is clicked
        String num = "8"; 
        typeNumber(num); // Calls the method to concatenate the entered number to the label
    }//GEN-LAST:event_n8MouseClicked

    private void n9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_n9MouseClicked
        
        // If 9 is clicked
        String num = "9";
        typeNumber(num); // Calls the method to concatenate the entered number to the label
    }//GEN-LAST:event_n9MouseClicked

    private void enterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_enterMouseClicked
        
        // if enter button is clicked 
        if(!(amount.getText().equals(""))){ // amount is not blank (therefre, user has entered digits)
            int enterAmount = Integer.parseInt(amount.getText()); // Stores the entered amount to an  variable
            if(enterAmount % 100 == 0 ){ // Checks if entered amount is divisible by 1000
                accountBalance(); // Calls the method to check the balance of the account
                if(choice1.isVisible() == true){ // Nested condition to check if it is for withdrawal
                     
                    if(!(currWithdraw < userWithdrawLimit && enterAmount <= userWithdrawLimit)){ // Checks if current withdraw has reached withdraw limit per account and entered amount is greater than the limit
                        maxWithdrawMsg.setText("Withdraw Limit Reached");
                        maxWithdrawMsg.setVisible(true);
                        return;
                    }
                    
                    // If entered amount for withdrawal is greater than balance
                    if(enterAmount > accBalance || accBalance - enterAmount < maintainingBal){
                        notEnoughBal.setVisible(true); // Shows invalid notice
                        return;
                    } 
                    
                    // If over all withdrawals hasn't reached the over all capacity for withdrawal
                    else if(overAllWithdrawals != withdrawCapacity){
                        withdraw(); // Calls the withdraw method to perform updates
                    }
                    
                }
                else if(choice2.isVisible() == true){ // Condition to check if it is for deposit
                    
                    // Checks if over all deposits hasn't reached the over all capacity for deposit
                    if(overAllDeposits != depositCapacity){
                        if(currDeposit < userDepositLimit && enterAmount <= userDepositLimit){ // Checks if current deposit has not reach deposit limit per account and entered amount is below the limit
                            deposit(); // Calls the deposit method to perform updates
                        }
                        else{ // Otherwise display a limit notice
                            maxDepositMsg.setText("Deposit Limit Reached.");
                            maxDepositMsg.setVisible(true);
                        }
                    }
                    else{ // Display a maximum capacity reached notice
                        maxDepositMsg.setText("Maximum Deposit Capacity Reached.");
                        maxDepositMsg.setVisible(true);
                    }
                }
            }
            else{ // If entered amount is not divisible by 1000, invalid notice will appear
                invalidAmount.setVisible(true);
            }
            
        }
    }//GEN-LAST:event_enterMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amount;
    private atm.Panel balPanel;
    private javax.swing.JLabel balance;
    private javax.swing.JPanel cash;
    private javax.swing.JPanel choice1;
    private javax.swing.JPanel choice2;
    private javax.swing.JPanel choice3;
    private javax.swing.JPanel choice4;
    private javax.swing.JLabel currBalance;
    private javax.swing.JLabel dec;
    private javax.swing.JLabel deposit;
    private javax.swing.JPanel dispenser;
    private atm.Panel dispenser1;
    private javax.swing.JLabel enter;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel invalidAmount;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel line;
    private javax.swing.JLabel logo;
    private javax.swing.JLabel maxDepositMsg;
    private javax.swing.JLabel maxWithdrawMsg;
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
    private javax.swing.JLabel notEnoughBal;
    private javax.swing.JPanel numContainer;
    private atm.Panel panel1;
    private atm.Panel panel10;
    private atm.Panel panel11;
    private atm.Panel panel12;
    private atm.Panel panel13;
    private atm.Panel panel14;
    private atm.Panel panel15;
    private atm.Panel panel17;
    private atm.Panel panel2;
    private atm.Panel panel3;
    private atm.Panel panel4;
    private atm.Panel panel5;
    private atm.Panel panel6;
    private atm.Panel panel7;
    private atm.Panel panel8;
    private atm.Panel panel9;
    private javax.swing.JLabel prompt;
    private javax.swing.JPanel receipt;
    private javax.swing.JPanel screen;
    private javax.swing.JLabel statement;
    private javax.swing.JPanel statementPanel;
    private rojerusan.RSTableMetro statementTable;
    private javax.swing.JLabel successful;
    private javax.swing.JLabel title;
    private javax.swing.JLabel type1;
    private javax.swing.JLabel type2;
    private javax.swing.JLabel undo;
    private javax.swing.JLabel withdraw;
    // End of variables declaration//GEN-END:variables
}
