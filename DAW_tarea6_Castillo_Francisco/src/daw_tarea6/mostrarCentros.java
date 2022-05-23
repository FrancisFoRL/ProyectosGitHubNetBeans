/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package daw_tarea6;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Window;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

/**
 * Clase que se encarga de mostrar y gestionar el panel de mostrarCentros.
 *
 * @author Francisco Castillo Brull
 * @version 18/05/2022
 */
public class mostrarCentros extends javax.swing.JPanel {

    //Atributos
    /**
     * Array de tipo JRadioButton que contendra todos los centros disponibles.
     */
    private JRadioButton[] rboton;

    /**
     * Array de tipo Centro que guardara todas los centros disponibles.
     */
    private ArrayList<Centro> centros;

    /**
     * Atributo de tipo persona que contendra la persona, la cual se añadira al
     * centro.
     */
    private Persona persona;

    /**
     * Atributo de tipo GestionMedica que guardara el objeto dicho.
     */
    private GestionMedica gestion;

    /**
     * Atributo de tipo Centro que contiene el centro a pasar a la clase
     * botonesCentros.
     */
    private Centro centro;

    /**
     * Atributo de tipo entero que guarda el tipo de persona con el que se va a
     * trabajar.
     */
    private int tipo;

    /**
     * Atributo entero el cual nos indica si una persona va ser cambiada de
     * lugar o un centro va ser eliminado.
     */
    private boolean eliminar;

    //Constructor
    public mostrarCentros(ArrayList<Centro> centros, GestionMedica gestion, boolean eliminar, int tipo, Persona persona) {
        this.centros = centros;
        this.gestion = gestion;
        this.persona = persona;
        this.eliminar = eliminar;
        this.tipo = tipo;
        rboton = new JRadioButton[centros.size()];
        initComponents();
        this.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        grupoBotonesCentros = new javax.swing.ButtonGroup();
        grupoPacientes = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jBotonAceptar = new javax.swing.JButton();
        jRadioConsulta = new javax.swing.JRadioButton();
        jRadioPlanta = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanelMostrar = new javax.swing.JPanel();

        grupoPacientes.add(jRadioConsulta);
        grupoPacientes.add(jRadioPlanta);

        jLabel1.setFont(new java.awt.Font("Elephant", 0, 14));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Elige una opcion: ");

        jBotonAceptar.setText("Aceptar");
        jBotonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBotonAceptarActionPerformed(evt);
            }
        });

        jRadioConsulta.setText("Consulta");
        if(tipo != 2){
            jRadioConsulta.setVisible(false);
        }

        jRadioPlanta.setText("Planta");
        if(tipo != 2){
            jRadioPlanta.setVisible(false);
        }

        jLabel2.setFont(new java.awt.Font("Elephant", 0, 14));
        jLabel2.setText("El paciente se asignara a una: ");
        if(tipo != 2){
            jLabel2.setVisible(false);
        }

        javax.swing.GroupLayout jPanelMostrarLayout = new javax.swing.GroupLayout(jPanelMostrar);
        jPanelMostrar.setLayout(jPanelMostrarLayout);
        jPanelMostrarLayout.setHorizontalGroup(
            jPanelMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 248, Short.MAX_VALUE)
        );
        jPanelMostrarLayout.setVerticalGroup(
            jPanelMostrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jPanelMostrar.setLayout(new java.awt.GridLayout(0,2,5,10));

        jScrollPane1.setViewportView(jPanelMostrar);
        for(int x = 0; x < centros.size(); x++){
            final int f = x;
            rboton[x] = new JRadioButton(centros.get(x).getNombreCentro());
            grupoBotonesCentros.add(rboton[x]);
            rboton[x].addActionListener(evt -> pulsado(f));
            jPanelMostrar.add(rboton[x]);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1)
                .addGap(24, 24, 24))
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(76, 76, 76))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jRadioConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(40, 40, 40)
                .addComponent(jRadioPlanta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(52, 52, 52))
            .addGroup(layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addComponent(jBotonAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(102, 102, 102))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                        .addGap(80, 80, 80)))
                .addGap(20, 20, 20)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioConsulta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jRadioPlanta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(jBotonAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(44, 44, 44))
        );
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Funcion del botonAceptar que controla que al ser pulsado, sugun el tipo y
     * eliminar, si el centro va ser eliminado o en el caso de persona, se
     * añadira o cambiara a ese centro.
     *
     * @param evt
     */
    private void jBotonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBotonAceptarActionPerformed
        boolean seleccionado = false;

        if (eliminar && tipo == 0) {
            for (int i = 0; i < centros.size(); i++) {
                if (rboton[i].isSelected()) {
                    seleccionado = true;
                    if (gestion.delCentro(centros.get(i))) {
                        JOptionPane.showMessageDialog(this, "El centro se elimino correctamente");
                        centros.remove(i);
                        seleccionado = true;
                        Window w = SwingUtilities.getWindowAncestor(this);
                        w.dispose();
                    } else {
                        JOptionPane.showMessageDialog(this, "El centro no se puede eliminar, ya que no esta vacio", "Eliminar Centro", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
            if (!seleccionado) {
                JOptionPane.showMessageDialog(this, "Tiene que seleccionar un centro!", "Eliminar Centro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (!eliminar && tipo == 0) {
            for (int i = 0; i < centros.size(); i++) {
                if (rboton[i].isSelected()) {
                    centro = centros.get(i);
                    seleccionado = true;
                    for (int y = 0; y < gestion.getCentrosMedicos().size(); y++) {
                        if (gestion.getCentrosMedicos().get(y).getIdentificador() == centro.getIdentificador()) {
                            GestionMedicaCentro.posicion = y;
                        }
                    }
                    Window w = SwingUtilities.getWindowAncestor(this);
                    w.dispose();
                    GestionMedicaCentro.jPanelPedirInf.setVisible(true);
                    GestionMedicaCentro.mostrarInf(centro);
                }
            }
            if (!seleccionado) {
                JOptionPane.showMessageDialog(this, "Tiene que seleccionar un centro!", "Eliminar Centro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (tipo == 1 || tipo == 2) {
            for (int i = 0; i < centros.size(); i++) {
                if (rboton[i].isSelected()) {
                    if (tipo == 1) {
                        centro = centros.get(i);
                        seleccionado = true;
                        for (int y = 0; y < gestion.getCentrosMedicos().size(); y++) {
                            if (gestion.getCentrosMedicos().get(y).getIdentificador() == centro.getIdentificador()) {
                                GestionMedicaCentro.posicion = y;
                            }
                        }
                        Window w = SwingUtilities.getWindowAncestor(this);
                        w.dispose();
                        if (!eliminar) {
                            crearPersona();
                        } else if (eliminar) {
                            cambiarCentro();
                        }
                    } else if (tipo == 2) {
                        if (jRadioConsulta.isSelected() || jRadioPlanta.isSelected()) {
                            centro = centros.get(i);
                            seleccionado = true;
                            for (int y = 0; y < gestion.getCentrosMedicos().size(); y++) {
                                if (gestion.getCentrosMedicos().get(y).getIdentificador() == centro.getIdentificador()) {
                                    GestionMedicaCentro.posicion = y;
                                }
                            }
                            Window w = SwingUtilities.getWindowAncestor(this);
                            w.dispose();
                            if (!eliminar) {
                                crearPersona();
                            } else if (eliminar) {
                                cambiarCentro();
                            }
                        }
                    }
                }
            }
            if (!seleccionado) {
                JOptionPane.showMessageDialog(this, "Faltan campos por seleccionar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jBotonAceptarActionPerformed

    /**
     * Funcion encargada de crear la persona segun su tipo. En el caso de ser
     * Paciente creara un JFrame que contendra la clase de botonesCentros, donde
     * ahi podra elegir en que consulta o habitacion sera añadido.
     */
    private void crearPersona() {
        if (GestionPersona.jRadioMedico.isSelected() && tipo == 1) {
            Medico medico = new Medico(GestionPersona.dni, GestionPersona.jAreaNombre.getText(), GestionPersona.jAreaApellido1.getText(),
                    GestionPersona.jAreaApellido2.getText(), GestionPersona.jComboGenero.getSelectedItem().toString(), GestionPersona.fecha, GestionPersona.jComboEspecialidad.getSelectedItem().toString());
            gestion.getCentrosMedicos().get(GestionMedicaCentro.posicion).addTrabajador(medico);
            medico.lugar = GestionMedicaCentro.posicion;
        } else if (GestionPersona.jRadioAdmin.isSelected() && tipo == 1) {
            Administrativo admin = new Administrativo(GestionPersona.dni, GestionPersona.jAreaNombre.getText(), GestionPersona.jAreaApellido1.getText(),
                    GestionPersona.jAreaApellido2.getText(), GestionPersona.jComboGenero.getSelectedItem().toString(), GestionPersona.fecha, GestionPersona.jComboEspecialidad.getSelectedItem().toString());
            gestion.getCentrosMedicos().get(GestionMedicaCentro.posicion).addTrabajador(admin);
            admin.lugar = GestionMedicaCentro.posicion;
        } else if (tipo == 2) {
            JFrame botonera = new JFrame();
            botonera.setLayout(new BorderLayout());
            botonera.setPreferredSize(new Dimension(800, 800));
            botonera.setMinimumSize(new Dimension(600, 600));
            botonera.setExtendedState(botonera.MAXIMIZED_BOTH);
            botonesCentros panel = new botonesCentros(gestion.getCentrosMedicos().get(GestionMedicaCentro.posicion), jRadioConsulta.isSelected(), GestionPersona.dni, null);
            botonera.add(panel, BorderLayout.CENTER);
            botonera.setVisible(true);
        }
        GestionPersona.jPedirInf.setVisible(false);
    }

    /**
     * Funcion que cambia de centro a una persona. Lo primero que hace es
     * eliminar a esa persona del centro en el que se encontraba para paso
     * seguido, añadirlo al nuevo centro.
     */
    private void cambiarCentro() {
        if (tipo == 1) {
            for (int x = 0; x < gestion.getCentrosMedicos().size(); x++) {
                for (int y = 0; y < gestion.getCentrosMedicos().get(x).getTrabajadores().size(); y++) {
                    if (persona.getDni().equals(gestion.getCentrosMedicos().get(x).getTrabajadores().get(y).getDni())) {
                        gestion.getCentrosMedicos().get(x).getTrabajadores().remove(y);
                    }
                }
            }
            persona.lugar = posicionCentro();
            gestion.getCentrosMedicos().get(posicionCentro()).addTrabajador(persona);
            JOptionPane.showMessageDialog(this, "El trabajador se cambio de centro correctamente");
        } else if (tipo == 2) {
            for (int x = 0; x < gestion.getCentrosMedicos().size(); x++) {
                gestion.getCentrosMedicos().get(x).getTrabajadores().remove(persona);
            }
            JFrame botonera = new JFrame();
            botonera.setLayout(new BorderLayout());
            botonera.setPreferredSize(new Dimension(800, 800));
            botonera.setMinimumSize(new Dimension(600, 600));
            botonera.setExtendedState(botonera.MAXIMIZED_BOTH);
            botonesCentros panel = new botonesCentros(gestion.getCentrosMedicos().get(GestionMedicaCentro.posicion), jRadioConsulta.isSelected(), null, persona);
            botonera.add(panel, BorderLayout.CENTER);
            botonera.setVisible(true);
        }
    }

    /**
     * Funcion que devuelve un entero, que en este caso sera la posicion del
     * centro si el identificador es igual que con el centro que se compara.
     *
     * @return
     */
    private int posicionCentro() {
        for (int x = 0; x < gestion.getCentrosMedicos().size(); x++) {
            if (gestion.getCentrosMedicos().get(x).getIdentificador() == centro.getIdentificador()) {
                return x;
            }
        }
        return -1;
    }

    /**
     * Funcion que se encarga de desactiva el boton de planta si el centro es
     * una Clinica, en el caso de que sea un Hospital, se volvera a activar.
     *
     * @param x entero que indica en que posicion de array se encuentra el
     * centro.
     */
    private void pulsado(int x) {
        if (tipo == 2) {
            if (rboton[x].isSelected() && centros.get(x) instanceof Clinica) {
                jRadioPlanta.setEnabled(false);
                jRadioConsulta.setSelected(true);
            } else if (rboton[x].isSelected() && centros.get(x) instanceof Hospital) {
                jRadioPlanta.setEnabled(true);
                grupoPacientes.clearSelection();
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup grupoBotonesCentros;
    private javax.swing.ButtonGroup grupoPacientes;
    private javax.swing.JButton jBotonAceptar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanelMostrar;
    private javax.swing.JRadioButton jRadioConsulta;
    private javax.swing.JRadioButton jRadioPlanta;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
