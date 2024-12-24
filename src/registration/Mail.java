package registration;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail {
    String toEmailAdd = "";
    String host = "smtp.gmail.com";
    String username = "aytsuu008@gmail.com";
    String password = "olmjrdvsvejenkll";
    
    public void sendEmail(String toEmailAdd, String message){
        Properties props = new Properties();
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.port","587");
        
        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
           protected PasswordAuthentication getPasswordAuthentication(){
               return new PasswordAuthentication(username,password);
           } 
        });
        
        try{
            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAdd));
            mimeMessage.setSubject("(AYTSUU BANK) YOUR ACCOUNT ID");
            mimeMessage.setText("\nGreetings from aytsuu bank here's your Account ID\n\nAccount ID: " + message);
            Transport.send(mimeMessage);
        }catch(Exception e){}
    }
}
