package io.cent.window;

import io.cent.DiscordBotCreator;
import io.cent.install.Installer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class InstallerWindow extends JFrame {
    private JButton install;
    private JProgressBar progressBar;
    private JLabel iconLabel, nameLabel;
    private JCheckBox checkBox;
    private JTextArea textArea;

    public InstallerWindow() {
        super("Installer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLayout(null);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        progressBar.setBounds(10, 400, 560, 30);
        progressBar.setString("0%");
        progressBar.setValue(0);

        install = new JButton("Install");
        install.setBounds(200, 700, 200, 50);
        install.setFont(new Font("MyFont", Font.BOLD, 20));
        install.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        install.addActionListener(e -> {
            if (!checkBox.isSelected()) {
                JOptionPane.showMessageDialog(this, "You must agree to the terms and conditions before installing.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            add(progressBar);

            install.setEnabled(false);
            install.setText("Downloading...");

            new Thread(() -> {
                Installer.downloadServices(this.progressBar);

                install.setText("Installing...");
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(60);
                    } catch (InterruptedException e1) {
                        e1.printStackTrace();
                    }
                    progressBar.setValue(i);
                    progressBar.setString("Installing: " + i + "%");

                    if (i == 100) {
                        Installer.install();
                        JOptionPane.showMessageDialog(new JFrame(), "Cent has been installed. Please restart the program.", "Installation Notice", JOptionPane.INFORMATION_MESSAGE);
                        System.exit(0);
                    }
                }
            }).start();
        });
        add(install);

        try {
            InputStream is = DiscordBotCreator.class.getClassLoader().getResourceAsStream("assets/icon.png");
            assert is != null;
            ImageIcon icon = new ImageIcon(ImageIO.read(is));
            setIconImage(icon.getImage());
            iconLabel = new JLabel(icon);
            iconLabel.setBounds(165, 30, 230, 210);
            add(iconLabel);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while trying to load the icon.\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(-999);
        }

        nameLabel = new JLabel("Discord Bot Creator by Cent");
        nameLabel.setFont(new Font("MyFont", Font.BOLD, 20));
        nameLabel.setBounds(150, 300, 300, 30);
        add(nameLabel);

        checkBox = new JCheckBox("I agree to the terms of service.");
        checkBox.setFont(new Font("MyFont", Font.PLAIN, 15));
        checkBox.setBounds(190, 650, 520, 30);
        checkBox.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        checkBox.setSelected(false);
        add(checkBox);

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("MyFont", Font.PLAIN, 15));
        textArea.setText("**TERMS OF SERVICE**\n" +
                "Cent is not responsible for any damage caused by the installation of the bot.\n" +
                "Cent is not responsible for any damage caused by the bot.\n" +
                "You cannot modify this program.\n" +
                "You cannot sell this program.\n" +
                "You are not allowed to share your License key.\n" +
                "Your license key will be terminated if you bypass any of the terms.");
        textArea.setBounds(10, 500, 560, 150);
        textArea.setSelectedTextColor(Color.red);
        add(textArea);

        setLocationRelativeTo(null);
        setResizable(false);
        setFocusable(true);
        setVisible(true);
        requestFocus();
    }
}
