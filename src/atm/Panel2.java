// This file creates a more rounder edged panel

package atm;
import java.awt.*;

public class Panel2 extends javax.swing.JPanel {
    
    
    // Class constructor
    public Panel2() {
        initComponents();
        setOpaque(false); // Hides the background (Not fully painting the entire panel)
    }
    
     // Overrides the existing method in java 
    @Override
    protected void paintComponent(Graphics g){
        // Initializes Graphics2D variable with the parameter
        Graphics2D g2 = (Graphics2D)g;
        
        // Changes the hints, color, measurements of the 2 dimensional graphics (Panel)
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponents(g); // Calls the super class and method paintComponents passing the graphics
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 115, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 111, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
