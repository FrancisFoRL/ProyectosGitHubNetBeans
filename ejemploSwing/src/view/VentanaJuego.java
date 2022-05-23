/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import excepciones.DimensionException;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import logica.Casilla;
import logica.TableroLogico;

/**
 *
 * @author david
 */
public class VentanaJuego extends javax.swing.JFrame implements MouseListener
{
    private TableroLogico tableroL;
    private int dim, numMinas;
    private JButton [][] botones;
    private PanelBotones panelBotones;
    
    
    /**
     * Creates new form VentanaJuego
     */
    public VentanaJuego() {
        initComponents();
        inicia(10,25);
    }
    
    private void inicia(int d, int m)
    {
        dim=d;
        numMinas=m;
        tableroL = new TableroLogico(dim, numMinas);
        botones = new JButton[d][d];
        iniciarBotones();
        panelBotones=new PanelBotones(botones);
        this.add(panelBotones, BorderLayout.CENTER);
        
        
        
    }
    
    private void iniciarBotones()
    {
        for(int i=0; i<dim;i++)
        {
            for(int j=0; j<dim;j++)
            {
                final int f=i;
                final int c=j;
                botones[i][j]=new JButton();
                botones[i][j].setFont(new java.awt.Font("Verdana", 1, 18)); 
                botones[i][j].setForeground(new java.awt.Color(255, 102, 102));
                botones[i][j].addActionListener(evt->pulsado(f,c));
                botones[i][j].addMouseListener(this);
            }
        }
    }
    
    private void pulsado(int f, int c)
    {
        System.out.println("Pulsado: "+f+","+c);
        if(tableroL.getTableroJuego()[f][c].isMina())
        {
            //perder
            mostrarMinas();
            desactivarBotones();
            JOptionPane.showMessageDialog(this, "****PERDISTE****");
        }
        else
        {
            //se destapa la casilla y las de alrededor, si es necesario
            destaparCasilla(f,c);
            if(tableroL.isJuegoGanado())
            {
                mostrarMinas();
                desactivarBotones();
                JOptionPane.showMessageDialog(this, "****GANASTE****");
            }
        }
    }
    
    private void mostrarMinas()
    {
        ArrayList<Casilla> aminas = tableroL.getListaMinas();
        for(Casilla c : aminas)
        {
            ponerIcono(botones[c.getFila()][c.getColumna()],"/img/mina.jpg");
        }
    }
    
    private void desactivarBotones()
    {
        for(int i=0; i<dim; i++)
        {
            for(int j=0;j<dim;j++)
            {
                botones[i][j].setEnabled(false);
            }
        }
        
        //Quitar listener
        /*
        for(int i=0; i<dim; i++)
        {
            for(int j=0;j<dim;j++)
            {
                botones[i][j].removeActionListener(botones[i][j].getActionListeners());
            }
        }
        */
    }
    
    private void ponerIcono(JButton jb, String uri)
    {
        int w =jb.getWidth()-10;
        int h = jb.getHeight()-10;
        ImageIcon icono = new ImageIcon(getClass().getResource(uri));
        jb.setIcon(new ImageIcon(icono.getImage().getScaledInstance(w, h, Image.SCALE_DEFAULT)));
    }
    
    private void destaparCasilla(int fila, int col)
    {
        tableroL.destaparCasilla(fila, col);
        for(int i=0; i<dim; i++)
        {
            for(int j=0;j<dim;j++)
            {
                if(tableroL.getTableroJuego()[i][j].isAbierta())
                {
                    if(tableroL.getTableroJuego()[i][j].getMinasProximas()>0)
                    {
                        botones[i][j].setText(""+tableroL.getTableroJuego()[i][j].getMinasProximas());
                    }
                    botones[i][j].setEnabled(false);
                }
                
            }
        }
    }
    
    private void ponerBandera(int fil, int col)
    {
        if(tableroL.getTableroJuego()[fil][col].isMarcada())
        {
            //quita la bandera
            tableroL.getTableroJuego()[fil][col].setMarcada(false);
            botones[fil][col].setIcon(null);
        }
        else
        {
            //pone bandera
            tableroL.getTableroJuego()[fil][col].setMarcada(true);
            ponerIcono(botones[fil][col], "/img/bandera.jpg");
        }
    }
    
    private void evalua (int d)
    {
        dim = d;
        switch (d)
        {
            case 5:
            case 6:
                numMinas=5;
                break;
            case 7:
            case 8:
                numMinas=20;
                break;
            case 9:
            case 10:
                numMinas=25;
                break;
            case 11:
            case 12:
                numMinas=30;
                break;
            case 13:
            case 14:
            case 15:
                numMinas=35;
                break;
        }
        nuevoJuego();
    }
    
    private void nuevoJuego()
    {
        //this.getContentPane().setVisible(false);
        this.remove(panelBotones);
        inicia(dim, numMinas); 
        //this.getContentPane().setVisible(true);
        this.revalidate();
        //this.setVisible(true);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jmOpciones = new javax.swing.JMenu();
        jmiNuevoJuego = new javax.swing.JMenuItem();
        jmiDimension = new javax.swing.JMenuItem();
        jmiSalir = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(600, 600));
        setPreferredSize(new java.awt.Dimension(800, 800));

        jmOpciones.setText("Opciones");

        jmiNuevoJuego.setText("Nuevo Juego");
        jmiNuevoJuego.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiNuevoJuegoActionPerformed(evt);
            }
        });
        jmOpciones.add(jmiNuevoJuego);

        jmiDimension.setText("Dimension");
        jmiDimension.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiDimensionActionPerformed(evt);
            }
        });
        jmOpciones.add(jmiDimension);

        jmiSalir.setText("Salir");
        jmiSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiSalirActionPerformed(evt);
            }
        });
        jmOpciones.add(jmiSalir);

        jMenuBar1.add(jmOpciones);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jmiDimensionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiDimensionActionPerformed
        String sDim = JOptionPane.showInputDialog(this,"Introduce dimensión (5-15)");
        int d;
        try
        {
            d=Integer.parseInt(sDim);
            if(d<5 || d>15)
            {
                throw new DimensionException(5, 15);
            }
            evalua(d);
        }
        catch (NumberFormatException nfe)
        {
            JOptionPane.showMessageDialog(this, "Error. Se espera número entre 5 y 15.");
        }
        catch (DimensionException de)
        {
            JOptionPane.showMessageDialog(this, de.getMessage());
        }
        
    }//GEN-LAST:event_jmiDimensionActionPerformed

    private void jmiNuevoJuegoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiNuevoJuegoActionPerformed
        nuevoJuego();  
    }//GEN-LAST:event_jmiNuevoJuegoActionPerformed

    private void jmiSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiSalirActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jmiSalirActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaJuego.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaJuego().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenu jmOpciones;
    private javax.swing.JMenuItem jmiDimension;
    private javax.swing.JMenuItem jmiNuevoJuego;
    private javax.swing.JMenuItem jmiSalir;
    // End of variables declaration//GEN-END:variables

    @Override
    public void mouseClicked(MouseEvent e) 
    {
       for(int i=0; i<dim; i++)
        {
            for(int j=0;j<dim;j++)
            {
                if(e.getSource()==botones[i][j] && botones[i][j].isEnabled() && e.getButton()==MouseEvent.BUTTON3 && e.getClickCount()==1)
                {
                    ponerBandera(i,j);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }
}
