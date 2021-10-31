package io.cent;

import io.cent.install.Installer;
import io.cent.util.DUtil;
import io.cent.util.UserFiles;
import io.cent.window.MainWindow;

import javax.swing.*;

import static io.cent.logging.Statistics.StatisticLog;

public class DiscordBotCreator {
    public static final String VERSION = "1.0-BETA";

    public DiscordBotCreator() {
        StatisticLog();

        UserFiles.USER_HOME = DUtil.getUserHome();

        if (!Installer.checkIfInstalled()) {
            Installer.install();
            JOptionPane.showMessageDialog(new JFrame(), "Cent has been installed. Please restart the program.", "Installation Notice", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

        Installer.loadAssets();

        new MainWindow();
    }

    public static void main(String[] args) {
        new DiscordBotCreator();
    }
}
