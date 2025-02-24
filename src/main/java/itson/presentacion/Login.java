package itson.presentacion;

import itson.control.ControlIniciarSesion;
import itson.entidades.Usuario;
import itson.usuariosDTOs.AccesoUsuarioDTO;
import itson.usuariosDTOs.SesionDTO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import observador.Observer;
import org.mindrot.jbcrypt.BCrypt;

public class Login extends javax.swing.JFrame{

    private final ControlIniciarSesion control;
    
    public Login(ControlIniciarSesion control) {
        initComponents();
        this.control = control;
      
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Left = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        campoCorreoElectronico = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        campoContrasenia = new javax.swing.JPasswordField();
        botonIngresar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        botonRegistrarme = new javax.swing.JButton();
        mensajeCorreo = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));
        jPanel1.setLayout(null);

        Left.setBackground(new java.awt.Color(255, 255, 255));
        Left.setMinimumSize(new java.awt.Dimension(400, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 102, 102));
        jLabel1.setText("INICIA SESIÓN ");

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Correo");

        campoCorreoElectronico.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        campoCorreoElectronico.setForeground(new java.awt.Color(102, 102, 102));
        campoCorreoElectronico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoCorreoElectronicoActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(102, 102, 102));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Contraseña");

        botonIngresar.setBackground(new java.awt.Color(0, 102, 102));
        botonIngresar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        botonIngresar.setForeground(new java.awt.Color(255, 255, 255));
        botonIngresar.setText("INGRESAR");
        botonIngresar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonIngresarMouseClicked(evt);
            }
        });
        botonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIngresarActionPerformed(evt);
            }
        });

        jLabel4.setText("No tengo una cuenta");

        botonRegistrarme.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        botonRegistrarme.setForeground(new java.awt.Color(255, 51, 51));
        botonRegistrarme.setText("Registrarme");
        botonRegistrarme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarmeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LeftLayout = new javax.swing.GroupLayout(Left);
        Left.setLayout(LeftLayout);
        LeftLayout.setHorizontalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2)
                        .addComponent(campoCorreoElectronico)
                        .addComponent(jLabel3)
                        .addComponent(campoContrasenia, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                        .addComponent(botonIngresar))
                    .addGroup(LeftLayout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(botonRegistrarme)))
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeftLayout.createSequentialGroup()
                .addContainerGap(81, Short.MAX_VALUE)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeftLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(61, 61, 61))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, LeftLayout.createSequentialGroup()
                        .addComponent(mensajeCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(178, 178, 178))))
        );
        LeftLayout.setVerticalGroup(
            LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LeftLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(jLabel1)
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoCorreoElectronico, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(mensajeCorreo)
                .addGap(1, 1, 1)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(campoContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(botonIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(LeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(botonRegistrarme))
                .addContainerGap(108, Short.MAX_VALUE))
        );

        jPanel1.add(Left);
        Left.setBounds(400, 0, 400, 515);

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));
        jPanel2.setForeground(new java.awt.Color(0, 102, 102));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(76, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(161, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 400, 500);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonRegistrarmeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarmeActionPerformed

        this.control.mostarFormularioRegistro();

    }//GEN-LAST:event_botonRegistrarmeActionPerformed

    private void botonIngresarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonIngresarMouseClicked

    }//GEN-LAST:event_botonIngresarMouseClicked

    private void campoCorreoElectronicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoCorreoElectronicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_campoCorreoElectronicoActionPerformed

    private void botonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIngresarActionPerformed
                                             
        String regexCorreo = "^[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*@[a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,5}$";
        String correo = this.campoCorreoElectronico.getText();

        char[] contraseniaConvertir = campoContrasenia.getPassword();
        String contraseniaPlana = new String(contraseniaConvertir);

        Pattern pattern = Pattern.compile(regexCorreo);
        Matcher matcher = pattern.matcher(correo);

        AccesoUsuarioDTO accesoDTO = new AccesoUsuarioDTO(correo, contraseniaPlana);
        Usuario usuario = control.autenticarUsuario(accesoDTO);

        if (usuario != null) {
            // Login exitoso
            SesionDTO.getInstancia().setUsuarioActual(usuario);
            System.out.println("Usuario en sesión: " + usuario); // Verificación
            this.dispose();
            control.mostrarMenu(); // <-- Ahora muestra el menú aquí
        } else {
            JOptionPane.showMessageDialog(this,
                    "Correo o contraseña incorrectos",
                    "Error de autenticación",
                    JOptionPane.ERROR_MESSAGE);
        }
        // Limpiar la contraseña del array por seguridad
        java.util.Arrays.fill(contraseniaConvertir, '0');


    }//GEN-LAST:event_botonIngresarActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Left;
    private javax.swing.JButton botonIngresar;
    private javax.swing.JButton botonRegistrarme;
    private javax.swing.JPasswordField campoContrasenia;
    private javax.swing.JTextField campoCorreoElectronico;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel mensajeCorreo;
    // End of variables declaration//GEN-END:variables
}
