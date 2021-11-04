package io.cent.install;

import io.cent.DiscordBotCreator;
import io.cent.assets.AssetObject;
import io.cent.util.DUtil;
import org.json.simple.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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

        File assets = new File(DUtil.appData + File.separator + "assets");
        if (!assets.exists()) {
            assets.mkdirs();
        }

        try {
            Files.copy(DiscordBotCreator.class.getClassLoader().getResourceAsStream("assets/settings.png"), new File(assets + File.separator + "settings.png").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(DiscordBotCreator.class.getClassLoader().getResourceAsStream("assets/icon.png"), new File(assets + File.separator + "icon.png").toPath(), StandardCopyOption.REPLACE_EXISTING);
            Files.copy(DiscordBotCreator.class.getClassLoader().getResourceAsStream("assets/workspace.png"), new File(assets + File.separator + "workspace.png").toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to install!\n" + ex.getMessage(), "Installation Fail!", JOptionPane.ERROR_MESSAGE);
            System.exit(-999);
        }

        try {
            JSONObject object = new JSONObject();
            object.put("installationTime", System.currentTimeMillis());
            object.put("version", DiscordBotCreator.VERSION);
            object.put("licenseKey", "null");

            FileWriter fw = new FileWriter(appData + File.separator + "data.json");
            fw.write(object.toJSONString());
            fw.flush();

            JSONObject object1 = new JSONObject();
            object1.put("askBeforeExit", true);
            object1.put("theme", "MonoLight");

            FileWriter fw1 = new FileWriter(appData + File.separator + "settings.json");
            fw1.write(object1.toJSONString());
            fw1.flush();

            JSONObject object2 = new JSONObject();
            object2.put("help", "my life is a waste of time; all i am is a piece of shit; there is nothing i hate in my life more than me; i wish i could just die; easily; it'd be nice; i should have attempted suicide sooner; it's too late now");

            FileWriter fw2 = new FileWriter(appData + File.separator + "help.json");
            fw2.write(object2.toJSONString());
            fw2.flush();

            DUtil.themes.mkdirs();

            InputStream is = DiscordBotCreator.class.getClassLoader().getResourceAsStream("mono_light.json");
            File monoLight = new File(appData + File.separator + "Themes" + File.separator + "mono_light.json");
            monoLight.createNewFile();
            Files.copy(is, monoLight.toPath(), StandardCopyOption.REPLACE_EXISTING);

            InputStream is1 = DiscordBotCreator.class.getClassLoader().getResourceAsStream("mono_dark.json");
            File monoDark = new File(appData + File.separator + "Themes" + File.separator + "mono_dark.json");
            monoDark.createNewFile();
            Files.copy(is1, monoDark.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Installation complete!");
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to install!\n" + ex.getMessage(), "Installation Fail!", JOptionPane.ERROR_MESSAGE);
            System.exit(-999);
        }
    }

    public static void loadAssets() {
        try {
            AssetObject.icon = ImageIO.read(new File(DUtil.appData + File.separator + "assets" + File.separator + "icon.png"));
            AssetObject.settings = ImageIO.read(new File(DUtil.appData + File.separator + "assets" + File.separator + "settings.png"));
            AssetObject.workspace = ImageIO.read(new File(DUtil.appData + File.separator + "assets" + File.separator + "workspace.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load assets!\n" + ex.getMessage(), "Installation Fail!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
