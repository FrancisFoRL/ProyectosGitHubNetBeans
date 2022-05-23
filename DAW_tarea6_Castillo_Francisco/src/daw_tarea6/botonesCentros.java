/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daw_tarea6;

import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * Clase que se encarga de mostrar y gestionar el panel de botonesCentros.
 *
 * @author Francisco Castillo Brull
 * @version 18/05/2022
 */
public class botonesCentros extends javax.swing.JPanel {

    //Atributos
    /**
     * Atributo de tipo centro, que contendra el centro del cual se va a mostrar
     * sus consulta y en caso de ser Hospital, sus habitaciones.
     */
    Centro centro;

    /**
     * Atributo que indicara si el objeto centro, se le añadira en paciente en
     * una consulta o no.
     */
    boolean consulta;

    /**
     * Atributo de tipo String que contendra el dni de la persona.
     */
    String dni;

    /**
     * Array de tipo JButton que contendra todas las habitaciones de un
     * hospital.
     */
    private JButton[][] botones;
    
    /**
     * Array de tipo JButton que contendra todas las consultas de un centro.
     */
    private JButton[] consultas;
    
    /**
     * Atributo de tipo persona que contendra el objeto a añadir a un centro.
     */
    private Persona persona;
    
    //Constructor
    public botonesCentros(Centro centro, boolean consulta, String dni, Persona persona) {
        this.dni = dni;
        this.centro = centro;
        this.consulta = consulta;
        this.persona = persona;
        if (!consulta) {
            botones = new JButton[(((Hospital) centro).getHabitaciones().length)][((Hospital) centro).getHabitaciones()[0].length];
        } else if (consulta) {
            consultas = new JButton[centro.getConsultas().length];
        }
        iniciarBotones();
        crearBotones();
    }

    /**
     * Funcion que se encarga de darle forma al panel y añadir todos los botones 
     * necesarios.
     */
    private void crearBotones() {
        if (!consulta) {
            GridLayout layout = new GridLayout(botones.length, botones[0].length, 3, 3);
            this.setLayout(layout);
            for (int i = 0; i < botones.length; i++) {
                this.add(new JLabel("Planta " + (i + 1)));
                for (int j = 0; j < botones[i].length; j++) {
                    this.add(botones[i][j]);
                }
            }
        } else if (consulta) {
            GridLayout layout = new GridLayout(consultas.length, 0, 3, 3);
            this.setLayout(layout);
            for (int i = 0; i < consultas.length; i++) {
                this.add(consultas[i]);
            }
        }
    }
    
    /**
     * Funcion que inicia todos los botones del array de consultas o botones, 
     * segun el caso.
     */
    private void iniciarBotones() {
        if (!consulta) {
            for (int i = 0; i < botones.length; i++) {
                for (int j = 0; j < botones[i].length; j++) {
                    final int f = i;
                    final int c = j;
                    botones[i][j] = new JButton();
                    botones[i][j].setFont(new java.awt.Font("Verdana", 1, 18));
                    botones[i][j].setText("Hab." + (j + 1));
                    botones[i][j].setForeground(new java.awt.Color(255, 102, 102));
                    if (((Hospital) centro).getHabitaciones()[i][j] != null) {
                        botones[i][j].setEnabled(false);
                    }
                    botones[i][j].addActionListener(evt -> pulsado(f, c));
                }
            }
        } else if (consulta) {
            for (int i = 0; i < consultas.length; i++) {
                final int f = i;
                consultas[i] = new JButton();
                consultas[i].setFont(new java.awt.Font("Verdana", 1, 18));
                consultas[i].setText("Consulta." + (i + 1));
                consultas[i].setForeground(new java.awt.Color(255, 102, 102));
                if (centro.getConsultas()[i] != null) {
                    consultas[i].setEnabled(false);
                }
                consultas[i].addActionListener(evt -> pulsado(f, 0));

            }
        }
    }

    /**
     * Funcio que se encarga de añadir el paciente al lugar que segun se haya 
     * seleccionado. Al final de todo esto, cerrara el jPanel.
     * @param f entero que contiene el numero fila del lugar seleccionado.
     * @param c entero que contiene el numero de columna del lugar seleccionado.

     */
    private void pulsado(int f, int c) {
        if (dni != null) {
            if (centro instanceof Clinica) {
                ((Clinica) centro).addPaciente(new Paciente(dni, GestionPersona.jAreaNombre.getText(), GestionPersona.jAreaApellido1.getText(), GestionPersona.jAreaApellido2.getText(),
                        GestionPersona.jComboGenero.getSelectedItem().toString(), GestionPersona.fecha), f);
                JOptionPane.showMessageDialog(this, "El paciente se añadio a la consulta " + (f + 1));
            } else if (centro instanceof Hospital && consulta) {
                ((Hospital) centro).addPaciente(new Paciente(dni, GestionPersona.jAreaNombre.getText(), GestionPersona.jAreaApellido1.getText(), GestionPersona.jAreaApellido2.getText(),
                        GestionPersona.jComboGenero.getSelectedItem().toString(), GestionPersona.fecha), f);
                JOptionPane.showMessageDialog(this, "El paciente se añadio a la consulta " + (f + 1));
            } else if (centro instanceof Hospital && !consulta) {
                ((Hospital) centro).addPaciente(new Paciente(dni, GestionPersona.jAreaNombre.getText(), GestionPersona.jAreaApellido1.getText(), GestionPersona.jAreaApellido2.getText(),
                        GestionPersona.jComboGenero.getSelectedItem().toString(), GestionPersona.fecha), f, c);
                JOptionPane.showMessageDialog(this, "El paciente se añadio a la planta " + (f + 1) + ", habitación " + (c + 1));
            }
        } else if (dni == null) {
            if (centro instanceof Clinica) {
                ((Clinica) centro).addPaciente((Paciente) persona, f);
                JOptionPane.showMessageDialog(this, "El paciente se añadio a la consulta " + (f + 1));
            } else if (centro instanceof Hospital && consulta) {
                ((Hospital) centro).addPaciente((Paciente) persona, f);
                JOptionPane.showMessageDialog(this, "El paciente se añadio a la consulta " + (f + 1));
            } else if (centro instanceof Hospital && !consulta) {
                ((Hospital) centro).addPaciente((Paciente) persona, f, c);
                JOptionPane.showMessageDialog(this, "El paciente se añadio a la planta " + (f + 1) + ", habitación " + (c + 1));
            }
        }
        Window w = SwingUtilities.getWindowAncestor(this);
        w.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridLayout(1, 0));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
