package daw_tarea6;

import java.util.ArrayList;
import librerias.Fecha;

import java.util.Arrays;

/**
 * Clase de Paciente que contiene lo necesario para gestionar a un paciente.
 * @author Francisco Castillo
 * @see Persona
 * @version 19/04/2022
 */

public class Paciente extends Persona {

    /**
     * Array de tipo fecha que guarda todas la visitas medica que tiene el Paciente.
     */
    private ArrayList<Fecha> visitasMedicas;

    /**
     * Atributos que indican donde se encuentra el paciente
     */
    protected int planta=-1, habitacion=-1, consulta=-1;

    //Constructores
    public Paciente(String dni, String nombre, String apellido1, String apellido2, String genero, Fecha fechaNacimiento) {
        super(dni, nombre, apellido1, apellido2, genero, fechaNacimiento);
        this.visitasMedicas = new ArrayList();
    }


    //Funciones

    /**
     * Funcion booleana que añade una visita medica nueva del Paciente. Comprobara que la fecha no sea menor a la fecha de nacimiento de Paciente. Si es menor a su fecha de
     * nacimiento devolvera un false.
     * @param visita parametro de tipo Fecha que contiene la fecha que se quiere añadir.
     * @return devuelve falso si la fecha pasada por parametro no es valida, si es valida se devolvera true y se añadira al array de visitasMedicas
     */
    public boolean addVisita(Fecha visita) {
        if (!visita.comprobarFechaValida(getFechaNacimiento(),0)) {
            return false;
        } else {
            for (int x = 0; x < visitasMedicas.size(); x++) {
                if (visita.getDia() == visitasMedicas.get(x).getDia() && visita.getMes() == visitasMedicas.get(x).getMes() && visita.getAnio() == visitasMedicas.get(x).getAnio()) {
                    return false;
                }
            }
            visitasMedicas.add(visita);
        }
        return true;
    }

    /**
     * Funcion de tipo String que busca la fecha mas reciente en el array de visitasMedicas y lo devuelve.
     * @return devuelve la ultima visitaMedica del Paciente.
     */
    private String ultimaVisita() {
        Fecha fecha = new Fecha(0,0,0);
        for (Fecha visitasMedica : visitasMedicas) {
            if (visitasMedica == null);
            else if (visitasMedica.getAnio() > fecha.getAnio()) {
                fecha.setFechaCompleta(visitasMedica.getDia(), visitasMedica.getMes(), visitasMedica.getAnio());
            } else if (visitasMedica.getAnio() == fecha.getAnio()) {
                if (visitasMedica.getMes() > fecha.getMes()) {
                    fecha.setFechaCompleta(visitasMedica.getDia(), visitasMedica.getMes(), visitasMedica.getAnio());
                } else if (visitasMedica.getMes() == fecha.getMes()) {
                    if (visitasMedica.getDia() > fecha.getDia())
                        fecha.setFechaCompleta(visitasMedica.getDia(), visitasMedica.getMes(), visitasMedica.getAnio());
                }
            }
        }
        return fecha.toString();
    }

    /**
     * Funcion de tipo booleana que comprueba que la fecha de nacimiento este entre rango que se indica.
     * @param fNacimiento atributo de tipo fecha que contiene la fecha a comprobar.
     * @return devuelve false si la fecha esta fuera de rango devolvera false, si no, devolvera true.
     */
    public static boolean validarFechaNacimiento(Fecha fNacimiento) {
        return fNacimiento.getAnio() >= YEAR - 120 && fNacimiento.getAnio() <= YEAR;
    }

    /**
     * Funcion implementada de la interfaz Estadisticas. Esta funcion busca los dias de visita medica en un mes y devuelve el numero de dias que se hizo una visita medica
     * en dicho mes.
     * @param mes atributo de tipo entero que contiene el mes en el que se buscaran los dias trabajados.
     * @return devuelve el numero de dias que se trabajo en el mes pasado por parametros.
     * @see Estadisticas
     */
    @Override
    public int diaporMes(int mes) {
        int cont = 0;
        for (Fecha visitasMedica : visitasMedicas) {
            if (visitasMedica == null) {
                break;
            } else {
                if (visitasMedica.getMes() == mes) {
                    cont++;
                }
            }
        }
        return cont;
    }

    @Override
    public String mostrarEstado() {
        return "ID: "+getIdentificador()+" || DNI:" + getDni() + " || Nombre: " + getNombre() + " || Apellidos: " + getApellido1() + " " + getApellido2() +
                " || Ultima visita medica: " + ultimaVisita() +"\n";
    }

    

    

}//Fin Paciente
