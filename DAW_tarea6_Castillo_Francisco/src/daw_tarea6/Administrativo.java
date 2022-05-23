package daw_tarea6;

import librerias.Fecha;

import java.util.ArrayList;

/**
 * Clase de Administrativo que contiene lo necesario para gestionar a un Administrativo.
 * @author Francisco Castillo
 * @see Persona
 * @version 19/04/2022
 */
public class Administrativo extends Persona{

    //Atributos
    /**
     * Array de tipo Fecha que guardara todos los dias trabajados de un Administrativo.
     */
    private ArrayList<Fecha> diasTrabajados;

    /**
     * Atributo de tipo cadena que guardara la especialidad de Administrativo.
     */
    private String area;


    //Constructores
    public Administrativo(String dni, String nombre, String apellido1, String apellido2, String genero, Fecha fechaNacimiento, String area) {
        super(dni, nombre, apellido1, apellido2, genero, fechaNacimiento);
        this.diasTrabajados = new ArrayList();
        this.area = area;
    }

    //Getters y Setters
    public void setArea(String area) {
        this.area = area;
    }

    public String getArea() {
        return area;
    }
    
    

    //Funcion
    /**
     * Funcion booleana que añade un dia trabajado nuevo del Administrativo. Comprobara que la fecha no sea menor a la fecha de nacimiento del Administrativo. Si es menor a su fecha de
     * nacimiento devolvera un false.
     * @param trabajo parametro de tipo Fecha que contiene la fecha que se quiere añadir.
     * @return devuelve falso si la fecha pasada por parametro no es valida, si es valida se devolvera true y se añadira al array de diasTrabajados.
     */
    public boolean addDiasTrabajados(Fecha trabajo){
        if (!trabajo.comprobarFechaValida(getFechaNacimiento(),18)) {
            return false;
        } else {
            for (int x = 0; x < diasTrabajados.size(); x++) {
                if (trabajo.getDia() == diasTrabajados.get(x).getDia() && trabajo.getMes() == diasTrabajados.get(x).getMes() && trabajo.getAnio() == diasTrabajados.get(x).getAnio()) {
                    return false;
                }
            }
            diasTrabajados.add(trabajo);
        }
        return true;
    }

    /**
     * Funcion booleana que comprueba que parametros pasado sea como los datos que se encuentran en la funcion.
     * @param especialidad datos de tipo cadena que contiene la especialidad del medico.
     * @return devuelve un true si la especialidad es correcta y false si no lo es.
     */
    public static boolean comprobarEspecialidad(String especialidad){
        return especialidad.equalsIgnoreCase("Urgencias") || especialidad.equalsIgnoreCase("Quirofano") || especialidad.equalsIgnoreCase("Administracion");
    }

    /**
     * Funcion de tipo booleana que comprueba que la fecha de nacimiento este entre rango que se indica.
     * @param fNacimiento atributo de tipo fecha que contiene la fecha a comprobar.
     * @return devuelve false si la fecha esta fuera de rango devolvera false, si no, devolvera true.
     */
    public static boolean validarFechaNacimiento(Fecha fNacimiento) {
        return fNacimiento.getAnio() >= YEAR - 65 && fNacimiento.getAnio() <= YEAR - 18;
    }

    /**
     * Funcion implementada de la interfaz Estadisticas. Esta funcion busca los dias trabajados en un mes y devuelve el numero de dias que se trabajo
     * en dicho mes.
     * @param mes atributo de tipo entero que contiene el mes en el que se buscaran los dias trabajados.
     * @return devuelve el numero de dias que se trabajo en el mes pasado por parametros.
     * @see Estadisticas
     */
    @Override
    public int diaporMes(int mes) {
        int cont = 0;
        for (Fecha diaTrabajado : diasTrabajados) {
            if (diaTrabajado == null) {
            } else {
                if (diaTrabajado.getMes() == mes) {
                    cont++;
                }
            }
        }
        return cont;
    }

    /**
     * Funcion implementada de la interfaz Estadisticas. Esta funcion muestra la informacion de un administrativo.
     * @see Persona
     */
    @Override
    public String mostrarEstado() {
        return "ID: "+getIdentificador()+ "|| DNI Administrativo: " + getDni() + " || Nombre: " + getNombre() + " || Apellidos: " + getApellido1() + " " + getApellido2() +
                " || Dias Trabajados: "+ diasTrabajados.size() + "\n";
    }

    @Override
    public String toString() {
        return super.toString()+ " || Area: "+area;
    }
}//Fin Administrativo
