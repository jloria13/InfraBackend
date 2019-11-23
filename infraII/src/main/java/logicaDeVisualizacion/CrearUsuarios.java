package logicaDeVisualizacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class CrearUsuarios extends javax.swing.JFrame {

    public CrearUsuarios() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldusuario = new javax.swing.JTextField();
        jTextFieldcontraseña = new javax.swing.JTextField();
        jButtoncrearUsuario = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Creación de Usuarios");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(161, 20, 190, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Usuario: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Contraseña: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));
        jPanel1.add(jTextFieldusuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 200, -1));
        jPanel1.add(jTextFieldcontraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 200, -1));

        jButtoncrearUsuario.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtoncrearUsuario.setText("Crear Usuario");
        jButtoncrearUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtoncrearUsuarioMouseClicked(evt);
            }
        });
        jPanel1.add(jButtoncrearUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jButtonCancelar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jPanel1.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    //Validar campo requerido.
    public boolean validarCampoRequerido(String usuario, String contraseña){
        return usuario.equals("") || contraseña.equals("");
    }
    //Validar entre 7 y 12 caracteres.
    public boolean validarUsuarioCaracteres(String usuario){
        return usuario.length() >= 7 && usuario.length() <= 12;
    }
    //Validar entre 7 y 15 caracteres.
    public boolean validarContraseñaCaracteres(String contraseña){
        return contraseña.length() >= 7 && contraseña.length() <= 12;
    }
    
    //Validar usuario alfanumerico.
    public boolean validarUsuarioAlfaNumerico(String usuario){
        return Pattern.matches("[a-zA-Z_0-9]+", usuario);
    }
    //Validar ccontraseña alfanumerica y signos.
    public boolean validarContraseñaAlfaNumerico(String contraseña){    
        return Pattern.matches("[a-zA-Z_0-9]+", contraseña);
    }
    //Validar signos
    public boolean validarContraseñaCaracteresEspeciales(String contraseña){
        Pattern regex = Pattern.compile("[$&+,:;=?@#|]");
        Matcher matcher = regex.matcher(contraseña);    
        return matcher.find();
    }
    private void jButtoncrearUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtoncrearUsuarioMouseClicked
        
        String usuario = jTextFieldusuario.getText().toLowerCase();
        String contraseña = new String(jTextFieldcontraseña.getText()).toLowerCase();
        
        if(validarCampoRequerido(usuario, contraseña)){
            JOptionPane.showMessageDialog(this, "Debe escribir un nombre de usuario y una contraseña");
        }
        else if(!validarUsuarioCaracteres(usuario) || !validarContraseñaCaracteres(contraseña)){
            JOptionPane.showMessageDialog(this, "El usuario debe contener entre 7 y 12 caracteres"
                    + " y la contraseña entre 7 y 15 caracteres");
        }
        else if(!validarUsuarioAlfaNumerico(usuario)){
            JOptionPane.showMessageDialog(this, "Usuario debe ser alfanumérico");
        }
        else if(!validarContraseñaAlfaNumerico(contraseña)){
            JOptionPane.showMessageDialog(this, "Contraseña debe ser alfanumérica y "
                    + "contener signos como !, ?, #, @, $");           
        }
        else if(!validarContraseñaCaracteresEspeciales(contraseña)){
            JOptionPane.showMessageDialog(this, "Contraseña debe ser alfanuméricoa y "
                    + "contener signos como !, ?, #, @, $");        
        }
        else{
            JOptionPane.showMessageDialog(this, "USUARIO Y CONTRASEÑA CREADOS EXITOSAMENTE");
        }          
    }//GEN-LAST:event_jButtoncrearUsuarioMouseClicked


    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CrearUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrearUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrearUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrearUsuarios.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrearUsuarios().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtoncrearUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField jTextFieldcontraseña;
    private javax.swing.JTextField jTextFieldusuario;
    // End of variables declaration//GEN-END:variables
}
