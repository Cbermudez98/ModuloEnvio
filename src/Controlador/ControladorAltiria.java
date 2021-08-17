/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Mensaje;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 *
 * @author Auxiliar
 */
public class ControladorAltiria {

    public static Mensaje obtenerMensaje() {
        Mensaje m = null;
        String cad = "";
        int contador = 0;
        String correo = "";
        String contrasena = "";
        String mensaje = "";
        //ControladorCorreo c = new ControladorCorreo();
        //Correo co = c.BuscarCoreo();
        try {
            File f = new File("C:\\Wil\\Mensaje.ini");
            FileReader fi = new FileReader(f);
            BufferedReader br = new BufferedReader(fi);

            while ((cad = br.readLine()) != null) {

                mensaje += cad + "\n";

                contador++;
            }
            //mensaje = co.getMensaje();
            m = loadM(mensaje);
            return m;
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Error archivo Mensaje.ini no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException e) {
            Logger.getLogger(ControladorAltiria.class.getName()).log(Level.SEVERE, null, e);
        }
        return m;
    }

    public static Mensaje loadM(String mensaje) {
        Mensaje m = new Mensaje();
        m.setMensaje(mensaje.trim());
        //System.out.println(correo+", "+contrasena+", "+mensaje);
        return m;
    }

    public static void AgregerMensaje(String mensaje) {
        try {
            String ruta = "C:\\Wil\\Mensaje.ini";
            String contenido = "";

            File f = new File(ruta);
            if (!f.exists()) {
                f.createNewFile();
            }

            FileWriter fw = new FileWriter(f);
            PrintWriter pw = new PrintWriter(f);
            pw.println(mensaje);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void EnviarSms(String telefono, String placa, String fecha) throws IOException {
        //Se fija el tiempo m´aximo de espera para conectar con el servidor (5000)
        //Se fija el tiempo m´aximo de espera de la respuesta del servidor (60000)
        //String mensaje = "Cda prueba \n\rle informa que su tecnico mecanica esta apunto de vencer\n\ry lo invitamos a acercarse a nuestras instalacionres"
        //+" para renovar\n\r muchas gracias";
        ControladorAltiria ca = new ControladorAltiria();
        Mensaje m = ca.obtenerMensaje();

        //String informacion = "informacion del vehículo \n\r placa del vehículo: "+vehiculo+"\n\r fecha de la revision: "+fechaRevision;
        //System.out.println(m.getMensaje());
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(60000)
                .build();
        //Se inicia el objeto HTTP
        HttpClientBuilder builder = HttpClientBuilder.create();
        builder.setDefaultRequestConfig(config);
        CloseableHttpClient httpClient = builder.build();
        //Se fija la URL sobre la que enviar la peticion POST
        HttpPost post = new HttpPost("http://www.altiria.net/api/http");
        //Se crea la lista de par´ametros a enviar en la peticion POST
        ArrayList<NameValuePair> parametersList = new ArrayList<NameValuePair>();
        //XX, YY y ZZ se corresponden con los valores de identificaci´on del
        //usuario en el sistema.
        parametersList.add(new BasicNameValuePair("cmd", "sendsms"));
        //domainId solo es necesario si el login no es un email
        //parametersList.add(new BasicNameValuePair("domainId", "XX"));
        parametersList.add(new BasicNameValuePair("concat", "true"));
        parametersList.add(new BasicNameValuePair("login", m.getCorreo()));
        parametersList.add(new BasicNameValuePair("passwd", m.getContrasena()));
        parametersList.add(new BasicNameValuePair("dest", "+57" + telefono));
        //parametersList.add(new BasicNameValuePair("dest", "+57" + telefono));
        parametersList.add(new BasicNameValuePair("msg", m.getMensaje()));
        parametersList.add(new BasicNameValuePair("msg", "Placa: " + placa.trim()));
        parametersList.add(new BasicNameValuePair("msg", "Fecha de revision: " + fecha.trim()));
        //No es posible utilizar el remitente en America pero sı en España y Europa
        //Descomentar la lınea solo si se cuenta con un remitente autorizado por Altiria
        //parametersList.add(new BasicNameValuePair("senderId", "remitente"));
        try {
            //Se fija la codificacion de caracteres de la peticion POST
            post.setEntity(new UrlEncodedFormEntity(parametersList, "UTF-8"));
        } catch (UnsupportedEncodingException uex) {
            System.out.println("ERROR: codificacion de caracteres no soportada");
        }
        CloseableHttpResponse response = null;
        try {
            System.out.println("Enviando peticion");
            //Se env´ıa la petici´on
            response = httpClient.execute(post);
            //Se consigue la respuesta
            String resp = EntityUtils.toString(response.getEntity());
            //Error en la respuesta del servidor
            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("ERROR: Codigo de error HTTP: " + response.getStatusLine().getStatusCode());
                System.out.println("Compruebe que ha configurado correctamente la direccion/url ");
                System.out.println("suministrada por Altiria");
                return;
            } else {
                //Se procesa la respuesta capturada en la cadena ’response’
                if (resp.startsWith("ERROR")) {
                    System.out.println(resp);
                    System.out.println("Codigo de error de Altiria. Compruebe las especificaciones");
                } else {
                    System.out.println(resp);
                }
            }
        } catch (Exception e) {
            System.out.println("Excepcion");
            e.printStackTrace();
            return;
        } finally {
            //En cualquier caso se cierra la conexi´on
            post.releaseConnection();
            if (response != null) {
                try {
                    response.close();
                } catch (IOException ioe) {
                    System.out.println("ERROR cerrando recursos");
                    ioe.printStackTrace();
                }
            }
        }
    }
}
