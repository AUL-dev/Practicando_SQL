package ActividadSQL;

import com.sun.jdi.IntegerType;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conexion = ConexionColegio.abrirConexion();
        boolean exit = false;
        do {
            //PREGUNTAR POR CONSOLA QUE QUIERE HACER
            Scanner scanner = new Scanner(System.in);
            System.out.println("1.-Crear un alumno.");
            System.out.println("2.-Ver los datos alumno.");
            System.out.println("3.-Eliminar a un alumno.");
            System.out.println("4.-Modificar los datos alumno.");
            System.out.println("5.-Ver a todos los alumnos.");
            System.out.println("6.-Ver los datos de los alumnos cuya nota sea superior o igual a la introducida.");
            System.out.println("7.- Salir del programa.");
            System.out.println("¿Qué desea hacer?");

            String opcion = scanner.nextLine();
            int opcionNumero = Integer.parseInt(opcion);

            //METODOS

            //CREAR ALUMNO
            if (opcionNumero == 1) {
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

            //VER ALUMNO
            if (opcionNumero == 2) {
                System.out.println("Escriba el id del alumno: ");
                int idAlumno = scanner.nextInt();
                ConsultaBBDD.verAlumno(conexion, idAlumno);
            }

            //ELIMINAR ALUMNO
            if (opcionNumero == 3) {
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

            //MODIFICAR ALUMNO
            if (opcionNumero == 4) {
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

            //MOSTRAR TODOS LOS ALUMNOS
            if (opcionNumero == 5) {
                ConsultaBBDD.mostrarAlumnos(conexion);
            }

            //DATOS DE ALUMNOS POR NOTA (MAYOR O IGUAL) INTRODUCIDA POR PARÁMETRO
            if (opcionNumero == 6) {
                System.out.println("Escriba una nota: ");
                float nota = scanner.nextFloat();
                if (nota < 0 || nota > 10) {
                    System.err.println("Escriba una nota correcta.");
                }
                ConsultaBBDD.datosAlumnosPorNotas(conexion, nota);
            }


            //SALIR DEL PROGRAMA
            if (opcionNumero == 7) {
                System.out.println("¿Está seguro de que desea salir?");
                System.out.println("1.- Sí");
                System.out.println("2.- No");
                String opcion2 = scanner.nextLine();
                int opcionSalir = Integer.parseInt(opcion2);
                if (opcionSalir == 1) {
                    exit = true;
                }

                //ESCOGER UNA OPCIÓN EXISTENTE
                if (opcionNumero < 1 || opcionNumero > 7) {
                    System.err.println("Escriba una opción correcta.");
                }
            }
        } while (!exit);
    }
}
