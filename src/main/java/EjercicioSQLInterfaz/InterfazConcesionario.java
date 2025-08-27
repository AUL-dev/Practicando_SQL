package EjercicioSQLInterfaz;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;

public class InterfazConcesionario extends JFrame {
    private JPanel panel;
    private JLabel jLabelMatricula;
    private JLabel jLabelMarca;
    private JLabel jLabelModelo;
    private JLabel jLabelPrecio;
    private JLabel jLabelExtras;
    private JTextField textFieldMatricula;
    private JTextField textFieldMarca;
    private JTextField textFieldModelo;
    private JTextField textFieldPrecio;
    private JTextField textFieldExtras;
    private JButton buttonAlta;
    private JButton buttonBaja;
    private JButton buttonConsulta;
    private JButton buttonActualizacion;
    private JButton buttonListado;
    private JButton buttonSalir;
    private JScrollPane jScrollPane;
    private JTable table1;
    private DefaultTableModel modelo;


    public InterfazConcesionario() {
        onInit();
        onInitTable();
        buttonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = abrirConcesionario();
                cerrarConcesionario(conexion);
                System.exit(0);
            }
        });
        buttonAlta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = abrirConcesionario();
                validarCampos();
                aniadirCoche(conexion);

            }
        });
        buttonBaja.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = abrirConcesionario();
                bajaCoche(conexion);
            }
        });
        buttonConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = abrirConcesionario();
                consultaCoche(conexion);
            }
        });
        buttonActualizacion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = abrirConcesionario();
                validarCampos();
                actualizarCoche(conexion);
            }
        });
        buttonListado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conexion = abrirConcesionario();
                listarCoches(conexion);
            }
        });
    }


    public void onInit() {
        this.setTitle("Mi Aplicación Java");
        this.setSize(800, 600);
        this.setResizable(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setContentPane(panel);
        this.pack();
    }

    public Connection abrirConcesionario() {
        Connection conexion = ConexionConcesionario.abrirConexion();
        return conexion;
    }

    public void cerrarConcesionario(Connection conexion) {
        ConexionConcesionario.cerrarConexion(conexion);
    }

    public void validarCampos() {
        try {
            //COMPROBAR QUE INTRODUCEN UN NÚMERO
            Integer.parseInt(textFieldPrecio.getText());
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        //COMPROBAR QUE INTRODUCEN TEXTO
        String texto1 = textFieldMarca.getText().trim().toLowerCase();
        if (texto1.isEmpty() || !texto1.matches("[\\p{L}]+")) {
            JOptionPane.showMessageDialog(null, "Escribe una marca correcta", "", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void aniadirCoche(Connection conexion) {
        try {
            String matricula = textFieldMatricula.getText();
            String marca = textFieldMarca.getText();
            String modeloCoche = textFieldModelo.getText();
            float precio = Float.parseFloat(textFieldPrecio.getText());
            String extras = textFieldExtras.getText();
            ConsultaBBDD.crearCoche(conexion, matricula, marca, modeloCoche, precio, extras);

            Object[] fila = new Object[5];
            fila[0] = matricula;
            fila[1] = marca;
            fila[2] = modeloCoche;
            fila[3] = precio;
            fila[4] = extras;
            modelo.addRow(fila);
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
    }

    private void onInitTable() {
        modelo = new DefaultTableModel(new Object[]{"Matricula", "Marca", "Modelo",
                "Precio", "Extras"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Hacer que solo las columnas diferentes del ID sean editables
                return column != 0; // El id es la columna 0.
            }
        };
        table1.setModel(modelo);

    }

    public void bajaCoche(Connection conexion) {
        try {
            String matricula = textFieldMatricula.getText();
            ConsultaBBDD.borrarCoche(conexion, matricula);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void consultaCoche(Connection conexion) {
        try {

            String matricula = textFieldMatricula.getText().trim();
            EntityCoche coche = ConsultaBBDD.consultarCoche(conexion, matricula);
            modelo = (DefaultTableModel) table1.getModel();
            modelo.setRowCount(0);

            if (coche != null) {
                Object[] fila = new Object[5];
                fila[0] = coche.getMatricula();
                fila[1] = coche.getMarca();
                fila[2] = coche.getModelo();
                fila[3] = coche.getPrecio();
                fila[4] = coche.getExtras();
                modelo.addRow(fila);
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró ningún coche con esa matrícula.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarCoche(Connection conexion) {
        try {

            String matricula = textFieldMatricula.getText();
            String marca = textFieldMarca.getText();
            String modelo = textFieldModelo.getText();
            float precio = Float.parseFloat(textFieldPrecio.getText());
            String extras = textFieldExtras.getText();
            ConsultaBBDD.actualizarCoche(conexion, matricula, marca, modelo, precio, extras);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void listarCoches(Connection conexion) {
        try {
            ArrayList<EntityCoche> coches = ConsultaBBDD.listadoCoches(conexion);
            modelo = (DefaultTableModel) table1.getModel();
            modelo.setRowCount(0);
            for (EntityCoche coche : coches) {
                Object[] fila = new Object[5];
                fila[0] = coche.getMatricula();
                fila[1] = coche.getMarca();
                fila[2] = coche.getModelo();
                fila[3] = coche.getPrecio();
                fila[4] = coche.getExtras();
                modelo.addRow(fila);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);


        }
    }

    public static void main(String[] args) {
        InterfazConcesionario interfazConcesionario = new InterfazConcesionario();
    }
}

