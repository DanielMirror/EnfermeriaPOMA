/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Tablas;
import javax.swing.table.DefaultTableModel;
import Conexion.Conexion;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import java.io.IOException;
import libs.Exportar;


/**
 *
 * @author pc personal
 */
public class VerEstudiantes extends javax.swing.JInternalFrame {

    /**
     * Creates new form VerEstudiantes
     */
    public VerEstudiantes() {
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
            return column != 0 && column != 3 && column != 1;
        }
    }
    
    public void mostrar() {
    
        Connection conn = Conexion.conectar();
        Statement st = null;
        String query = "SELECT * FROM verEstudiantes";
        
        Object[] columnNames = {"Matricula", "Nombre", "Edad", "Curso"};
        DefaultTableModel model = new CustomTableModel(new Object[0][0], columnNames);
        
        visor.setModel(model);
        
        String[] datos = new String[4];
        
        try {
            
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            

            while(rs.next()) {
            
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                
                model.addRow(datos);
            
            } 
               
        } catch(SQLException e) {
            System.out.println("Error al consultar estudiantes" + e.getMessage());
        } finally {
            try {
                if(conn != null) {
                    conn.close();
                } if(st != null) {
                    st.close();
                }
            } catch(SQLException e) {
                System.out.println("Error al cerrar conexiones" + e.getMessage());
            }
        }
    
    }
    
    public void mostrarConsulta1() {
        Connection conn = Conexion.conectar();
        PreparedStatement pst = null;
        String Nombre = BuscarNomTxt.getText();
        
        String query = "SELECT * FROM verEstudiantes WHERE nombre LIKE ?";
        
        Object[] columnNames = {"Matricula", "Nombre", "Edad", "Curso"};
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
    
    public void mostrarConsulta2() {
        Connection conn = Conexion.conectar();
        PreparedStatement pst = null;
        String Nombre = BuscarNomTxt.getText();
        
        String query = "SELECT e.matricula,e.Nombre,Edad,Curso,p.Nombre FROM verEstudiantes AS e JOIN estudiantes_padres AS ep ON e.matricula = ep.matricula JOIN verPadres as p ON ep.CedulaPA = p.CedulaPA WHERE ep.CedulaPA LIKE ?";
        
        Object[] columnNames = {"Matricula", "Nombre", "Edad", "Curso", "Padre"};
        DefaultTableModel model = new CustomTableModel(new Object[0][0], columnNames);
        
        visor.setModel(model);
        
        String[] datos = new String[5];
        
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, '%'+Nombre+'%');            

            ResultSet rs = pst.executeQuery();
            
            while(rs.next()) {
            
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                
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
        String query = "UPDATE verEstudiantes SET edad = ? WHERE Matricula = ?";
                
        int fila = visor.getSelectedRow();
        
        String Matricula = this.visor.getValueAt(fila, 0).toString();
        String edadx = this.visor.getValueAt(fila, 2).toString();

        
        int edad = Integer.parseInt(edadx);

        
        try {
            
            conn = Conexion.conectar();
            pst = conn.prepareStatement(query);
            
            pst.setInt(1,edad);
            pst.setString(2, Matricula);
            
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
        String query = "DELETE FROM estudiantes WHERE matricula = ?";
                
        int fila = visor.getSelectedRow();
        
        String Matricula = this.visor.getValueAt(fila, 0).toString();
        
        try {
            
            conn = Conexion.conectar();
            pst = conn.prepareStatement(query);
            
            pst.setString(1, Matricula);
            
            int RA = pst.executeUpdate();
            
            if(RA > 0) {
                JOptionPane.showMessageDialog(null,"Se ha eliminado el registro");
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

        ActualizarBTN = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BuscarNomTxt = new javax.swing.JTextField();
        ActualizarBTN1 = new javax.swing.JLabel();
        EliminarBTN1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        TipoBusquedaCB = new javax.swing.JComboBox<>();
        EliminarBTN = new javax.swing.JLabel();
        RecargarBTN = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Ver Estudiantes");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ActualizarBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/ActualizarBTN.png"))); // NOI18N
        ActualizarBTN.setText("jLabel2");
        ActualizarBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        ActualizarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ActualizarBTNMouseClicked(evt);
            }
        });
        getContentPane().add(ActualizarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(897, 314, 142, -1));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 400, -1, -1));

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

        ActualizarBTN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/ActualizarBTN.png"))); // NOI18N
        ActualizarBTN1.setText("jLabel2");
        ActualizarBTN1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        ActualizarBTN1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ActualizarBTN1MouseClicked(evt);
            }
        });
        getContentPane().add(ActualizarBTN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(897, 314, 142, -1));

        EliminarBTN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/EliminarBTN.png"))); // NOI18N
        EliminarBTN1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        EliminarBTN1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                EliminarBTN1MouseClicked(evt);
            }
        });
        EliminarBTN1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EliminarBTN1KeyTyped(evt);
            }
        });
        getContentPane().add(EliminarBTN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(894, 400, 150, -1));

        visor.setBackground(new java.awt.Color(31, 124, 199));
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

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 14)); // NOI18N
        jLabel3.setText("Tipo de Búsqueda");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 180, -1, -1));

        TipoBusquedaCB.setBackground(new java.awt.Color(31, 124, 199));
        TipoBusquedaCB.setForeground(new java.awt.Color(255, 255, 255));
        TipoBusquedaCB.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Normal", "Por Padre" }));
        getContentPane().add(TipoBusquedaCB, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 200, 110, 20));

        EliminarBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/EliminarBTN.png"))); // NOI18N
        EliminarBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        getContentPane().add(EliminarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(894, 400, 150, -1));

        RecargarBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/NewRecargarBTN.png"))); // NOI18N
        RecargarBTN.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        RecargarBTN.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                RecargarBTNMouseClicked(evt);
            }
        });
        getContentPane().add(RecargarBTN, new org.netbeans.lib.awtextra.AbsoluteConstraints(894, 490, 150, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/ExportarBTN2.png"))); // NOI18N
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(887, 250, 50, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/NewVerEstudiantesBG.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ActualizarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualizarBTNMouseClicked
        ActualizarDatos();
    }//GEN-LAST:event_ActualizarBTNMouseClicked

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
                case "Por Padre":
                    mostrarConsulta2();
                    break;
                default:
                    throw new AssertionError();
            }
            
        }
    }//GEN-LAST:event_BuscarNomTxtKeyTyped

    private void ActualizarBTN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualizarBTN1MouseClicked
        ActualizarDatos();
        mostrar();
    }//GEN-LAST:event_ActualizarBTN1MouseClicked

    private void RecargarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RecargarBTNMouseClicked
        mostrar();
    }//GEN-LAST:event_RecargarBTNMouseClicked

    private void EliminarBTN1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EliminarBTN1KeyTyped

    }//GEN-LAST:event_EliminarBTN1KeyTyped

    private void EliminarBTN1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_EliminarBTN1MouseClicked
        EliminarDatos();
        mostrar();
    }//GEN-LAST:event_EliminarBTN1MouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
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
    }//GEN-LAST:event_jLabel4MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ActualizarBTN;
    private javax.swing.JLabel ActualizarBTN1;
    private javax.swing.JTextField BuscarNomTxt;
    private javax.swing.JLabel EliminarBTN;
    private javax.swing.JLabel EliminarBTN1;
    private javax.swing.JLabel RecargarBTN;
    private javax.swing.JComboBox<String> TipoBusquedaCB;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable visor;
    // End of variables declaration//GEN-END:variables
}
