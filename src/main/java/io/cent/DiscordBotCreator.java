package io.cent;

import io.cent.install.Installer;
import io.cent.project.Project;
import io.cent.theme.Theme;
import io.cent.util.Log;
import io.cent.window.AppConsole;
import io.cent.util.DUtil;
import io.cent.util.UserFiles;
import io.cent.window.InstallerWindow;
import io.cent.window.MainWindow;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import static io.cent.logging.Statistics.StatisticLog;

public class DiscordBotCreator {
    public static boolean openConsole = false;
    public static Project openProject = null;
    public static final String VERSION = "1.0-BETA";
    public static Theme selectedTheme = null;

    public static MainWindow mainWindow;

    public DiscordBotCreator() {
        Log.info("Starting...");

        try {
            new Thread(() -> {
                try {
                    JWindow jWindow = new JWindow();
                    jWindow.setSize(230, 210);
                    jWindow.setLocationRelativeTo(null);
                    jWindow.setAlwaysOnTop(true);
                    jWindow.setBackground(new Color(0, 0, 0, 0));

                    JLabel jLabel = new JLabel(new ImageIcon(ImageIO.read(getClass().getClassLoader().getResourceAsStream("assets/icon.png"))));
                    jLabel.setVisible(true);

                    jWindow.add(jLabel);
                    jWindow.setVisible(true);

                    Thread.sleep(3000);
                    jWindow.dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }).start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        StatisticLog();

        UserFiles.USER_HOME = DUtil.getUserHome();

        if (!Installer.checkIfInstalled()) {
            new InstallerWindow();
            return;
        }

        runServices();
        Installer.loadAssets();
        init();

        mainWindow = new MainWindow();
    }

    public static void main(String[] args) {
        new DiscordBotCreator();
    }

    public static void updateProject() {
        mainWindow.setProject(openProject);
    }

    private void init() {
        selectedTheme = DUtil.getSelectedTheme();
    }

    private void runServices() {
        try {
            String filePathString = DUtil.appData + File.separator + "CentServices" + File.separator + "CentServices.jar";

            ProcessBuilder pc = new ProcessBuilder(
                    "cmd.exe", "/c", "java -jar " +filePathString
            );
            Process p = pc.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while (r.readLine() != null) {
                Log.info("Services -> " + r.readLine());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void pop(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public final static AppConsole console;

    static {
        console = new AppConsole();
        if (openConsole) {
            console.setVisible(true);
        }
    }
}
