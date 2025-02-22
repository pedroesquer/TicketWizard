/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package itson.presentacion;

import itson.control.ControlActualizarBoleto;
import itson.control.ControlIniciarSesion;
import itson.entidades.Usuario;
import itson.persistencia.BoletosDAO;
import itson.persistencia.ManejadorConexiones;
import itson.persistencia.UsuariosDAO;
import itson.usuariosDTOs.SesionDTO;

/**
 *
 * @author rauln
 */
public class Menu extends javax.swing.JFrame {

    /**
     * Creates new form Menu
     */
    ManejadorConexiones manejadorConexiones = new ManejadorConexiones();
    UsuariosDAO usuariosDAO = new UsuariosDAO(manejadorConexiones);
    BoletosDAO boletosDAO = new BoletosDAO(manejadorConexiones);

    ControlIniciarSesion controlInicio = new ControlIniciarSesion(usuariosDAO);
    ControlActualizarBoleto controlActualizar = new ControlActualizarBoleto(boletosDAO);

    public Menu() {
        initComponents();
        mostrarDatosUsuario();

    }

    // Para obtener datos del usuario actual
    private void mostrarDatosUsuario() {
        Usuario usuarioActual = SesionDTO.getInstancia().getUsuarioActual();
        if (usuarioActual != null) {
            usuarioLabel.setText(usuarioActual.getNombre() + "!");
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

        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        venderBoletosLabel = new javax.swing.JLabel();
        misBoletosLabel = new javax.swing.JLabel();
        cerrarSesionLabel = new javax.swing.JLabel();
        boletosApartadosLabel = new javax.swing.JLabel();
        transaccionesLabel = new javax.swing.JLabel();
        comprarBoletosLabel = new javax.swing.JLabel();
        usuarioLabel = new javax.swing.JLabel();
        saludoLabel = new javax.swing.JLabel();

        jLabel5.setBackground(new java.awt.Color(0, 102, 102));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("SIGN UP");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 500));
        setName("menuFrame"); // NOI18N

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setBackground(new java.awt.Color(0, 102, 102));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 102, 102));
        jLabel6.setText("MENU");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        venderBoletosLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VenderBoletos.png"))); // NOI18N
        venderBoletosLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                venderBoletosLabelMouseClicked(evt);
            }
        });

        misBoletosLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/MisBoletos.png"))); // NOI18N
        misBoletosLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                misBoletosLabelMouseClicked(evt);
            }
        });

        cerrarSesionLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/CerrarSesion.png"))); // NOI18N
        cerrarSesionLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarSesionLabelMouseClicked(evt);
            }
        });

        boletosApartadosLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/BoletosApartados.png"))); // NOI18N
        boletosApartadosLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boletosApartadosLabelMouseClicked(evt);
            }
        });

        transaccionesLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/HistorialTransacciones.png"))); // NOI18N
        transaccionesLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                transaccionesLabelMouseClicked(evt);
            }
        });

        comprarBoletosLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ComprarBoletos.png"))); // NOI18N
        comprarBoletosLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                comprarBoletosLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(cerrarSesionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(boletosApartadosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(misBoletosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(comprarBoletosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(transaccionesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(venderBoletosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(venderBoletosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(misBoletosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comprarBoletosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(boletosApartadosLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cerrarSesionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(transaccionesLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        usuarioLabel.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        usuarioLabel.setForeground(new java.awt.Color(0, 102, 102));

        saludoLabel.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        saludoLabel.setText("Bienvenido,");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(89, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(162, 162, 162)
                .addComponent(saludoLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usuarioLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(usuarioLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saludoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comprarBoletosLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_comprarBoletosLabelMouseClicked
        // TODO add your handling code here:
        ComprarBoletos comprarBoletos = new ComprarBoletos(controlActualizar);
        comprarBoletos.setVisible(true);
        comprarBoletos.pack();
        comprarBoletos.setLocationRelativeTo(null);
        //this.setVisible(false);

    }//GEN-LAST:event_comprarBoletosLabelMouseClicked

    private void misBoletosLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_misBoletosLabelMouseClicked
        // TODO add your handling code here:
        MisBoletos misBoletos = new MisBoletos(this.controlInicio, this.controlActualizar, this);
        misBoletos.setVisible(true);
        misBoletos.pack();
        misBoletos.setLocationRelativeTo(null);
        //this.setVisible(false);
    }//GEN-LAST:event_misBoletosLabelMouseClicked

    private void venderBoletosLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_venderBoletosLabelMouseClicked
        // TODO add your handling code here:
        VenderBoletos venderBoletos = new VenderBoletos(this.controlInicio, this.controlActualizar, this);
        venderBoletos.setVisible(true);
        venderBoletos.pack();
        venderBoletos.setLocationRelativeTo(null);
        //this.setVisible(false);
    }//GEN-LAST:event_venderBoletosLabelMouseClicked

    private void cerrarSesionLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarSesionLabelMouseClicked
        // TODO add your handling code here:
        Login login = new Login(this.controlInicio);
        login.setVisible(true);
        login.pack();
        login.setLocationRelativeTo(null);
        this.dispose();
        //this.setVisible(false);
    }//GEN-LAST:event_cerrarSesionLabelMouseClicked

    private void boletosApartadosLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_boletosApartadosLabelMouseClicked
        // TODO add your handling code here:
        BoletosApartados boletosApartados = new BoletosApartados(this.controlInicio, this.controlActualizar, this);
        boletosApartados.setVisible(true);
        boletosApartados.pack();
        boletosApartados.setLocationRelativeTo(null);
        //this.setVisible(false);
    }//GEN-LAST:event_boletosApartadosLabelMouseClicked

    private void transaccionesLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_transaccionesLabelMouseClicked
        // TODO add your handling code here:
        HistorialTransacciones historialTransacciones = new HistorialTransacciones(this.controlInicio, this.controlActualizar, this);
        historialTransacciones.setVisible(true);
        historialTransacciones.pack();
        historialTransacciones.setLocationRelativeTo(null);
        //this.setVisible(false);
    }//GEN-LAST:event_transaccionesLabelMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel boletosApartadosLabel;
    private javax.swing.JLabel cerrarSesionLabel;
    private javax.swing.JLabel comprarBoletosLabel;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel misBoletosLabel;
    private javax.swing.JLabel saludoLabel;
    private javax.swing.JLabel transaccionesLabel;
    private javax.swing.JLabel usuarioLabel;
    private javax.swing.JLabel venderBoletosLabel;
    // End of variables declaration//GEN-END:variables
}
