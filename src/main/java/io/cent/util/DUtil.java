package io.cent.util;

import io.cent.DiscordBotCreator;
import io.cent.data.SettingsJSON;
import io.cent.theme.Theme;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DUtil {
    public static final File appData = new File(DUtil.getAppData() + File.separator + "DiscordBotCreator" + File.separator + "V" + DiscordBotCreator.VERSION);
    public static final File projects = new File(getUserHome() + File.separator + "Bot Creator Projects");
    public static final File themes = new File(DUtil.getAppData() + File.separator + "DiscordBotCreator" + File.separator + "V" + DiscordBotCreator.VERSION + File.separator + "Themes");

    // Get user's app data directory
    public static File getAppData() {
        ProcessBuilder builder = new ProcessBuilder(new String[]{"cmd", "/C echo %APPDATA%"});

        BufferedReader br = null;
        try {
            Process start = builder.start();
            br = new BufferedReader(new InputStreamReader(start.getInputStream()));
            String path = br.readLine();
            // TODO HACK do not know why but I get an extra '"' at the end
            if (path.endsWith("\"")) {
                path = path.substring(0, path.length() - 1);
            }
            return new File(path.trim());


        } catch (IOException ex) {
            Logger.getLogger(DUtil.class.getName()).log(Level.SEVERE, "Cannot get Application Data Folder", ex);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    Logger.getLogger(DUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return null;
    }

    // get user's home directory
    public static File getUserHome() {
        ProcessBuilder builder = new ProcessBuilder(new String[]{"cmd", "/C echo %USERPROFILE%"});

        BufferedReader br = null;
        try {
            Process start = builder.start();
            br = new BufferedReader(new InputStreamReader(start.getInputStream()));
            String path = br.readLine();
            // TODO HACK do not know why but I get an extra '"' at the end
            if (path.endsWith("\"")) {
                path = path.substring(0, path.length() - 1);
            }
            return new File(path.trim());
        } catch (Exception ex) {
            Logger.getLogger(DUtil.class.getName()).log(Level.SEVERE, "Cannot get User Home Folder", ex);
        }

        return new File("/");
    }

    public static String convertToDate(long input) {
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy | HH:mm:ss");
        Date date = new Date(input);

        return format.format(date);
    }

    public static void delay(Runnable runnable, long millis) {
        new Thread(() -> {
            try {
                Thread.sleep(millis);
                runnable.run();
            } catch (InterruptedException ex) {
                Logger.getLogger(DUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();
    }

    public static Theme getSelectedTheme() {
        try {
            for (File f : themes.listFiles()) {
                Theme t = Theme.parse(f);
                if (t != null) {
                    if (t.getName() == SettingsJSON.get("theme").toString()) {
                        return t;
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(DUtil.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Cannot load theme", "Error", JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    public static String getSourceUrl(String url) throws Exception {
        URL yahoo = new URL(url);
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream(), "UTF-8"));
        String inputLine;
        String total = "";
        while ((inputLine = in.readLine()) != null)
            total += inputLine;
        in.close();

        return total;
    }

    public static String fetchDownloadLink(String str) {
        System.out.println("Fetching download link");
        try {
            String regex = "(?=\\<)|(?<=\\>)";
            String data[] = str.split(regex);
            String found = "NOTFOUND";
            for (String dat : data) {
                if (dat.contains("DLP_mOnDownload(this)")) {
                    found = dat;
                    break;
                }
            }
            String wentthru = found.substring(found.indexOf("href=\"\"") + 6);
                    wentthru = wentthru.substring(0, wentthru.indexOf("\""));
            return wentthru;
        } catch (Exception e)
        {
            e.printStackTrace();
            return "ERROR";
        }
    }

    public static ImageIcon getIconImage(String path) {
        try {
            return new ImageIcon(ImageIO.read(new File(path)));
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
