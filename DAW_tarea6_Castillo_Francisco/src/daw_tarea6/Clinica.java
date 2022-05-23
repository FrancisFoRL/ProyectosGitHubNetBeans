package daw_tarea6;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import librerias.Fecha;

/**
 * Clase de Clinica que contiene lo necesario para gestiona una Clinica.
 *
 * @author Francisco Castillo
 * @see Centro
 * @version 19/04/2022
 */
public class Clinica extends Centro {

    //Constructor
    public Clinica(String nombreCentro, String direccionCentro, int limiteConsultas) {
        super(nombreCentro, direccionCentro, limiteConsultas);
    }

    //Funciones
    /**
     * Funcion booleana que añade un paciente a un consulta si es posible, en el
     * caso de que no sea posible, se devolvera un false.
     *
     * @param enf Objeto de tipo paciente que se añadira a una consulta.
     * @param consulta numero de consulta a la que se quiere añadir el paciente.
     * @return devuelve false si el numero de consulta pasado por parametro esta
     * ocupado, si no, devolvera true.
     */
    public void addPaciente(Paciente enf, int consulta) {
        getConsultas()[consulta] = enf;
        int dia = Persona.DIAHOY.getDayOfMonth();
        int mes = Persona.DIAHOY.getMonthValue();
        int anio = Persona.DIAHOY.getYear();
        Fecha fecha = new Fecha();
        fecha.setFechaCompleta(dia, mes, anio);
        enf.addVisita(fecha);
        enf.consulta = consulta;
        if(enf.planta > -1 && enf.habitacion > -1){
            enf.planta = -1;
            enf.habitacion = -1;
        }
    }

    /**
     * Funcion booleana que elimina al paciente de la clinica. Si el paciente
     * esta asignado a algun lugar de la clinica, su posicion se quedara nula y
     * el objeto de esta persona se añadira al array de delPaciente y se
     * devolvera true. En caso de que no este asignado se devolvera false, ya
     * que no habra nada que eliminar.
     *
     * @param enf Objeto de tipo Paciente que se quiere eliminar.
     * @return devolvera true si el paciente se elimino correctamente, si no, se
     * devolvera un false.
     */
    public void removePaciente(Paciente enf) throws IOException {
        if (enf.consulta == -1) ; else if (getConsultas()[enf.consulta].getDni().equals(enf.getDni())) {
            getConsultas()[enf.consulta] = null;
            enf.lugar = -1;
            enf.consulta = -1;
            File fichero = new File("src\\Pacientes\\"+enf.getDni()+".dat");
            try {
                ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(fichero));
                escribiendoFichero.writeObject(enf);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Clinica.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    /**
     * Funcion implementada de la interfaz Estadisticas. Esta funcion busca los
     * dias de visita medica en un mes y devuelve el numero de dias que se hizo
     * una visita medica en dicho mes.
     *
     * @param mes atributo de tipo entero que contiene el mes en el que se
     * buscaran los dias de visitas medicas.
     * @return devuelve el numero de dias que se visito en el mes pasado por
     * parametros.
     * @see Estadisticas
     */
    @Override
    public int diaporMes(int mes) {
        int cont = 0;
        for (int x = 0; x < getConsultas().length; x++) {
            if (getConsultas()[x] == null) ; else {
                if (getConsultas()[x].diaporMes(mes) > 0) {
                    cont++;
                }
            }
        }
        return cont;
    }

    /**
     * Funcion implementada de la interfaz Estadisticas. Esta funcion muestra la
     * informacion de una Clinica.
     *
     * @see Persona
     */
    @Override
    public String mostrarEstado() {
        String texto;
        texto = "---------Consultas---------\n";
        for (int x = 0; x < getConsultas().length; x++) {
            if (getConsultas()[x] == null) {
                texto = "Consulta " + (x + 1) + " libre\n";
            } else {
                texto = "Consulta " + (x + 1) + " tiene un paciente ||";
                getConsultas()[x].mostrarEstado();
            }
        }
        return texto;
    }
}
