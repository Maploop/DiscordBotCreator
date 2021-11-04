package io.cent.theme;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;

public interface Theme {
    static Theme parse(File path) {
        JSONObject json = null;
        Theme theme = null;

        try {
            json = (JSONObject) new JSONParser().parse(new FileReader(path));

            JSONObject finalJson = json;
            theme = new Theme() {
                @Override
                public Color getBackground() {
                    JSONObject background = (JSONObject) finalJson.get("background");
                    int r = (int) background.get("r");
                    int g = (int) background.get("g");
                    int b = (int) background.get("b");

                    return new Color(r, g, b);
                }

                @Override
                public Color getMenuBar() {
                    JSONObject menuBar = (JSONObject) finalJson.get("menuBar");
                    int r = (int) menuBar.get("r");
                    int g = (int) menuBar.get("g");
                    int b = (int) menuBar.get("b");

                    return new Color(r, g, b);
                }

                @Override
                public Font getFont() {
                    JSONObject font = (JSONObject) finalJson.get("font");
                    String name = (String) font.get("name");
                    String style = (String) font.get("style");
                    int size = (int) font.get("size");

                    return new Font(name, style.equals("BOLD") ? Font.BOLD : style.equals("ITALIC") ? Font.ITALIC : 0, size);
                }

                @Override
                public Color getText() {
                    JSONObject text = (JSONObject) finalJson.get("text");
                    int r = (int) text.get("r");
                    int g = (int) text.get("g");
                    int b = (int) text.get("b");

                    return new Color(r, g, b);
                }

                @Override
                public Color getButton() {
                    JSONObject button = (JSONObject) finalJson.get("button");
                    int r = (int) button.get("r");
                    int g = (int) button.get("g");
                    int b = (int) button.get("b");

                    return new Color(r, g, b);
                }

                @Override
                public String getName() {
                    return finalJson.get("name").toString();
                }

                @Override
                public String getAuthor() {
                    return finalJson.get("author").toString();
                }
            };
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error parsing theme file: " + path.getAbsolutePath());
        }

        return theme;
    }

    Color getBackground();
    Color getMenuBar();
    Font getFont();
    Color getText();
    Color getButton();
    String getName();
    String getAuthor();
}
