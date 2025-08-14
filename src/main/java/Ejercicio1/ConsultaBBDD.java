package Ejercicio1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ConsultaBBDD {
    public static void main(String[] args) {
        Connection connection = Conexion.abrirConexion();
        ArrayList<CountryLanguage> array = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM WORLD.COUNTRYLANGUAGE");
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                String countryCode = resultSet.getString("CountryCode");
                String language = resultSet.getString("Language");
                boolean isOfficial = resultSet.getBoolean("IsOfficial");
                float percentage = resultSet.getFloat("Percentage");
                CountryLanguage nuevoCountryLanguage = new CountryLanguage(countryCode, language, isOfficial, percentage);
                array.add(nuevoCountryLanguage);
            }
            for (CountryLanguage country : array) {
                System.out.println("***********");
                System.out.print(country.getCountryCode() + ", ");
                System.out.print(country.getLanguage() + ", ");
                System.out.print(country.isOfficial() + ", ");
                System.out.println(country.getPercentage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
