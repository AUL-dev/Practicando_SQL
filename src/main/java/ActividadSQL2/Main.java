package ActividadSQL2;

import ActividadSQL.ConexionColegio;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection conexion = ConexionEmpresa.abrirConexion();
        boolean exit = false;
        do {
            //PREGUNTAR POR CONSOLA QUE QUIERE HACER
            Scanner scanner = new Scanner(System.in);
            System.out.println("1.-Crear un empleado.");
            System.out.println("2.-Ver los datos de un empleado.");
            System.out.println("3.-Eliminar a un empleado.");
            System.out.println("4.-Mostrar empleados.");
            System.out.println("5.-Intercambiar días.");
            System.out.println("6.- Salir del programa.");
            System.out.println("¿Qué desea hacer?");

            String opcion = scanner.nextLine();
            int opcionNumero = Integer.parseInt(opcion);

            //CREAR EMPLEADO
            if (opcionNumero == 1) {
                System.out.println("Escriba el nombre del empleado: ");
                String nombre = scanner.nextLine();
                System.out.println("Escriba los apellidos del empleado: ");
                String apellidos = scanner.nextLine();
                System.out.println("Escriba el departamento del empleado: ");
                String departamento = scanner.nextLine();
                System.out.println("Escriba el sueldo del empleado: ");
                float sueldo = scanner.nextFloat();
                scanner.nextLine();
                System.out.println("Escriba el día libre del empleado: ");
                String diaLibre = scanner.nextLine();
                ConsultaBBDD.crearEmpleado(conexion, nombre, apellidos, departamento, sueldo, diaLibre);
            }
            //VER EMPLEADO
            if (opcionNumero == 2) {
                System.out.println("Escriba el id del empleado: ");
                int idEmpleado = scanner.nextInt();
                ConsultaBBDD.verEmpleado(conexion, idEmpleado);
            }

            //ELIMINAR EMPLEADO
            if (opcionNumero == 3) {
                System.out.println("Escriba el id del empleado: ");
                int idEmpleado = scanner.nextInt();
                scanner.nextLine();
                ConsultaBBDD.verEmpleado(conexion, idEmpleado);
                System.out.println("¿Desea eliminar al empleado?");
                System.out.println("1.- Sí");
                System.out.println("2.- No");
                String opcionEliminar = scanner.nextLine();
                int opcionEliminarNumero = Integer.parseInt(opcionEliminar);
                if (opcionEliminarNumero == 1) {
                    ConsultaBBDD.eliminarEmpleado(conexion, idEmpleado);
                }
            }

            //MOSTRAR TODOS LOS EMPLEADOS
            if (opcionNumero == 4) {
                ConsultaBBDD.mostrarEmpleados(conexion);
            }

            //INTERCAMBIAR DIAS
            if (opcionNumero == 5) {
                System.out.println("Introduzca el id del primer empleado: ");
                int idEmpleado1 = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduzca el id del segundo empleado: ");
                int idEmpleado2 = scanner.nextInt();
                scanner.nextLine();
                scanner.nextLine();
                ConsultaBBDD.intercambiarDias(conexion, idEmpleado1, idEmpleado2);
            }

            //SALIR DEL PROGRAMA
            if (opcionNumero == 6) {
                System.out.println("¿Está seguro de que desea salir?");
                System.out.println("1.- Sí");
                System.out.println("2.- No");
                String opcion2 = scanner.nextLine();
                int opcionSalir = Integer.parseInt(opcion2);
                if (opcionSalir == 1) {
                    exit = true;
                    ConexionEmpresa.cerrarConexion(conexion);
                }

                //ESCOGER UNA OPCIÓN EXISTENTE
                if (opcionNumero < 1 || opcionNumero > 6) {
                    System.err.println("Escriba una opción correcta.");
                }
            }
        } while (!exit);
    }
}
