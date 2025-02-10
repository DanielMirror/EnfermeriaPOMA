/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Formularios;
import Conexion.Queries;
import javax.swing.JOptionPane;

/**
 *
 * @author pc personal
 */
public class RegistrarEstudiantes extends javax.swing.JInternalFrame {

    /**
     * Creates new form RegistrarEstudiantes
     */
    public RegistrarEstudiantes() {
        initComponents();
    }
    
    public String ConseguirFecha() {
        
        String Fecha;
        String Dia = DiaTxt.getText();
        String MesX = (String) MesTxt.getSelectedItem();
        String Mes;
        String AñoXY = AñoTxt.getText();
        int AñoX = Integer.parseInt(AñoXY);
        String Año = null;
        
        switch (MesX) {
            case "Enero" -> Mes = "01";
            case "Febrero" -> Mes = "02";
            case "Marzo" -> Mes = "03";
            case "Abril" -> Mes = "04";
            case "Mayo" -> Mes = "05";
            case "Junio" -> Mes = "06";
            case "Julio" -> Mes = "07";
            case "Agosto" -> Mes = "08";
            case "Octubre" -> Mes = "09";
            case "Septiembre" -> Mes = "10";
            case "Noviembre" -> Mes = "11";
            case "Diciembre" -> Mes = "12";
            default -> throw new AssertionError();
        }
        
        boolean esMenorde1000 = (AñoX < 1000);
        if(esMenorde1000 || AñoX > 9999) {
            JOptionPane.showMessageDialog(rootPane, "Limite de caracteres en año alcanzado");
        } else {
            Año = String.valueOf(AñoX);
        }
        
        Fecha = Año+"-"+Mes+"-"+Dia;
        
        return Fecha;
        
    }
    
    public String ConseguirHoraEnt() {
        
        String HoraEntrada = null;
        String MinutosEntrada = null;
        
        String HoraEX = HoraETxt.getText();
        int HoraE = Integer.parseInt(HoraEX);
        
        boolean HoraEsMayorQue = (HoraE > 24);
        boolean HoraEsMenorQue = (HoraE < 1);
        
        if(HoraEsMayorQue || HoraEsMenorQue) {
        
            JOptionPane.showMessageDialog(rootPane, "Valor de Hora Excedido");
        
        } else {
        
            HoraEntrada = HoraEX;
            
        }
        
        String MinutosEX = MinutosETxt.getText();
        int MinutosE = Integer.parseInt(MinutosEX);
        
        boolean MinutosEsMayorQue = (MinutosE > 60);
        boolean MinutosEsMenorQue = (MinutosE < 1);
        
        if(MinutosEsMayorQue || MinutosEsMenorQue) {
            JOptionPane.showMessageDialog(rootPane, "Valor de Minutos no Admitido");
        } else {
            MinutosEntrada = MinutosEX;
        }
        
        String HoraMinutosEntrada = HoraEntrada+":"+MinutosEntrada+":00";
        
        return HoraMinutosEntrada;
    }
    
    public String ConseguirHoraSal() {
    
    String HoraSX = HoraSTxt.getText();
    String MinutosSX = MinutosSTxt.getText();
    String HoraSalida = null;
    String MinutosSalida = null;
   
    int HoraS = Integer.parseInt(HoraSX);
    int MinutosS = Integer.parseInt(MinutosSX);
    
    boolean HoraMayorQue = (HoraS > 24);
    boolean HoraMenorQue = (HoraS < 1);
    
    if(HoraMayorQue || HoraMenorQue) {
        JOptionPane.showMessageDialog(rootPane,"Valor de hora excedido");
    } else {
        HoraSalida = HoraSX;
    }
    
    boolean MinutosMayorQue = (MinutosS > 59);
    boolean MinutosMenorQue = (MinutosS < 1);
    
    if(MinutosMayorQue || MinutosMenorQue) {
        JOptionPane.showMessageDialog(rootPane,"Valor de Minutos no Admitido");
    } else {
        MinutosSalida = MinutosSX;
    }
    
    String HoraMinutosSalida = HoraSalida+":"+MinutosSalida+":"+"00";
    
    return HoraMinutosSalida;
    
    }
    
    public int ConseguirDiagnostico() {
    
    int Diagnostico;
    String DiagnosticoX = (String) DiagnosticoTxt.getSelectedItem();
    
        switch (DiagnosticoX) {
            case "Dolor de Cabeza":
                Diagnostico = 1161;
                break;
            case "Asma":
                Diagnostico = 1162;
                break;
            case "Alergia":
                Diagnostico = 1263;
                break;
            case "Esguince":
                Diagnostico = 1763;
                break;
            case "Dolor de Estomago":
                Diagnostico = 1773;
                break;
            default:
                throw new AssertionError();
        }
    return Diagnostico;
    }
    
    private void InsertarRegistro() {
    
        String Nombre = NombreTxt.getText();
        String Matricula;
        String Fecha;
        String HoraLlegada;
        String HoraSalida;
        String Cedula;
        int Diagnostico;
        Queries Consulta = new Queries();
        
        Fecha = ConseguirFecha();
        HoraLlegada = ConseguirHoraEnt();
        HoraSalida = ConseguirHoraSal();
        Cedula = Acceso.CedulaPE;
        Diagnostico = ConseguirDiagnostico();
        Matricula = Consulta.buscarMatricula(Nombre);
        
        Consulta.RegistrarEstudiante(Fecha, HoraLlegada, HoraSalida, Cedula, Diagnostico, Matricula);
    
    }
    
    
    

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        NombreTxt = new javax.swing.JTextField();
        DiaTxt = new javax.swing.JTextField();
        DiagnosticoTxt = new javax.swing.JComboBox<>();
        MesTxt = new javax.swing.JComboBox<>();
        AñoTxt = new javax.swing.JTextField();
        HoraETxt = new javax.swing.JTextField();
        MinutosETxt = new javax.swing.JTextField();
        MinutosSTxt = new javax.swing.JTextField();
        HoraSTxt = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setIconifiable(true);
        setTitle("Nuevo Registro Estudiante");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/Escritorio/Multimedia/RegistroICON.png"))); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        NombreTxt.setBackground(new java.awt.Color(31, 124, 199));
        NombreTxt.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        NombreTxt.setForeground(new java.awt.Color(255, 255, 255));
        NombreTxt.setBorder(null);
        NombreTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NombreTxtActionPerformed(evt);
            }
        });
        getContentPane().add(NombreTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 350, 20));

        DiaTxt.setBackground(new java.awt.Color(31, 124, 199));
        DiaTxt.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        DiaTxt.setForeground(new java.awt.Color(255, 255, 255));
        DiaTxt.setBorder(null);
        DiaTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DiaTxtActionPerformed(evt);
            }
        });
        getContentPane().add(DiaTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 300, 50, 20));

        DiagnosticoTxt.setBackground(new java.awt.Color(31, 124, 199));
        DiagnosticoTxt.setForeground(new java.awt.Color(255, 255, 255));
        DiagnosticoTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Dolor de Cabeza", "Asma", "Alergia", "Esguince", "Dolor de Estomago" }));
        getContentPane().add(DiagnosticoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, -1, -1));

        MesTxt.setBackground(new java.awt.Color(31, 124, 199));
        MesTxt.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        MesTxt.setForeground(new java.awt.Color(255, 255, 255));
        MesTxt.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        getContentPane().add(MesTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 70, -1));

        AñoTxt.setBackground(new java.awt.Color(31, 124, 199));
        AñoTxt.setForeground(new java.awt.Color(255, 255, 255));
        AñoTxt.setBorder(null);
        getContentPane().add(AñoTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 300, 60, 20));

        HoraETxt.setBackground(new java.awt.Color(31, 124, 199));
        HoraETxt.setForeground(new java.awt.Color(255, 255, 255));
        HoraETxt.setBorder(null);
        getContentPane().add(HoraETxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 300, 60, 20));

        MinutosETxt.setBackground(new java.awt.Color(31, 124, 199));
        MinutosETxt.setForeground(new java.awt.Color(255, 255, 255));
        MinutosETxt.setBorder(null);
        getContentPane().add(MinutosETxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 300, 60, 20));

        MinutosSTxt.setBackground(new java.awt.Color(31, 124, 199));
        MinutosSTxt.setForeground(new java.awt.Color(255, 255, 255));
        MinutosSTxt.setBorder(null);
        getContentPane().add(MinutosSTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 410, 50, 30));

        HoraSTxt.setBackground(new java.awt.Color(31, 124, 199));
        HoraSTxt.setForeground(new java.awt.Color(255, 255, 255));
        HoraSTxt.setBorder(null);
        getContentPane().add(HoraSTxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 410, 50, 30));
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 300, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/Multimedia/NewRegistrarBTN.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 372, -1, 60));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Formularios/Multimedia/NuevoRegistro.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void DiaTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DiaTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DiaTxtActionPerformed

    private void NombreTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NombreTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_NombreTxtActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        InsertarRegistro();
    }//GEN-LAST:event_jLabel3MouseClicked
    

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField AñoTxt;
    private javax.swing.JTextField DiaTxt;
    private javax.swing.JComboBox<String> DiagnosticoTxt;
    private javax.swing.JTextField HoraETxt;
    private javax.swing.JTextField HoraSTxt;
    private javax.swing.JComboBox<String> MesTxt;
    private javax.swing.JTextField MinutosETxt;
    private javax.swing.JTextField MinutosSTxt;
    private javax.swing.JTextField NombreTxt;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
