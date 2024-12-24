// This file creates a method to add data in the database

package atm;
import java.sql.*;
import registration.*;

public class Database{
   
    // Declaring variables necessary for database connection
   public Connection connect; 
   public ResultSet rs;
   public Statement st;
   public PreparedStatement ps;
   
   public String user = "root";
   public String pass = "";
   public String url = "jdbc:mysql://localhost:3306/atm";
   
   public void addAccount(String First_Name, String Last_Name, String Email, String Pin){
       
       int AccountID = generateID();
       // Performing connection
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           connect = DriverManager.getConnection(url,user,pass);
           String sql = "insert ignore into accounts(First_Name, Last_Name, Email, AccountID, Pin, Balance) values(?, ?, ?, ?, ?, ?)";
           ps = connect.prepareStatement(sql);
           
           // Sets values for each column in the database table
           ps.setString(1, First_Name.toUpperCase());
           ps.setString(2,Last_Name.toUpperCase());
           ps.setString(3,Email);
           ps.setString(4,String.valueOf(AccountID));
           ps.setString(5,Pin);
           ps.setString(6, "0");
           
           // Updates the database
           ps.executeUpdate();
           
           sendEmail(Email,String.valueOf(AccountID));
       }catch(Exception e){
           e.printStackTrace();
       }
   }
   
   public void sendEmail(String email, String accountId){
       Mail sendEmail = new Mail();
       sendEmail.sendEmail(email, accountId);
   }
   
   public int generateID(){
      int startId = 1000000000;
      int lastAccountID = 0;
      try{
          Class.forName("com.mysql.cj.jdbc.Driver");
          connect = DriverManager.getConnection(url,user,pass);
          st = connect.createStatement();
          rs = st.executeQuery("SELECT AccountID FROM accounts ORDER by AccountID");
          
          while(rs.next()){
              lastAccountID = Integer.parseInt(rs.getString("AccountID"));
          }
          
          if(lastAccountID != 0){
            return lastAccountID+1;
          }
        
      }catch(Exception e){ e.printStackTrace(); }
      
      return startId;
   }
   
   // This method stores data in the statement table within the database
   public void statement(String AccountID, String Amount, String Transaction, String Date){
       
       // Performing connection
       try{
           Class.forName("com.mysql.cj.jdbc.Driver");
           connect = DriverManager.getConnection(url,user,pass);
           String sql = "insert ignore into statements(AccountID, Amount, Transaction, Date) values(?, ?, ?, ?)";
           ps = connect.prepareStatement(sql);
           
           // Sets values for each column in the database table
           ps.setString(1, AccountID);
           ps.setString(2,Amount);
           ps.setString(3,Transaction);
           ps.setString(4,Date);
           
           // Updates the database
           ps.executeUpdate();
           
       }catch(Exception e){
           e.printStackTrace();
       }
   }
}
