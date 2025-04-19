/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Tablas;

import java.sql.Connection;
import Conexion.Conexion;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import libs.Exportar;



/**
 *
 * @author pc personal
 */
public class VerRegistrosEst extends javax.swing.JInternalFrame {

    /**
     * Creates new form VerRegistrosEst
     */
    public VerRegistrosEst() {
        initComponents();
        mostrar();
    }
    
    private class CustomTableModel extends DefaultTableModel {
        public CustomTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }
        
        @Override
        public boolean isCellEditable(int row, int column) {
           
            return column != 0 && column != 4 && column != 5 && column != 6 && column != 7 && column != 8 && column != 9 && column != 10;
        }
    }
    
    public void mostrar() {
    Connection conn = Conexion.conectar();
    Statement stm = null;

    String query = "SELECT * FROM verRegistroEstudiante";

  
    Object[] columnNames = {
        "Matricula", "Fecha", "Llegada", "Salida", "Nombre",
        "Edad", "Curso", "Diagnostico", "Medicamento",
        "Cantidad", "Personal", "Despachado" 
    };

    DefaultTableModel model = new CustomTableModel(new Object[0][0], columnNames);
    visor.setModel(model);

    String[] datos = new String[12]; 

    try {
        stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(query);

        while (rs.next()) {
            for (int i = 0; i < 11; i++) {
                datos[i] = rs.getString(i + 1);
            }
            
            
            String despachado = rs.getString(12); 
            if ("1".equals(despachado)) {
                datos[11] = "Despachado";
            } else {
                datos[11] = "No Despachado";
            }
            
            model.addRow(datos);
        }

    } catch (SQLException e) {
        System.out.println("Error al recopilar registros: " + e.getMessage());
    } finally {
        try {
            if (stm != null) stm.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexiones: " + e.getMessage());
        }
    }
}


    
   

    
    
    
    
    
    public void mostrarConsulta() {
    Connection conn = Conexion.conectar();
    PreparedStatement pst = null;
    String Nombre = BuscarNomTxt.getText();
    boolean soloDespachados = checkBoxDespachados.isSelected(); 

    
    String query = "SELECT * FROM verRegistroEstudiante WHERE nombreE LIKE ?";
    
    if (soloDespachados) {
        query += " AND Despachado = 1";  
    }

    Object[] columnNames = {"Matricula", "Fecha", "Llegada", "Salida", "Nombre", "Edad", "Curso", "Diagnostico", "Medicamento", "Cantidad", "Personal", "Estado"};
    DefaultTableModel model = new CustomTableModel(new Object[0][0], columnNames);

    visor.setModel(model);

    String[] datos = new String[12];  

    try {
        pst = conn.prepareStatement(query);
        pst.setString(1, '%' + Nombre + '%'); 

        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
           
            for (int i = 0; i < 11; i++) {
                datos[i] = rs.getString(i + 1);
            }

            
            int despachado = rs.getInt("Despachado");
            datos[11] = (despachado == 1) ? "Despachado" : "No Despachado";

            model.addRow(datos);
        }
    } catch (SQLException e) {
        System.out.println("Error al realizar búsqueda: " + e.getMessage());
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexiones: " + e.getMessage());
        }
    }
}

    
    public void ActualizarDatos() {
        
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "UPDATE registro_est SET Fecha_visita = ?, Hora_Llegada = ?, Hora_salida = ? WHERE Matricula = ?";
                
        int fila = visor.getSelectedRow();
        
        String Matricula = this.visor.getValueAt(fila, 0).toString();
        String Fecha = this.visor.getValueAt(fila, 1).toString();
        String Llegada = this.visor.getValueAt(fila, 2).toString();
        String Salida = this.visor.getValueAt(fila, 3).toString();
        
        try {
            
            conn = Conexion.conectar();
            pst = conn.prepareStatement(query);
            
            pst.setString(1,Fecha);
            pst.setString(2, Llegada);
            pst.setString(3, Salida);
            pst.setString(4, Matricula);
            
            int RA = pst.executeUpdate();
            
            if(RA > 0) {
                JOptionPane.showMessageDialog(null,"Se ha actualizado el registro");
            } else {
                JOptionPane.showMessageDialog(null,"No se ha podido actualizar");
            }
        
        } catch(SQLException e) {
            System.out.println("Error de UPDATE: "+ e.getMessage());
        } finally{
            try{
                if(conn != null) {
                    conn.close();
                } if(pst != null) {
                    pst.close();
                }
            } catch(SQLException e) {
                System.out.println("Error al cerrar conexiones" + e.getMessage());
            }
        }
    }
    
    public void EliminarDatos() {
        
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "DELETE FROM registro_est WHERE matricula = ? AND Fecha_visita = ? AND Hora_Llegada = ?";
                
        int fila = visor.getSelectedRow();
        
        String Matricula = this.visor.getValueAt(fila, 0).toString();
        String Fecha = this.visor.getValueAt(fila, 1).toString();
        String Llegada = this.visor.getValueAt(fila, 2).toString();
        
        try {
            
            conn = Conexion.conectar();
            pst = conn.prepareStatement(query);
            
            pst.setString(1, Matricula);
            pst.setString(2, Fecha);
            pst.setString(3, Llegada);
            
            int RA = pst.executeUpdate();
            
            if(RA > 0) {
                JOptionPane.showMessageDialog(null,"Se ha eliminado");
            } else {
                JOptionPane.showMessageDialog(null,"No se ha podido eliminar");
            }
        
        } catch(SQLException e) {
            System.out.println("Error de DELETE: "+ e.getMessage());
        } finally{
            try{
                if(conn != null) {
                    conn.close();
                } if(pst != null) {
                    pst.close();
                }
            } catch(SQLException e) {
                System.out.println("Error al cerrar conexiones" + e.getMessage());
            }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        BuscarNomTxt = new javax.swing.JTextField();
        ActualizarBTN = new javax.swing.JLabel();
        EliminarBTN = new javax.swing.JLabel();
        RecargarBTN = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        checkBoxDespachados = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Registro Estudiantes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        visor.setBackground(new java.awt.Color(31, 124, 199));
        visor.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        visor.setForeground(new java.awt.Color(255, 255, 255));
        visor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        visor.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        jScrollPane1.setViewportView(visor);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 850, 480));

        BuscarNomTxt.setBackground(new java.awt.Color(247, 247, 247));
        BuscarNomTxt.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        BuscarNomTxt.setBorder(null);
        BuscarNomTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscarNomTxtKeyTyped(evt);
            }
        });
        getContentPane().add(BuscarNomTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 430, 30));

        ActualizarBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/ActualizarBTN.png"))); // NOI18N
        ActualizarBTN.setText("jLabel2");
        ActualizarBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        ActualizarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ActualizarBTNMouseClicked(evt);
            }
        });
        getContentPane().add(ActualizarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(897, 314, 142, -1));

        EliminarBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/EliminarBTN.png"))); // NOI18N
        EliminarBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        EliminarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarBTNMouseClicked(evt);
            }
        });
        getContentPane().add(EliminarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(894, 400, 150, -1));

        RecargarBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/NewRecargarBTN.png"))); // NOI18N
        RecargarBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        RecargarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RecargarBTNMouseClicked(evt);
            }
        });
        getContentPane().add(RecargarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(894, 490, 150, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/ExportarBTN.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, -1, -1));

        checkBoxDespachados.setText("Ver despachados");
        checkBoxDespachados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxDespachadosActionPerformed(evt);
            }
        });
        getContentPane().add(checkBoxDespachados, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 180, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/EstudiantesConsultadosBG.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ActualizarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualizarBTNMouseClicked
        ActualizarDatos();
        mostrar();
    }//GEN-LAST:event_ActualizarBTNMouseClicked

    private void BuscarNomTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BuscarNomTxtKeyTyped
        if(evt.getKeyChar() == KeyEvent.VK_ENTER) {
            mostrarConsulta();
        }
    }//GEN-LAST:event_BuscarNomTxtKeyTyped

    private void RecargarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RecargarBTNMouseClicked
        mostrar();
    }//GEN-LAST:event_RecargarBTNMouseClicked

    private void EliminarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarBTNMouseClicked
        EliminarDatos();
        mostrar();
    }//GEN-LAST:event_EliminarBTNMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        String[] opciones = {"PDF", "Excel"};
       int seleccion = JOptionPane.showOptionDialog(null, "Elige una opción:", "Selección", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);

        switch (seleccion) {
            case 0:
                System.out.println("Se ha impreso PDF");
                Exportar.PDF(visor);
                
                break;

            case 1:
   
                try {
                    Exportar.Excel(visor);
                } catch (IOException ex) {
                    System.out.println("Error: " + ex);
                }       break;
            default:
                System.out.println("Error al Imprimir");
                break;
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void checkBoxDespachadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxDespachadosActionPerformed
        // TODO add your handling code here:
        mostrarConsulta();
    }//GEN-LAST:event_checkBoxDespachadosActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActualizarBTN;
    private javax.swing.JTextField BuscarNomTxt;
    private javax.swing.JLabel EliminarBTN;
    private javax.swing.JLabel RecargarBTN;
    private javax.swing.JCheckBox checkBoxDespachados;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable visor;
    // End of variables declaration//GEN-END:variables
}
