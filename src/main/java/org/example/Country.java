package org.example;


import java.sql.*;


public class Country {
    public static void main(String[] args) {
        Connection abrirConexion = ConexionBBDD.abrirConexion();

        try {
            PreparedStatement preparedStatement = abrirConexion.prepareStatement("SELECT * FROM COUNTRY where COUNTRY = ?");
            ResultSet resultSet = preparedStatement.executeQuery();


            // Procesar los resultados
            while (resultSet.next()) {
                // Obtener los valores de las columnas del ResultSet
                String code = resultSet.getString("Code");
                String name = resultSet.getString("Name");
                String continent = resultSet.getString("Continent");
                String region = resultSet.getString("Region");
                double population = resultSet.getDouble("Population");
                double surfaceArea = resultSet.getDouble("SurfaceArea");
                short indepYear = resultSet.getShort("IndepYear");
                float gnp = resultSet.getFloat("GNP");

                // Mostrar los datos obtenidos
                System.out.println("Código: " + code + ", Nombre: " + name + ", Continente: " + continent +
                        ", Región: " + region + ", Población: " + population + ", Superficie: " + surfaceArea + ", Año independencia: " + indepYear + ", GNP: " + gnp);
            }
            ConexionBBDD.cerrarConexion(abrirConexion);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}