/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.presentacion;

import itson.control.ControlComprarBoleto;
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
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
    private final ControlComprarBoleto controlActualizar;

    public ComprarBoletos(ControlComprarBoleto controlActualizar) {
        initComponents();
        inicializarComponentesPersonalizados();
        this.controlActualizar = controlActualizar;

        setSize(800, 500);
        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    /**
     * Inicializa componentes personalizados sin modificar initComponents()
     */


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
            List<String> boletosTransaccion = new LinkedList<>();
            for (int i = 0; i < tablaBoletos.getRowCount(); i++) {
                Boolean seleccionado = (Boolean) tablaBoletos.getValueAt(i, 7); // Columna 7 es el checkbox
                if (seleccionado != null && seleccionado) {
                    String idBoleto = (String) tablaBoletos.getValueAt(i, 0); // Suponiendo que la primera columna es el ID
                    boletosTransaccion.add(idBoleto);

                    ActualizarBoletoDTO actualizarBoletoDTO = new ActualizarBoletoDTO(idBoleto, usuarioActualCodigo, ActualizarBoletoDTO.Estado.Vendido);
                    this.controlActualizar.actualizarBoleto(actualizarBoletoDTO);

                }
            }
            
            this.controlActualizar.procesarCompraBoleto(manejadorConexiones, SesionDTO.getInstancia().getUsuarioActual().getCodigoUsuario(), boletosTransaccion);
            System.out.println(boletosTransaccion);

        });

    }
    /**
     * 
     */
    private void llenarTablaBoletos() {
        BoletosDAO boletosDAO = new BoletosDAO(manejadorConexiones);

        List<NuevoBoletoEventoDTO> listaBoletos = boletosDAO.consultarBoletosEventos();
        DefaultTableModel modelo = (DefaultTableModel) this.tablaBoletos.getModel();
        //Por cada artista devuelto por la clase control lo agregamos a la JTable
        for (NuevoBoletoEventoDTO boleto : listaBoletos) {
            Object[] fillTable = {
                boleto.getNumeroControl(),
                boleto.getFechaHora(),
                boleto.getNombreEv(),
                boleto.getAsiento(),
                boleto.getPrecioOriginal(),
                boleto.getLugar(),
                boleto.getTipoCompra()
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

        iconLabel = new javax.swing.JLabel();
        lblHome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setName("comprarBoletoFrame"); // NOI18N
        setSize(new java.awt.Dimension(800, 500));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        iconLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smallerIcon.png"))); // NOI18N

        lblHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/homeIcon.png"))); // NOI18N
        lblHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblHomeMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Comprar Boletos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(lblHome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 173, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(153, 153, 153)
                .addComponent(iconLabel))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(iconLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 385, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(lblHome))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

    }//GEN-LAST:event_formWindowClosing

    private void lblHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblHomeMouseClicked
        this.dispose();
    }//GEN-LAST:event_lblHomeMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblHome;
    // End of variables declaration//GEN-END:variables
}
