package Ejercicio1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    public static Connection abrirConexion() {
        String url = "jdbc:mysql://localhost:3306/world";
        String usuario = "root";
        String passwd = "admin";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, usuario, passwd);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public static void cerrarConexion(Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
