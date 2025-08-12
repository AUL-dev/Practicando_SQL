package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBBDD {

    public static Connection abrirConexion() {
        String url = "jdbc:mysql://localhost:3306/world";
        String usuario = "root";  // Tu usuario de MySQL
        String passwd = "admin";  // Tu contraseña de MySQL
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, usuario, passwd);
            System.out.println("Conexión exitosa a la base de datos MySQL.");

            // Aquí puedes hacer tus operaciones con la base de datos...

            //conn.close();  // Cerramos la conexión al final
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public static void cerrarConexion (Connection conn) {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
