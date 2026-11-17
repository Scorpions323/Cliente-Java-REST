package es.etg.dawes.java.rest.client;

import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;

import javax.swing.*;
import java.awt.*;

public class Gui extends JFrame {

    private static final String TITULO_VENTANA = "Cliente REST en Java";
    private static final String TEXTO_BOTON = "Ejecutar Servicio REST";
    private static final String TEXTO_BOTON_LIMPIAR = "Limpiar";
    private static final String ETIQUETA_ENTRADA = "Cuerpo JSON a enviar (solo para POST y PUT):";
    private static final String ETIQUETA_SALIDA = "Salida del Servicio:";
    private static final String TEXTO_VACIO = "";
    private static final String JSON_POR_DEFECTO = "{\n  \"title\": \"Nuevo titulo\",\n  \"body\": \"Contenido de prueba\",\n  \"userId\": 1\n}";
    private static final String[] METODOS_HTTP = {
            Cliente.METODO_GET,
            Cliente.METODO_POST,
            Cliente.METODO_PUT,
            Cliente.METODO_DELETE
    };

    private static final String FUENTE_UI = "Segoe UI";
    private static final String FUENTE_CODIGO = "Consolas";
    private static final int TAMANO_FUENTE_TITULO = 20;
    private static final int TAMANO_FUENTE_NORMAL = 13;

    private static final int ANCHO_VENTANA = 800;
    private static final int ALTO_VENTANA = 600;
    private static final int COLUMNAS_CAMPO_URL = 28;
    private static final int MARGEN_EXTERIOR = 20;
    private static final int ESPACIO_PRINCIPAL = 15;
    private static final int ESPACIO_MEDIO = 10;
    private static final int ESPACIO_BARRA_H = 8;
    private static final int ESPACIO_PEQUENO = 5;
    private static final int FILAS_GRID_TEXTO = 2;
    private static final int COLUMNAS_GRID_TEXTO = 1;

    private JButton btnEjecutar;
    private JButton btnLimpiar;
    private JComboBox<String> comboMetodo;
    private JTextField txtUrl;
    private JTextArea txtEntradaJson;
    private JScrollPane scrollPaneSalida;
    private JTextArea txtSalida;
    private JLabel lblTitulo;
    private JLabel lblSalida;
    private JPanel panelPrincipal;

    public Gui() {
        initComponents();
    }

    private void initComponents() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle(TITULO_VENTANA);
        setSize(ANCHO_VENTANA, ALTO_VENTANA);
        setLocationRelativeTo(null);

        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout(ESPACIO_PRINCIPAL, ESPACIO_PRINCIPAL));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(MARGEN_EXTERIOR, MARGEN_EXTERIOR, MARGEN_EXTERIOR, MARGEN_EXTERIOR));

        lblTitulo = new JLabel(TITULO_VENTANA, JLabel.CENTER);
        lblTitulo.setFont(new Font(FUENTE_UI, Font.BOLD, TAMANO_FUENTE_TITULO));
        panelPrincipal.add(lblTitulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new BorderLayout(ESPACIO_MEDIO, ESPACIO_MEDIO));

        JPanel panelBarraSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER, ESPACIO_BARRA_H, ESPACIO_PEQUENO));

        comboMetodo = new JComboBox<>(METODOS_HTTP);
        comboMetodo.setFont(new Font(FUENTE_UI, Font.BOLD, TAMANO_FUENTE_NORMAL));

        txtUrl = new JTextField(Cliente.RUTA_EJEMPLO, COLUMNAS_CAMPO_URL);
        txtUrl.setFont(new Font(FUENTE_CODIGO, Font.PLAIN, TAMANO_FUENTE_NORMAL));

        btnEjecutar = new JButton(TEXTO_BOTON);
        btnEjecutar.setFont(new Font(FUENTE_UI, Font.BOLD, TAMANO_FUENTE_NORMAL));
        btnEjecutar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEjecutar.addActionListener(this::btnEjecutarActionPerformed);

        btnLimpiar = new JButton(TEXTO_BOTON_LIMPIAR);
        btnLimpiar.setFont(new Font(FUENTE_UI, Font.PLAIN, TAMANO_FUENTE_NORMAL));
        btnLimpiar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnLimpiar.addActionListener(e -> txtSalida.setText(TEXTO_VACIO));

        panelBarraSuperior.add(comboMetodo);
        panelBarraSuperior.add(txtUrl);
        panelBarraSuperior.add(btnEjecutar);
        panelBarraSuperior.add(btnLimpiar);

        panelCentro.add(panelBarraSuperior, BorderLayout.NORTH);

        JPanel panelTextos = new JPanel(
                new GridLayout(FILAS_GRID_TEXTO, COLUMNAS_GRID_TEXTO, ESPACIO_MEDIO, ESPACIO_MEDIO));

        JPanel panelEntrada = new JPanel(new BorderLayout(ESPACIO_PEQUENO, ESPACIO_PEQUENO));
        JLabel lblEntrada = new JLabel(ETIQUETA_ENTRADA);
        lblEntrada.setFont(new Font(FUENTE_UI, Font.PLAIN, TAMANO_FUENTE_NORMAL));

        txtEntradaJson = new JTextArea(JSON_POR_DEFECTO);
        txtEntradaJson.setFont(new Font(FUENTE_CODIGO, Font.PLAIN, TAMANO_FUENTE_NORMAL));
        txtEntradaJson.setLineWrap(true);
        txtEntradaJson.setWrapStyleWord(true);
        JScrollPane scrollEntrada = new JScrollPane(txtEntradaJson);

        panelEntrada.add(lblEntrada, BorderLayout.NORTH);
        panelEntrada.add(scrollEntrada, BorderLayout.CENTER);

        JPanel panelAreaSalida = new JPanel(new BorderLayout(ESPACIO_PEQUENO, ESPACIO_PEQUENO));
        lblSalida = new JLabel(ETIQUETA_SALIDA);
        lblSalida.setFont(new Font(FUENTE_UI, Font.PLAIN, TAMANO_FUENTE_NORMAL));

        txtSalida = new JTextArea();
        txtSalida.setFont(new Font(FUENTE_CODIGO, Font.PLAIN, TAMANO_FUENTE_NORMAL));
        txtSalida.setEditable(false);
        txtSalida.setLineWrap(true);
        txtSalida.setWrapStyleWord(true);

        scrollPaneSalida = new JScrollPane(txtSalida);
        scrollPaneSalida.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        panelAreaSalida.add(lblSalida, BorderLayout.NORTH);
        panelAreaSalida.add(scrollPaneSalida, BorderLayout.CENTER);

        panelTextos.add(panelEntrada);
        panelTextos.add(panelAreaSalida);

        panelCentro.add(panelTextos, BorderLayout.CENTER);
        panelPrincipal.add(panelCentro, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {
        String urlEscrita = txtUrl.getText().trim();
        String metodoSeleccionado = (String) comboMetodo.getSelectedItem();
        String jsonAEnviar = txtEntradaJson.getText();

        Cliente cliente = new Cliente(urlEscrita);
        String salida = cliente.ejecutar(metodoSeleccionado, jsonAEnviar);
        txtSalida.setText(salida);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatArcDarkIJTheme());
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        EventQueue.invokeLater(() -> new Gui().setVisible(true));
    }
}