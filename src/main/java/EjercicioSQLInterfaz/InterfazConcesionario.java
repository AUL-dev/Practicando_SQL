package EjercicioSQLInterfaz;

import javax.swing.*;

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

    public void interfazConcesionario(){
    onInit();
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

}

