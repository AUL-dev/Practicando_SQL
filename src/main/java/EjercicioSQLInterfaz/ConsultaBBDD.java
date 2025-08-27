package EjercicioSQLInterfaz;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConsultaBBDD {

    public static void crearCoche(Connection conexion, String matricula, String marca, String modelo, float precio, String extras) {
        try {
            String sql = "INSERT INTO COCHES (MATRICULA, MARCA, MODELO, PRECIO, EXTRAS) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, matricula);
            preparedStatement.setString(2, marca);
            preparedStatement.setString(3, modelo);
            preparedStatement.setFloat(4, precio);
            preparedStatement.setString(5, extras);
            int nRows = preparedStatement.executeUpdate();
            if (nRows >= 1) {
                System.out.println("Se ha insertado correctamente el coche: ");
                System.out.println("Marca: " + marca);
                System.out.println("Modelo: " + modelo);
                System.out.println("Matrícula: " + matricula);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void borrarCoche(Connection conexion, String matricula) {
        try {
            String sql = "DELETE FROM COCHES WHERE MATRICULA = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, matricula);
            int i = preparedStatement.executeUpdate();
            System.out.println("Se han borrado " + i + " registros.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static EntityCoche consultarCoche(Connection conexion, String matricula) {
            EntityCoche coche = null;
        try {
            String sql = "SELECT * FROM COCHES WHERE MATRICULA = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, matricula);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String marca = resultSet.getString("Marca");
                String modelo = resultSet.getString("Modelo");
                float precio = resultSet.getFloat("Precio");
                String extras = resultSet.getString("Extras");
                coche = new EntityCoche(matricula, marca, modelo, precio, extras);
                System.out.println("Matrícula: " + coche.getMatricula());
                System.out.print("Marca: " + coche.getMarca() + ", ");
                System.out.print("Modelo: " + coche.getModelo() + ", ");
                System.out.print("Precio: " + coche.getPrecio() + ", ");
                System.out.print("Extras: " + coche.getExtras() + ", ");
                System.out.println("*****************");

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coche;
    }

    public static void actualizarCoche(Connection conexion, String matricula, String marca, String modelo, float precio, String extras) {
        try {
            String sql = "UPDATE COCHES SET MARCA = ?, MODELO = ?, PRECIO = ?, EXTRAS = ?  WHERE MATRICULA = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, marca);
            preparedStatement.setString(2, modelo);
            preparedStatement.setFloat(3, precio);
            preparedStatement.setString(4, extras);
            preparedStatement.setString(5, matricula);
            int i = preparedStatement.executeUpdate();
            System.out.println("Se han actualizado " + i + " registros.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static ArrayList<EntityCoche> listadoCoches(Connection conexion) {
        ArrayList<EntityCoche> coches = new ArrayList<>();

        try {
            String sql = "SELECT * FROM COCHES";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String matricula = resultSet.getString("Matricula");
                String marca = resultSet.getString("Marca");
                String modelo = resultSet.getString("Modelo");
                float precio = resultSet.getFloat("Precio");
                String extras = resultSet.getString("Extras");
                EntityCoche coche = new EntityCoche(matricula, marca, modelo, precio, extras);
                coches.add(coche);
                System.out.println("Marca: " + coche.getMarca());
                System.out.println("Modelo: " + coche.getModelo());
                System.out.println("Precio: " + coche.getPrecio());
                System.out.println("Extras: " + coche.getExtras());
                System.out.println("***************************");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return coches;
    }
}
