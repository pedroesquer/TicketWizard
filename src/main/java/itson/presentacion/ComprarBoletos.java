/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.presentacion;

import itson.control.ControlActualizarBoleto;
import itson.control.ControlIniciarSesion;
import itson.entidades.Boleto;
import itson.entidades.Usuario;
import itson.persistencia.BoletosDAO;
import itson.persistencia.ManejadorConexiones;
import itson.usuariosDTOs.ActualizarBoletoDTO;
import itson.usuariosDTOs.NuevoBoletoEventoDTO;
import itson.usuariosDTOs.SesionDTO;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Raul Montoya, Pedro Morales, Juan Heras
 */
public class ComprarBoletos extends javax.swing.JFrame {

    /**
     * Creates new form ComprarBoletos
     */
    private JTable tablaBoletos;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    private JLabel lblUsuario;
    private JLabel lblSaldo;
    private JButton btnComprar;
    ManejadorConexiones manejadorConexiones = new ManejadorConexiones();
    private final ControlActualizarBoleto controlActualizar;

    public ComprarBoletos(ControlActualizarBoleto controlActualizar) {
        initComponents();
        inicializarComponentesPersonalizados();
        mostrarDatosUsuario();
        this.controlActualizar = controlActualizar;

        setSize(800, 500);
        pack();  // Ajusta el tamaño de la ventana según los componentes
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * Inicializa componentes personalizados sin modificar initComponents()
     */
    // Para obtener datos del usuario actual
    private void mostrarDatosUsuario() {
        Usuario usuarioActual = SesionDTO.getInstancia().getUsuarioActual();
        if (usuarioActual != null) {
            lblUsuario.setText("Bienvenido, " + usuarioActual.getNombre());
            lblSaldo.setText("Saldo: $" + usuarioActual.getSaldo());
        }
    }

    private void inicializarComponentesPersonalizados() {
        // Panel principal para mantener el layout personalizado
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(Color.WHITE);
        panelPrincipal.setBounds(0, 0, 800, 500);
        add(panelPrincipal);

        // Información del usuario (parte superior)
        lblUsuario = new JLabel();
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        lblUsuario.setBounds(30, 10, 200, 30);
        panelPrincipal.add(lblUsuario);

        lblSaldo = new JLabel();
        lblSaldo.setForeground(Color.RED);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 18));
        lblSaldo.setBounds(30, 40, 200, 30);
        panelPrincipal.add(lblSaldo);

        JButton btnAgregarSaldo = new JButton("Agregar saldo");
        btnAgregarSaldo.setBounds(230, 20, 150, 30);
        panelPrincipal.add(btnAgregarSaldo);

        // Configuración del modelo de la tabla
        modeloTabla = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 7) {
                    return Boolean.class; // La última columna es un checkbox
                }
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7; // Solo se puede editar el checkbox
            }
        };

        // Añadiendo columnas
        modeloTabla.addColumn("ID");
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Evento");
        modeloTabla.addColumn("Asiento");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Lugar");
        modeloTabla.addColumn("Tipo");
        modeloTabla.addColumn("Seleccionar");

        // Creación de la tabla con el modelo
        tablaBoletos = new JTable(modeloTabla);
        tablaBoletos.setRowHeight(40);

        // Cargar datos de boletos (precargados)
        llenarTablaBoletos();

        // Configuración del JScrollPane para hacer la tabla desplazable
        scrollPane = new JScrollPane(tablaBoletos);
        scrollPane.setBounds(30, 140, 720, 250);
        panelPrincipal.add(scrollPane);

        // Botón para confirmar la compra
        btnComprar = new JButton("Comprar boletos");
        btnComprar.setBounds(320, 410, 150, 30);
        panelPrincipal.add(btnComprar);

        btnComprar.addActionListener((ActionEvent e) -> {
            // Obtener las filas seleccionadas de la tabla
            Integer usuarioActualCodigo = SesionDTO.getInstancia().getUsuarioActual().getCodigoUsuario();
            for (int i = 0; i < tablaBoletos.getRowCount(); i++) {
                Boolean seleccionado = (Boolean) tablaBoletos.getValueAt(i, 7); // Columna 7 es el checkbox
                if (seleccionado != null && seleccionado) {
                    String idBoleto = (String) tablaBoletos.getValueAt(i, 0); // Suponiendo que la primera columna es el ID
                    System.out.println("Número de control seleccionado: " + idBoleto);
                    ActualizarBoletoDTO actualizarBoletoDTO = new ActualizarBoletoDTO(idBoleto, usuarioActualCodigo, ActualizarBoletoDTO.Estado.Vendido);
                    this.controlActualizar.actualizarBoleto(actualizarBoletoDTO);
                }
            }
        });

        // Configuración del JFrame
        setLayout(null);
        setTitle("Comprar Boleto");
        setSize(800, 501);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void llenarTablaBoletos() {
        BoletosDAO boletosDAO = new BoletosDAO(manejadorConexiones);

        List<NuevoBoletoEventoDTO> listaBoletos = boletosDAO.consultarBoletosEventos();
        DefaultTableModel modelo = (DefaultTableModel) this.tablaBoletos.getModel();
        //Por cada artista devuelto por la clase control lo agregamos a la JTable
        for (NuevoBoletoEventoDTO boleto : listaBoletos) {
            Object[] fillTable = {
                boleto.getID(),
                boleto.getFechaHora(),
                boleto.getNombreEv(),
                boleto.getAsiento(),
                boleto.getPrecioOriginal(),
                boleto.getLugar(),
                boleto.getTipo()
            };
            modelo.addRow(fillTable);
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("comprarBoletoFrame"); // NOI18N
        setSize(new java.awt.Dimension(800, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
