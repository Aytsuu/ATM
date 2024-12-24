// This file runs the program

package atm;

public class Atm {
    public static void main(String[] args) {
        
        // Creates an object of the frame Login
        Login atm = new Login();
        atm.setVisible(true); // Shows the frame on your screen
        atm.setResizable(false); // Prohibits user from resizing the frame
    }   
}
