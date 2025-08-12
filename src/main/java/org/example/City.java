package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class City {
    public static void main(String[] args) {
        Connection abrirConexion2 = ConexionBBDD.abrirConexion();
        try {
            PreparedStatement preparedStatement = abrirConexion2.prepareStatement("SELECT * FROM WORLD.CITY WHERE population >= 5000000");
            ResultSet resulset = preparedStatement.executeQuery();

            while (resulset.next()) {
                int id = resulset.getInt("ID");
                String nombrePais = resulset.getString("Name");
                String codigoPais = resulset.getString("CountryCode");
                String distrito = resulset.getString("District");
                float poblacion = resulset.getFloat("Population");

                System.out.println("ID: " + id + " ,nombre del país: " + nombrePais +
                               " ,código del país: " + codigoPais + " ,distrito: " + distrito +
                        " ,población: " + poblacion);
            }
            ConexionBBDD.cerrarConexion(abrirConexion2);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
