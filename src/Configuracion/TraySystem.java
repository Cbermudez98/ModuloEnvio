/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Configuracion;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 * @author Auxiliar
 */
public class TraySystem {

    private JFrame parent;
    private PopupMenu popup = new PopupMenu();
    private final Image image = new ImageIcon(getClass().getResource("/Iconos/icons8-email-64.png")).getImage();
    private final TrayIcon trayIcon = new TrayIcon(image, "ModuloEnvio", popup);

    public TraySystem(JFrame frame) {
        this.parent = frame;
        if (SystemTray.isSupported()) {
            SystemTray systemtray = SystemTray.getSystemTray();
            trayIcon.setImageAutoSize(true);
            MouseListener mouseListener = new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent evt) {

                    //Si se presiona con el boton izquierdo en el icono
                    //y la aplicacion esta minimizada se muestra una frase
                    if (evt.getButton() == MouseEvent.BUTTON1 && parent.getExtendedState() == JFrame.ICONIFIED) {
                        MensajeTrayIcon("Modulo Envio ejecutandose en segundo plano", TrayIcon.MessageType.INFO);
                    }
                }

                @Override
                public void mouseEntered(MouseEvent evt) {/*nada x aqui circulen...*/
                }

                @Override
                public void mouseExited(MouseEvent evt) {/*nada x aqui circulen...*/
                }

                @Override
                public void mousePressed(MouseEvent evt) {/*nada x aqui circulen...*/
                }

                @Override
                public void mouseReleased(MouseEvent evt) {/*nada x aqui circulen...*/
                }
            };

            ActionListener exitListener = (ActionEvent e) -> {
                System.exit(0);
            };
            ActionListener restoreListener = (ActionEvent e) -> {
                //si esta minimizado restaura JFrame
                if (parent.getExtendedState() == JFrame.ICONIFIED) {
                    parent.setVisible(true);
                    parent.setExtendedState(JFrame.NORMAL);
                    parent.repaint();
                   //  if(timer!=null) timer.cancel();
                }
            };
            MenuItem exitAppItem = new MenuItem("Salir");
            exitAppItem.addActionListener(exitListener);
            popup.add(exitAppItem);

            MenuItem restoreAppItem = new MenuItem("Restaurar");
            restoreAppItem.addActionListener(restoreListener);
            popup.add(restoreAppItem);
            trayIcon.addMouseListener(mouseListener);

            //Añade el TrayIcon al SystemTray
            try {
                systemtray.add(trayIcon);
            } catch (AWTException e) {
                System.err.println("Error:" + e.getMessage());
            }
        } else {
            System.err.println("Error: SystemTray no es soportado");
            return;
        }
        parent.addWindowListener(new WindowAdapter() {
            @Override
            public void windowIconified(WindowEvent e) {
                parent.setVisible(false);//Se oculta JFrame
                //Se inicia una tarea cuando se minimiza           
             /*   if (timer != null) {
                    timer.cancel();
                }
                timer = new Timer();
                timer.schedule(new MyTimerTask(), 2000, 12000);//Se ejecuta cada 12 segundos*/
            }
        });
    }

    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            actionBackground();
        }

        /**
         * accion a realizar cuando la aplicacion a sido minimizada
         */
        public void actionBackground() {
            int num1 = (int) Math.floor(Math.random() * 6 + 1);
            int num2 = (int) Math.floor(Math.random() * 6 + 1);
            MensajeTrayIcon("Haciendo algo: " + num1 + " + " + num2 + " = " + (num1 + num2), TrayIcon.MessageType.INFO);
        }

    }

    public void MensajeTrayIcon(String texto, TrayIcon.MessageType tipo) {
        trayIcon.displayMessage("Modulo Envio", texto, tipo);
    }

}
