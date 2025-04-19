    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Tablas;
import Conexion.Conexion;
import com.itextpdf.text.DocumentException;
import java.awt.event.KeyEvent;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import libs.Exportar;



/**
 *
 * @author pc personal
 */
public class VerAsistentes extends javax.swing.JInternalFrame {

    /**
     * Creates new form VerMedicamentos
     */
    public VerAsistentes() {
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
        Statement st = null;
        
        String query = "SELECT * FROM verAsistentes";
        
        Object[] columnNames = {"Matricula", "Nombre", "Fecha del Turno", "Jornada"};
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
            System.out.println("Error al consultar los asistentes" + e.getMessage());
        } finally {
        
            try {
                if(conn != null) {
                    conn.close();
                } if(st != null) {
                    st.close();
                } 
            } catch(SQLException e) {
                System.out.println("Error al cerrar conexiones"+ e.getMessage());
                }
        }
    }
    
    public void mostrarConsulta() {
        Connection conn = Conexion.conectar();
        PreparedStatement pst = null;
        String Nombre = BuscarNomTxt.getText();
        
        String query = "SELECT * FROM verAsistentes WHERE Nombre LIKE ?";
        
        Object[] columnNames = {"Matricula", "Nombre", "Fecha del Turno"};
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
    
    public void ActualizarDatos() {
        
        Connection conn = null;
        PreparedStatement pst = null;
        String query = "UPDATE asistentes SET Fecha_turno = ? WHERE matricula = ?";
                
        int fila = visor.getSelectedRow();
        
        String Matricula = this.visor.getValueAt(fila, 0).toString();
        String Turno = this.visor.getValueAt(fila, 2).toString();
        
        try {
            
            conn = Conexion.conectar();
            pst = conn.prepareStatement(query);
            
            pst.setString(1,Turno);
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
        String query = "DELETE FROM asistentes WHERE matricula = ?";
                
        int fila = visor.getSelectedRow();
        
        String Matricula = this.visor.getValueAt(fila, 0).toString();
        
        try {
            
            conn = Conexion.conectar();
            pst = conn.prepareStatement(query);
            
            pst.setString(1, Matricula);
            
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        BuscarNomTxt = new javax.swing.JTextField();
        ActualizarBTN = new javax.swing.JLabel();
        EliminarBTN = new javax.swing.JLabel();
        RecargarBTN = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Ver Medicamentos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 205, -1, -1));

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
        visor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                visorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(visor);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 103, 845, 474));

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
        getContentPane().add(BuscarNomTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(372, 42, 435, 29));

        ActualizarBTN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/ActualizarBTN.png"))); // NOI18N
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
        EliminarBTN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                EliminarBTNKeyTyped(evt);
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

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/NewVerAsistentesBG.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarNomTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BuscarNomTxtKeyTyped
        if(evt.getKeyChar() == KeyEvent.VK_ENTER) {
            mostrarConsulta();
        }
    }//GEN-LAST:event_BuscarNomTxtKeyTyped

    private void visorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_visorMouseClicked

    }//GEN-LAST:event_visorMouseClicked

    private void ActualizarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualizarBTNMouseClicked
        ActualizarDatos();
        mostrar();
    }//GEN-LAST:event_ActualizarBTNMouseClicked

    private void BuscarNomTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarNomTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarNomTxtActionPerformed

    private void RecargarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RecargarBTNMouseClicked
        mostrar();
    }//GEN-LAST:event_RecargarBTNMouseClicked

    private void EliminarBTNKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_EliminarBTNKeyTyped
        
    }//GEN-LAST:event_EliminarBTNKeyTyped

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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable visor;
    // End of variables declaration//GEN-END:variables
}
