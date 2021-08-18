/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloenvio;

import Controlador.ControladorCorreo;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import vista.Login;

/**
 *
 * @author Auxiliar
 */
public class ModuloEnvio {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatArcIJTheme());
            UIManager.put("Component.arrowType", "chevron");
            UIManager.put("ScrollBar.trackArc", 999);
            UIManager.put("ScrollBar.thumbArc", 999);
            UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
            UIManager.put("ScrollBar.track", new Color(0xe0e0e0));
            UIManager.put("Button.arc", 999);
            UIManager.put("Component.arc", 999);
            UIManager.put("ProgressBar.arc", 999);
            UIManager.put("TextComponent.arc", 999);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        Login l = new Login();
        l.setVisible(true);
    }

}
