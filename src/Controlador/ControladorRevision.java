/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Revision;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.lang.String;

/**
 *
 * @author Auxiliar
 */
public class ControladorRevision {

    public static Connection con;
    public static ArrayList lista;

    public static ArrayList buscarVehiculo(String fecha1, String fecha2) throws SQLException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        Revision r = new Revision();
        PreparedStatement pst2 = null;
        ResultSet rs2 = null;
        try {
            int cont = 0;
            ConexionBaseDeDatos mbd = new ConexionBaseDeDatos().getInstancia();
            con = mbd.getConexion();
            pst = con.prepareStatement("select revision.consecutivowil, revision.targa, revision.fecha, cliente.correo from revision,cliente where revision.numerodocumento = cliente.numerodocumento");
            rs = pst.executeQuery();
            lista = new ArrayList<>();
            ArrayList tem = new ArrayList<>();

            while (rs.next()) {

                //System.out.println("1: " + rs.getString(1) + ", 2:" + rs.getString(2) + ", 3:" + rs.getString(3) + ", 4:" + rs.getString(4));
                pst2 = con.prepareStatement("select revision.targa, revision.fecha, cliente.correo, cliente.telefono, concat(cliente.nombres, ' ', cliente.apellidos) from revision,cliente where fecha between '" + fecha1 + "' and '" + fecha2 + "' and consecutivowil = '" + rs.getString(1) + "' and revision.numerodocumento = cliente.numerodocumento");
                rs2 = pst2.executeQuery();
                //JOptionPane.showMessageDialog(null,pst2);
                if (rs2.next()) {
                    //if (!rs2.getString(3).trim().equals("NA") && !rs2.getString(3).trim().equals("Na") && !rs2.getString(3).trim().equals(".NA") && !rs2.getString(3).trim().equals("NO TIENE") && !rs2.getString(3).trim().equals("") && !rs2.getString(3).trim().equals(null) && !rs2.getString(4).trim().equals("0")) {
                    if (rs2.getString(3).trim().contains("@") && !rs2.getString(4).trim().equals("0") && longitudCadena(rs2.getString(4).trim()) == 10) {
                        cont++;
                        Object temp[] = {rs2.getString(1), rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getString(5)};

                        //System.out.println(cont);
                        lista.add(rs2.getString(1) + ";" + rs2.getString(2) + ";" + rs2.getString(3) + ";" + rs2.getString(4) + ";" + rs2.getString(5));
                        //System.out.println(lista);
                        //lista.add(temp);
                        //System.out.println(temp);
                        //System.out.println(Arrays.toString(temp));
                        //System.out.println("interno");
                        //JOptionPane.showMessageDialog(null,rs2.toString());
                        //System.out.println("targa: " + rs2.getString(1) + ", fecha: " + rs2.getString(2) + ", correocliente: " + rs2.getString(3));
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        return lista;
    }

    public static int longitudCadena(String cadena) {
        int cont = cadena.trim().length();
        return cont;
    }
}
