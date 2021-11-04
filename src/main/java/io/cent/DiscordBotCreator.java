package io.cent;

import io.cent.install.Installer;
import io.cent.project.Project;
import io.cent.theme.Theme;
import io.cent.util.DUtil;
import io.cent.util.UserFiles;
import io.cent.window.InstallerWindow;
import io.cent.window.MainWindow;

import javax.swing.*;

import static io.cent.logging.Statistics.StatisticLog;

public class DiscordBotCreator {
    public static Project openProject = null;
    public static final String VERSION = "1.0-BETA";
    public static Theme selectedTheme = null;

    public static MainWindow mainWindow;

    public DiscordBotCreator() {
        StatisticLog();

        UserFiles.USER_HOME = DUtil.getUserHome();

        if (!Installer.checkIfInstalled()) {
            new InstallerWindow();
            return;
        }

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
}
