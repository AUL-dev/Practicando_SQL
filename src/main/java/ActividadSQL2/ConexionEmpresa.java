package ActividadSQL2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionEmpresa {
    public static Connection abrirConexion() {
        String url = "jdbc:mysql://localhost:3306/empresa";
        String usuario = "root";
        String passwd = "admin";
        Connection conexion = null;
        try {
            conexion = DriverManager.getConnection(url, usuario, passwd);
            System.out.println("Conexión exitosa a la base de datos.");
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
