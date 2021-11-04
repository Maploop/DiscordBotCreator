package io.cent.listener;

import io.cent.util.DUtil;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class KeyListener extends KeyAdapter {
    private Map<Integer, Boolean> pressed = new HashMap<>();

    @Override
    public void keyPressed(KeyEvent e) {
        pressed.put(e.getKeyCode(), true);
        update();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressed.put(e.getKeyCode(), false);
        update();
    }

    public boolean isPressed(int keyCode) {
        return pressed.getOrDefault(keyCode, false);
    }

    public void update() {
        if (isPressed(KeyEvent.VK_CONTROL) && isPressed(KeyEvent.VK_ALT) && isPressed(KeyEvent.VK_H)) {
            try {
                FileReader fr = new FileReader(DUtil.appData + File.separator + "help.json");
                JSONObject json = (JSONObject) new JSONParser().parse(fr);
                JOptionPane.showMessageDialog(null, json.get("help").toString().split(";"), "help", JOptionPane.QUESTION_MESSAGE);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "", "help", JOptionPane.ERROR_MESSAGE);
            }

            pressed.put(KeyEvent.VK_CONTROL, false);
            pressed.put(KeyEvent.VK_ALT, false);
            pressed.put(KeyEvent.VK_H, false);
            return;
        }
    }
}
