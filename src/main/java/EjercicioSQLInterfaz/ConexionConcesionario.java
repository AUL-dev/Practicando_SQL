package EjercicioSQLInterfaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionConcesionario {

    public static Connection abrirConexion() {
        String url = "jdbc:mysql://localhost:3306/concesionario";
        String usuario = "root";
        String passswd = "admin";
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, usuario, passswd);
            System.out.println("Conexión exitosa.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conexion;
    }

    public static void cerrarConexion(Connection conexion) {
        try {
            conexion.close();
            System.out.println("Conexión cerrada.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
