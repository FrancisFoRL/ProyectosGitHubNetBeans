package daw_tarea6;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase abstrata Centro que contiene toda la informacion necesaria que hace
 * falta para crear una nueva Centro.
 *
 * @author Francisco Castillo
 * @version 19/04/2022
 */
public abstract class Centro implements Estadisticas, Serializable, Comparable<Centro> {

    /**
     * Atributos que contendran los datos principales de un Centro.
     */
    private String nombreCentro, direccionCentro;

    /**
     * identificador: Atributo que guarda el ID propio del Centro. Atributos que
     * llevan la cuenta de Medicos y Administrativos creados.
     */
    private int identificador, contMedicos, contAdministrativos;

    /**
     * Atributo que indica en numero de consultas que tendra un centro.
     */
    protected int limiteConsultas;

    /**
     * Atributos que lleva la cuenta de los ID que se asignan a los Centros.
     */
    protected static int contID;

    /**
     * Atributos que lleva la cuenta de los Centros creados.
     */
    protected static int contCentros;

    /**
     * Array donde se guardaran los paciente de consultas
     */
    private Paciente[] consultas;

    /**
     * Array donde se guardaran los trabajadores
     */
    private ArrayList<Persona> trabajadores;


    /**
     * Array donde se guardaran los pacientes eliminados
     */
    protected static Persona[] delPaciente = new Persona[5];

    //Constructores
    public Centro(String nombreCentro, String direccionCentro, int limiteConsultas) {
        this.nombreCentro = nombreCentro;
        this.direccionCentro = direccionCentro;
        this.consultas = new Paciente[limiteConsultas];
        this.limiteConsultas = limiteConsultas;
        this.trabajadores = new ArrayList();
        identificador = contID++;
        contCentros++;
    }

    //Getters y Setters
    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public String getDireccionCentro() {
        return direccionCentro;
    }

    public void setDireccionCentro(String direccionCentro) {
        this.direccionCentro = direccionCentro;
    }

    public Paciente[] getConsultas() {
        return consultas;
    }

    public ArrayList<Persona> getTrabajadores() {
        return trabajadores;
    }

    public int getIdentificador() {
        return identificador;
    }

    //Funciones
    /**
     * Funcion que comprueba si la cadena nombreCentro pasada por parametro no
     * sea igual a ningun nombre de los objetos que se encuentran dentro del
     * array.
     *
     * @param nombreCentro cadena que contiene el nombre del Centro.
     * @param centros array donde se comprobaran los nombres
     * @return devuelve true si el nombre no existe dentro del array, si no,
     * devolvera false.
     */
    protected static boolean validarNombre(String nombreCentro, ArrayList<Centro> centros) {
        for (int x = 0; x < centros.size(); x++) {
            if (centros.get(x) == null); else if (centros.get(x).nombreCentro.equalsIgnoreCase(nombreCentro)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Funcion que comprueba si la cadena nombreCentro pasada por parametro no
     * sea igual a ningun nombre de los objetos que se encuentran dentro del
     * array.
     *
     * @param nombreDireccion cadena que contiene el nombre de la Direccion.
     * @param centros array donde se comprobaran los nombres
     * @return devuelve true si el nombre no existe dentro del array, si no,
     * devolvera false.
     */
    protected static boolean validarDireccion(String nombreDireccion, ArrayList<Centro> centros) {
        for (int x = 0; x < centros.size(); x++) {
            if (centros.get(x) == null); else if (centros.get(x).nombreCentro.equalsIgnoreCase(nombreDireccion)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Funcion que añade un trabajador al array de trabajadores.
     *
     * @param trabajador objeto de tipo persona que se añadira al array.
     */
    public void addTrabajador(Persona trabajador) {
        trabajadores.add(trabajador);
        if (trabajador instanceof Medico) {
            contMedicos++;
            for (int x = 0; x < trabajadores.size(); x++) {
                if (trabajador.getDni().equals(trabajadores.get(x).getDni())) {
                    trabajador.posArray = x;
                }
            }
        } else if (trabajador instanceof Administrativo) {
            contAdministrativos++;
            for (int x = 0; x < trabajadores.size(); x++) {
                if (trabajador.getDni().equals(trabajadores.get(x).getDni())) {
                    trabajador.posArray = x;
                }
            }
        }    
    }

    /**
     * Funcion booleana que elimina al trabajdor del array de trabajadores. Si
     * el trabajador esta asignado a algun lugar, su posicion se quedara nula y
     * el objeto de esta persona se añadira al array de delTrabajador y se
     * devolvera true. En caso de que no este asignado se devolvera false, ya
     * que no habra nada que eliminar.
     *
     * @param trabajador Objeto de tipo Persona que se quiere eliminar.
     * @return devolvera true si el trabajador se elimino correctamente, si no,
     * se devolvera un false.
     */
    public boolean removeTrabajador(Persona trabajador) {
        if (trabajador.lugar == -1 && trabajador.posArray == -1) ; else if (trabajadores.get(trabajador.posArray).getDni().equals(trabajador.getDni())) {
            trabajadores.remove(trabajador);
            trabajador.lugar = -1;
            trabajador.posArray = -1;
            if (trabajador instanceof Medico) {
                contMedicos--;
            } else if (trabajador instanceof Administrativo) {
                contAdministrativos--;
            }
            File fichero = new File("src\\Trabajadores\\"+trabajador.getDni()+".dat");
            ObjectOutputStream escribiendoFichero;
            try {
                escribiendoFichero = new ObjectOutputStream(new FileOutputStream(fichero));
                escribiendoFichero.writeObject(trabajador);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Centro.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Centro.class.getName()).log(Level.SEVERE, null, ex);
            }
            return true;
        }
        return false;
    }

    

    @Override
    public int compareTo(Centro o) {
        return nombreCentro.compareTo(o.getNombreCentro());
    }

}//Fin de Centro
