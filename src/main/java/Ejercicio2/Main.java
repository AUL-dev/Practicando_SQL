package Ejercicio2;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
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

            //METODOS
            //******************

            //CREAR ANIMAL
            if (opcionNumero == 1) {
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

            //LECTURA DE UN ANIMAL
            if (opcionNumero == 2) {
                System.out.println("Escriba el número de ID del animal: ");
                int opcionId = scanner.nextInt();
                ConsultaBBDD.consultarAnimal(conn, opcionId);
            }

            //LECTURA DE TODOS LOS ANIMALES
            if (opcionNumero == 3) {
                ConsultaBBDD.leerAnimales(conn);
            }

            //ACTUALIZAR ANIMAL
            if (opcionNumero == 4) {

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

            //BORRAR UN ANIMAL
            if (opcionNumero == 5) {
                System.out.println("Escriba el número de ID del animal: ");
                int opcionId = scanner.nextInt();
                ConsultaBBDD.borrarAnimal(conn, opcionId);
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
                }
            }
            if (opcionNumero < 1 || opcionNumero > 6) {
                System.err.println("Escriba una opción correcta.");
            }
        } while (!exit);

        ConexionZoo.cerrarConexion(conn);
    }
}
