package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryLanguage {
    public static void main(String[] args) {
        Connection abrirConexion = ConexionBBDD.abrirConexion();
        try {
            PreparedStatement preparedStatement = abrirConexion.prepareStatement("SELECT COUNT(*),Percentage, COUNTRYCODE FROM WORLD.COUNTRYLANGUAGE GROUP BY Percentage, COUNTRYCODE ORDER BY COUNT(*) DESC");
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {

                String codPais = resultSet.getString("CountryCode");
                //String language = resultSet.getString("Language");
               // boolean esOficial = resultSet.getBoolean("IsOfficial");
                float porcentaje = resultSet.getFloat("Percentage");
                System.out.println("Código pais: " + codPais + " porcentaje: " + porcentaje);
                //System.out.println("Código del pais: " + codPais + " ,lenguage: " + language +
                     //   " , oficial: " + esOficial + " , porcentaje: " + porcentaje);
            }
            ConexionBBDD.cerrarConexion(abrirConexion);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


