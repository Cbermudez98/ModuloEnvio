/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Empresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Auxiliar
 */
public class ControladorEmpresa {
    public static Connection con;
    public static Empresa buscarEmpresa() {
        Empresa e = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ConexionBaseDeDatos mbd = new ConexionBaseDeDatos().getInstancia();
        con = mbd.getConexion();
        
        String query = "select nombre, direccion, telefono, ciudad, email from empresa where id = 1";
        try {
            pst = con.prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()){
                e = LoadE(rs);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return e;
    }

    public static Empresa LoadE(ResultSet rs) throws SQLException {
        Empresa e = new Empresa();
        e.setNombre(rs.getString(1));
        e.setDireccion(rs.getString(2));
        e.setTelefono(rs.getString(3));
        e.setCiudad(rs.getString(4));
        e.setEmail(rs.getString(5));
        return e;
    }
}
