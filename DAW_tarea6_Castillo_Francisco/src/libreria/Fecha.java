package librerias;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Year;

/**
 * Clase de Fecha que gestiona y almacenada dia, mes y año deseado. Esta tambien gestiona que la fecha sea correcta
 * @author Francisco Castillo
 * @version 28/01/2022
 */
public class Fecha implements Serializable {
    //Atributos
    private int dia, mes, anio;
    protected static final LocalDate DIAHOY = LocalDate.now();
    private static final int year = Year.now().getValue();
    //Constructores
    public Fecha(){
        anio = 1;
        mes = 1;
        dia = 1;
    }

    public Fecha(int mes, int dia, int anio) {
        if (comprobarAnio(anio)) {
            this.anio = anio;
        } else {
            this.anio = 1;
        }

        if (comprobarMes(mes)) {
            this.mes = mes;
        } else {
            this.mes = 1;
        }

        if (comprobarDia(dia, mes, anio)) {
            this.dia = dia;
        } else {
            this.dia = 1;
        }
    }

    //Getters
    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    //Funciones
    /**
     * Funcion que se le pasa una fecha y comprueba que sea correcta, si no devuelve que la fecha
     * no es valida.
     * @param dia Dia del mes
     * @param mes Mes del año
     * @param anio Valor de año
     * @return dara un true si la fecha pasada es correcta, si hay algun error, devolvera false.
     */
    public boolean setFechaCompleta(int dia, int mes, int anio){
        if (comprobarAnioCompleto(anio)) {
            this.anio = anio;
        } else {
            return false;
        }

        if (comprobarMes(mes)) {
            this.mes = mes;
        } else {
            return false;
        }

        if (comprobarDia(dia, mes, anio)) {
            this.dia = dia;
        } else {
            return false;
        }
        return true;
    }

    /**
     * Funcion que se le pasa una fecha y comprueba que sea correcta, si no devuelve que la fecha
     * no es valida. Podremos añadir un rango de años.
     * @param rango1 Rango del año
     * @param rango2 Rango del año
     * @param dia Dia del mes
     * @param mes Mes del año
     * @param anio Valor de año
     * @return dara un true si la fecha pasada es correcta, si hay algun error, devolvera false.
     */
    public boolean setFechaCompletaRango(int rango1, int rango2, int dia, int mes, int anio){
        if(rango1 < rango2) {
            if (anio >= rango1 && anio <= rango2) {
                this.anio = anio;
            } else {
                return false;
            }
        }else{
            if (anio <= rango1 && anio >= rango2) {
                this.anio = anio;
            } else {
                return false;
            }
        }

        if (comprobarMes(mes)) {
            this.mes = mes;
        } else {
            return false;
        }

        if (comprobarDia(dia, mes, anio)) {
            this.dia = dia;
        } else {
            return false;
        }
        return true;
    }

    /**
     * Funcion que se le pasa un valor entero que sera un año y comprueba que este dentro de un
     * rango de 0 y 2100.
     * @return dara un true si año pasado es correcto, si no, devolvera false.
     */
    private boolean comprobarAnio(int rango) {
        if (anio < rango || anio > year) {
            return false;
        }
        return true;
    }

    private boolean comprobarAnioCompleto(int anio) {
        if (anio < 0 || anio > 2100) {
            return false;
        }
        return true;
    }

    /**
     * Funcion que se le pasa un valor entero que sera un mes y comprueba que este dentro de un
     * rango de 1 y 12.
     * @return dara un true si el mes pasado es correcto, si no, devolvera false.
     */
    private boolean comprobarMes() {
        if (mes < 1 || mes > 12) {
            return false;
        }
        return true;
    }

    private boolean comprobarMes(int mes) {
        if (mes < 1 || mes > 12) {
            return false;
        }
        return true;
    }

    /**
     * Funcion que se le pasa un valor entero que de dia, mes y año. Este compruba que los dias
     * corresponde al mes que se le paso por parametro y con año se comprueba si al ser bisiesto se le añade
     * el dia 29 al mes de Febrero.
     * @return dara un true si el dia pasado es correcto, si no, devolvera false.
     */
    private boolean comprobarDia() {
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            return dia >= 1 && dia <= 31;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return dia >= 1 && dia <= 30;
        } else if (mes == 2 && anioBisiesto(anio)) {
            return dia >= 1 && dia <= 29;
        } else if (mes == 2) {
            return dia >= 1 && dia <= 28;
        }
        return true;
    }

    public static boolean comprobarDia(int dia, int mes, int anio) {
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            return dia >= 1 && dia <= 31;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return dia >= 1 && dia <= 30;
        } else if (mes == 2 && anioBisiesto(anio)) {
            return dia >= 1 && dia <= 29;
        } else if (mes == 2) {
            return dia >= 1 && dia <= 28;
        }
        return true;
    }

    public static int rangoDia(int mes, int anio) {
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            return 31;
        } else if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            return 30;
        } else if (mes == 2 && anioBisiesto(anio)) {
            return 29;
        } else if (mes == 2) {
            return 28;
        }
        return 0;
    }

    public boolean comprobarFechaValida(int rangoAnio){
        if(!comprobarAnio(rangoAnio)){
            return false;
        }
        if(!comprobarMes()){
            return false;
        }
        if(!comprobarDia()){
            return false;
        }
        return true;
    }

    public boolean comprobarFechaValida(Fecha persona, int rango){
        if(anio > DIAHOY.getYear() || anio < persona.getAnio() + rango){
            return false;
        }else if(mes > DIAHOY.getMonthValue() && anio == DIAHOY.getYear()){
            return false;
        }else if(dia > DIAHOY.getDayOfMonth() && mes == DIAHOY.getMonthValue() && anio == DIAHOY.getYear()){
            return false;
        }else if(mes < persona.getMes() && anio == persona.getAnio() + rango){
            return false;
        }else if(dia < persona.getDia() && mes == persona.getMes() && anio == persona.getAnio() + rango){
            return false;
        }
        return true;
    }

    /**
     * Funcion que comprueba si el año pasado por parametro es bisiesto.
     * @param anio Valor de año
     * @return dara un true si el año es bisiesto, si no, devolvera false.
     */
    private static boolean anioBisiesto(int anio){
        if(anio % 4 == 0){
            return anio % 100 != 0;
        }else if(anio % 100 == 0){
            return anio % 400 == 0;
        }
        return false;
    }



    //toString
    @Override
    public String toString() {
        return dia + "/" + mes + "/" + anio;
    }


}
