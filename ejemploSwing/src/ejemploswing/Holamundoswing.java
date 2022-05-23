/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemploswing;

// importar la librería Swing
import javax.swing.*;

public class Holamundoswing
{
    public static void main(String[] args) 
    {
        // Crear una ventana con título denominada frame
        JFrame frame = new JFrame("Ventana Hola Mundo");
        // Terminar el programa al cerrar la ventana
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        // Crear una etiqueta denominada label
        JLabel label= new JLabel("Hola Mundo");
        // Añadir la etiqueta a la ventana
        //frame.getContentPane().add(label);
        frame.add(label);
        // Establece el tamaño de la ventana de la forma más adecuada
        frame.pack();
        // Centrar la ventana
        frame.setLocationRelativeTo(null);
        // Hacer visible la ventana
        frame.setVisible(true);
    } // fin main
}// fin clase Holamundoswing
