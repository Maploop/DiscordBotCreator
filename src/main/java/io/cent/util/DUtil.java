package io.cent.util;

import io.cent.DiscordBotCreator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DUtil {
    public static final File appData = new File(DUtil.getAppData() + File.separator + "DiscordBotCreator" + File.separator + "V" + DiscordBotCreator.VERSION);
    public static final File projects = new File(getUserHome() + File.separator + "Bot Creator Projects");

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
}
