/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.presentacion;

import itson.entidades.Boleto;
import itson.persistencia.BoletosDAO;
import itson.persistencia.ManejadorConexiones;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author rauln
 */

public class ComprarBoleto extends javax.swing.JFrame {

    /**
     * Creates new form ComprarBoleto
     */
    
    private JTable tablaBoletos;
    private DefaultTableModel modeloTabla;
    private JScrollPane scrollPane;
    private JTextField txtBusqueda;
    private JLabel lblUsuario;
    private JLabel lblSaldo;
    private JButton btnComprar;
    ManejadorConexiones manejadorConexiones = new ManejadorConexiones();
    public ComprarBoleto() {
        initComponents();
        inicializarComponentesPersonalizados();
        

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
        lblUsuario = new JLabel("¡Hola, usuario!");
        lblUsuario.setFont(new Font("Arial", Font.BOLD, 18));
        lblUsuario.setBounds(30, 10, 200, 30);
        panelPrincipal.add(lblUsuario);

        lblSaldo = new JLabel("Saldo: $1500");
        lblSaldo.setForeground(Color.RED);
        lblSaldo.setFont(new Font("Arial", Font.BOLD, 18));
        lblSaldo.setBounds(30, 40, 200, 30);
        panelPrincipal.add(lblSaldo);

        JButton btnAgregarSaldo = new JButton("Agregar saldo");
        btnAgregarSaldo.setBounds(230, 20, 150, 30);
        panelPrincipal.add(btnAgregarSaldo);

        // Barra de búsqueda
        txtBusqueda = new JTextField();
        txtBusqueda.setBounds(30, 90, 300, 30);
        txtBusqueda.setToolTipText("Buscar evento...");
        panelPrincipal.add(txtBusqueda);

        // Configuración del modelo de la tabla
        modeloTabla = new DefaultTableModel() {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) {
                    return Boolean.class; // La última columna es un checkbox
                }
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 5; // Solo se puede editar el checkbox
            }
        };

        // Añadiendo columnas
        modeloTabla.addColumn("Fecha");
        modeloTabla.addColumn("Evento");
        modeloTabla.addColumn("Asiento");
        modeloTabla.addColumn("Precio");
        modeloTabla.addColumn("Lugar");
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

        // Acción del botón para mostrar los boletos comprados
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarBoletosComprados();
            }
        });

        // Configuración del JFrame
        setLayout(null);
        setTitle("Comprar Boleto");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void llenarTablaBoletos() {
        BoletosDAO boletosDAO = new BoletosDAO(manejadorConexiones);

        List<Boleto> listaArtistas = boletosDAO.consultarBoletos();
        DefaultTableModel modelo = (DefaultTableModel)this.tablaBoletos.getModel();
       //Por cada artista devuelto por la clase control lo agregamos a la JTable
       for(Boleto boleto : listaArtistas){
           Object [] fillTable = {
               boleto.getNumeroSerie(),
               boleto.getCodigoEvento(),
               boleto.getFila(),
               boleto.getEstado(),
               boleto.getCodigoUsuario()
           };
           modelo.addRow(fillTable);
       }
    // Consulta SQL para obtener los boletos desde la base de datos
        String consultaBoletos = "SELECT DATE(ev.fechaHora) as 'Fecha', ev.nombre as 'Evento', concat(asiento, fila) as 'Asiento', precioOriginal, concat(recinto, ', ', ciudad) as 'Lugar'  FROM BOLETOS AS BO INNER JOIN EVENTOS AS EV ON BO.CODIGOEVENTO = EV.CODIGOEVENTO;";

        try (Connection conexion = manejadorConexiones.crearConexion();
             PreparedStatement ps = conexion.prepareStatement(consultaBoletos);
             ResultSet rs = ps.executeQuery()) {

            

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar los boletos: " + ex.getMessage());
        }
    }


    private void mostrarBoletosComprados() {
        StringBuilder boletosComprados = new StringBuilder("Boletos Comprados:\n");
        double totalCompra = 0.0;
        Vector<String> boletosSeleccionados = new Vector<>();

        // Recorrer filas y verificar los seleccionados
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            Boolean seleccionado = (Boolean) modeloTabla.getValueAt(i, 5);

            if (seleccionado != null && seleccionado) {
                // Obtener información del boleto seleccionado
                String evento = (String) modeloTabla.getValueAt(i, 3);
                String lugar = (String) modeloTabla.getValueAt(i, 4);
                String numeroBoleto = (String) modeloTabla.getValueAt(i, 0); // Ajusta el índice si es necesario

                boletosComprados.append("- ")
                        .append(evento).append(" | ")
                        .append(lugar).append("\n");

                boletosSeleccionados.add(numeroBoleto);

                // Obtener precio del boleto
                try (Connection conexion = manejadorConexiones.crearConexion()) {
                    String consultaPrecio = "SELECT precioOriginal FROM boletos WHERE numeroControl = ?";
                    PreparedStatement ps = conexion.prepareStatement(consultaPrecio);
                    ps.setString(1, numeroBoleto);
                    ResultSet rs = ps.executeQuery();

                    if (rs.next()) {
                        totalCompra += rs.getDouble("precioOriginal");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Error al consultar precio: " + ex.getMessage());
                    return;
                }
            }
        }

        // Verificar si se seleccionó al menos un boleto
        if (boletosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún boleto.", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Verificar saldo del usuario
        double saldoUsuario = 0.0;
        try (Connection conexion = manejadorConexiones.crearConexion()) {
            String consultaSaldo = "SELECT saldo FROM usuarios WHERE codigoUsuario = ?";
            PreparedStatement ps = conexion.prepareStatement(consultaSaldo);
            ps.setInt(1, 1); // Ajusta el código de usuario según tu lógica de sesión
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                saldoUsuario = rs.getDouble("saldo");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al consultar saldo: " + ex.getMessage());
            return;
        }

        if (saldoUsuario < totalCompra) {
            JOptionPane.showMessageDialog(this, "Saldo insuficiente.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Inicia la compra
        try (Connection conexion = manejadorConexiones.crearConexion()) {
            conexion.setAutoCommit(false);

            // Descontar saldo
            String actualizarSaldo = "UPDATE usuarios SET saldo = saldo - ? WHERE codigoUsuario = ?";
            PreparedStatement psSaldo = conexion.prepareStatement(actualizarSaldo);
            psSaldo.setDouble(1, totalCompra);
            psSaldo.setInt(2, 1); // Ajusta el código de usuario
            psSaldo.executeUpdate();

            // Actualizar estado de boletos y registrar transacción
            for (String numeroBoleto : boletosSeleccionados) {
                // Actualizar estado de boleto
                String actualizarEstado = "UPDATE boletos SET estado = 'Vendido' WHERE numeroControl = ?";
                PreparedStatement psBoleto = conexion.prepareStatement(actualizarEstado);
                psBoleto.setString(1, numeroBoleto);
                psBoleto.executeUpdate();

                // Registrar transacción
                String insertarTransaccion = "INSERT INTO transacciones (monto, estado, fechaHora, codigoComprador) VALUES (?, 'Completado', NOW(), ?)";
                PreparedStatement psTransaccion = conexion.prepareStatement(insertarTransaccion);
                psTransaccion.setDouble(1, totalCompra);
                psTransaccion.setInt(2, 1); // Ajusta el código de usuario
                psTransaccion.executeUpdate();
            }

            // Confirmar transacción
            conexion.commit();

            JOptionPane.showMessageDialog(this, boletosComprados.toString(), "Compra Realizada", JOptionPane.INFORMATION_MESSAGE);

            ocultarBoletosVendidos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error en la compra: " + ex.getMessage());
        }
    }


    private void ocultarBoletosVendidos() {
        Vector<Vector<Object>> filasVisibles = new Vector<>();

        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String estado = (String) modeloTabla.getValueAt(i, 4);

            // Solo se mantienen las filas que no están vendidas
            if (!"Vendido".equals(estado)) {
                Vector<Object> fila = new Vector<>();
                for (int j = 0; j < modeloTabla.getColumnCount(); j++) {
                    fila.add(modeloTabla.getValueAt(i, j));
                }
                filasVisibles.add(fila);
            }
        }

        modeloTabla.setRowCount(0);
        for (Vector<Object> fila : filasVisibles) {
            modeloTabla.addRow(fila.toArray());
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("comprarBoletoFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(800, 500));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
