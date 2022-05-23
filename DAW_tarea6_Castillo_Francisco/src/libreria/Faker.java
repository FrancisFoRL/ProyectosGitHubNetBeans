package librerias;

import java.util.Random;

/**
 * Clase faker que se usa para que devuelva valores de forma aletoria de los Arrays que tiene integrados.
 * @author Francisco Castillo
 * @version 28/01/2022
 */
public class Faker {
    /**
     * Array privada y estatica que almacena varios nombres.
     */
    private static final String[] NOMBRESCENT = {"Singapore General","Toronto General","Gregorio Marañón","12 de Octubre"};
    private static final String[] DIRECCION = {"Calle Leonardo da Vinci 7", "Calle de Velázquez 80", "Calle Puerta de Granada 19", "Calle La Laja 1"};

    private static final String[] DNIALE = {"42881462D", "69882282W", "54022811C", "30359786Q", "47667541H", "61160527P", "64142734N", "11154482B", "13151070S", "80879330Z", "91339399M",
            "44262850V", "47395347Y", "26360492P", "94620226C", "83385986S", "40682192E"};
    private static final String[] ESPMEDICO = {"Traumatologia", "Dermatologia", "Oftalmologia", "Alergologia", "Geriatria", "Psiquiatria"};

    private static final String[] ESPADMINISTRATIVO = {"Urgencias", "Quirofano", "Administracion"};
    private static final String[] GENERO = {"Masculino", "Femenino", "Sin Definir"};
    private static final String[] NOMBRES = {"Alan", "Jacinto", "Martinez", "Alicia", "Jesús", "Mirta", "Andrea", "Josefina", "Mónica", "Andrés", "Juan",
            "Nicolás", "Antonia", "Juana", "Noé", "Antonio", "Juárez", "Noelia", "Azul", "Julia", "Paula", "Bartolomé", "Julián", "Patricio", "Belén", "Juliana",
            "Renzo", "Celeste", "Julio", "Rodrigo", "Edgardo", "Leandro", "Rodríguez", "Felicia", "Luis", "Romina", "Florencia", "Luisa", "Rosario", "Gaspar", "Marcelo"};

    /**
     * Array privada y estatica que almacena varios apellidos.
     */
    private static final String[] APELLIDOS = {"Castillo", "Muñoz", "Rodriguez", "Brull", "Garcia", "Ortiz", "González", "Martínez", "Jiménez", "Diaz", "Gil", "Iglesias", "Santos",
            "Cano", "Vidal", "Flores"};

    /**
     * Array privada y estatica que almacena varios puestos de baloncesto.
     */
    private static final String[] PUESTO_BALONCESTO = {"Pivot", "Alero", "Base"};

    /**
     * Array privada y estatica que almacena varios puestos de futbol.
     */
    private static final String[] PUESTO_FUTBOL = {"Delantero", "Mediocentro", "Defensa"};

    /**
     * Array privada y estatica que almacena varios puestos de balonmano.
     */
    private static final String[] PUESTO_BALONMANO = {"Pivot", "Central", "Defensa"};

    /**
     * Array privada y estatica que almacena varios nombre de equipo.
     */
    private static final String[] NOMBRE_EQUIPO = {"Troyanos","La Rebambaramba","Gandules Club","Todo o Nada","Cero Empate","Los Intocables","Atletas de Mentiras","Los Gladiadores", "Fuego Ardiente",
            "Volantes Cómicos","El Milagro","Liga Oro"};

    /**
     * Funcion que no recibe nada y que devuelve un nombre aleatorio del array de nombres.
     *
     * @return devuelve un String con un nombre.
     */
    public static String nombreCent() {
        Random r1 = new Random();
        int num = r1.nextInt(NOMBRESCENT.length);
        return NOMBRESCENT[num];
    }

    public static String DNI() {
        Random r1 = new Random();
        int num = r1.nextInt(DNIALE.length);
        return DNIALE[num];
    }

    public static String especialidadMedico() {
        Random r1 = new Random();
        int num = r1.nextInt(ESPMEDICO.length);
        return ESPMEDICO[num];
    }

    public static String especialidadAdministrativo() {
        Random r1 = new Random();
        int num = r1.nextInt(ESPADMINISTRATIVO.length);
        return ESPADMINISTRATIVO[num];
    }

    public static String genero() {
        Random r1 = new Random();
        int num = r1.nextInt(GENERO.length);
        return GENERO[num];
    }
    public static String nombreDireccion() {
        Random r1 = new Random();
        int num = r1.nextInt(DIRECCION.length);
        return DIRECCION[num];
    }

    public static String nombres() {
        Random r1 = new Random();
        int num = r1.nextInt(NOMBRES.length);
        return NOMBRES[num];
    }

    /**
     * Funcion que no recibe nada y que devuelve un apellido aleatorio del array de apellidos.
     * @return devuelve un String con un provincia.
     */
    public static String apellidos() {
        Random r1 = new Random();
        int num = r1.nextInt(APELLIDOS.length);
        return APELLIDOS[num];
    }

    /**
     * Funcion que no recibe nada y que devuelve un puesto de baloncesto aleatorio del array de PUESTO_BALONCESTO.
     * @return devuelve un String con una posicion de baloncesto.
     */
    public static String puestoBaloncesto() {
        Random r1 = new Random();
        int num = r1.nextInt(PUESTO_BALONCESTO.length);
        return PUESTO_BALONCESTO[num];
    }

    /**
     * Funcion que no recibe nada y que devuelve un puesto de futbol aleatorio del array de PUESTO_FUTBOL.
     * @return devuelve un String con una posicion de futbol.
     */
    public static String puestoFutbol() {
        Random r1 = new Random();
        int num = r1.nextInt(PUESTO_FUTBOL.length);
        return PUESTO_FUTBOL[num];
    }

    /**
     * Funcion que no recibe nada y que devuelve un puesto de baloncesto aleatorio del array de PUESTO_BALONMANO.
     * @return devuelve un String con una posicion de balonmano.
     */
    public static String puestoBalonmano() {
        Random r1 = new Random();
        int num = r1.nextInt(PUESTO_BALONMANO.length);
        return PUESTO_BALONMANO[num];
    }

    /**
     * Funcion que no recibe nada y que devuelve un nombre de equipo aleatorio del array de NOMBRE_EQUIPO.
     * @return devuelve un String con una nombre de equipo.
     */
    public static String nombreEquipo() {
        Random r1 = new Random();
        int num = r1.nextInt(NOMBRE_EQUIPO.length);
        return NOMBRE_EQUIPO[num];
    }

    /**
     * Funcion que genera de forma aletoria un fecha entre el rango de años pasados por paramentro.
     * @param rango1 entero que sera un año
     * @param rango2 rango1 entero que sera un año
     * @return devuelve una fecha completa
     */
    public static Fecha fechaAleatoria(int rango1, int rango2){
        Random r = new Random();
        Fecha fecha = new Fecha();
        int dia, anio = 0;
        if (rango1<rango2){
            anio = r.nextInt(rango1,rango2);
        }else if(rango1>rango2){
            anio = r.nextInt(rango2,rango1);
        }
        int mes = r.nextInt(1,12);
        do{
            dia = r.nextInt(1,31);
        }while(!Fecha.comprobarDia(dia,mes,anio));
        fecha.setFechaCompleta(dia,mes,anio);
        return fecha;
    }
}