/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.GridLayout;
import javax.swing.JButton;

/**
 *
 * @author david
 */
public class PanelBotones extends javax.swing.JPanel {

    /**
     * Creates new form PanelBotones
     */
    /*public PanelBotones() {
        initComponents();
    }*/
    public PanelBotones(JButton [][] botones) 
    {
        GridLayout layout = new GridLayout(botones.length, botones.length, 3,3);
        this.setLayout(layout);
        for(int i=0; i<botones.length;i++)
        {
            for(int j=0; j<botones.length;j++)
            {
                this.add(botones[i][j]);
            }
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
