package logicaDeVisualizacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import logic.Cliente;

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
        usuario = new javax.swing.JTextField();
        contraseña = new javax.swing.JTextField();
        jButtoncrearUsuario = new javax.swing.JButton();
        jButtonCancelar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        atras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setText("Creación de Usuarios");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 200, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Usuario: ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 80, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Contraseña: ");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));
        jPanel1.add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 80, 200, -1));
        jPanel1.add(contraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 200, -1));

        jButtoncrearUsuario.setBackground(new java.awt.Color(255, 255, 255));
        jButtoncrearUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtoncrearUsuario.setForeground(new java.awt.Color(0, 102, 102));
        jButtoncrearUsuario.setText("Crear Usuario");
        jButtoncrearUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtoncrearUsuarioMouseClicked(evt);
            }
        });
        jPanel1.add(jButtoncrearUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, -1, -1));

        jButtonCancelar.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonCancelar.setForeground(new java.awt.Color(0, 102, 102));
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonCancelarMouseClicked(evt);
            }
        });
        jPanel1.add(jButtonCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 220, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Al digitar un usuario, las letras deben ir en minúscula.");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 340, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("La contraseña debe contener caractéres alfanuméricos y");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 300, 320, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("signos especiales como ! # $ ? @.");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 280, -1));

        atras.setBackground(new java.awt.Color(255, 255, 255));
        atras.setForeground(new java.awt.Color(0, 102, 102));
        atras.setText("atrás");
        atras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                atrasMouseClicked(evt);
            }
        });
        jPanel1.add(atras, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 310, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //---------------------------VALIDACIONES---------------------------//
    
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
        return Pattern.matches("[a-z_0-9]+", usuario);
    }
    //Validar caractéres especiales y alfanuméricos.
    public boolean validarContraseña(String contraseña){
        return Pattern.matches("([a-zA-Z]+[0-9]+)\\w+[?$#@!]+", contraseña);
    }
    
    private void jButtoncrearUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtoncrearUsuarioMouseClicked
        
        Cliente cli = new Cliente();
        ArrayList<String> array = new ArrayList<>();
        
        String user = usuario.getText();
        String password = contraseña.getText();
        
        array.add(user);
        array.add(password);
        
        System.out.println("ELEMENTO SUB0 " + array.get(0));
        System.out.println("ELEMENTO SUB1 " + array.get(1));
        
        //Validar campos vacíos.
        if(validarCampoRequerido(user, password)){
            JOptionPane.showMessageDialog(this, "Debe escribir un nombre de usuario y una contraseña");
        }
        //Validar cantidad caracteres.
        else if(!validarUsuarioCaracteres(user) || !validarContraseñaCaracteres(password)){
            JOptionPane.showMessageDialog(this, "El usuario debe contener entre 7 y 12 caracteres"
                    + " y la contraseña entre 7 y 15 caracteres");
        }
        //Validar usuario alfanumérico.
        else if(!validarUsuarioAlfaNumerico(user)){
            JOptionPane.showMessageDialog(this, "Usuario debe ser alfanumérico y"
                    + "las letras deben estar en minúscula.");
        }
        //Validar caracteres especiales y alfanumericos de contraseña.
        else if(!validarContraseña(password)){
            JOptionPane.showMessageDialog(this, "Contraseña debe tener caracteres alfanuméricos"
                    + "y algunos de los siguientes caracteres especiales:  ! ? $ # @.");        
        }
        else{
            try {
                cli.insertUser(array);
            } catch (IOException ex) {
                Logger.getLogger(CrearUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InterruptedException ex) {
                Logger.getLogger(CrearUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CrearUsuarios.class.getName()).log(Level.SEVERE, null, ex);
            }
            JOptionPane.showMessageDialog(this, "USUARIO Y CONTRASEÑA CREADOS EXITOSAMENTE");
        }          
    }//GEN-LAST:event_jButtoncrearUsuarioMouseClicked

    private void jButtonCancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonCancelarMouseClicked
        // TODO add your handling code here:
        usuario.setText(" ");
        contraseña.setText(" ");      
    }//GEN-LAST:event_jButtonCancelarMouseClicked

    private void atrasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_atrasMouseClicked
        // TODO add your handling code here:
        VistaAdmi va = new VistaAdmi();
        va.setVisible(true);
        dispose();        
    }//GEN-LAST:event_atrasMouseClicked


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
    private javax.swing.JButton atras;
    private javax.swing.JTextField contraseña;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtoncrearUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField usuario;
    // End of variables declaration//GEN-END:variables
}
