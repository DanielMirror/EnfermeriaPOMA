/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Formularios;
import Conexion.*;
/**
 *
 * @author pc personal
 */
public class NuevoPadre extends javax.swing.JInternalFrame {

    /**
     * Creates new form NuevoPadre
     */
    public NuevoPadre() {
        initComponents();
    }
    
    private void InsertarRegistro() {
        
        Queries consulta = new Queries();
        String Cedula = CedulaTxt.getText();
        String Nombre1 = Nombre1Txt.getText();
        String Nombre2 = Nombre2Txt.getText();
        String Apellido1 = Apellido1Txt.getText();
        String Apellido2 = Apellido2Txt.getText();
        String Telefono = TelefonoTxt.getText();
        
        consulta.NuevoPadre(Cedula, Nombre1, Nombre2, Apellido1, Apellido2, Telefono);
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Nombre1Txt = new javax.swing.JTextField();
        Nombre2Txt = new javax.swing.JTextField();
        Apellido1Txt = new javax.swing.JTextField();
        Apellido2Txt = new javax.swing.JTextField();
        CedulaTxt = new javax.swing.JTextField();
        TelefonoTxt = new javax.swing.JTextField();
        MatriculaTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Nombre1Txt.setBackground(new java.awt.Color(31, 124, 199));
        Nombre1Txt.setForeground(new java.awt.Color(255, 255, 255));
        Nombre1Txt.setBorder(null);
        Nombre1Txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nombre1TxtActionPerformed(evt);
            }
        });
        getContentPane().add(Nombre1Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 300, 100, 30));

        Nombre2Txt.setBackground(new java.awt.Color(31, 124, 199));
        Nombre2Txt.setForeground(new java.awt.Color(255, 255, 255));
        Nombre2Txt.setBorder(null);
        Nombre2Txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Nombre2TxtActionPerformed(evt);
            }
        });
        getContentPane().add(Nombre2Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 300, 110, 30));

        Apellido1Txt.setBackground(new java.awt.Color(31, 124, 199));
        Apellido1Txt.setForeground(new java.awt.Color(255, 255, 255));
        Apellido1Txt.setBorder(null);
        Apellido1Txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Apellido1TxtActionPerformed(evt);
            }
        });
        getContentPane().add(Apellido1Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 380, 110, 30));

        Apellido2Txt.setBackground(new java.awt.Color(31, 124, 199));
        Apellido2Txt.setForeground(new java.awt.Color(255, 255, 255));
        Apellido2Txt.setBorder(null);
        Apellido2Txt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Apellido2TxtActionPerformed(evt);
            }
        });
        getContentPane().add(Apellido2Txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 380, 110, 30));

        CedulaTxt.setBackground(new java.awt.Color(31, 124, 199));
        CedulaTxt.setForeground(new java.awt.Color(255, 255, 255));
        CedulaTxt.setBorder(null);
        CedulaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CedulaTxtActionPerformed(evt);
            }
        });
        getContentPane().add(CedulaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 170, 150, 30));

        TelefonoTxt.setBackground(new java.awt.Color(31, 124, 199));
        TelefonoTxt.setForeground(new java.awt.Color(255, 255, 255));
        TelefonoTxt.setBorder(null);
        TelefonoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TelefonoTxtActionPerformed(evt);
            }
        });
        getContentPane().add(TelefonoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, 130, 20));

        MatriculaTxt.setBackground(new java.awt.Color(31, 124, 199));
        MatriculaTxt.setForeground(new java.awt.Color(255, 255, 255));
        MatriculaTxt.setBorder(null);
        MatriculaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MatriculaTxtActionPerformed(evt);
            }
        });
        getContentPane().add(MatriculaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 390, 130, 20));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/Multimedia/NuevoPadreBG.png"))); // NOI18N
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/Multimedia/NewRegistrarBTN.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 372, -1, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Nombre1TxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nombre1TxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nombre1TxtActionPerformed

    private void Nombre2TxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Nombre2TxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Nombre2TxtActionPerformed

    private void Apellido1TxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Apellido1TxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Apellido1TxtActionPerformed

    private void Apellido2TxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Apellido2TxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Apellido2TxtActionPerformed

    private void CedulaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CedulaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CedulaTxtActionPerformed

    private void TelefonoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TelefonoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TelefonoTxtActionPerformed

    private void MatriculaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MatriculaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MatriculaTxtActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        InsertarRegistro();
    }//GEN-LAST:event_jLabel3MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Apellido1Txt;
    private javax.swing.JTextField Apellido2Txt;
    private javax.swing.JTextField CedulaTxt;
    private javax.swing.JTextField MatriculaTxt;
    private javax.swing.JTextField Nombre1Txt;
    private javax.swing.JTextField Nombre2Txt;
    private javax.swing.JTextField TelefonoTxt;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
