package ActividadSQL2;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Connection conexion = ConexionEmpresa.abrirConexion();
        boolean exit = false;
        do {
            int opcion = preguntarOpcion(conexion);

            switch (opcion) {
                case 1:
                    crearEmpleado(conexion);
                    break;
                case 2:
                    verEmpleado(conexion);
                    break;
                case 3:
                    eliminarEmpleado(conexion);
                    break;
                case 4:
                    mostrarEmpleados(conexion);
                    break;
                case 5:
                    actualizarEmpleado(conexion);
                    break;
                case 6:
                    intercambiarDias(conexion);
                    break;
                case 7:
                    if (salirPrograma(conexion)) {
                        exit = true;
                    }
                    break;
                default:
                    throw new Exception("Escribe una opción correcta.");
            }

        } while (!exit);
    }

    public static int preguntarOpcion(Connection conexion) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("1.-Crear un empleado.");
        System.out.println("2.-Ver los datos de un empleado.");
        System.out.println("3.-Eliminar a un empleado.");
        System.out.println("4.-Mostrar empleados.");
        System.out.println("5.-Actualizar empleado.");
        System.out.println("6.-Intercambiar días.");
        System.out.println("7.- Salir del programa.");
        System.out.println("¿Qué desea hacer?");

        String opcion = scanner.nextLine();
        int opcionNumero = Integer.parseInt(opcion);
        return opcionNumero;
    }

    public static void crearEmpleado(Connection conexion) {

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

    public static void verEmpleado(Connection conexion) {
        System.out.println("Escriba el id del empleado: ");
        int idEmpleado = scanner.nextInt();
        ConsultaBBDD.verEmpleado(conexion, idEmpleado);
    }

    public static void eliminarEmpleado(Connection conexion) {

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

    public static void mostrarEmpleados(Connection conexion) {

        ConsultaBBDD.mostrarEmpleados(conexion);
    }

    public static void actualizarEmpleado(Connection conexion) {
        System.out.println("Escriba el id del empleado: ");
        int idEmpleado = scanner.nextInt();
        scanner.nextLine();
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
        ConsultaBBDD.actualizarEmpleado(conexion, idEmpleado, nombre, apellidos, departamento, sueldo, diaLibre);
    }

    public static void intercambiarDias(Connection conexion) {

        System.out.println("Introduzca el id del primer empleado: ");
        int idEmpleado1 = scanner.nextInt();
        System.out.println("Introduzca el id del segundo empleado: ");
        int idEmpleado2 = scanner.nextInt();

        ConsultaBBDD.intercambiarDias(conexion, idEmpleado1, idEmpleado2);
    }


    public static boolean salirPrograma(Connection conexion) {

        System.out.println("¿Está seguro de que desea salir?");
        System.out.println("1.- Sí");
        System.out.println("2.- No");
        String opcion2 = scanner.nextLine();
        int opcionSalir = Integer.parseInt(opcion2);
        if (opcionSalir == 1) {
            ConexionEmpresa.cerrarConexion(conexion);
            return true;
        }
        return false;
    }
}
