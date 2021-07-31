/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Correo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;
import vista.Login;
import vista.VistaCorreo;
import vista.menuPrincipal;

/**
 *
 * @author Auxiliar
 */
public class ControladorCorreo {

    public static Correo BuscarCoreo() {
        Correo c = null;
        String cad = "";
        int contador = 0;
        String correo = "";
        String password = "";
        String asunto = "";
        String mensaje = "";
        int aux = 0;
        try {
            File f = new File("C:\\Wil\\Envio.ini");
            FileReader fi = new FileReader(f);
            BufferedReader bf = new BufferedReader(fi);

            while ((cad = bf.readLine()) != null) {
                if (contador == 0) {
                    correo = cad;
                }

                if (contador == 1) {
                    password = cad;
                }

                if (contador == 2) {
                    asunto = cad;
                }
                if (contador >= 3 && !cad.equals("")) {
                    mensaje += cad + "\n\r";
                }
                contador++;
            }

            c = loadC(correo, password, asunto, mensaje);
            return c;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error archivo de correo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(ControladorCorreo.class.getName()).log(Level.SEVERE, null, ex);
        }

        return c;
    }

    public static void AgregarCorreo(String correo, String password, String asunto, String mensaje) {

        try {
            String ruta = "C:\\Wil\\Envio.ini";
            String contenido = "";

            File f = new File(ruta);
            if (!f.exists()) {
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(correo);
            pw.println(password);
            pw.println(asunto);
            pw.println(mensaje);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Correo loadC(String correo, String password, String asunto, String mensaje) {
        Correo c = new Correo();
        c.setCorreo(correo);
        c.setPassword(password);
        c.setAsunto(asunto);
        c.setMensaje(mensaje);
        return c;
    }

    public static void enviarCorreo(String smtp, String port, String vehiculo, String asunto, String correo, String fechaRevision) {
        Correo c = BuscarCoreo();
        Properties props = new Properties();
        System.out.println("smtp: " + smtp + ", port: " + port + ", vehiculo: " + vehiculo + ", asunto: " + asunto + ", correo: " + correo + ", fechaRevision: " + fechaRevision);
        //System.out.println(c.getMensaje());
        String informacion = "informacion del vehículo \n\r placa del vehículo: "+vehiculo+"\n\r fecha de la revision: "+fechaRevision;
        //System.out.println(informacion);
        if (c != null) {
            props.setProperty("mail.smtp.host", smtp);
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", port);
            props.setProperty("mail.smtp.user", c.getCorreo());
            props.setProperty("mail.smtp.user", c.getPassword());
            props.setProperty("mail.smtp.auth", "true");

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(c.getCorreo(), c.getPassword());
                }
            });

            try {
                BodyPart text = new MimeBodyPart();
                text.setText(c.getMensaje());
                BodyPart text2 = new MimeBodyPart();
                text2.setText(informacion);
                BodyPart imagenAdjunta = new MimeBodyPart();
                imagenAdjunta.setDataHandler(new DataHandler(new FileDataSource("C:\\Reportes\\logocda.png")));
                imagenAdjunta.setFileName("logocda.png");
                MimeMultipart m = new MimeMultipart();
                m.addBodyPart(text);
                m.addBodyPart(text2);
                m.addBodyPart(imagenAdjunta);

                MimeMessage message = new MimeMessage(session);
                message.setFrom(new InternetAddress(c.getCorreo()));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
                message.setSubject(asunto);
                message.setContent(m);
                Transport transport = session.getTransport();
                transport.connect(c.getCorreo(), c.getPassword());
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
        }
    }
}
