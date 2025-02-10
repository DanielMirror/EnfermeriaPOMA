package Main;

import java.util.logging.Level;
import java.util.logging.Logger;
import Formularios.Acceso;
import Splash.Splash;


public class Main {

    public static void main(String[] args) {
        
        
        Runnable mRun = () -> {
            
            // JFrame que funge como splash
            Splash mSplash = new Splash();
            mSplash.setVisible(true);
            
            try {
                Thread.sleep(6000); // 6000 milisegundos equivale a 6 segundos.
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            mSplash.dispose();
            
            // JFrame que funge como pantalla principal
            Acceso newAcceso = new Acceso();
            newAcceso.setVisible(true);
            
            
        };
        
        
        Thread mHiloSplash = new Thread(mRun);
        mHiloSplash.start();
        
    }
    
    
}
