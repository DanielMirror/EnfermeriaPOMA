/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;



/**
 *
 * @author pc personal
 */
public class Queries {
    
    public boolean validarUsuario(String usuario,String clave) {
    
        Connection conn = Conexion.conectar();
        boolean esValido = false;
        
        try {
        
        String Consulta = "SELECT * FROM acceso WHERE usuario = ? AND clave = ?";
        
        PreparedStatement pst = conn.prepareStatement(Consulta);
        pst.setString(1, usuario);
        pst.setString(2, clave);
        
        ResultSet rs = pst.executeQuery();
        esValido = rs.next();
            
        } catch (SQLException e) {
        
            System.out.println("Error SQL" + e.getMessage());
            
        }  finally {
        
            try {
            
                if (conn != null) {
                
                    conn.close();
                }
                
            } catch (SQLException e) {
                        
                System.out.println("Error al cerrar la conexion "+ e.getMessage());            
            }
        
        }    
    return esValido;
    }
    
    public String CedulaPE(String usuario,String clave) {
    
    Connection conn2 = Conexion.conectar();
    String CedulaPE = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    try {
    
        String consulta = "SELECT CedulaPE FROM acceso WHERE usuario = ? AND clave = ?";
        pst = conn2.prepareStatement(consulta);
        pst.setString(1,usuario);
        pst.setString(2,clave);
        
        rs = pst.executeQuery();
        
        if(rs.next()) {
        
            CedulaPE = rs.getString("CedulaPE");
            
        } else {
        
            System.out.println("Fallo en conseguir info del personal");
            
        }
        
    } catch(SQLException e) {
    
            System.out.println("Error SQL: " +e.getMessage());
        
        } finally {
        
            try {
            
                if(conn2 != null) {
                    conn2.close();
                }
                
                if(rs != null) {
                    rs.close();
                }
                
                if(pst != null)
                    pst.close();
                
            } catch(SQLException e) {
                System.out.println("Error cerrando conexiones: "+ e.getMessage());
            }
        
        }
    
    return CedulaPE;
    
    }
    
    public String buscarMatricula(String nombre) {
    Connection conn1 = Conexion.conectar();
    String matricula = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    try {
        // Formatea la consulta correctamente
        String consulta = "SELECT matricula FROM busqueda_matricula WHERE nombre LIKE ?";
        pst = conn1.prepareStatement(consulta);
        
        // Añade los comodines directamente al valor de búsqueda
        pst.setString(1, "%" + nombre + "%");
        
        // Ejecuta la consulta
        rs = pst.executeQuery();

        // Verifica si hay un resultado y, si es así, obtiene el valor de la columna "matricula"
        if (rs.next()) {
            matricula = rs.getString("matricula");
        } else {
            System.out.println("No se encontró el registro");
        }
    } catch (SQLException e) {
        System.out.println("Error SQL: " + e.getMessage());
    } finally {
        // Cierra los recursos
        try {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn1 != null) {
                conn1.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }

    return matricula;
}
    
    public void RegistrarEstudiante(String fecha, String horaLlegada, String horaSalida, String Cedula, int ID_diagnos, String Matricula) {
    Connection conn3 = Conexion.conectar();
    PreparedStatement pst = null;
    
    try {
        // Consulta para la inserción
        String query = "INSERT INTO registro_est (Fecha_visita, Hora_llegada, Hora_salida, CedulaPE, ID_diagnos, Matricula) VALUES (?, ?, ?, ?, ?, ?)";
        
        // Inicializa PreparedStatement con la consulta
        pst = conn3.prepareStatement(query);
        
        // Establece los valores de los parámetros
        pst.setString(1, fecha);
        pst.setString(2, horaLlegada);
        pst.setString(3, horaSalida);
        pst.setString(4, Cedula);
        pst.setInt(5, ID_diagnos);
        pst.setString(6, Matricula);
        
        // Ejecuta la consulta de inserción
        int rowsAffected = pst.executeUpdate();
        
        // Verifica si la inserción fue exitosa
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null,"Registro de estudiante insertado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null,"Registro de estudiante insertado con éxito");
        }
    } catch (SQLException e) {
        // Imprime la pila de excepciones para obtener más detalles
        System.out.println("Error al insertar registro de estudiante: " + e.getMessage());
    } finally {
        // Cierra los recursos en orden inverso
        try {
            if (pst != null) {
                pst.close();
            }
            if (conn3 != null) {
                conn3.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
    
    public void NuevoEstudiante(String Matricula, String Nombre1, String Nombre2, String Apellido1, String Apellido2, int Edad, int Curso) {
    Connection conn3 = Conexion.conectar();
    PreparedStatement pst = null;
    
    try {
        // Consulta para la inserción
        String query = "INSERT INTO estudiantes VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        // Inicializa PreparedStatement con la consulta
        pst = conn3.prepareStatement(query);
        
        // Establece los valores de los parámetros
        pst.setString(1, Matricula);
        pst.setString(2, Nombre1);
        pst.setString(3, Nombre2);
        pst.setString(4, Apellido1);
        pst.setString(5, Apellido2);
        pst.setInt(6, Edad);
        pst.setInt(7, Curso);
        
        // Ejecuta la consulta de inserción
        int rowsAffected = pst.executeUpdate();
        
        // Verifica si la inserción fue exitosa
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null,"Nuevo estudiante insertado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null,"No se ha podido insertar");
        }
    } catch (SQLException e) {
        // Imprime la pila de excepciones para obtener más detalles
        System.out.println("Error al insertar registro de estudiante: " + e.getMessage());
    } finally {
        // Cierra los recursos en orden inverso
        try {
            if (pst != null) {
                pst.close();
            }
            if (conn3 != null) {
                conn3.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
    
    public void NuevoPadre(String Cedula, String Nombre1, String Nombre2, String Apellido1, String Apellido2, String Telefono) {
    Connection conn3 = Conexion.conectar();
    PreparedStatement pst = null;
    
    try {
        // Consulta para la inserción
        String query = "INSERT INTO padres VALUES (?, ?, ?, ?, ?, ?)";
        
        // Inicializa PreparedStatement con la consulta
        pst = conn3.prepareStatement(query);
        
        // Establece los valores de los parámetros
        pst.setString(1, Cedula);
        pst.setString(2, Nombre1);
        pst.setString(3, Nombre2);
        pst.setString(4, Apellido1);
        pst.setString(5, Apellido2);
        pst.setString(6, Telefono);
        
        // Ejecuta la consulta de inserción
        int rowsAffected = pst.executeUpdate();
        
        // Verifica si la inserción fue exitosa
        if (rowsAffected > 0) {
            JOptionPane.showMessageDialog(null,"Nuevo estudiante insertado con éxito.");
        } else {
            JOptionPane.showMessageDialog(null,"No se ha podido insertar");
        }
    } catch (SQLException e) {
        // Imprime la pila de excepciones para obtener más detalles
        System.out.println("Error al insertar registro de estudiante: " + e.getMessage());
    } finally {
        // Cierra los recursos en orden inverso
        try {
            if (pst != null) {
                pst.close();
            }
            if (conn3 != null) {
                conn3.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    }
}
    
    public String buscarCedulaPro(String Nombre) {
    
        Connection conn = Conexion.conectar();
        PreparedStatement pst = null;
        ResultSet rs = null;
        String CedulaPro = null;
        
        try {
        
            String query = "Select CedulaPro FROM busqueda_CedulaPro WHERE nombre LIKE ?";
            
            pst = conn.prepareStatement(query);
            
            pst.setString(1,"%"+Nombre+"%");
            
            rs = pst.executeQuery();
            
            if(rs.next()) {
            
                CedulaPro = rs.getString("CedulaPro");
                
            } else {
                JOptionPane.showMessageDialog(null,"No se ha encontradoal profesor");
            }
            
        } catch(SQLException e) {
            System.out.println("Error al conseguir la CedulaPro: "+ e.getMessage());
        } finally {
        
            try {
                if(conn != null) {
                    conn.close();
                } if (pst != null) {
                    pst.close();
                } if (rs != null) {
                    rs.close();
                }
            } catch(SQLException e) {
                System.out.println("Error al cerrar conexiones" + e.getMessage());
            } 
        }
        
        return CedulaPro;
    }
    
    public void RegistrarProfesor(String fecha, String horaLlegada, String horaSalida, String Cedula, int ID_diagnos, String CedulaPro) {
    Connection conn = Conexion.conectar();
    PreparedStatement pst = null;
    
    try {
        
        String query = "INSERT INTO registro_prof(Fecha_visita,Hora_llegada,Hora_salida,CedulaPE,ID_Diagnos,CedulaPro) VALUES(?,?,?,?,?,?)";
        pst = conn.prepareStatement(query);
        
        pst.setString(1,fecha);
        pst.setString(2,horaLlegada);
        pst.setString(3,horaSalida);
        pst.setString(4,Cedula);
        pst.setInt(5,ID_diagnos);
        pst.setString(6,CedulaPro);
        
        int RA = pst.executeUpdate();
        
        if(RA > 0) {
            JOptionPane.showMessageDialog(null,"Se ha realizado el registro");
        } else {
            JOptionPane.showMessageDialog(null,"No se ha podido realizar el registro");
        }
        
    } catch(SQLException e) {
        System.out.println("Error Sql al insertar registro:" + e.getMessage());
    } finally {
        try {
            if (conn != null) {
                conn.close();
            } if (pst != null) {
                pst.close();
            }
        } catch(SQLException e) {
            System.out.println("Error SQL cerrando conexiones: " + e.getMessage());
        }
    }
    
    }
    
    public void RegistrarMedicamento(String Nombre, int Cantidad, String Fecha) {
        
        Connection conn = null;
        PreparedStatement pst = null;

        
        String query = "INSERT INTO medicamentos(Descripcion,Cantidad,Fecha_Vencimiento) VALUES(?,?,?)";
        
        try {
            conn = Conexion.conectar();
            
            pst = conn.prepareStatement(query);
            
            pst.setString(1, Nombre);
            pst.setInt(2, Cantidad);
            pst.setString(3, Fecha);
            
            int RA = pst.executeUpdate();
            
            if(RA > 0) {
                JOptionPane.showMessageDialog(null, "Se ha realizado el registro");
            } else {
                JOptionPane.showMessageDialog(null,"No se ha realizado el registro");
            }
        } catch(SQLException e) {
            System.out.println("Error al registrar medicamento "+ e.getMessage());
        } finally {
            try{
                if(conn != null) {
                conn.close();
            } if (pst != null) {
                pst.close();
            }
            } catch(SQLException e) {
            System.out.println("Error al registrar medicamento "+ e.getMessage());
        } 
    
    }      
    }
    
    public void RegistrarAsistente(String Matricula, String Fecha_turno) {
        
    Connection conn = null;
    PreparedStatement pst = null;

    // Cambié el query para especificar las columnas
    String query = "INSERT INTO asistentes (Matricula, Fecha_turno) VALUES(?,?)";
    
    try {
        // Obtener la conexión
        conn = Conexion.conectar();
        
        // Preparar la declaración
        pst = conn.prepareStatement(query);
        
        // Asignar los valores de Matricula y Fecha_turno
        pst.setString(1, Matricula);
        pst.setString(2, Fecha_turno);
        
        // Ejecutar la consulta
        int RA = pst.executeUpdate();
        
        // Verificar si la inserción fue exitosa
        if (RA > 0) {
            JOptionPane.showMessageDialog(null, "Se ha realizado el registro");
        } else {
            JOptionPane.showMessageDialog(null, "No se ha realizado el registro");
        }
    } catch (SQLException e) {
        System.out.println("Error al registrar asistente: " + e.getMessage());
    } finally {
        // Cerrar los recursos
        try {
            if (conn != null) {
                conn.close();
            }
            if (pst != null) {
                pst.close();
            }
        } catch (SQLException e) {
            System.out.println("Error al cerrar recursos: " + e.getMessage());
        }
    


    
}      
}
    
}
