package ActividadSQL2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaBBDD {
    public static void crearEmpleado(Connection conexion, String nombre, String apellidos, String departamento, float sueldo, String diaLibre) {
        try {
            String sql = "INSERT INTO EMPLEADOS (NOMBRE,APELLIDOS,DEPARTAMENTO,SUELDO,DIA_LIBRE) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidos);
            preparedStatement.setString(3, departamento);
            preparedStatement.setFloat(4, sueldo);
            preparedStatement.setString(5, diaLibre);
            int nRows = preparedStatement.executeUpdate();
            if (nRows >= 1) {
                System.out.println("Empleado creado.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void eliminarEmpleado(Connection conexion, int idEmpleado) {
        try {
            String sql = "DELETE FROM EMPLEADOS WHERE ID_EMPLEADO = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, idEmpleado);
            int registros = preparedStatement.executeUpdate();
            System.out.println("Se han eliminado " + registros + " registros.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void verEmpleado(Connection conexion, int idEmpleado) {
        try {
            String sql = "SELECT * FROM EMPLEADOS WHERE ID_EMPLEADO = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setInt(1, idEmpleado);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("Nombre");
                String apellidos = resultSet.getString("Apellidos");
                String departamento = resultSet.getString("Departamento");
                float sueldo = resultSet.getFloat("Sueldo");
                String diaLIbre = resultSet.getString("Dia_Libre");
                EntityEmpleado empleado = new EntityEmpleado(idEmpleado,nombre, apellidos, departamento, sueldo, diaLIbre);
                System.out.println("Id empleado: " + empleado.getIdEmpleado());
                System.out.println("Nombre: " + empleado.getNombre());
                System.out.println("Apellidos: " + empleado.getApellidos());
                System.out.println("Departamento: " + empleado.getDepartamento());
                System.out.println("Sueldo: " + empleado.getSueldo());
                System.out.println("Día libre: " + empleado.getDiaLibre());
                System.out.println("*********************");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void mostrarEmpleados(Connection conexion) {
        try {
            String sql = "SELECT * FROM EMPLEADOS";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int idEmpleado = resultSet.getInt("ID_EMPLEADO");
                String nombre = resultSet.getString("Nombre");
                String apellidos = resultSet.getString("Apellidos");
                String departamento = resultSet.getString("Departamento");
                float sueldo = resultSet.getFloat("Sueldo");
                String diaLibre = resultSet.getString("Dia_Libre");
                EntityEmpleado empleado = new EntityEmpleado(idEmpleado,nombre, apellidos, departamento, sueldo, diaLibre);
                System.out.println("Id empleado: " + empleado.getIdEmpleado());
                System.out.println("Nombre: " + empleado.getNombre());
                System.out.println("Apellidos: " + empleado.getApellidos());
                System.out.println("Curso: " + empleado.getDepartamento());
                System.out.println("Nota final: " + empleado.getSueldo());
                System.out.println("Día libre: " + empleado.getDiaLibre());
                System.out.println("************************");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void intercambiarDias(Connection conexion, int idEmpleado1, int idEmpleado2) {
        try {
            String seleccionarDiaLibre = "SELECT DIA_LIBRE FROM EMPLEADOS WHERE ID_EMPLEADO = ?";
            String modificarDia = "UPDATE EMPLEADOS SET DIA_LIBRE = ? WHERE ID_EMPLEADO = ?";

            PreparedStatement preparedStatement = conexion.prepareStatement(seleccionarDiaLibre);
            preparedStatement.setInt(1, idEmpleado1);
            ResultSet resultSet = preparedStatement.executeQuery();
            String diaLibre = null;
            if (resultSet.next()) {
                diaLibre = resultSet.getString("Dia_Libre");
            } else {
                throw new SQLException("Empleado con ID " + idEmpleado1 + " no encontrado.");
            }

            preparedStatement.setInt(1, idEmpleado2);
            ResultSet resultSet2 = preparedStatement.executeQuery();
            String diaLibre2 = null;
            if (resultSet2.next()) {
                diaLibre2 = resultSet2.getString("Dia_Libre");
            } else {
                throw new SQLException("Empleado con ID " + idEmpleado2 + " no encontrado.");
            }

            PreparedStatement modificar = conexion.prepareStatement(modificarDia);
            modificar.setString(1, diaLibre2);
            modificar.setInt(2, idEmpleado1);
            modificar.executeUpdate();


            modificar.setString(1, diaLibre);
            modificar.setInt(2, idEmpleado2);
            modificar.executeUpdate();

            preparedStatement.close();
            resultSet.close();
            resultSet2.close();
            modificar.close();

            System.out.println("Intercambio realizado con éxito.");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void actualizarEmpleado(Connection conexion, int idEmpleado, String nombre, String apellidos, String departamento, float sueldo, String diaLibre) {
        try {
            String sql = "UPDATE EMPLEADOS SET NOMBRE = ?, APELLIDOS = ?, DEPARTAMENTO = ?, SUELDO = ?, DIA_LIBRE = ? WHERE ID_EMPLEADO = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidos);
            preparedStatement.setString(3, departamento);
            preparedStatement.setFloat(4, sueldo);
            preparedStatement.setString(5, diaLibre);
            preparedStatement.setInt(6, idEmpleado);
            int i = preparedStatement.executeUpdate();
            System.out.println("Se han actualizado " + i + " registros.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

