package itson.presentacion;

import com.toedter.calendar.JDateChooser;
import itson.control.ControlVenderBoletos;
import itson.persistencia.ManejadorConexiones;
import itson.usuariosDTOs.ActualizarBoletoDTO;
import itson.usuariosDTOs.SesionDTO;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import observador.Observer;

public class TablaVenderBoletos extends JInternalFrame implements Observer {

    private JTable tablaBoletos;
    private DefaultTableModel modeloTabla;
    private JButton btnConfirmar;
    private int codigoUsuario;
    // Guarda los índices de las filas seleccionadas (columna "Seleccionar" en el modelo principal)
    private ArrayList<Integer> boletosSeleccionados = new ArrayList<>();
    private ControlVenderBoletos controlVenderBoletos;
    private ManejadorConexiones manejadorConexiones = new ManejadorConexiones();

    public TablaVenderBoletos(ControlVenderBoletos controlVenderBoletos) {
        this.controlVenderBoletos = controlVenderBoletos;
        
        // Verificar sesión
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
        ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).setNorthPane(null);
        setLayout(new BorderLayout());

        // Modelo de la tabla principal (incluye columna oculta "Número de Control")
        modeloTabla = new DefaultTableModel(new Object[][]{}, new String[]{
            "Número de Control", "Evento", "Asiento", "Fecha", "Recinto", "Precio Original", "Precio Máximo", "Seleccionar"
        }) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 7) {
                    return Boolean.class;
                }
                if (columnIndex == 3) {
                    return Date.class;
                }
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7;
            }
        };

        tablaBoletos = new JTable(modeloTabla);
        // Ocultar la columna "Número de Control" (índice 0)
        tablaBoletos.getColumnModel().getColumn(0).setMinWidth(0);
        tablaBoletos.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaBoletos.getColumnModel().getColumn(0).setPreferredWidth(0);

        // Estilos de la tabla
        tablaBoletos.setOpaque(false);
        tablaBoletos.setBackground(new java.awt.Color(255, 255, 255));
        tablaBoletos.setForeground(new java.awt.Color(0, 0, 0));
        tablaBoletos.setShowGrid(false);
        tablaBoletos.setIntercellSpacing(new Dimension(0, 0));
        tablaBoletos.setShowHorizontalLines(false);
        tablaBoletos.setShowVerticalLines(false);

        // Centrar el texto en las columnas (desde índice 1 hasta penúltima, ya que el checkbox es índice 7)
        DefaultTableCellRenderer centrarTexto = new DefaultTableCellRenderer();
        centrarTexto.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 1; i < tablaBoletos.getColumnCount() - 1; i++) {
            tablaBoletos.getColumnModel().getColumn(i).setCellRenderer(centrarTexto);
        }

        JScrollPane scrollPane = new JScrollPane(tablaBoletos);
        scrollPane.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(null);
        setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para confirmar selección (se guardan los índices de las filas seleccionadas)
        btnConfirmar = new JButton("Confirmar Selección");
        add(btnConfirmar, BorderLayout.SOUTH);
        btnConfirmar.addActionListener(e -> confirmarSeleccion());

        cargarBoletos();
    }
    
    
    @Override
    public void actualizar() {
        cargarBoletos(); // Recarga la tabla
    }

    // Carga los boletos del usuario en la tabla principal
    private void cargarBoletos() {
        modeloTabla.setRowCount(0);
        String consultaSQL = """
            SELECT 
                b.numeroControl AS numeroControl,
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
                b.codigoUsuario = ? and b.estado = ?;
        """;
        try (Connection conn = manejadorConexiones.crearConexion();
             PreparedStatement stmt = conn.prepareStatement(consultaSQL)) {
            stmt.setInt(1, codigoUsuario);
            stmt.setString(2, "Vendido");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String numeroControl = rs.getString("numeroControl");
                String evento = rs.getString("evento");
                int asiento = rs.getInt("asiento");
                Date fecha = rs.getTimestamp("fecha");
                String recinto = rs.getString("recinto");
                float precioOriginal = rs.getBigDecimal("precioOriginal").floatValue();
                float precioMaximo = precioOriginal + (precioOriginal * 0.03f);
                Object[] fila = {
                    numeroControl, // Columna 0: Número de Control (oculta)
                    evento,        // Columna 1: Evento
                    String.valueOf(asiento), // Columna 2: Asiento
                    fecha,         // Columna 3: Fecha del Evento
                    recinto,       // Columna 4: Recinto
                    String.format("%.2f", precioOriginal), // Columna 5: Precio Original
                    String.format("%.2f", precioMaximo),     // Columna 6: Precio Máximo
                    false          // Columna 7: Checkbox de Selección
                };
                modeloTabla.addRow(fila);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    // Editor para JDateChooser en la ventana emergente (columna "Fecha Límite")
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

    // Renderer para JDateChooser en la ventana emergente (columna "Fecha Límite")
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

    // Ventana emergente para confirmar la venta de los boletos seleccionados
    private void mostrarVentanaEmergente() {
        JDialog dialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Boletos Seleccionados", Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(900, 400);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(null);

        // Modelo para la ventana emergente: se copian solo las filas seleccionadas
        DefaultTableModel modeloSeleccion = new DefaultTableModel(new Object[][]{}, new String[]{
            "Número de Control", "Evento", "Asiento", "Fecha", "Recinto", "Precio Original", "Precio Máximo", "Nuevo Precio Máximo", "Fecha Límite"
        }) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 7 || column == 8;
            }
        };

        // Copiar solo las filas seleccionadas (utilizando los índices almacenados en boletosSeleccionados)
        for (Integer index : boletosSeleccionados) {
            Object[] fila = new Object[9];
            for (int i = 0; i < modeloTabla.getColumnCount(); i++) {
                fila[i] = modeloTabla.getValueAt(index, i);
            }
            fila[7] = "";      // Nuevo Precio Máximo (a ingresar)
            fila[8] = null;    // Fecha Límite (a seleccionar con JDateChooser)
            modeloSeleccion.addRow(fila);
        }

        JTable tablaSeleccion = new JTable(modeloSeleccion);
        // Configurar editor y renderer para la columna "Fecha Límite" (índice 8)
        tablaSeleccion.getColumnModel().getColumn(8).setCellEditor(new DateChooserCellEditor());
        tablaSeleccion.getColumnModel().getColumn(8).setCellRenderer(new DateChooserCellRenderer());

        DefaultTableCellRenderer centrarTexto = new DefaultTableCellRenderer();
        centrarTexto.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < tablaSeleccion.getColumnCount(); i++) {
            if (i != 8) {
                tablaSeleccion.getColumnModel().getColumn(i).setCellRenderer(centrarTexto);
            }
        }

        tablaSeleccion.getTableHeader().setReorderingAllowed(false);
        tablaSeleccion.setShowGrid(true);
        tablaSeleccion.setGridColor(new java.awt.Color(200, 200, 200));
        tablaSeleccion.setOpaque(false);
        tablaSeleccion.setBackground(new java.awt.Color(255, 255, 255));
        tablaSeleccion.setForeground(new java.awt.Color(0, 0, 0));
        tablaSeleccion.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(tablaSeleccion);
        scrollPane.getViewport().setBackground(new java.awt.Color(255, 255, 255));
        scrollPane.setBorder(null);
        dialog.add(scrollPane, BorderLayout.CENTER);

        // Botón Confirmar Venta con validaciones y actualización de datos
        JButton btnConfirmarVenta = new JButton("Confirmar Venta");
        btnConfirmarVenta.addActionListener((ActionEvent e) -> {
            // Forzar que se detenga la edición en curso para que se guarden los valores del JDateChooser
            if (tablaSeleccion.isEditing()) {
                tablaSeleccion.getCellEditor().stopCellEditing();
            }
            
            boolean datosValidos = true;
            Date hoy = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Integer usuarioActualCodigo = SesionDTO.getInstancia().getUsuarioActual().getCodigoUsuario();
            // Ahora se usará una lista de DTO en lugar de una lista de String
            List<ActualizarBoletoDTO> listaDTO = new LinkedList<>();

            for (int i = 0; i < modeloSeleccion.getRowCount(); i++) {
                // Validar "Nuevo Precio Máximo" (columna 7)
                String nuevoPrecioMaximoStr = (String) modeloSeleccion.getValueAt(i, 7);
                try {
                    if (nuevoPrecioMaximoStr.isEmpty() || Float.parseFloat(nuevoPrecioMaximoStr) <= 0) {
                        JOptionPane.showMessageDialog(dialog, "Nuevo Precio Máximo inválido en la fila " + (i + 1));
                        datosValidos = false;
                        break;
                    }
                    float nuevoPrecioMaximo = Float.parseFloat(nuevoPrecioMaximoStr);
                    float precioOriginal = Float.parseFloat((String) modeloSeleccion.getValueAt(i, 5));
                    float precioMaximoPermitido = precioOriginal * 1.03f;
                    if (nuevoPrecioMaximo > precioMaximoPermitido) {
                        JOptionPane.showMessageDialog(dialog, "El Nuevo Precio Máximo no puede exceder el 103% del Precio Original en la fila " + (i + 1));
                        datosValidos = false;
                        break;
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Nuevo Precio Máximo debe ser un número en la fila " + (i + 1));
                    datosValidos = false;
                    break;
                }

                // Validar "Fecha Límite" (columna 8)
                Date fechaLimite = (Date) modeloSeleccion.getValueAt(i, 8);
                if (fechaLimite == null) {
                    JOptionPane.showMessageDialog(dialog, "Debe seleccionar una Fecha Límite en la fila " + (i + 1));
                    datosValidos = false;
                    break;
                }
                if (fechaLimite.before(hoy)) {
                    JOptionPane.showMessageDialog(dialog, "La Fecha Límite no puede ser antes de hoy en la fila " + (i + 1));
                    datosValidos = false;
                    break;
                }
                Date fechaEvento = (Date) modeloSeleccion.getValueAt(i, 3);
                if (fechaLimite.after(fechaEvento)) {
                    JOptionPane.showMessageDialog(dialog, "La Fecha Límite no puede ser después de la Fecha del Evento en la fila " + (i + 1));
                    datosValidos = false;
                    break;
                }

                // Obtener el identificador (Número de Control) de la fila
                String numeroControl = (String) modeloSeleccion.getValueAt(i, 0);
                // Convertir el nuevo precio máximo a precio actual
                float precioActual = Float.parseFloat(nuevoPrecioMaximoStr);
                // Crear el DTO con estado Disponible; el orden es: numeroControl, codigoUsuario, estado, precioActual, fechaLimite
                ActualizarBoletoDTO dto = new ActualizarBoletoDTO(
                        numeroControl,
                        usuarioActualCodigo,
                        ActualizarBoletoDTO.Estado.Disponible,
                        precioActual,
                        fechaLimite
                );
                listaDTO.add(dto);
                // Actualizar individualmente (opcional, si deseas actualizar uno a uno antes de procesar la venta)
                this.controlVenderBoletos.actualizarBoletoVenta(dto);
            }

            if (datosValidos) {
                this.controlVenderBoletos.procesarVentaBoletos(manejadorConexiones, usuarioActualCodigo, listaDTO);
                JOptionPane.showMessageDialog(dialog, "¡Venta confirmada!");
                dialog.dispose();
                System.out.println("Boletos puestos a la venta: " + listaDTO);
                // Recargar la tabla principal para eliminar los boletos vendidos
                cargarBoletos();
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnConfirmarVenta);
        dialog.add(panelBoton, BorderLayout.SOUTH);
        cargarBoletos();
        dialog.setVisible(true);
    }

    // Método para obtener los índices de las filas seleccionadas en la tabla principal (columna "Seleccionar", índice 7)
    private void confirmarSeleccion() {
        boletosSeleccionados.clear();
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            if (Boolean.TRUE.equals(modeloTabla.getValueAt(i, 7))) {
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
