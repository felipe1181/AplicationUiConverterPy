/*
 * Copyright (C) 2017 Felipe Henrique Ferraresi
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

/**
 *
 * @author Felipe Henrique Ferraresi
 */
package pythonferraresiconverter;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 *
 * @author Felipe Henrique Ferrerasi
 */
public class PythonFerraresiConverter {

    private final SystemTray TRAY = SystemTray.getSystemTray();
    private final PopupMenu jPopupMenu = new PopupMenu();
    private final MenuItem jmiSair = new MenuItem("Sair"), jmiConverter = new MenuItem("Converter");

    private ImageIcon ICONE;
    private Image IMAGEM;

    /**
     * @param args the command line arguments
     */
    public PythonFerraresiConverter() {
        /**
         * *************
         */
         
        final TrayIcon trayIcon;

        if (SystemTray.isSupported()) {
            try {
                ICONE = new ImageIcon("C:\\felipeconvert\\ic.png");
            } catch (Exception e) {
                generateDefaultIcon();
            } finally {
                if (ICONE == null || (ICONE.getIconHeight() < 16 || ICONE.getIconWidth() < 16)) {
                    generateDefaultIcon();
                }
                IMAGEM = ICONE.getImage();
                try {
                    TrayIcon icon = new TrayIcon(IMAGEM, "Conversor Ferraresi");

                    ActionListener ActionConverter = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new SelectFile();
                        }
                    };

                    ActionListener ActionSair = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    };

                    jmiConverter.addActionListener(ActionConverter);
                    jmiSair.addActionListener(ActionSair);
                    jPopupMenu.add(jmiConverter);
                    jPopupMenu.add(jmiSair);
                    icon.setPopupMenu(jPopupMenu);
                    TRAY.add(icon);

                } catch (AWTException ex) {
                }
            }

        }

        /**
         * *************
         */
    }

    private void generateDefaultIcon() {
        ICONE = (ImageIcon) UIManager.getIcon("FileView.computerIcon");
        new Thread(new downloadImage()).start();
    }

    private static class downloadImage implements Runnable {

        @Override
        public void run() {
            URL url;
            try {
                url = new URL("http://i.imgur.com/5vB9JHf.png");

                InputStream in = new BufferedInputStream(url.openStream());
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int n = 0;
                while (-1 != (n = in.read(buf))) {
                    out.write(buf, 0, n);
                }
                out.close();
                in.close();
                byte[] response = out.toByteArray();

                File dirIcon = new File("C:\\felipeconvert");
                if (!dirIcon.exists()) {
                    dirIcon.mkdirs();
                } 

                FileOutputStream fos = new FileOutputStream("C:\\felipeconvert\\ic.png");
                fos.write(response);
                fos.close();
            } catch (MalformedURLException ex) {
                Logger.getLogger(PythonFerraresiConverter.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(PythonFerraresiConverter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        public static void main(String[] args) {
            new PythonFerraresiConverter();
        }
    }

}
