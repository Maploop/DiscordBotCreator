package io.cent.install;

import io.cent.DiscordBotCreator;
import io.cent.assets.AssetObject;
import io.cent.util.DUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Logger;

public class Installer {
    public static boolean checkIfInstalled() {
        Logger.getLogger(Installer.class.getName()).info("Checking if installed...");
        return new java.io.File(DUtil.getAppData() + File.separator + "DiscordBotCreator" + File.separator + "V" + DiscordBotCreator.VERSION).exists();
    }

    public static void install() {
        System.out.println("Installation not found! Installing new version...");

        File appData = new File(DUtil.getAppData() + File.separator + "DiscordBotCreator" + File.separator + "V" + DiscordBotCreator.VERSION);
        if (!appData.exists()) {
            appData.mkdirs();
        }

        try {
            File icon = new File(appData + File.separator + "icon.png");
            icon.createNewFile();

            JSONObject object = new JSONObject();
            object.put("installationTime", System.currentTimeMillis());
            object.put("version", DiscordBotCreator.VERSION);
            object.put("licenseKey", "null");

            FileWriter fw = new FileWriter(appData + File.separator + "data.json");
            fw.write(object.toJSONString());
            fw.flush();

            File file = new File("src/main/resources/icon.png");
            Files.copy(file.toPath(), icon.toPath());

            AssetObject.icon = ImageIO.read(icon);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to install!\n" + ex.getMessage(), "Installation Fail!", JOptionPane.ERROR_MESSAGE);
            System.exit(-999);
        }
    }

    public static void loadAssets() {
        try {
            File icon = new File(DUtil.getAppData() + File.separator + "DiscordBotCreator" + File.separator + "V" + DiscordBotCreator.VERSION + File.separator + "icon.png");
            AssetObject.icon = ImageIO.read(icon);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load assets!\n" + ex.getMessage(), "Installation Fail!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
