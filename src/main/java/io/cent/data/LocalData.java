package io.cent.data;

import io.cent.DiscordBotCreator;
import io.cent.util.DUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.awt.image.DataBufferUShort;
import java.io.File;
import java.io.FileReader;

public class LocalData {
    private static final File file =
            new File(DUtil.getAppData() + File.separator + "DiscordBotCreator" + File.separator + "V" + DiscordBotCreator.VERSION + File.separator + "data.json");

    public static String get(String path) {
        String result = "null";

        if (!file.canRead())
            file.setReadable(true);

        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader(file)) {
            Object o = parser.parse(reader);
            JSONObject object = (JSONObject) o;
            result = object.get(path).toString();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }
}
