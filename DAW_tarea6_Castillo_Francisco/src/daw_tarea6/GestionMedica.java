package daw_tarea6;

import libreria.PeticionDatos;
import librerias.Faker;
import librerias.Fecha;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Clase principal de gestion del programa
 *
 * @author Francisco Castillo
 * @see Persona
 * @version 19/04/2022
 */
public class GestionMedica implements Serializable {

    //Atributos 
    /**
     * Array que guardara todos lo centros que se creen.
     */
    private ArrayList<Centro> centrosMedicos;
    /**
     * Atributo que nos permitira sacar varios datos como el dia de hoy, mes,
     * etc.
     */
    protected static final LocalDate DIAHOY = LocalDate.now();

    //Constructores
    public GestionMedica() {
        centrosMedicos = new ArrayList();
    }

    public ArrayList<Centro> getCentrosMedicos() {
        return centrosMedicos;
    }

    //Funciones
    /**
     * Funcion que pasa a ficheros todos los atributos estaticos y objetos del
     * programa tal cual como se finalizo.
     *
     * @param gestion objeto que se pasara a fichero.
     */
    public void pasarFichero(GestionMedica gestion) throws IOException {
        int[] guardar = {Persona.contID, Centro.contID, Centro.contCentros};
        File fichero = new File("src\\EstadoPrograma\\centros.dat");
        File ficheroStatic = new File("src\\EstadoPrograma\\AtributosStatic.dat");
        ObjectOutputStream escribiendoFichero = new ObjectOutputStream(new FileOutputStream(fichero));
        ObjectOutputStream atributos = new ObjectOutputStream(new FileOutputStream(ficheroStatic));
        escribiendoFichero.writeObject(gestion);
        atributos.writeObject(guardar);
        escribiendoFichero.close();
        atributos.close();
    }

    /**
     * Funcion de tipo GestionMedica, que carga los Ficheros si existe. En el
     * caso de no existir generara de forma aleatoria los objetos. Al final se
     * devolvera el objeto de tipo GestionMedica.
     *
     * @return devuelve el objeto de GestionMedica cargado de fichero o creado
     * nuevo de forma aleatoria.
     */
    public void cargarFicheros() throws IOException, ClassNotFoundException {
        GestionMedica gestion;
        String nombre, direccion;
        boolean comprobar;
        Random r1 = new Random();
        File fichero = new File("src\\EstadoPrograma\\centros.dat");
        File ficheroStatic = new File("src\\EstadoPrograma\\AtributosStatic.dat");
        File trabajadores = new File("src\\Trabajadores");
        int[] array;
        int cont;
        if (fichero.exists()) {
            ObjectInputStream lectura = new ObjectInputStream(new FileInputStream(fichero));
            ObjectInputStream arrayescribir = new ObjectInputStream(new FileInputStream(ficheroStatic));
            array = (int[]) arrayescribir.readObject();
            gestion = (GestionMedica) lectura.readObject();
            centrosMedicos = gestion.getCentrosMedicos();
            Persona.contID = array[0];
            Centro.contID = array[1];
            Centro.contCentros = array[2];
            lectura.close();
            arrayescribir.close();
            JFrame frame = new JFrame();
            JOptionPane.showMessageDialog(frame, "Se cargo el estado anterior del programa");
        } else {
            for (int x = 0; x < 4; x += 2) {
                do {
                    comprobar = true;
                    nombre = Faker.nombreCent();
                    if (!validarNombre(nombre, false)) {
                        comprobar = false;
                    }
                } while (!comprobar);
                do {
                    comprobar = true;
                    direccion = Faker.nombreDireccion();
                    if (!Centro.validarDireccion(direccion, centrosMedicos)) {
                        comprobar = false;
                    }
                } while (!comprobar);
                centrosMedicos.add(new Hospital(nombre, direccion, r1.nextInt(3, 7), r1.nextInt(3, 9), r1.nextInt(5, 16)));
                cont = 0;
                for (int y = 0; y < 2; y++) {
                    centrosMedicos.get(x).addTrabajador(personaAle(1));
                    centrosMedicos.get(x).getTrabajadores().get(cont).lugar = x;
                    cont++;
                    centrosMedicos.get(x).addTrabajador(personaAle(2));
                    centrosMedicos.get(x).getTrabajadores().get(cont).lugar = x;
                    cont++;
                }
                do {
                    comprobar = true;
                    nombre = Faker.nombreCent();
                    if (!validarNombre(nombre, false)) {
                        comprobar = false;
                    }
                } while (!comprobar);
                do {
                    comprobar = true;
                    direccion = Faker.nombreDireccion();
                    if (!validarDireccion(direccion, false)) {
                        comprobar = false;
                    }
                } while (!comprobar);
                centrosMedicos.add(new Clinica(nombre, direccion, r1.nextInt(3, 7)));
                cont = 0;
                for (int y = 0; y < 2; y++) {
                    centrosMedicos.get(x + 1).addTrabajador(personaAle(1));
                    centrosMedicos.get(x + 1).getTrabajadores().get(cont).lugar = x + 1;
                    cont++;
                    centrosMedicos.get(x + 1).addTrabajador(personaAle(2));
                    centrosMedicos.get(x + 1).getTrabajadores().get(cont).lugar = x + 1;
                    cont++;
                }
            }
        }
    }

    protected boolean validarNombre(String nombreCentro, boolean editar) {
        for (int x = 0; x < centrosMedicos.size(); x++) {
            if (centrosMedicos.get(x).getNombreCentro().equalsIgnoreCase(nombreCentro) && editar); else if (centrosMedicos.get(x).getNombreCentro().equalsIgnoreCase(nombreCentro)) {
                return false;
            }
        }
        return true;
    }

    protected boolean validarDireccion(String nombreDireccion, boolean editar) {
        for (int x = 0; x < centrosMedicos.size(); x++) {
            if (centrosMedicos.get(x).getDireccionCentro().equalsIgnoreCase(nombreDireccion) && editar); else if (centrosMedicos.get(x).getDireccionCentro().equalsIgnoreCase(nombreDireccion)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Funcion que muestra las estadisticas de los pacientes por pantalla. Se
     * buscara si el paciente tiene en su array de visitasMedicas un mes igual
     * que el pasado por parametro. Si lo tiene se mostrara por pantalla, si no,
     * no.
     *
     * @param mes valor de mes que se buscara en pacientes.
     * @param centro Centro en donde se buscara a los pacientes.
     */
    private static void mostrarEstadisticaPac(int mes, Centro centro) {
        Persona[] personas = new Paciente[5];
        int cont = 0;
        for (int x = 0; x < centro.getConsultas().length; x++) {
            if (!Arrays.asList(personas).contains(null)) {
                Persona[] aux = new Persona[personas.length];
                for (int y = 0; y < aux.length; y++) {
                    aux[y] = personas[y];
                }
                personas = new Persona[aux.length * 2];
                for (int y = 0; y < aux.length; y++) {
                    personas[y] = aux[y];
                }
            }
            if (centro.getConsultas()[x] == null) ; else if (centro.getConsultas()[x].diaporMes(mes) > 0) {
                personas[cont] = centro.getConsultas()[x];
                cont++;
            }
        }
        if (centro instanceof Hospital) {
            for (int x = 0; x < ((Hospital) centro).getHabitaciones().length; x++) {
                for (int y = 0; y < ((Hospital) centro).getHabitaciones()[x].length; y++) {
                    if (((Hospital) centro).getHabitaciones()[x][y] == null) ; else if (((Hospital) centro).getHabitaciones()[x][y].diaporMes(mes) > 0) {
                        personas[cont] = ((Hospital) centro).getHabitaciones()[x][y];
                        cont++;
                    }
                }
            }
        }
        //shell_ascPersona(personas);
        for (int x = 0; x < personas.length; x++) {
            if (personas[x] == null) ; else {
                personas[x].mostrarEstado();
            }
        }
    }

    /**
     * Funcion que muestra las personas por pantalla. Segun el valor que
     * contenga tipo, se mostraran Pacientes, Administrativos o Medicos. Se
     * buscara si el paciente tiene en su array de visitasMedicas o
     * diasTrabajados un mes igual que el pasado por parametro. Si lo tiene se
     * mostrara por pantalla, si no, no.
     *
     * @param mes valor de mes que se buscara en persona.
     * @param tipo atributo que contiene el numero de el tipo de Persona que se
     * mostrara.
     * @param centro Centro en donde se buscara a la persona.
     */
    public ArrayList<Persona> mostrarEstadistica(int mes, Centro centro, int tipo) {
        int opcion;
        ArrayList<Persona> personas = new ArrayList();
        int cont = 0;
        if (tipo == 1 || tipo == 2) {
            for (int x = 0; x < centro.getTrabajadores().size(); x++) {
                if (tipo == 1 && centro.getTrabajadores().get(x) instanceof Medico && centro.getTrabajadores().get(x).diaporMes(mes) > 0) {
                    personas.add(centro.getTrabajadores().get(x));
                    cont++;

                } else if (tipo == 2 && centro.getTrabajadores().get(x) instanceof Administrativo && centro.getTrabajadores().get(x).diaporMes(mes) > 0) {
                    personas.add(centro.getTrabajadores().get(x));
                    cont++;
                }
            }
        } else {
            for (int x = 0; x < centro.getConsultas().length; x++) {
                if (centro.getConsultas()[x] == null); else if (centro.getConsultas()[x].diaporMes(mes) > 0) {
                    personas.add(centro.getConsultas()[x]);
                    cont++;
                }
            }
            if (centro instanceof Hospital) {
                for (int x = 0; x < ((Hospital) centro).getHabitaciones().length; x++) {
                    for (int y = 0; y < ((Hospital) centro).getHabitaciones()[x].length; y++) {
                        if (((Hospital) centro).getHabitaciones()[x][y] == null); else if (((Hospital) centro).getHabitaciones()[x][y].diaporMes(mes) > 0) {
                            personas.add(((Hospital) centro).getHabitaciones()[x][y]);
                            cont++;
                        }
                    }
                }
            }
        }
        if (cont == 0) {
            System.out.println("No hay nada creado");
        }
        return personas;
    }

    /**
     * Funcion de tipo entera que busca un centro segun de tipo que se desee. Se
     * muestra por pantalla y el usuario elige, despues se buscara en que
     * posicion se encuentra en el array principal y por ultimo se pasa por
     * parametro la posicion en la que se encuentra.
     *
     * @param centros Array principal donde se buscara el centro.
     * @param tipo segun su valor, indicara que tipo de centro se va a querer
     * mostrar.
     * @return devuelve en que posicion se encuentra el centro en el array
     * principal.
     */
    public ArrayList<Centro> encontrarCentro(int tipo) {
        ArrayList<Centro> aux = new ArrayList();
        for (int x = 0; x < centrosMedicos.size(); x++) {
            if (centrosMedicos.get(x) instanceof Hospital && tipo == 0) {
                aux.add(centrosMedicos.get(x));
            } else if (centrosMedicos.get(x) instanceof Clinica && tipo == 1) {
                aux.add(centrosMedicos.get(x));
            } else if (tipo == 2) {
                aux.add(centrosMedicos.get(x));
            }
        }
        return aux;
    }

    /**
     * Funcion que busca una posicion en el array para el nuevo centro pasado
     * por parametro, Aumentara el array en caso de que el array este lleno.
     *
     * @param centro Objeto centro que se asignara a una posicion.
     */
    public void nuevoCentro(Centro centro) {
        centrosMedicos.add(centro);
    }

    /**
     * Funcion booleana que elimina el centro que se desee eliminar. Se
     * eliminara y se devolvera true si se puede eliminar, si no, no se
     * eliminiara y se devolvera false, ya que habra pacientes o trabajadores
     * dentro.
     *
     * @param gestion Objeto gestion que se usara para acceder a los centros y
     * eliminarlos.
     * @param tipo segun su valor, indicara que tipo de centro se va eliminar.
     * @return devolvera true si el centro se elimino y si no false ya que no se
     * pudo eliminar.
     */
    public boolean delCentro(Centro centro) {
        int x;
        if (centro instanceof Hospital) {
            for (int j = 0; j < ((Hospital) centro).getHabitaciones().length; j++) {
                for (int y = 0; y < ((Hospital) centro).getHabitaciones()[j].length; y++) {
                    if (((Hospital) centro).getHabitaciones()[j][y] == null) ; else {
                        return false;
                    }
                }
            }
        }

        for (int i = 0; i < centro.getConsultas().length; i++) {
            if (centro.getConsultas()[i] == null) ; else {
                return false;
            }
        }

        for (int y = 0; y < centro.getTrabajadores().size(); y++) {
            if (centro.getTrabajadores().get(y) == null) ; else {
                return false;
            }
        }
        for (int y = 0; y < centrosMedicos.size(); y++) {
            if (centro.getIdentificador() == centrosMedicos.get(y).getIdentificador()) {
                centrosMedicos.remove(y);
            }
        }
        return true;
    }

    /**
     * Funcion de tipo Centro que crean un centro nuevo segun el valor del
     * parametro tipo.
     *
     * @param tipo contendra el valor para saber de que tipo se creara el
     * centro, de tipo Hospital o Clinica.
     * @param gestion se usara para ver que no hay otros centros con la
     * direccion o nombres iguales.
     * @return devuelve el nuevo objeto de tipo centro creada.
     */
    public Centro crearCentro(int tipo, GestionMedica gestion) {
        String nombre, direccion;
        boolean comprobar;
        int limiteConsultas, plantas, habitacionesPorPlanta;
        do {
            comprobar = true;
            nombre = PeticionDatos.pedirCadena("Nombre del centro:");
            if (!Centro.validarNombre(nombre, gestion.centrosMedicos)) {
                comprobar = false;
            }
        } while (!comprobar);
        do {
            comprobar = true;
            direccion = PeticionDatos.pedirCadenaLimite(true, true, 100, "Direccion del centro: ");
            if (!Centro.validarDireccion(direccion, gestion.centrosMedicos)) {
                comprobar = false;
            }
        } while (!comprobar);
        limiteConsultas = PeticionDatos.pedirEntero("Numero de consultas: ");
        if (tipo == 0) {
            plantas = PeticionDatos.pedirEntero("Numero de plantas del centro: ");
            habitacionesPorPlanta = PeticionDatos.pedirEntero("Numero de habitaciones por planta: ");
            return new Hospital(nombre, direccion, limiteConsultas, plantas, habitacionesPorPlanta);
        } else if (tipo == 1) {
            return new Clinica(nombre, direccion, limiteConsultas);
        }
        return null;
    }

    /**
     * Funcion que edita el centro que se pasa por parametro.
     *
     * @param centro Objeto de tipo centro a editar
     * @param gestion se usara para ver que no hay otros centros con la
     * direccion o nombres iguales.
     */
    public void editarCentro(Centro centro, GestionMedica gestion) {
        boolean comprobar;
        String nombre, direccion;
        do {
            comprobar = true;
            nombre = PeticionDatos.pedirCadena("Nombre del centro:");
            if (!Centro.validarNombre(nombre, gestion.centrosMedicos)) {
                comprobar = false;
            }
        } while (!comprobar);
        centro.setNombreCentro(nombre);
        do {
            comprobar = true;
            direccion = PeticionDatos.pedirCadenaLimite(true, true, 100, "Direccion del centro: ");
            if (!Centro.validarDireccion(direccion, gestion.centrosMedicos)) {
                comprobar = false;
            }
        } while (!comprobar);
        centro.setDireccionCentro(direccion);
    }

    /**
     * Funcion de tipo Persona que crean una persona nuevo segun el valor del
     * parametro tipo.
     *
     * @param numTipo contendra el valor para saber de que tipo se creara la
     * persona, de tipo Persona, Medico, Administrativo.
     * @param dni contendra el valor de dni que tendra la nueva persona.
     * @return devuelve un objeto con la informacion necesaria de tipo Persona
     */
    private static Persona crearPersona(int numTipo, String dni) {
        String nombre, apellido1, apellido2, genero, posicion = "";
        int dia, mes, anio;
        Fecha fecha = new Fecha();
        nombre = PeticionDatos.pedirCadena("Nombre: ");
        apellido1 = PeticionDatos.pedirCadena("1er apellido: ");
        apellido2 = PeticionDatos.pedirCadena("2o apellido: ");
        do {
            genero = PeticionDatos.pedirCadena("Genero (Masculino/Femenino/Sin Definir): ");
            if (!Medico.validarGenero(genero)) {
                System.out.println("Genero no valido");
            }
        } while (!Persona.validarGenero(genero));
        if (numTipo == 0) {
            do {
                anio = PeticionDatos.pedirEntero("Año de nacimiento: ");
                mes = PeticionDatos.pedirEnteroRango(1, 12, 3, "Mes de nacimiento: ");
                dia = PeticionDatos.pedirEnteroRango(1, Fecha.rangoDia(mes, anio), 3, "Dia de nacimiento: ");
                fecha.setFechaCompleta(dia, mes, anio);
            } while (!Paciente.validarFechaNacimiento(fecha));
        } else if (numTipo == 1) {
            do {
                anio = PeticionDatos.pedirEntero("Año de nacimiento: ");
                mes = PeticionDatos.pedirEnteroRango(1, 12, 3, "Mes de nacimiento: ");
                dia = PeticionDatos.pedirEnteroRango(1, Fecha.rangoDia(mes, anio), 3, "Dia de nacimiento: ");
                fecha.setFechaCompleta(dia, mes, anio);
            } while (!Medico.validarFechaNacimiento(fecha));
        } else if (numTipo == 2) {
            do {
                anio = PeticionDatos.pedirEntero("Año de nacimiento: ");
                mes = PeticionDatos.pedirEnteroRango(1, 12, 3, "Mes de nacimiento: ");
                dia = PeticionDatos.pedirEnteroRango(1, Fecha.rangoDia(mes, anio), 3, "Dia de nacimiento: ");
                fecha.setFechaCompleta(dia, mes, anio);
            } while (!Administrativo.validarFechaNacimiento(fecha));
        }

        if (numTipo == 1) {
            do {
                posicion = PeticionDatos.pedirCadena("Especialidad del medico: ");
            } while (!Medico.comprobarEspecialidad(posicion));
        } else if (numTipo == 2) {
            do {
                posicion = PeticionDatos.pedirCadena("Especialidad del administrativo: ");
            } while (!Administrativo.comprobarEspecialidad(posicion));
        }

        if (numTipo == 0) {
            return new Paciente(dni, nombre, apellido1, apellido2, genero, fecha);
        } else if (numTipo == 1) {
            return new Medico(dni, nombre, apellido1, apellido2, genero, fecha, posicion);
        } else if (numTipo == 2) {
            return new Administrativo(dni, nombre, apellido1, apellido2, genero, fecha, posicion);
        }
        return null;
    }

    /**
     * Funcion que edita la persona que se pasa por parametro. Numtipo nos
     * permitira saber si es un Paciente o un trabajador.
     *
     * @param persona Objeto de tipo persona que se va a editar.
     * @param numTipo se usara para saber de que tipo se editara la Persona.
     */
    private static void editarPersona(Persona persona, int numTipo) {
        String genero, posicion;
        int dia, mes, anio;
        Fecha fecha = new Fecha();
        persona.setNombre(PeticionDatos.pedirCadena("Nombre[" + persona.getNombre() + "]: "));
        persona.setApellido1(PeticionDatos.pedirCadena("1e apellido[" + persona.getApellido1() + "]: "));
        persona.setApellido2(PeticionDatos.pedirCadena("2o apellido[" + persona.getApellido2() + "]: "));
        do {
            genero = PeticionDatos.pedirCadena("Genero " + persona.getGenero() + "(Masculino/Femenino/Sin Definir): ");
            if (!Medico.validarGenero(genero)) {
                System.out.println("Genero no valido");
            }
        } while (!Persona.validarGenero(genero));

        if (numTipo == 0) {
            do {
                anio = PeticionDatos.pedirEntero("Año de nacimiento: ");
                mes = PeticionDatos.pedirEnteroRango(1, 12, 3, "Mes de nacimiento: ");
                dia = PeticionDatos.pedirEnteroRango(1, Fecha.rangoDia(mes, anio), 3, "Dia de nacimiento: ");
                fecha.setFechaCompleta(dia, mes, anio);
            } while (!Paciente.validarFechaNacimiento(fecha));
        } else if (numTipo == 1) {
            do {
                anio = PeticionDatos.pedirEntero("Año de nacimiento: ");
                mes = PeticionDatos.pedirEnteroRango(1, 12, 3, "Mes de nacimiento: ");
                dia = PeticionDatos.pedirEnteroRango(1, Fecha.rangoDia(mes, anio), 3, "Dia de nacimiento: ");
                fecha.setFechaCompleta(dia, mes, anio);
            } while (!Medico.validarFechaNacimiento(fecha));
        } else if (numTipo == 2) {
            do {
                anio = PeticionDatos.pedirEntero("Año de nacimiento: ");
                mes = PeticionDatos.pedirEnteroRango(1, 12, 3, "Mes de nacimiento: ");
                dia = PeticionDatos.pedirEnteroRango(1, Fecha.rangoDia(mes, anio), 3, "Dia de nacimiento: ");
                fecha.setFechaCompleta(dia, mes, anio);
            } while (!Administrativo.validarFechaNacimiento(fecha));
            persona.setFechaNacimiento(fecha);
        }

        if (numTipo == 1) {
            do {
                posicion = PeticionDatos.pedirCadena("Especialidad del medico: ");
            } while (!Medico.comprobarEspecialidad(posicion));
            ((Medico) persona).setEspecialidad(posicion);
        } else if (numTipo == 2) {
            do {
                posicion = PeticionDatos.pedirCadena("Especialidad del administrativo: ");
            } while (!Administrativo.comprobarEspecialidad(posicion));
            ((Administrativo) persona).setArea(posicion);
        }
    }

    /**
     * Funcion que añade una persona donde desee el usuario. Se controla que
     * donde quiere el usuario asignar al Paciente en su caso, este no este
     * ocupado por otro paciente.
     *
     * @param centro se usara para mostrar y asignar la nueva persona.
     * @param persona persona que sera asignada a un lugar.
     * @param numTipo segun su valor, se añadira un paciente o un trabajador.
     */
    /**
     * Funcion que nos vale para añadir un diaTrabajado, si es Medico o
     * Administrativo, o un dia de visitaMedica, si es Paciente.
     *
     * @param persona Objeto persana al que se le añadira la nueva fecha de
     * diaTrabajado o visitaMedica.
     */
    private static void añadirDia(Persona persona) {
        Fecha fecha = new Fecha();
        int anio, mes, dia;
        if (persona instanceof Medico) {
            do {
                anio = PeticionDatos.pedirEntero("Año: ");
                mes = PeticionDatos.pedirEnteroRango(1, 12, 3, "Mes: ");
                dia = PeticionDatos.pedirEnteroRango(1, Fecha.rangoDia(mes, anio), 3, "Dia: ");
                fecha.setFechaCompleta(dia, mes, anio);
            } while (!((Medico) persona).addDiasTrabajados(fecha));
        } else if (persona instanceof Administrativo) {
            do {
                anio = PeticionDatos.pedirEntero("Año: ");
                mes = PeticionDatos.pedirEnteroRango(1, 12, 3, "Mes: ");
                dia = PeticionDatos.pedirEnteroRango(1, Fecha.rangoDia(mes, anio), 3, "Dia: ");
                fecha.setFechaCompleta(dia, mes, anio);
            } while (!((Administrativo) persona).addDiasTrabajados(fecha));
        } else if (persona instanceof Paciente) {
            do {
                anio = PeticionDatos.pedirEntero("Año: ");
                mes = PeticionDatos.pedirEnteroRango(1, 12, 3, "Mes: ");
                dia = PeticionDatos.pedirEnteroRango(1, Fecha.rangoDia(mes, anio), 3, "Dia: ");
                fecha.setFechaCompleta(dia, mes, anio);
            } while (!((Paciente) persona).addVisita(fecha));
        }
    }

    /**
     * Funcion de tipo Persona que aumenta el array pasado por parametro, si
     * esta lleno y lo aumenta al doble de la capacidad que puede tener. En el
     * caso de no usar Arrays.asList, lo que se haria sería un bucle que
     * compruebe todas la posiciones, si encuentra un null saldria del bucle y
     * no aumentaria. En el caso de llegar a la ultima posicion y no encontrar
     * ningun nulo ya se aumentaria el array.
     *
     * @param array Array de tipo persona que se aumentara si esta lleno.
     * @return devuelve el array aumentado, si hizo falta.
     */
    private static Persona[] aumentarArray(Persona[] array) {
        if (!Arrays.asList(array).contains(null)) {//Arrays.asList nos permite buscar en el array el valor que deseemos, en este caso un null
            Persona[] aux = new Persona[array.length];
            for (int x = 0; x < aux.length; x++) {
                aux[x] = array[x];
            }
            array = new Persona[aux.length * 2];
            for (int x = 0; x < aux.length; x++) {
                array[x] = aux[x];
            }
        }
        return array;
    }

    /**
     * Funcion de tipo Persona que genera una persona de forma aleatoria y luego
     * devuelve el objeto Persona.
     *
     * @param numTipo el valor indicara de que tipo se creara la forma
     * aleatoria.
     * @param centros se usara para ver que no hay un dni igual al generado
     * aleatoriamente en el array de los Trabajadores.
     * @return
     */
    private Persona personaAle(int numTipo) throws IOException, FileNotFoundException, ClassNotFoundException {
        String nombre, apellido1, apellido2, genero, posicion = "", dni;
        Fecha fecha = new Fecha();
        nombre = Faker.nombres();
        apellido1 = Faker.apellidos();
        apellido2 = Faker.apellidos();
        do {
            genero = Faker.genero();
        } while (!Persona.validarGenero(genero));
        if (numTipo == 1) {
            do {
                fecha = Faker.fechaAleatoria(1900, DIAHOY.getYear());
            } while (!Medico.validarFechaNacimiento(fecha));
        } else if (numTipo == 2) {
            do {
                fecha = Faker.fechaAleatoria(1900, DIAHOY.getYear());
            } while (!Administrativo.validarFechaNacimiento(fecha));
        }

        if (numTipo == 1) {
            do {
                posicion = Faker.especialidadMedico();
            } while (!Medico.comprobarEspecialidad(posicion));
        } else if (numTipo == 2) {
            do {
                posicion = Faker.especialidadAdministrativo();
            } while (!Administrativo.comprobarEspecialidad(posicion));
        }

        do {
            dni = Faker.DNI();
        } while (Persona.existePers(centrosMedicos, dni, 1) != null);

        if (numTipo == 1) {
            return new Medico(dni, nombre, apellido1, apellido2, genero, fecha, posicion);
        } else if (numTipo == 2) {
            return new Administrativo(dni, nombre, apellido1, apellido2, genero, fecha, posicion);
        }
        return null;
    }
}
