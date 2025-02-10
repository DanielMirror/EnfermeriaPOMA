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
public class VerPadres extends javax.swing.JInternalFrame {

    /**
     * Creates new form VerRegistrosEst
     */
    public VerPadres() {
        initComponents();
        mostrar();
    }
    
    private class CustomTableModel extends DefaultTableModel {
        public CustomTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }
        
        @Override
        public boolean isCellEditable(int row, int column) {
            // Define las columnas que deseas bloquear
            // Por ejemplo, si quieres bloquear la columna 0 (Matricula) y la columna 2 (Fecha del Turno):
            return column != 0 && column != 1;
        }
    }
    
    public void mostrar() {
        Connection conn = Conexion.conectar();
        Statement stm = null;
        
        String query = "SELECT * FROM verPadres";
        
        Object[] columnNames = {"Cedula", "Nombre", "Teléfono"};
        DefaultTableModel model = new CustomTableModel(new Object[0][0], columnNames);

        
        visor.setModel(model);
        
        String[] datos = new String[3];
        
        try{
        
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            
            while(rs.next()) {
            
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                model.addRow(datos);
            
            }
        
        }catch(SQLException e) {
            System.out.println("Error al recopilar registros: "+ e.getMessage());
        } finally {
        
            try {
                if(stm != null) {
                    stm.close();
                } if(conn != null) {
                    conn.close();
                }
            } catch(SQLException e) {
                System.out.println("Error al cerrar conexiones: "+ e.getMessage());
            }
        
        }
    }
    
    public void mostrarConsulta1() {
        Connection conn = Conexion.conectar();
        PreparedStatement pst = null;
        String Nombre = BuscarNomTxt.getText();
        
        String query = "SELECT * FROM verPadres WHERE nombre LIKE ?";
        
        Object[] columnNames = {"Cedula", "Nombre", "Teléfono"};
        DefaultTableModel model = new CustomTableModel(new Object[0][0], columnNames);

        
        visor.setModel(model);
        
        String[] datos = new String[3];
        

        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, '%'+Nombre+'%');            

            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
            
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                
                model.addRow(datos);
            
            }
        } catch(SQLException e) {
                        System.out.println("Error al realizar busqueda " + e.getMessage());
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
    
    public void mostrarConsulta2() {
        Connection conn = Conexion.conectar();
        PreparedStatement pst = null;
        String Nombre = BuscarNomTxt.getText();
        
        String query = "SELECT p.CedulaPA,p.Nombre,p.Numero_telef,e.Nombre FROM verPadres AS p JOIN estudiantes_padres AS ep ON p.CedulaPA = ep.CedulaPA JOIN verEstudiantes as e ON ep.Matricula = e.Matricula WHERE ep.Matricula LIKE ?";
        
        Object[] columnNames = {"Cedula", "Nombre", "Teléfono", "Hijo"};
        DefaultTableModel model = new CustomTableModel(new Object[0][0], columnNames);
        
        visor.setModel(model);
        
        String[] datos = new String[4];
        
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, '%'+Nombre+'%');            

            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
            
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);

                
                model.addRow(datos);
            
            } 
        } catch(SQLException e) {
                        System.out.println("Error al realizar busqueda " + e.getMessage());
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
    
    public void ActualizarDatos() {
        
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "UPDATE verPadres SET Numero_telef = ? WHERE cedulaPA = ?";
                
        int fila = visor.getSelectedRow();
        
        String Cedula = this.visor.getValueAt(fila, 0).toString();
        String Telefono = this.visor.getValueAt(fila, 2).toString();
        
        try {
            
            conn = Conexion.conectar();
            pst = conn.prepareStatement(query);
            
            pst.setString(1,Telefono);
            pst.setString(2, Cedula);
            
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
        String query = "DELETE FROM verPadres WHERE cedulaPA = ?";
                
        int fila = visor.getSelectedRow();
        
        String Cedula = this.visor.getValueAt(fila, 0).toString();
        
        try {
            
            conn = Conexion.conectar();
            pst = conn.prepareStatement(query);
            
            pst.setString(1, Cedula);
            
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
        RecargarBTN = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        TipoBusquedaCB = new javax.swing.JComboBox<>();
        EliminarBTN = new javax.swing.JLabel();
        RecargarBTN1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setTitle("Registro Padres");
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
        jScrollPane1.setViewportView(visor);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 850, 480));

        BuscarNomTxt.setBackground(new java.awt.Color(247, 247, 247));
        BuscarNomTxt.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        BuscarNomTxt.setBorder(null);
        BuscarNomTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarNomTxtActionPerformed(evt);
            }
        });
        BuscarNomTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscarNomTxtKeyTyped(evt);
            }
        });
        getContentPane().add(BuscarNomTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 42, 430, 29));

        ActualizarBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/ActualizarBTN.png"))); // NOI18N
        ActualizarBTN.setText("jLabel2");
        ActualizarBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        ActualizarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ActualizarBTNMouseClicked(evt);
            }
        });
        getContentPane().add(ActualizarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(897, 314, 142, -1));

        RecargarBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/NewRecargarBTN.png"))); // NOI18N
        RecargarBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        RecargarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RecargarBTNMouseClicked(evt);
            }
        });
        getContentPane().add(RecargarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(894, 490, 150, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel3.setText("Tipo de Búsqueda");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 180, -1, -1));

        TipoBusquedaCB.setBackground(new java.awt.Color(31, 124, 199));
        TipoBusquedaCB.setForeground(new java.awt.Color(255, 255, 255));
        TipoBusquedaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Por Hijo" }));
        getContentPane().add(TipoBusquedaCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 200, 110, 20));

        EliminarBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/EliminarBTN.png"))); // NOI18N
        EliminarBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        EliminarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarBTNMouseClicked(evt);
            }
        });
        getContentPane().add(EliminarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(894, 400, 150, -1));

        RecargarBTN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/NewRecargarBTN.png"))); // NOI18N
        RecargarBTN1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        RecargarBTN1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RecargarBTN1MouseClicked(evt);
            }
        });
        getContentPane().add(RecargarBTN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(894, 490, 150, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/ExportarBTN2.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(887, 250, 50, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/NewVerPadresBG.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarNomTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarNomTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarNomTxtActionPerformed

    private void BuscarNomTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BuscarNomTxtKeyTyped
        if(evt.getKeyChar() == KeyEvent.VK_ENTER) {
            String opcion = (String) TipoBusquedaCB.getSelectedItem();
            
            switch (opcion) {
                case "Normal":
                    mostrarConsulta1();
                    break;
                case "Por Hijo":
                    mostrarConsulta2();
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }//GEN-LAST:event_BuscarNomTxtKeyTyped

    private void ActualizarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualizarBTNMouseClicked
        ActualizarDatos();
        mostrar();
    }//GEN-LAST:event_ActualizarBTNMouseClicked

    private void RecargarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RecargarBTNMouseClicked
        mostrar();
    }//GEN-LAST:event_RecargarBTNMouseClicked

    private void RecargarBTN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RecargarBTN1MouseClicked
        mostrar();
    }//GEN-LAST:event_RecargarBTN1MouseClicked

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActualizarBTN;
    private javax.swing.JTextField BuscarNomTxt;
    private javax.swing.JLabel EliminarBTN;
    private javax.swing.JLabel RecargarBTN;
    private javax.swing.JLabel RecargarBTN1;
    private javax.swing.JComboBox<String> TipoBusquedaCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable visor;
    // End of variables declaration//GEN-END:variables
}
