/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Tablas;
import Conexion.Conexion;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import libs.Exportar;



/**
 *
 * @author pc personal
 */
public class VerMedicamentos extends javax.swing.JInternalFrame {

    /**
     * Creates new form VerMedicamentos
     */
    public VerMedicamentos() {
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
            return column != 0;
        }
    }
    
    public void mostrar() {
    
        Connection conn = Conexion.conectar();
        Statement st = null;
        
        String query = "SELECT * FROM medicamentos";
        
        Object[] columnNames = {"ID", "Nombre", "Cantidad", "Fecha Vencimiento"};
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
            System.out.println("Error al consultar los medicamentos" + e.getMessage());
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
        String Nombre = BuscarNomTxt1.getText();
        
        String query = "SELECT * FROM medicamentos WHERE descripcion LIKE ?";
        
        Object[] columnNames = {"ID", "Nombre", "Cantidad", "Fecha Vencimiento"};
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
        String query = "UPDATE medicamentos SET Descripcion = ?, Cantidad = ?, Fecha_vencimiento = ? WHERE ID_medicamento = ?";
                
        int fila = visor.getSelectedRow();
        
        String ID_medicamentox = this.visor.getValueAt(fila, 0).toString();
        String Nombre = this.visor.getValueAt(fila, 1).toString();
        String Cantidadx = this.visor.getValueAt(fila, 2).toString();
        String Fecha_vencimiento = this.visor.getValueAt(fila, 3).toString();
        
        int ID_medicamento = Integer.parseInt(ID_medicamentox);
        int Cantidad = Integer.parseInt(Cantidadx);
        
        try {
            
            conn = Conexion.conectar();
            pst = conn.prepareStatement(query);
            
            pst.setString(1,Nombre);
            pst.setInt(2, Cantidad);
            pst.setString(3, Fecha_vencimiento);
            pst.setInt(4, ID_medicamento);
            
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
        String query = "DELETE FROM medicamentos WHERE ID_medicamento = ?";
                
        int fila = visor.getSelectedRow();
        
        String Idx = this.visor.getValueAt(fila, 0).toString();
        int Id = Integer.parseInt(Idx);
        
        try {
            
            conn = Conexion.conectar();
            pst = conn.prepareStatement(query);
            
            pst.setInt(1,Id);
            
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

        BuscarNomTxt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        visor = new javax.swing.JTable();
        ActualizarBTN = new javax.swing.JLabel();
        EliminarBTN = new javax.swing.JLabel();
        RecargarBTN = new javax.swing.JLabel();
        EliminarBTN1 = new javax.swing.JLabel();
        BuscarNomTxt1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

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

        setClosable(true);
        setIconifiable(true);
        setTitle("Ver Medicamentos");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(472, 178, -1, -1));

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

        EliminarBTN1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/EliminarBTN.png"))); // NOI18N
        EliminarBTN1.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        getContentPane().add(EliminarBTN1, new org.netbeans.lib.awtextra.AbsoluteConstraints(894, 400, 150, -1));

        BuscarNomTxt1.setBackground(new java.awt.Color(247, 247, 247));
        BuscarNomTxt1.setBorder(null);
        BuscarNomTxt1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BuscarNomTxt1KeyTyped(evt);
            }
        });
        getContentPane().add(BuscarNomTxt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 40, 430, 30));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/ExportarBTN.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 210, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Tablas/Multimedia/NewVerMedicamentosBG.png"))); // NOI18N
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BuscarNomTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarNomTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BuscarNomTxtActionPerformed

    private void BuscarNomTxtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BuscarNomTxtKeyTyped
        if(evt.getKeyChar() == KeyEvent.VK_ENTER) {
            mostrarConsulta();
        }
    }//GEN-LAST:event_BuscarNomTxtKeyTyped

    private void ActualizarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ActualizarBTNMouseClicked
        ActualizarDatos();
        mostrar();
    }//GEN-LAST:event_ActualizarBTNMouseClicked

    private void RecargarBTNMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_RecargarBTNMouseClicked
        mostrar();
    }//GEN-LAST:event_RecargarBTNMouseClicked

    private void BuscarNomTxt1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BuscarNomTxt1KeyTyped
        if(evt.getKeyChar() == KeyEvent.VK_ENTER) {
            mostrarConsulta();
        }
    }//GEN-LAST:event_BuscarNomTxt1KeyTyped

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
    private javax.swing.JTextField BuscarNomTxt1;
    private javax.swing.JLabel EliminarBTN;
    private javax.swing.JLabel EliminarBTN1;
    private javax.swing.JLabel RecargarBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable visor;
    // End of variables declaration//GEN-END:variables
}
