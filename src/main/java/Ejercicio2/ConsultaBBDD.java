package Ejercicio2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaBBDD {

    public static void consultarAnimal(Connection conn, int id) {
        try {
            String sql = "SELECT * FROM ZOO.ANIMALES WHERE Id_Animal = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("Nombre");
                int edad = resultSet.getInt("Edad");
                String genero = resultSet.getString("Genero");
                String especie = resultSet.getString("Especie");
                String ubicacion = resultSet.getString("Ubicacion");
                EntityAnimal animal = new EntityAnimal(nombre, edad, genero, especie, ubicacion);
                System.out.print("Nombre: " + animal.getNombre() + ", ");
                System.out.print("Edad: " + animal.getEdad() + ", ");
                System.out.print("Genero: " + animal.getGenero() + ", ");
                System.out.print("Especie: " + animal.getEspecie() + ", ");
                System.out.println("Ubicación: " + animal.getUbicacion());
                System.out.println("*****************");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void leerAnimales(Connection conn) {
        try {
            String sql = "SELECT * FROM ZOO.ANIMALES";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("Nombre");
                int edad = resultSet.getInt("Edad");
                String genero = resultSet.getString("Genero");
                String especie = resultSet.getString("Especie");
                String ubicacion = resultSet.getString("Ubicacion");
                EntityAnimal animal = new EntityAnimal(nombre, edad, genero, especie, ubicacion);
                System.out.print("Nombre: " + animal.getNombre() + ", ");
                System.out.print("Edad: " + animal.getEdad() + ", ");
                System.out.print("Genero: " + animal.getGenero() + ", ");
                System.out.print("Especie: " + animal.getEspecie() + ", ");
                System.out.println("Ubicación: " + animal.getUbicacion());
                System.out.println("*****************");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrarAnimal(Connection conn, int id) {
        try {
            String sql = "DELETE FROM ZOO.ANIMALES WHERE Id_Animal = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearAnimal(Connection conn, String nombre, int edad, String genero, String especie, String ubicacion) {
        try {
            String sql = "INSERT INTO ZOO.ANIMALES (NOMBRE, EDAD, GENERO, ESPECIE, UBICACION) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, edad);
            preparedStatement.setString(3, genero);
            preparedStatement.setString(4, especie);
            preparedStatement.setString(5, ubicacion);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void actualizarAnimal(Connection conn, int id, String nombre, int edad, String genero, String especie, String ubicacion) {
        try {
            String sql = "UPDATE ZOO.ANIMALES SET NOMBRE = ?, EDAD = ?, GENERO = ?, ESPECIE = ?, UBICACION = ? WHERE ID_ANIMAL = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setInt(2, edad);
            preparedStatement.setString(3, genero);
            preparedStatement.setString(4, especie);
            preparedStatement.setString(5, ubicacion);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}

