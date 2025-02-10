/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Conexion;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;



/**
 *
 * @author pc personal
 */
public class Conexion {
    
    private static final String URL = "jdbc:mysql://LocalHost:3306/enfermeriapoma";
    private static final String USUARIO = "root";
    private static final String CLAVE = "1234";
    
    public static Connection conectar() {
    
        Connection conn = null;
        try {
        
            conn = DriverManager.getConnection(URL,USUARIO,CLAVE);
            System.out.println("La conexion fue exitosa");
            
        } catch (SQLException e) {
        
        System.out.println("Error de conexion" + e.getMessage());
        
        }
        
        return conn;
        
    }
    
}
