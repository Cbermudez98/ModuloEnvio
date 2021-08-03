/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import Controlador.ControladorAltiria;
import Controlador.ControladorCorreo;
import Controlador.ControladorExcel;
import Controlador.ControladorRevision;
import Modelo.Correo;
import Modelo.Mensaje;
import com.formdev.flatlaf.intellijthemes.FlatArcIJTheme;
import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.String;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import javafx.scene.control.DatePicker;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Auxiliar
 */
public class menuPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form menuPrincipal
     */
    DefaultTableModel dtm = new DefaultTableModel();
    ArrayList l;

    public menuPrincipal() {
        initComponents();
        this.setSize(700, 450);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        ImageIcon i = new ImageIcon(getClass().getResource("/Iconos/icons8-email-64.png"));
        ImageIcon carga = new ImageIcon(getClass().getResource("/Iconos/Spinner.gif"));
        btnEnviarcorreo.setCursor(new Cursor(HAND_CURSOR));
        btnEnviarcorreo.setFocusPainted(false);
        reinciarEnvio.setCursor(new Cursor(HAND_CURSOR));
        reinciarEnvio.setFocusPainted(false);
        btnMensaje.setCursor(new Cursor(HAND_CURSOR));
        btnMensaje.setFocusPainted(false);
        Image i2 = i.getImage();
        this.setIconImage(i2);

        dtm.addColumn("placa");
        dtm.addColumn("fecha revision");
        dtm.addColumn("Correo cliente");
        dtm.addColumn("Telefono");
        //Correo.setBorder(new EmptyBorder(5, 5, 5, 5));
        jMenu1.setCursor(new Cursor(HAND_CURSOR));
        Correo.setCursor(new Cursor(HAND_CURSOR));
        Mensaje.setCursor(new Cursor(HAND_CURSOR));
        //Mensaje.setBorder(new EmptyBorder(5, 5, 5, 5));

        fecha1.setCursor(new Cursor(HAND_CURSOR));
        fecha2.setCursor(new Cursor(HAND_CURSOR));

        /*try {
            UIManager.setLookAndFeel(new FlatArcIJTheme());
            UIManager.put("Component.arrowType", "chevron");
            UIManager.put("ScrollBar.trackArc", 999);
            UIManager.put("ScrollBar.thumbArc", 999);
            UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
            UIManager.put("ScrollBar.track", new Color(0xe0e0e0));
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }*/
        try {
            UIManager.setLookAndFeel(new FlatArcIJTheme());
            UIManager.put("Component.arrowType", "chevron");
            UIManager.put("ScrollBar.trackArc", 999);
            UIManager.put("ScrollBar.thumbArc", 999);
            UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
            UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
            UIManager.put("ScrollBar.track", new Color(0xe0e0e0));
            UIManager.put("Button.arc", 999);
            UIManager.put("TabbedPane.selectedBackground", Color.white);
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        //Personalizar los botones de java
        //btnEnviarcorreo.setBackground(Color.decode("#1e81b0"));
        /*btnEnviarcorreo.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEnviarcorreo.setBackground(Color.decode("#778899"));
                btnEnviarcorreo.setForeground(Color.decode("#FFFFF0"));
                //btnEnviarcorreo.setSize(new Dimension(btnEnviarcorreo.getWidth()+5,btnEnviarcorreo.getHeight()+5));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnEnviarcorreo.setBackground(UIManager.getColor("#fff"));
                //btnEnviarcorreo.setSize(new Dimension(btnEnviarcorreo.getWidth()-5,btnEnviarcorreo.getHeight()-5));
                btnEnviarcorreo.setForeground(Color.BLACK);
            }
        }
        );

        reinciarEnvio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reinciarEnvio.setBackground(Color.decode("#778899"));
                reinciarEnvio.setForeground(Color.decode("#FFFFF0"));
                //reinciarEnvio.setSize(new Dimension(reinciarEnvio.getWidth()+5,reinciarEnvio.getHeight()+5));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                reinciarEnvio.setBackground(UIManager.getColor("#fff"));
                //reinciarEnvio.setSize(new Dimension(reinciarEnvio.getWidth()-5,reinciarEnvio.getHeight()-5));
                reinciarEnvio.setForeground(Color.BLACK);
            }
        });

        btnMensaje.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMensaje.setBackground(Color.decode("#778899"));
                btnMensaje.setForeground(Color.decode("#FFFFF0"));
                //reinciarEnvio.setSize(new Dimension(reinciarEnvio.getWidth()+5,reinciarEnvio.getHeight()+5));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMensaje.setBackground(UIManager.getColor("#fff"));
                //reinciarEnvio.setSize(new Dimension(reinciarEnvio.getWidth()-5,reinciarEnvio.getHeight()-5));
                btnMensaje.setForeground(Color.BLACK);
            }
        });*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEnviarcorreo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEnvios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        fecha1 = new com.toedter.calendar.JDateChooser();
        fecha2 = new com.toedter.calendar.JDateChooser();
        reinciarEnvio = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnMensaje = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        Correo = new javax.swing.JMenuItem();
        Mensaje = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEnviarcorreo.setText("Correo");
        btnEnviarcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarcorreoActionPerformed(evt);
            }
        });

        tblEnvios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblEnvios);

        jLabel1.setText("Selecione un rando de fechas");

        reinciarEnvio.setText("Reinciar Envio");
        reinciarEnvio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reinciarEnvioActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha Inicial");

        jLabel3.setText("Fecha Final");

        btnMensaje.setText("Mensaje");
        btnMensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMensajeActionPerformed(evt);
            }
        });

        jMenu1.setText("| Parametros |");

        Correo.setText("Correo");
        Correo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CorreoActionPerformed(evt);
            }
        });
        jMenu1.add(Correo);

        Mensaje.setText("Mensajes");
        Mensaje.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MensajeActionPerformed(evt);
            }
        });
        jMenu1.add(Mensaje);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEnviarcorreo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnMensaje)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(reinciarEnvio)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 539, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(fecha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fecha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEnviarcorreo)
                    .addComponent(reinciarEnvio)
                    .addComponent(btnMensaje))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CorreoActionPerformed
        VistaCorreo v = new VistaCorreo();
        v.setVisible(true);
    }//GEN-LAST:event_CorreoActionPerformed
    //No esta en uso
    public static String RestarFecha(String fecha) {
        String f[] = fecha.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(f[0]) - 1, Integer.parseInt(f[1]) - 1, Integer.parseInt(f[2]));
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        return s.format(calendar.getTime());
    }

    private void btnEnviarcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarcorreoActionPerformed
        //necesito enviar smtp, puerto, vehiculo, asunto, placa, correo, fecha de revision
        ControladorRevision cr = new ControladorRevision();
        Correo c = ControladorCorreo.BuscarCoreo();
        //ArrayList l;

        if (fecha1.getDate() == null || fecha2.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Error seleccione fechas", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Date date = fecha1.getDate();
        Date date2 = fecha2.getDate();

        String full = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String full2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
        System.out.println(full);
        System.out.println(full2);
        tblEnvios.setModel(dtm);
        tblEnvios
                .setDefaultEditor(Object.class,
                        null);
        boolean gmail = false;
        if (c != null) {
            try {
                l = cr.buscarVehiculo(full, full2);
                String tem[] = null;
                if (c.getCorreo().contains("@gmail.com")) {
                    gmail = true;
                }

                if (c.getCorreo().contains("@hotmail.com")) {
                    gmail = false;
                }

                Iterator it = l.iterator();
                while (it.hasNext()) {
                    //System.out.println(it.next().toString());
                    tem = it.next().toString().split(";");
                    //System.out.println(tem[2].toString());
                    if (gmail == true) {
                        //ControladorCorreo.enviarCorreo("smtp.gmail.com", "587", tem[0].toString(), c.getAsunto(), tem[2].toString(), tem[1].toString());
                        //System.out.println("smtp.gmail.com"+ "587" + tem[0].toString()+ c.getAsunto()+ tem[2].toString()+ tem[1].toString());
                    }

                    if (gmail == false) {
                        //ControladorCorreo.enviarCorreo("smtp.live.com", "25", tem[0].toString(), c.getAsunto(), tem[2].toString(), tem[1].toString());
                    }
                }

                JOptionPane.showMessageDialog(null, "Enviados con exito");

                for (int i = 0; i < l.size(); i++) {
                    String tempo[] = null;
                    tempo = l.get(i).toString().split(";");
                    dtm.addRow(tempo);

                }
                ControladorExcel.exportarExcelCorreo(tblEnvios, full, full2);
            } catch (SQLException ex) {
                Logger.getLogger(menuPrincipal.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (IOException io) {
                Logger.getLogger(menuPrincipal.class
                        .getName()).log(Level.SEVERE, null, io);
            }
        }
    }//GEN-LAST:event_btnEnviarcorreoActionPerformed

    private void reinciarEnvioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reinciarEnvioActionPerformed
        // TODO add your handling code here:
        tblEnvios.setModel(new DefaultTableModel());
        l.clear();
        fecha1.setDate(null);
        fecha2.setDate(null);
    }//GEN-LAST:event_reinciarEnvioActionPerformed

    private void btnMensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMensajeActionPerformed
        /*try {
            // TODO add your handling code here:
            //ControladorAltiria.EnviarSms("3017852750");
            ControladorAltiria.EnviarSms("3105267745", "VBI46F","2020-12-50");
        } catch (IOException ex) {
            Logger.getLogger(menuPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        Mensaje m = null;
        try {
            m = ControladorAltiria.obtenerMensaje();
            if (m != null) {
                ControladorRevision cr = new ControladorRevision();
                //ArrayList l;

                if (fecha1.getDate() == null || fecha2.getDate() == null) {
                    JOptionPane.showMessageDialog(null, "Error seleccione fechas", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Date date = fecha1.getDate();
                Date date2 = fecha2.getDate();

                String full = new SimpleDateFormat("yyyy-MM-dd").format(date);
                String full2 = new SimpleDateFormat("yyyy-MM-dd").format(date2);
                System.out.println(full);
                System.out.println(full2);
                tblEnvios.setModel(dtm);
                tblEnvios.setDefaultEditor(Object.class, null);
                if (m != null) {
                    try {
                        l = cr.buscarVehiculo(full, full2);
                        String tem[] = null;

                        Iterator it = l.iterator();
                        while (it.hasNext()) {
                            //System.out.println(it.next().toString());
                            tem = it.next().toString().split(";");
                            //System.out.println(tem[2].toString());
                            //ControladorAltiria.EnviarSms(tem[4], temp[1],temp[2]);
                        }

                        JOptionPane.showMessageDialog(null, "Enviados con exito");

                        for (int i = 0; i < l.size(); i++) {
                            String tempo[] = null;
                            tempo = l.get(i).toString().split(";");
                            dtm.addRow(tempo);
                        }

                        ControladorExcel.exportarExcelSMS(tblEnvios, full, full2);
                    } catch (SQLException ex) {
                        Logger.getLogger(menuPrincipal.class
                                .getName()).log(Level.SEVERE, null, ex);

                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(menuPrincipal.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_btnMensajeActionPerformed

    private void MensajeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MensajeActionPerformed
        // TODO add your handling code here:
        Vista.VistaMensaje v = new Vista.VistaMensaje();
        v.setVisible(true);
    }//GEN-LAST:event_MensajeActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
 /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem Correo;
    private javax.swing.JMenuItem Mensaje;
    private javax.swing.JButton btnEnviarcorreo;
    private javax.swing.JButton btnMensaje;
    private static com.toedter.calendar.JDateChooser fecha1;
    private static com.toedter.calendar.JDateChooser fecha2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton reinciarEnvio;
    private static javax.swing.JTable tblEnvios;
    // End of variables declaration//GEN-END:variables
}
