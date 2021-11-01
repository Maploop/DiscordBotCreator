package io.cent;

import io.cent.install.Installer;
import io.cent.project.Project;
import io.cent.util.DUtil;
import io.cent.util.UserFiles;
import io.cent.window.MainWindow;

import javax.swing.*;

import static io.cent.logging.Statistics.StatisticLog;

public class DiscordBotCreator {
    public static Project openProject = null;
    public static final String VERSION = "1.0-BETA";

    public static MainWindow mainWindow;

    public DiscordBotCreator() {
        StatisticLog();

        UserFiles.USER_HOME = DUtil.getUserHome();

        if (!Installer.checkIfInstalled()) {
            Installer.install();
            JOptionPane.showMessageDialog(new JFrame(), "Cent has been installed. Please restart the program.", "Installation Notice", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        Installer.loadAssets();

        mainWindow = new MainWindow();
    }

    public static void main(String[] args) {
        new DiscordBotCreator();
    }

    public static void updateProject() {
        mainWindow.setProject(openProject);
    }
}
