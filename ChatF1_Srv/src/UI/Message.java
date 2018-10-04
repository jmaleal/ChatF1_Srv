/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author jorgeleal
 */
public class Message {

    public static void ShowMessage(Object obj, String msg, int i) {
        JOptionPane.showMessageDialog(null, obj, msg, i);
    }

    public static void systrayMsg(String titulo, String mensagem, TrayIcon.MessageType tipo) {
        Image image = Toolkit.getDefaultToolkit().getImage(".//icon.jpg");
        final TrayIcon ti = new TrayIcon(image);
        final SystemTray st = SystemTray.getSystemTray();
        try {
            st.add(ti);

            ti.displayMessage(titulo, mensagem, TrayIcon.MessageType.INFO);

            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1500); // Don't do this
                        st.remove(ti);
                    } catch (InterruptedException ie) {
                        // You won't need this when the sleep is removed
                    }
                }
            });
        } catch (AWTException ex) {
            ShowMessage("Erro na notificação!", "::: ERRO :::", JOptionPane.ERROR_MESSAGE);
        }
    }

}
