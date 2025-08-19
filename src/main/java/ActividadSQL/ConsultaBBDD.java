package ActividadSQL;

import com.sun.jdi.ClassNotPreparedException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaBBDD {
    public static void crearAlumno(Connection conexion, String nombre, String apellidos, String curso, float notaFinal, String observaciones) {
        try {
            String sql = "INSERT INTO ALUMNOS (NOMBRE,APELLIDOS,CURSO, NOTA_FINAL,OBSERVACIONES) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidos);
            preparedStatement.setString(3, curso);
            preparedStatement.setFloat(4, notaFinal);
            preparedStatement.setString(5, observaciones);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void verAlumno(Connection conexion, int idAlumno) {
        try {
            String sql = "SELECT * FROM ALUMNOS WHERE ID_ALUMNO = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("Nombre");
                String apellidos = resultSet.getString("Apellidos");
                String curso = resultSet.getString("Curso");
                float notaFinal = resultSet.getFloat("Nota_Final");
                String observaciones = resultSet.getString("Observaciones");
                EntityAlumno alumno = new EntityAlumno(nombre, apellidos, curso, notaFinal, observaciones);
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Apellidos: " + alumno.getApellidos());
                System.out.println("Curso: " + alumno.getCurso());
                System.out.println("Nota final: " + alumno.getNotaFinal());
                System.out.println("Observaciones: " + alumno.getObservaciones());
                System.out.println("*********************");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void eliminarAlumno(Connection conexion, int idAlumno) {
        try {
            String sql = "DELETE FROM ALUMNOS WHERE ID_ALUMNO = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Alumno eliminado.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void modificarAlumno(Connection conexion, int idAlumno, String nombre, String apellidos, String curso, float notaFinal, String observaciones) {
        try {
            String sql = "UPDATE ALUMNOS SET NOMBRE = ?, APELLIDOS = ?, CURSO = ?, NOTA_FINAL = ?, OBSERVACIONES = ? WHERE ID_ALUMNO = ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, nombre);
            preparedStatement.setString(2, apellidos);
            preparedStatement.setString(3, curso);
            preparedStatement.setFloat(4, notaFinal);
            preparedStatement.setString(5, observaciones);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void mostrarAlumnos(Connection conexion) {
        try {
            String sql = "SELECT * FROM ALUMNOS";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String nombre = resultSet.getString("Nombre");
                String apellidos = resultSet.getString("Apellidos");
                String curso = resultSet.getString("Curso");
                float notaFinal = resultSet.getFloat("Nota final");
                String observaciones = resultSet.getString("Observaciones");
                EntityAlumno alumno = new EntityAlumno(nombre, apellidos, curso, notaFinal, observaciones);
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Apellidos: " + alumno.getApellidos());
                System.out.println("Curso: " + alumno.getCurso());
                System.out.println("Nota final: " + alumno.getNotaFinal());
                System.out.println("Observaciones: " + alumno.getObservaciones());
                System.out.println("************************");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void datosAlumnosPorNotas(Connection conexion, float nota) {
        try {
            String sql = "SELECT * FROM ALUMNOS WHERE NOTA_FINAL >= ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String nombre = resultSet.getString("Nombre");
                String apellidos = resultSet.getString("Apellidos");
                String curso = resultSet.getString("Curso");
                float notaFinal = resultSet.getFloat("Nota final");
                String observaciones = resultSet.getString("Observaciones");
                EntityAlumno alumno = new EntityAlumno(nombre, apellidos, curso, notaFinal, observaciones);
                System.out.println("Nombre: " + alumno.getNombre());
                System.out.println("Apellidos: " + alumno.getApellidos());
                System.out.println("Curso: " + alumno.getCurso());
                System.out.println("Nota final: " + alumno.getNotaFinal());
                System.out.println("Observaciones: " + alumno.getObservaciones());
                System.out.println("************************");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
