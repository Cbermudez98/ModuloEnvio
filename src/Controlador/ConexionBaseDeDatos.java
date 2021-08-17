/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Auxiliar
 */
public class ConexionBaseDeDatos {

    public static Connection conexion;
    public static String usuario = "root";
    public static String contrasena = "123456789";
    public static String driver = "com.mysql.jdbc.Driver";
    public static String server;

    public static String puerto = "3307";
    public static String basedatos = "newwilnewfinal";
    public static String rutaCompleta = "jdbc:mysql://" + server + ":" + puerto + "/" + basedatos + "";
    public static ConexionBaseDeDatos instancia;

    public Logger log = Logger.getLogger(Controlador.ConexionBaseDeDatos.class.getName());

    public static ConexionBaseDeDatos getInstancia() {
        if (ConexionBaseDeDatos.instancia == null) {
            ConexionBaseDeDatos.instancia = new ConexionBaseDeDatos();
        }
        return instancia;
    }
    
    public static void busqueda() throws IOException {
        int contado = 0;
        String cad = "";
        try {
            File config = new File("C:\\Wil\\Config.ini");
            FileReader l = new FileReader(config);
            BufferedReader le = new BufferedReader(l);
            while ((cad = le.readLine()) != null) {
                //System.out.println("Soy cad : " + cad);
                if (contado == 0) {
                    ConexionBaseDeDatos.server = cad;
                    rutaCompleta = "jdbc:mysql://" + server + ":" + puerto + "/" + basedatos + "";
                }
                contado++;
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error no se encontro archivo de configuracion " + e);
        }
    }

    public void conectar() throws Exception {
        busqueda();
        if (this.getConexion() != null) {
            if (!this.getConexion().isClosed()) {
                return;
            }
        }
        try {
            Class.forName(this.getDriver());
            this.setConexion(DriverManager.getConnection(rutaCompleta, usuario, contrasena));

        } catch (ClassNotFoundException er1) {
            JOptionPane.showMessageDialog(null, er1, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Error al Conectarse a la Base de Datos");
        } catch (SQLException er2) {
            JOptionPane.showMessageDialog(null, er2, "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Error al Conectarse a la Base de Datos");
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public String getDriver() {
        return driver;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setBasedatos(String basedatos) {
        this.basedatos = basedatos;
    }

    public void setConexion(Connection conexion) {
        this.conexion = conexion;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public ConexionBaseDeDatos() {

    }

    public String getBasedatos() {
        return basedatos;
    }

    public static void setInstancia(ConexionBaseDeDatos aInstancia) {
        instancia = aInstancia;
    }

}
