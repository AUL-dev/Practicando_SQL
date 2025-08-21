package ActividadSQL;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Connection conexion = ConexionColegio.abrirConexion();
        boolean exit = false;
        do {
            int opcionNumero = obtenerOpcionNumero();
            switch (opcionNumero) {
                case 1:
                    crearAlumno(conexion);
                    break;
                case 2:
                    verAlumno(conexion);
                    break;
                case 3:
                    eliminarAlumno(conexion);
                    break;
                case 4:
                    modificarAlumno(conexion);
                    break;
                case 5:
                    mostrarAlumnos(conexion);
                    break;
                case 6:
                    datosAlumnosPorNota(conexion);
                    break;
                case 7:
                    if (salirPrograma(conexion)) {
                        exit = true;
                    }
                    break;
                default:
                    throw new Exception("Escriba una opción correcta.");
            }

        } while (!exit);
    }

    private static int obtenerOpcionNumero() {
        //PREGUNTAR POR CONSOLA QUE QUIERE HACER
        System.out.println("1.-Crear un alumno.");
        System.out.println("2.-Ver los datos alumno.");
        System.out.println("3.-Eliminar a un alumno.");
        System.out.println("4.-Modificar los datos alumno.");
        System.out.println("5.-Ver a todos los alumnos.");
        System.out.println("6.-Ver los datos de los alumnos cuya nota sea superior o igual a la introducida.");
        System.out.println("7.- Salir del programa.");
        System.out.println("¿Qué desea hacer?");

        String opcion = scanner.nextLine();
        return Integer.parseInt(opcion);
    }

    public static void crearAlumno(Connection conexion) {

        System.out.println("Escriba el nombre del alumno: ");
        String nombre = scanner.nextLine();
        System.out.println("Escriba los apellidos del alumno: ");
        String apellidos = scanner.nextLine();
        System.out.println("Escriba el curso del alumno: ");
        String curso = scanner.nextLine();
        System.out.println("Escriba la nota final del alumno: ");
        float notaFinal = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Escriba las observaciones del alumno: ");
        String observaciones = scanner.nextLine();
        ConsultaBBDD.crearAlumno(conexion, nombre, apellidos, curso, notaFinal, observaciones);
    }

    public static void verAlumno(Connection conexion) {
        System.out.println("Escriba el id del alumno: ");
        int idAlumno = scanner.nextInt();
        ConsultaBBDD.verAlumno(conexion, idAlumno);
    }

    public static void eliminarAlumno(Connection conexion) {

        System.out.println("Escriba el id del alumno: ");
        int idAlumno = scanner.nextInt();
        scanner.nextLine();
        ConsultaBBDD.verAlumno(conexion, idAlumno);
        System.out.println("¿Desea eliminar al alumno?");
        System.out.println("1.- Sí");
        System.out.println("2.- No");
        String opcionEliminar = scanner.nextLine();
        int opcionEliminarNumero = Integer.parseInt(opcionEliminar);
        if (opcionEliminarNumero == 1) {
            ConsultaBBDD.eliminarAlumno(conexion, idAlumno);
        }
    }

    public static void modificarAlumno(Connection conexion) {

        System.out.println("Escriba el id del alumno: ");
        int idAlumno = scanner.nextInt();
        scanner.nextLine();
        ConsultaBBDD.verAlumno(conexion, idAlumno);

        System.out.println("Escriba el nombre del alumno: ");
        String nombre = scanner.nextLine();
        System.out.println("Escriba los apellidos del alumno: ");
        String apellidos = scanner.nextLine();
        System.out.println("Escriba el curso del alumno: ");
        String curso = scanner.nextLine();
        System.out.println("Escriba la nota final del alumno: ");
        float notaFinal = scanner.nextFloat();
        scanner.nextLine();
        System.out.println("Escriba las observaciones del alumno: ");
        String observaciones = scanner.nextLine();
        ConsultaBBDD.modificarAlumno(conexion, idAlumno, nombre, apellidos, curso, notaFinal, observaciones);
    }

    public static void mostrarAlumnos(Connection conexion) {

        ConsultaBBDD.mostrarAlumnos(conexion);
    }

    public static void datosAlumnosPorNota(Connection conexion) {

        System.out.println("Escriba una nota: ");
        float nota = scanner.nextFloat();
        if (nota < 0 || nota > 10) {
            System.err.println("Escriba una nota correcta.");
        } else {
            ConsultaBBDD.datosAlumnosPorNotas(conexion, nota);
        }
    }

    public static boolean salirPrograma(Connection conexion) {

        System.out.println("¿Está seguro de que desea salir?");
        System.out.println("1.- Sí");
        System.out.println("2.- No");
        String opcion2 = scanner.nextLine();
        int opcionSalir = Integer.parseInt(opcion2);
        if (opcionSalir == 1) {
            ConexionColegio.cerrarConexion(conexion);
            return true;
        }
        return false;
    }

}
