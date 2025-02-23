package itson.presentacion;

import com.toedter.calendar.JDateChooser;
import itson.persistencia.ManejadorConexiones;
import itson.usuariosDTOs.SesionDTO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class TablaVenderBoletos extends JInternalFrame {

    private JTable tablaBoletos;
    private DefaultTableModel modeloTabla;
    private JButton btnConfirmar;
    private int codigoUsuario;
    private ArrayList<Integer> boletosSeleccionados = new ArrayList<>();

    public TablaVenderBoletos() {
        // Verificación de sesión
        if (SesionDTO.getInstancia().getUsuarioActual() != null) {
            this.codigoUsuario = SesionDTO.getInstancia().getUsuarioActual().getCodigoUsuario();
        } else {
            JOptionPane.showMessageDialog(this, "No se ha iniciado sesión. Por favor, inicia sesión primero.");
            this.setVisible(false);
            return;
        }

        // Configuración del JInternalFrame
        setTitle("Mis Boletos");
        setSize(700, 300);
        setClosable(false);
        setResizable(false);
        setMaximizable(false);
        setIconifiable(false);
        setFocusable(false);
        setEnabled(false);
        setDefaultCloseOperation(JInternalFrame.DO_NOTHING_ON_CLOSE);

        // Quitar el borde superior para evitar arrastrar
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);

        // Layout para el contenido de la tabla
        setLayout(new BorderLayout());

        // Modelo de la tabla
        modeloTabla = new DefaultTableModel(new Object[][]{}, new String[]{
            "Evento", "Asiento", "Fecha", "Recinto", "Precio Original", "Precio Máximo", "Seleccionar"
        }) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 6 ? Boolean.class : String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6;
            }
        };

        // Configuración de la tabla
        tablaBoletos = new JTable(modeloTabla);

        // Fondo blanco para toda la tabla y celdas
        tablaBoletos.setOpaque(false);
        tablaBoletos.setBackground(new java.awt.Color(255, 255, 255));
        tablaBoletos.setForeground(new java.awt.Color(0, 0, 0));

        // Sin líneas visibles de separación
        tablaBoletos.setShowGrid(false);
        tablaBoletos.setIntercellSpacing(new java.awt.Dimension(0, 0));
        tablaBoletos.setShowHorizontalLines(false);
        tablaBoletos.setShowVerticalLines(false);

        // Centrar el texto en todas las columnas (excepto la última que es checkbox)
        DefaultTableCellRenderer centrarTexto = new DefaultTableCellRenderer();
        centrarTexto.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaBoletos.getColumnCount() - 1; i++) {
            tablaBoletos.getColumnModel().getColumn(i).setCellRenderer(centrarTexto);
        }

        // Fondo blanco en el JScrollPane
        JScrollPane scrollPane = new JScrollPane(tablaBoletos);
        scrollPane.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(null);

        // Sin borde en el JInternalFrame
        this.setBorder(null);

        // Añadir el JScrollPane al JInternalFrame
        add(scrollPane, BorderLayout.CENTER);

        // Botón Confirmar
        btnConfirmar = new JButton("Confirmar Selección");
        add(btnConfirmar, BorderLayout.SOUTH);

        btnConfirmar.addActionListener(e -> confirmarSeleccion());

        // Cargar datos en la tabla
        cargarBoletos();
    }

    private void cargarBoletos() {
        modeloTabla.setRowCount(0);
        ManejadorConexiones manejador = new ManejadorConexiones();
        String consultaSQL = """
            SELECT 
                e.nombre AS evento, 
                b.asiento, 
                e.fechaHora AS fecha, 
                e.recinto, 
                b.precioOriginal AS precioOriginal
            FROM 
                boletos b
            JOIN 
                eventos e ON b.codigoEvento = e.codigoEvento
            WHERE 
                b.codigoUsuario = ?;
        """;

        try (Connection conn = manejador.crearConexion(); PreparedStatement stmt = conn.prepareStatement(consultaSQL)) {
            stmt.setInt(1, codigoUsuario);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                float precioOriginal = rs.getBigDecimal("precioOriginal").floatValue();
                float precioMaximo = precioOriginal + (precioOriginal * 0.03f);

                Object[] fila = {
                    rs.getString("evento"),
                    rs.getInt("asiento"),
                    rs.getTimestamp("fecha"),
                    rs.getString("recinto"),
                    String.format("%.2f", precioOriginal),
                    String.format("%.2f", precioMaximo),
                    false
                };
                modeloTabla.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Agregar esta clase interna para el editor personalizado
    private class DateChooserCellEditor extends AbstractCellEditor implements TableCellEditor {

        private JDateChooser dateChooser;

        public DateChooserCellEditor() {
            dateChooser = new JDateChooser();
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            if (value instanceof Date) {
                dateChooser.setDate((Date) value);
            } else {
                dateChooser.setDate(null);
            }
            return dateChooser;
        }

        @Override
        public Object getCellEditorValue() {
            return dateChooser.getDate();
        }
    }

    // Agregar esta clase interna para el renderer personalizado
    private class DateChooserCellRenderer extends DefaultTableCellRenderer {

        private JDateChooser dateChooser;

        public DateChooserCellRenderer() {
            dateChooser = new JDateChooser();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
            if (value instanceof Date) {
                dateChooser.setDate((Date) value);
                return dateChooser;
            }
            return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        }
    }

    private void mostrarVentanaEmergente() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Boletos Seleccionados", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(900, 400);
        dialog.setLayout(new BorderLayout());

        // Centrar la ventana en la pantalla
        dialog.setLocationRelativeTo(null);

        // Modelo de la tabla para la ventana emergente
        DefaultTableModel modeloSeleccion = new DefaultTableModel(new Object[][]{}, new String[]{
            "Evento", "Asiento", "Fecha", "Recinto", "Precio Original", "Precio Máximo",
            "Nuevo Precio Máximo", "Fecha Límite", "Confirmar Venta"
        }) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 8) { // Confirmar Venta
                    return Boolean.class;
                }
                return Object.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 6 || column == 7 || column == 8;
            }
        };

        // Cargar datos seleccionados en el nuevo modelo
        boletosSeleccionados.forEach(index -> {
            Object[] fila = new Object[9];
            for (int i = 0; i < 5; i++) {
                fila[i] = modeloTabla.getValueAt(index, i);
            }

            float precioOriginal = Float.parseFloat((String) modeloTabla.getValueAt(index, 4));
            float precioMaximo = precioOriginal + (precioOriginal * 0.03f);
            fila[5] = String.format("%.2f", precioMaximo);

            fila[6] = ""; // Nuevo Precio Máximo
            fila[7] = null; // Fecha Límite (será manejada por el DateChooser)
            fila[8] = false; // Confirmar Venta
            modeloSeleccion.addRow(fila);
        });

        // Crear la tabla con el nuevo modelo
        JTable tablaSeleccion = new JTable(modeloSeleccion);

        // Configurar el editor y renderer personalizado para la columna de fecha
        tablaSeleccion.getColumnModel().getColumn(7).setCellEditor(new DateChooserCellEditor());
        tablaSeleccion.getColumnModel().getColumn(7).setCellRenderer(new DateChooserCellRenderer());

        // Centrar el texto en las columnas (excepto la de fecha y checkbox)
        DefaultTableCellRenderer centrarTexto = new DefaultTableCellRenderer();
        centrarTexto.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaSeleccion.getColumnCount(); i++) {
            if (i != 7 && i != 8) {
                tablaSeleccion.getColumnModel().getColumn(i).setCellRenderer(centrarTexto);
            }
        }

        // Ajustes de la tabla emergente
        tablaSeleccion.getTableHeader().setReorderingAllowed(false);
        tablaSeleccion.setShowGrid(true);
        tablaSeleccion.setGridColor(new java.awt.Color(200, 200, 200));
        tablaSeleccion.setOpaque(false);
        tablaSeleccion.setBackground(new java.awt.Color(255, 255, 255));
        tablaSeleccion.setForeground(new java.awt.Color(0, 0, 0));
        tablaSeleccion.setFillsViewportHeight(true);

        // Añadir la tabla al dialogo
        JScrollPane scrollPane = new JScrollPane(tablaSeleccion);
        scrollPane.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(null);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Botón Confirmar Venta
        JButton btnConfirmarVenta = new JButton("Confirmar Venta");
        btnConfirmarVenta.addActionListener(e -> {
            boolean datosValidos = true;

            for (int i = 0; i < modeloSeleccion.getRowCount(); i++) {
                // Validar que el nuevo precio máximo sea un float válido
                String nuevoPrecioMaximoStr = (String) modeloSeleccion.getValueAt(i, 6);
                try {
                    if (nuevoPrecioMaximoStr.isEmpty() || Float.parseFloat(nuevoPrecioMaximoStr) <= 0) {
                        JOptionPane.showMessageDialog(dialog, "Nuevo Precio Máximo inválido en la fila " + (i + 1));
                        datosValidos = false;
                        break;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Nuevo Precio Máximo debe ser un número en la fila " + (i + 1));
                    datosValidos = false;
                    break;
                }

                // Validar que la fecha límite no sea nula
                Date fechaLimite = (Date) modeloSeleccion.getValueAt(i, 7);
                if (fechaLimite == null) {
                    JOptionPane.showMessageDialog(dialog, "Debe seleccionar una fecha límite en la fila " + (i + 1));
                    datosValidos = false;
                    break;
                }

                // Validar que el checkbox de confirmar venta esté marcado
                Boolean confirmarVenta = (Boolean) modeloSeleccion.getValueAt(i, 8);
                if (confirmarVenta == null || !confirmarVenta) {
                    JOptionPane.showMessageDialog(dialog, "Debe confirmar la venta en la fila " + (i + 1));
                    datosValidos = false;
                    break;
                }
            }

            if (datosValidos) {
                JOptionPane.showMessageDialog(dialog, "¡Venta confirmada!");
                dialog.dispose();
            }
        });

        // Panel para el botón
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnConfirmarVenta);
        dialog.add(panelBoton, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private void confirmarSeleccion() {
        boletosSeleccionados.clear();
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if (Boolean.TRUE.equals(modeloTabla.getValueAt(i, 6))) {
                boletosSeleccionados.add(i);
            }
        }
        if (boletosSeleccionados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No has seleccionado ningún boleto.");
        } else {
            mostrarVentanaEmergente();
        }
    }
}
