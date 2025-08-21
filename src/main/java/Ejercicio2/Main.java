package Ejercicio2;

import com.mysql.cj.exceptions.ClosedOnExpiredPasswordException;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // CRUD (ANIMAL)
        // C - Create.
        // R - Read.
        // U - Update.
        // D - Delete.

        boolean exit = false;
        Connection conn = ConexionZoo.abrirConexion();

        do {
            //CREO EL MENU
            Scanner scanner = new Scanner(System.in);
            System.out.println("1.- Crear un nuevo animal.");
            System.out.println("2.- Leer un animal.");
            System.out.println("3.- Leer todos los animales.");
            System.out.println("4.- Actualizar un animal.");
            System.out.println("5.- Borrar un animal.");
            System.out.println("6.- Salir.");
            System.out.println("¿Qué desea hacer?");

            //ESCOGE LA OPCIÓN
            String opcion = scanner.nextLine();
            int opcionNumero = Integer.parseInt(opcion);

            switch (opcionNumero) {
                case 1:
                    crearAnimal(conn);
                    break;
                case 2:
                    lecturaAnimal(conn);
                    break;
                case 3:
                    mostrarAnimales(conn);
                    break;
                case 4:
                    actualizarAnimal(conn);
                    break;
                case 5:
                    borrarAnimal(conn);
                    break;
                case 6:
                    if (salirPrograma(conn))  {
                        exit = true;
                    }
                    break;
                default:
                    throw new Exception("Escriba una opción correcta.");
            }

        } while (!exit);
    }

    public static void crearAnimal(Connection conn) {

        System.out.println("Escriba el nombre del animal: ");
        String nombre = scanner.nextLine();

        System.out.println("Escriba la edad del animal: ");
        int edad = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Escriba el genero del animal: ");
        String genero = scanner.nextLine();

        System.out.println("Escriba la especie del animal: ");
        String especie = scanner.nextLine();

        System.out.println("Escriba la ubicación del animal: ");
        String ubicacion = scanner.nextLine();

        ConsultaBBDD.crearAnimal(conn, nombre, edad, genero, especie, ubicacion);
    }

    public static void lecturaAnimal(Connection conn) {

        System.out.println("Escriba el número de ID del animal: ");
        int idAnimal = scanner.nextInt();
        ConsultaBBDD.consultarAnimal(conn, idAnimal);
    }

    public static void mostrarAnimales(Connection conn) {

        ConsultaBBDD.leerAnimales(conn);

    }

    public static void actualizarAnimal(Connection conn) {
        System.out.println("Escriba el número de ID del animal: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Escriba el nombre del animal: ");
        String nombre = scanner.nextLine();

        System.out.println("Escriba la edad del animal: ");
        int edad = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Escriba el genero del animal: ");
        String genero = scanner.nextLine();

        System.out.println("Escriba la especie del animal: ");
        String especie = scanner.nextLine();

        System.out.println("Escriba la ubicación del animal: ");
        String ubicacion = scanner.nextLine();

        ConsultaBBDD.actualizarAnimal(conn, id, nombre, edad, genero, especie, ubicacion);

    }

    public static void borrarAnimal(Connection conn) {
        System.out.println("Escriba el número de ID del animal: ");
        int opcionId = scanner.nextInt();
        ConsultaBBDD.borrarAnimal(conn, opcionId);

    }

    public static boolean salirPrograma(Connection conn) {

        System.out.println("¿Está seguro de que desea salir?");
        System.out.println("1.- Sí");
        System.out.println("2.- No");
        String opcion2 = scanner.nextLine();
        int opcionSalir = Integer.parseInt(opcion2);
        if (opcionSalir == 1) {
            ConexionZoo.cerrarConexion(conn);
            return true;
        }
        return false;
    }


}
